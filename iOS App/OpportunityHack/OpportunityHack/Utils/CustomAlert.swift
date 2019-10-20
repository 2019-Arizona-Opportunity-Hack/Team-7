//
//  CustomAlert.swift
//  OpportunityHack
//
//  Created by Ishan Sarangi on 10/20/19.
//  Copyright © 2019 Ishan Sarangi. All rights reserved.
//

import Foundation
import UIKit

class CustomAlert {
    class func showAlert(title:String?, message:String, cancelButtonTitle:String?, cancelHandler:(()->Void)? = nil) {
        guard let rootVC = UIApplication.shared.windows.first?.rootViewController else {
            return
        }
        let alertController = UIAlertController(title: title, message: message, preferredStyle: .alert)
        let cancelAction = UIAlertAction(title: cancelButtonTitle ?? "OK", style: .cancel) { UIAlertAction in
            cancelHandler?()
        }
        alertController.addAction(cancelAction)
        rootVC.present(alertController, animated: true, completion:nil)
    }
}
