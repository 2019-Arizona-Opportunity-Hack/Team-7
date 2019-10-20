//
//  RegisterViewController.swift
//  OpportunityHack
//
//  Created by Ishan Sarangi on 10/19/19.
//  Copyright © 2019 Ishan Sarangi. All rights reserved.
//

import UIKit
import Alamofire
import ARSLineProgress

class RegisterViewController: UIViewController {

    @IBOutlet weak var firstNameTF: MyTextField!
    @IBOutlet weak var lastNameTF: MyTextField!
    @IBOutlet weak var emailTF: MyTextField!
    
    @IBOutlet weak var passwordTF: MyTextField!
    @IBOutlet weak var phoneTF: MyTextField!
    @IBOutlet weak var AddressTF: MyTextField!
    @IBOutlet weak var cityTF: MyTextField!
    @IBOutlet weak var stateTF: MyTextField!
    
    @IBOutlet weak var countryTF: MyTextField!
    @IBOutlet weak var zipTF: MyTextField!
    @IBOutlet weak var preferredOptionSegment: UISegmentedControl!
    @IBOutlet weak var registerButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        isResisterEnabled()
        passwordTF.isSecureTextEntry = true
        // Do any additional setup after loading the view.
    }
    
    @IBAction func registerTapped(_ sender: Any) {
        if isResisterEnabled(){
            ARSLineProgress.show()
            //            let user = User(firstName: firstNameTF.text, lastName: lastNameTF.text, email: emailTF.text, password: passwordTF.text, phone: phoneTF.text, address: AddressTF.text, city: cityTF.text, state: stateTF.text, zip: zipTF.text, country: countryTF.text, preferredOption: preferredOptionSegment.selectedSegmentIndex == 0 ? "email" : "phone")
            
            let parameters: [String: String] = [
                "firstName": firstNameTF.text!,
                "lastName": lastNameTF.text!,
                "email": emailTF.text!,
                "password": passwordTF.text!,
                "phoneNumber": phoneTF.text!,
                "address": AddressTF.text!,
                "city": cityTF.text ?? "",
                "state": stateTF.text ?? "",
                "zip": zipTF.text!,
                "country": countryTF.text!,
                "preferredContact": preferredOptionSegment.selectedSegmentIndex == 0 ? "email" : "phone",
                "userType" : "Donor"
            ]
            // header to be sent in the post request if required
            let urlString = Utils.register
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
                
            }
            }
//            AF.request(urlString, method: .post, parameters: parameters)
//                .responseJSON { response in
//                    switch response.result {
//                    case .success:
//                        print(response)
//                        ARSLineProgress.showSuccess()
//                        ViewManager.updateLoggedInRootVC()
//
//                        break
//                    case .failure(let error):
//                        ARSLineProgress.showFail()
//                        CustomAlert.showAlert(title: "Error!!!", message: "Please try again", cancelButtonTitle: "Ok")
//                        print(error)
//
//                    }
//            }
                        
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

    @discardableResult
    func isResisterEnabled() -> Bool{
        if firstNameTF.text != nil && !firstNameTF.text!.isEmpty && lastNameTF.text != nil && !lastNameTF.text!.isEmpty && emailTF.text != nil && !emailTF.text!.isEmpty && passwordTF.text != nil && !passwordTF.text!.isEmpty && phoneTF.text != nil && !phoneTF.text!.isEmpty && AddressTF.text != nil && !AddressTF.text!.isEmpty && zipTF.text != nil && !zipTF.text!.isEmpty {
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
}

extension RegisterViewController: UITextFieldDelegate {
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
