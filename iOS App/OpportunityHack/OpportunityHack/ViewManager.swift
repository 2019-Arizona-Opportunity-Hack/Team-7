//
//  ViewManager.swift
//  OpportunityHack
//
//  Created by Ishan Sarangi on 10/19/19.
//  Copyright © 2019 Ishan Sarangi. All rights reserved.
//

import Foundation
import UIKit

class ViewManager {
    
    static func updateLoggedInRootVC(_ id: String, name: String){
        guard let rootVC = UIStoryboard.init(name: "Main", bundle: nil).instantiateViewController(withIdentifier: "DonorViewController") as? DonorViewController else {
            return
        }
        rootVC.id = id
        UserDefaults.standard.set(id, forKey: Utils.donorIDKey)
        rootVC.userName = name
        let navigationController = UINavigationController(rootViewController: rootVC)
        UIApplication.shared.windows.first?.rootViewController = navigationController
        UIApplication.shared.windows.first?.makeKeyAndVisible()

    }
    
    static func updateLoggedOutRootVC(){
           guard let rootVC = UIStoryboard.init(name: "Main", bundle: nil).instantiateViewController(withIdentifier: "HomeController") as? HomeController else {
               return
           }
           UIApplication.shared.windows.first?.rootViewController = rootVC
           UIApplication.shared.windows.first?.makeKeyAndVisible()
    }
}
