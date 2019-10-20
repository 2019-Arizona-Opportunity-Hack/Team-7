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
                "email": emailTF.text as! String,
                "password": passTF.text as! String,
            ]

            let urlString = Utils.login
            let header: HTTPHeaders = [HTTPHeader(name: "Content-Type", value: "application/json")]

            let request = ParameterEncoding.json.encode(urlString, bodyParameters:parameters)
            _ = WebServiceManager.sharedInstance.fetchRequest(request).responseJSON {[weak self] (_, response, error) in
                guard let weakself = self else {return}
                
                DispatchQueue.main.async {
                    
                guard let responseDict = response as? [String: Any] else {
                    ARSLineProgress.showFail()
                    CustomAlert.showAlert(title: "Error!!!", message: "Please try again", cancelButtonTitle: "Ok")
                    return
                }
                
                    if let result = responseDict["statusCode"] as? Double, result != 200 {
                        ARSLineProgress.showFail()
                        CustomAlert.showAlert(title: "Error!!!", message: "Please try again", cancelButtonTitle: "Ok")
                    } else {
                        if let id  = responseDict["id"] as? String, let user = responseDict["user"] as? [String:Any], let name = user["firstName"] as? String{
                            ARSLineProgress.showSuccess()
                            ViewManager.updateLoggedInRootVC(id, name: name)
                            
                        } else {
                            ARSLineProgress.showFail()
                            CustomAlert.showAlert(title: "Error!!!", message: "Please try again", cancelButtonTitle: "Ok")
                        }
                    }
//                else {
//                    ARSLineProgress.showFail()
//                    CustomAlert.showAlert(title: "Error!!!", message: "Please try again", cancelButtonTitle: "Ok")
//                    print(error)
//
//                }
                }

            }
            /*
            AF.request(urlString, method: .post, parameters: parameters, encoding: URLEncoding.default, headers: header).responseJSON { response in

                if let data = response.data {
                    print(response)
                    print("REQUEST \n\n + \(response.request?.httpBody)")
                    print("REQUEST \n\n + \(response.response)")

                    let json = String(data: data, encoding: String.Encoding.utf8)
                    print("Response: \(json)")
                }
            }

            AF.request(urlString, method: .post, parameters: parameters, encoding: URLEncoding.default, headers: header)
                .responseString { response in
                    switch response.result {
                    case .success:
                        print(response)
                        print("REQUEST \n\n + \(response.request?.httpBody)")
                        print("REQUEST \n\n + \(response.response)")
                        ARSLineProgress.showSuccess()
                        ViewManager.updateLoggedInRootVC()

                        break
                    case .failure(let error):
                        ARSLineProgress.showFail()
                        CustomAlert.showAlert(title: "Error!!!", message: "Please try again", cancelButtonTitle: "Ok")
                        print(error)
                    }
            }
            */
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
