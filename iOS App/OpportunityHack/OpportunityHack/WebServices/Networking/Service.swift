//
//  Service.swift

/**
Responsible for sending a request and receiving the response from the server, as well as managing its underlying `NSURLSessionTask`.
*/

import Foundation
import UIKit

@objc class Service : NSObject { //, CustomStringConvertible {
    let delegate: TaskDelegate
    var task: URLSessionTask { return delegate.task }
    let session: URLSession
    var request: Foundation.URLRequest { return task.originalRequest! }
    var response: HTTPURLResponse? { return task.response as? HTTPURLResponse }
    var progress: Progress? { return delegate.progress }

    init(session: URLSession, task: URLSessionDataTask) {
        self.session = session
        self.delegate = TaskDelegate(task: task)
    }
    
    // MARK: Progress
    /**
    Sets a closure to be called periodically during the lifecycle of the request as data is downloaded from the server.
    
    - parameter closure: The code to be executed periodically during the lifecycle of the request.
    
    - returns: Service.
    */
    func progress(_ closure: ((Int64, Int64, Int64) -> Void)? = nil) -> Self {
        delegate.dataProgress = closure
        return self
    }
    
    // MARK: Response
    
    /**
    A closure used by response handlers that takes a request and data and returns a serialized object and any error that occured in the process.
    */
    typealias Serializer = (Foundation.URLRequest, Data?) -> (Any?, Error?)

    /**
    A closure used by response handlers that takes a request and serialized object and any error that occured in the process.
    */
    typealias TaskCompletionHandler = (HTTPURLResponse?, Any?, Error?) -> Void

    /**
    Creates a response serializer that returns the associated data as-is.
    */
    class func responseDataSerializer() -> Serializer {
        return { (request, data) in
            return (data, nil)
        }
    }
    
    /**
    Adds a handler to be called once the task has finished.
    
    - parameter queue: The queue on which the completion handler is dispatched.
    - parameter serializer: The closure responsible for serializing the request, response, and data.
    - parameter completionHandler: The code to be executed once the request has finished.
    
    - returns: Service.
    */
    @discardableResult func responseFor(queue: DispatchQueue? = nil, serializer: @escaping Serializer, completionHandler: @escaping TaskCompletionHandler) -> Self {
        
        delegate.queue.async {
        
            var data  = self.delegate.downloadedData as Data
            if let string = self.response?.allHeaderFields["Content-Type"] as? String{
                if(string.contains("charset=ISO-8859-1")){
                    let formedString =  String.init(data: data, encoding: String.Encoding.isoLatin1)
                    if let formedData = formedString?.data(using: .utf8){
                        data = formedData
                    }
                }
            }
            let (responseObject, serializationError): (Any?, Error?) = serializer(self.request, data)
            
            (queue ?? DispatchQueue.main).async {
                completionHandler(self.response, responseObject, self.delegate.error ?? serializationError)
            }
        }
        
        return self
    }
    
    /**
    Adds a handler to be called once the task has finished.
    
    - parameter completionHandler: The code to be executed once the task has finished.
    
    - returns: Service.
    */
    @discardableResult func response(_ completionHandler: @escaping TaskCompletionHandler) -> Self {
        return responseFor(queue:nil, serializer: Service.responseDataSerializer(), completionHandler: completionHandler)
    }
    
    func suspend() {
        task.suspend()
    }
    
    func resume() {
        task.resume()
    }
    
    func cancel() {
        task.cancel()
    }
    
    // MARK: description and debug preview
    
    func detailInfo() -> String {
        
        let requestBody:String?
        
        if JSONSerialization.isValidJSONObject(request.httpBody!) {
            let data = try! JSONSerialization.jsonObject(with: request.httpBody!, options: JSONSerialization.ReadingOptions.allowFragments) as! NSDictionary
            requestBody = data.description as String?
        } else {
            requestBody = String(data: request.httpBody!, encoding: String.Encoding(rawValue: String.Encoding.utf8.rawValue))
        }
        
        let requestDesc = String(format: "URL: %@ \n Method: %@ \n Header: %@ \n Body: %@", request.url!.absoluteString, request.httpMethod!, request.allHTTPHeaderFields ?? "nil", requestBody ?? "nil")

        return "Request: {\(requestDesc)} \n Response: Code {\(response!.statusCode)}"
    }
    
    override var description: String {
        return detailInfo() as String
    }

    override var debugDescription: String {
        return detailInfo() as String
    }
}

// MARK: - Response Serializers
// MARK: String

