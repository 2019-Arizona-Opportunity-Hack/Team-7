//
//  JSONUtilities.swift

import Foundation

typealias JSON = Any
typealias JSONDictionary = Dictionary<String, JSON>
typealias JSONArray = Array<JSON>

//MARK: - JSONDecodable -
public protocol JSONDecodable {
    static func decode(_ json: Any) -> Self?
}

//MARK: - JSON Data to model -
func objectFromJSONData<T:JSONDecodable>(_ atPath:String) -> T? {
    
    if let data = try? Data(contentsOf: URL(fileURLWithPath: atPath)) {
        do {
            let json:Any = try JSONSerialization.jsonObject(with: data, options: .allowFragments)
            return T.decode(json);
        } catch _ {
        }
    }
    
    return nil
}

func objectCollectionFromJSONData<T:JSONDecodable>(_ atPath:String) -> [T]? {
    
    if let data = try? Data(contentsOf: URL(fileURLWithPath: atPath)) {
        do {
            let json = try JSONSerialization.jsonObject(with: data, options: .allowFragments)
            if let parsedJson = json as? [Any]{
                return parsedJson.map{ (serializedDict) -> T in return T.decode(serializedDict)!;}
            }
        } catch _ {
            
        }
    }
    
    return nil
}
