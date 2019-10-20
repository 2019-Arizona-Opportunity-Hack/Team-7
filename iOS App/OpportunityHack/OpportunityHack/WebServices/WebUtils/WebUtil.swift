//
//  WebServiceUtilities.swift

import Foundation

enum Method: String {
    case GET = "GET"
    case POST = "POST"
    case DELETE = "DELETE"
}


enum ParameterEncoding {
    /**
    Uses `NSJSONSerialization` to create a JSON representation of the parameters object, which is set as the body of the request. The `Content-Type` HTTP header field of an encoded request is set to `application/json`.
    */
    case json
    /**
    Consider only first object (must be string type) of parameters dictionary and encode using NSUTF8StringEncoding, which is set as the body of the request. The `Content-Type` HTTP header field of an encoded request is set to `text/plain`.
    */
    case plainText
    /**
    Uses the associated closure value to construct a new request given an existing request and parameters.
    */
    case custom((NSMutableURLRequest, [String: String]?, Any?) -> NSMutableURLRequest)
    
    func encode(_ URLString: String, method: Method = .POST, headerParameters: [String: String]? = nil, bodyParameters: Any? = nil, useBaseAuth:Bool = false) -> URLRequest {
        
        let request = NSMutableURLRequest(url: URL(string: URLString)!)
        request.timeoutInterval = 120
        
        request.httpMethod = method.rawValue
        
        if let headerDict = headerParameters {
            for (key , value) in headerDict {
                request.setValue(value, forHTTPHeaderField: key)
            }
        }
                
        switch self {
        case .json:
            
            if let bodyDict:Any = bodyParameters {
                
                let options = JSONSerialization.WritingOptions()
                do {
                    let data = try JSONSerialization.data(withJSONObject: bodyDict, options: options)
                    request.setValue("application/json", forHTTPHeaderField: "Content-Type")
                    request.httpBody = data
                } catch _ {
                }
            }
            
        case .plainText:
            
            if let bodyText = bodyParameters {
                request.setValue("text/plain", forHTTPHeaderField: "Content-Type")
   
                if let text = bodyText as? NSString {
                    request.httpBody = text.data(using: String.Encoding.utf8.rawValue, allowLossyConversion: false)
                } else if bodyText is Data {
                    request.httpBody = bodyText as? Data
                }
            }
            
        case .custom(let closure):
            return closure(request, headerParameters, bodyParameters) as URLRequest
        }
        
        return request as URLRequest
    }
}

func URLRequest(_ method: Method, _ URLString: String, headers: [String: String]? = nil) -> NSMutableURLRequest {
    let mutableURLRequest = NSMutableURLRequest(url: URL(string: URLString)!)
    mutableURLRequest.httpMethod = method.rawValue
    if let headers = headers {
        for (headerField, headerValue) in headers {
            mutableURLRequest.setValue(headerValue, forHTTPHeaderField: headerField)
        }
    }    
    return mutableURLRequest
}

public protocol DictionaryEncodable {
    func toDictionary() -> [String:Any]
}

extension Foundation.URLRequest : DictionaryEncodable {
    
    public func toDictionary() -> [String:Any] {

        
        var dictionary = [String:Any]()
        
        dictionary["URL"] = self.url ?? "nil"
        dictionary["Method"] = self.httpMethod  ?? "nil"
        dictionary["Headers"] = self.allHTTPHeaderFields ?? "nil"

        if let body = self.httpBody {
            if JSONSerialization.isValidJSONObject(body) {
                do {
                    dictionary["BODY"] = try JSONSerialization.jsonObject(with: body, options: .allowFragments)
                } catch _ {
                }
            } else {
                dictionary["BODY"] = NSString(data: body, encoding: String.Encoding.utf8.rawValue) ?? "nil"
            }
        }
        
        return dictionary
    }
}

public func ObjectOrNil(_ key:String, dictionary:[String : Any]?) -> Any? {
    
    var value:Any?
    
    if let dict:[String : Any] = dictionary {
        let objectOrNil:Any? = dict[key]
        
        if !(objectOrNil == nil || (objectOrNil is NSNull)) {
            value = objectOrNil
        }
    }
    
    return value
}

public func clearCookieBy(name:String) {
    if let cookies = HTTPCookieStorage.shared.cookies {
        for cookie in cookies {
            if cookie.name == name {
                HTTPCookieStorage.shared.deleteCookie(cookie)
            }
        }
    }
}

