//
//  NetworkManager.swift

import Foundation

typealias JSONObjDictionary = [String: AnyObject]

enum APIError: Error, CustomStringConvertible {
  //TODO add other generic cases for errors here
  case invalidResponse
  var description: String {
    switch self {
    case .invalidResponse: return "Received an invalid response"
    }
  }
}


enum HttpMethod{
    case get
    case post([String:Any])
    case put([String:Any])
    case delete
}

extension HttpMethod{
    
    var type:String{
        switch self {
        case .get: return "GET"
        case .post: return "POST"
        case .put: return "PUT"
        case .delete: return "DELETE"
        }
    }

}

struct Resource<A> {
    let url: URL
    let method: HttpMethod
    let parse: (Data) -> A?
    let additionHeaders: [String: Any]?
}

extension Resource {
    init(url: URL,  parseJSON: @escaping (Any) -> A?,method: HttpMethod = .get, additionHeaders:[String: Any]  = [:], useBaseAuth:Bool = false) {
        self.url = url
        self.method = method
        var header = additionHeaders
        self.additionHeaders = header
        self.parse = { data in
            let json = try? JSONSerialization.jsonObject(with: data as Data , options: JSONSerialization.ReadingOptions())
            return json.flatMap(parseJSON)
        }
    }
}

extension NSMutableURLRequest {
    convenience init<A>(resource: Resource<A>) {
        self.init(url: resource.url)
        httpMethod = resource.method.type
        if case let .post(json) = resource.method  {
            let bodyData = try! JSONSerialization.data(withJSONObject: json, options: JSONSerialization.WritingOptions())
            httpBody = bodyData
        }
        if case let .put(json) = resource.method  {
            let bodyData = try! JSONSerialization.data(withJSONObject: json, options: JSONSerialization.WritingOptions())
            httpBody = bodyData
        }
        self.allHTTPHeaderFields = resource.additionHeaders as! [String : String]?
    }
}


let configuration: URLSessionConfiguration = {
    
    var configuration = URLSessionConfiguration.default
    
    var header = [String:String]()
    
    header ["Accept"]  = "application/json"
    header ["Content-Type"]  = "application/json"
    configuration.httpAdditionalHeaders = header
    
    return configuration
}()

let defaultSession = URLSession(configuration: configuration)

class WebServiceManagerNew{
    func load<A>(resource: Resource<A>, completion: @escaping (A?, Error?) -> ()) {
        let request = NSMutableURLRequest(resource: resource)        
        defaultSession.dataTask(with: request as URLRequest) { data, response, error in

            guard let data = data else {
                completion(nil, error)
                return
            }
            
            completion(resource.parse(data), error)
            }.resume()
    }
    
    func load<A>(resource: Resource<A>, completion: @escaping (A?, URLResponse?, Error?) -> ()) {
        let request = NSMutableURLRequest(resource: resource)
        defaultSession.configuration.httpCookieAcceptPolicy = .always
        defaultSession.dataTask(with: request as URLRequest) { data, response, error in
            
            guard let data = data else {
                completion(nil, response, error)
                return
            }
            
            completion(resource.parse(data), response, error)
            }.resume()
    }
}

