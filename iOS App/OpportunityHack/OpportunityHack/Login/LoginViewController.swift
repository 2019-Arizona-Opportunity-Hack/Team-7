//
//  LoginViewController.swift
//  OpportunityHack
//
//  Created by Ishan Sarangi on 10/19/19.
//  Copyright © 2019 Ishan Sarangi. All rights reserved.
//

import UIKit
import Alamofire
import ARSLineProgress

class LoginViewController: UIViewController {

    @IBOutlet weak var emailTF: MyTextField!
    @IBOutlet weak var passTF: MyTextField!
    @IBOutlet weak var registerButton: UIButton!
    override func viewDidLoad() {
        super.viewDidLoad()

        isResisterEnabled()

        // Do any additional setup after loading the view.
    }
    
    @discardableResult
    func isResisterEnabled() -> Bool{
        if  emailTF.text != nil && emailTF.text?.isEmpty != true && passTF.text != nil && passTF.text?.isEmpty != true {
            registerButton.isEnabled = true
            registerButton.isUserInteractionEnabled = true
            registerButton.alpha = 1.0
            return true
        } else {
            registerButton.isEnabled = false
            registerButton.isUserInteractionEnabled = false
            registerButton.alpha = 0.5
            return false

        }
    }

    @IBAction func loginTapped(_ sender: Any) {
        if isResisterEnabled(){
            ARSLineProgress.show()
            let parameters: [String: String] = [
                "email": emailTF.text!,
                "password": passTF.text!,
            ]

            let urlString = Utils.login
            AF.request(urlString, method: .post, parameters: parameters)
                .responseJSON { response in
                    switch response.result {
                    case .success:
                        print(response)
                        ARSLineProgress.showSuccess()
                        ViewManager.updateLoggedInRootVC()

                        break
                    case .failure(let error):
                        CustomAlert.showAlert(title: "Error!!!", message: "Please try again", cancelButtonTitle: "Ok")
                        print(error)
                        ARSLineProgress.showFail()
                    }
            }
            
        } else {
            //show error
            ARSLineProgress.showFail()
            CustomAlert.showAlert(title: "Missing Data!!!", message: "Please fill all the mandatory fields", cancelButtonTitle: "Ok")

        }
        
    }
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}

extension LoginViewController: UITextFieldDelegate {
    func textFieldDidBeginEditing(_ textField: UITextField) {
        self.isResisterEnabled()
    }
    
    func textFieldShouldBeginEditing(_ textField: UITextField) -> Bool {
        self.isResisterEnabled()
        return true
    }
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        self.isResisterEnabled()
        return true

    }

}
