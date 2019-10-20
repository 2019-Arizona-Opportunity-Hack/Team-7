//
//  WebServiceManager.swift

import Foundation
import UIKit

/**
    Responsible for creating and managing `Service` objects, as well as their underlying `NSURLSession`.
*/

let MultipartFormDataEncodingMemoryThreshold: UInt64 = 10 * 1024 * 1024

@objc class WebServiceManager:NSObject {
    
    static let sharedInstance:WebServiceManager = {
        struct Singleton1 {
            
            static var onceToken : Int = 0
            static var instance : WebServiceManager? = nil
            
            static var configuration: URLSessionConfiguration = {
                
                var configuration = URLSessionConfiguration.default
                configuration.urlCache = URLCache(memoryCapacity: 0, diskCapacity: 0, diskPath: nil)
                
                var header = [String:String]()
                
                if let version = Bundle.main.infoDictionary?["CFBundleShortVersionString"] as? String {
                    header ["ver"] = version
                }
                
                header ["Accept"]  = "application/json"
                header ["Content-Type"]  = "application/json"
                configuration.httpAdditionalHeaders = header
                
                return configuration
            }()
        }
        return WebServiceManager(configuration: Singleton1.configuration)
    }()
    
    static let sharedBackgroundInstance:WebServiceManager = {
        struct Singleton2 {
            
            static var onceToken : Int = 0
            static var instance : WebServiceManager? = nil
            
            static var configuration: URLSessionConfiguration = {
                
                var configuration = URLSessionConfiguration.default
                configuration.urlCache = URLCache(memoryCapacity: 0, diskCapacity: 0, diskPath: nil)

              
                var header = [String:String]()
                
                header ["Accept"]  = "application/json"
                header ["Content-Type"]  = "application/json"
                configuration.httpAdditionalHeaders = header
                
                return configuration
            }()
        }

        return WebServiceManager(configuration: Singleton2.configuration)
    }()

    let delegate: SessionDelegate
    let session: URLSession
    lazy var queue:DispatchQueue = DispatchQueue(label: "WebServiceManagerQueue", attributes: .concurrent)
    var backgroundCompletionHandler: (() -> Void)?

    private  init(configuration: URLSessionConfiguration) {
        self.delegate = SessionDelegate()
        self.session = URLSession(configuration: configuration, delegate: delegate, delegateQueue: nil)
        super.init()
        
        delegate.sessionDidFinishEventsForBackgroundURLSession = { [weak self] session in
            guard let strongSelf = self else { return }
            DispatchQueue.main.async { strongSelf.backgroundCompletionHandler?() }
        }
    }

    deinit {
        self.session.invalidateAndCancel()
    }

    @discardableResult func fetch(_ URLString:String, method: Method = .POST, headerParameters: [String: String]? = nil, bodyParameters: [String: Any]? = nil, encoding: ParameterEncoding = .json) -> Service {
        return fetchRequest(encoding.encode(URLString, method: method, headerParameters: headerParameters, bodyParameters: bodyParameters as Any?) as URLRequest)
    }

    @discardableResult func fetchRequest(_ URLRequest: Foundation.URLRequest) -> Service {
        let dataTask: URLSessionDataTask = self.session.dataTask(with: URLRequest)
        let service = Service(session: session, task: dataTask)
        delegate[service.delegate.task] = service.delegate
        service.resume()
        return service
    }
}