extension Service {
    /**
    Creates a response serializer that returns a string initialized from the response data with the specified string encoding.
    
    - parameter encoding: The string encoding. `NSUTF8StringEncoding` by default.
    
    - returns: A string response serializer.
    */
    class func stringResponseSerializer(_ encoding: String.Encoding = String.Encoding.utf8) -> Serializer {
        return { (_, data) in
            let string = NSString(data: data!, encoding: encoding.rawValue)
            return (string, nil)
        }
    }
    
    /**
    Adds a handler to be called once the task has finished.
    
    - parameter completionHandler: A closure to be executed once the task has finished.
    
    - returns: Service.
    */
    @discardableResult func responseString(_ completionHandler: @escaping TaskCompletionHandler) -> Self {
        return responseFor(queue:nil, serializer: Service.stringResponseSerializer(), completionHandler: completionHandler)
    }
}

// MARK: JSON and Genric
extension Service {
    
    class func JSONResponseSerializer(_ options: JSONSerialization.ReadingOptions = .mutableContainers) -> Serializer {
        return { (request, data) in
            if data == nil {
                return (nil, nil)
            }
            
            var error: NSError?
            var json: Any? = nil
            do {
                json = try JSONSerialization.jsonObject(with: data!, options: options)
            } catch let error1 as NSError {
                error = error1
                json = nil
            } catch {
            }
            return (json, error)
        }
    }

    @discardableResult func responseJSON(_ completionHandler: @escaping TaskCompletionHandler , options: JSONSerialization.ReadingOptions) -> Self {
        return responseFor(queue:nil, serializer: Service.JSONResponseSerializer(options), completionHandler: completionHandler)
    }
    
    @discardableResult func responseJSON(_ completionHandler: @escaping TaskCompletionHandler) -> Self {
        return responseFor(queue:nil, serializer: Service.JSONResponseSerializer(.allowFragments), completionHandler: completionHandler)
    }
    
    @discardableResult func responseObject<T:JSONDecodable>(_ completionHandler: @escaping (Foundation.URLRequest, T?, Error?) -> Void) -> Self {
        
        delegate.queue.async {
            
            let JSONSerializer = Service.JSONResponseSerializer()
            let (response, error): (JSON?, Error?) = JSONSerializer(self.request , self.delegate.downloadedData as Data)
            
            var object:T?
            if let serializedRes:JSON = response {
                object = T.decode(serializedRes)
            }

            DispatchQueue.main.async {
                completionHandler(self.request, object, self.delegate.error ?? error)
            }
        }

        return self
    }
    
    @discardableResult func responseCollectionObject<T:JSONDecodable>(_ completionHandler: @escaping (Foundation.URLRequest, [T]?, Error?) -> Void) -> Self {
        
        delegate.queue.async {
            
            let JSONSerializer = Service.JSONResponseSerializer()
            let (response, error): (JSON?, Error?) = JSONSerializer(self.request , self.delegate.downloadedData as Data)
            
            var collectionObject:[T]?;
            
            if let serializedRes = response as? JSONArray {
                collectionObject = serializedRes.flatMap{ (serializedDict) -> T? in return T.decode(serializedDict)}
            }
            
            DispatchQueue.main.async {
                completionHandler(self.request, collectionObject, self.delegate.error ?? error)
            }
        }
        
        return self
    }
}

// MARK: Image
extension Service {
    
    class func imageResponseSerializer() -> Serializer {
        
        return { (request, data) in
            
            if data == nil {
                return (nil, nil)
            }
            
            let image = UIImage(data: data!, scale: UIScreen.main.scale)
            
            return (image, nil)
        }
    }
    
    @discardableResult func responseImage(_ completionHandler: @escaping TaskCompletionHandler) -> Self {
        return responseFor(queue:nil, serializer: Service.imageResponseSerializer(), completionHandler: completionHandler)
    }
}

extension Service {
    
    internal class func LoginResponseSerializer() -> Serializer {
        return { (request, data) in
            
            var responseObject = Dictionary<String,Any>()
            var error:Error?
            
            if data != nil {
                let JSONSerializer = Service.JSONResponseSerializer()
                
                let (JSON, serializationError): (Any?, Error?) = JSONSerializer(request, data)
                error = serializationError
                
                if let responseDict = JSON as? [String : Any] {
                    responseObject = responseDict
                }
            }
            return (responseObject , error)
        }
    }
    
    func responseLoginResult(_ completionHandler: @escaping TaskCompletionHandler) -> Self {
        return responseFor(serializer: Service.LoginResponseSerializer(), completionHandler: { (request, JSON, error) in
            completionHandler(request, JSON, error)
        })
    }
}

