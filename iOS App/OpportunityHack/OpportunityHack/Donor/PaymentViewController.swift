//
//  PaymentViewController.swift
//  OpportunityHack
//
//  Created by Ishan Sarangi on 10/20/19.
//  Copyright © 2019 Ishan Sarangi. All rights reserved.
//

import UIKit
import BraintreeDropIn
import Braintree
import Alamofire
import ARSLineProgress

class PaymentViewController: UIViewController {

    @IBOutlet weak var paymentMethodView: UIView!
    @IBOutlet weak var pmDesc: UILabel!
    @IBOutlet weak var pmIcon: UIView!
    @IBOutlet weak var pmIconImgView: UIImageView!
    
    var methodDesc: String!
    var id : String?

    @IBOutlet weak var pmHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var pmIconWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var addPayment: UIButton!
    @IBOutlet weak var confirmPurchase: UIButton!
    @IBOutlet weak var donationAmountTF: UITextField!
    
    @IBOutlet weak var pmLabel: UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()

        if methodDesc != nil && methodDesc.isEmpty {
            updateView(true)
            
        } else {
            updateView(false)
        }
    }
    
    func updateView(_ show: Bool){
        if show {
            pmHeightConstraint.constant = 50
            addPayment.setTitle("Change Payment Method", for: .normal)
            pmIconWidthConstraint.constant = 50
            confirmPurchase.isEnabled = true
            confirmPurchase.isUserInteractionEnabled = true
            confirmPurchase.alpha = 1
            pmDesc.text = methodDesc
            pmLabel.text = "Payment Methods"
        } else {
            addPayment.setTitle("Add Payment Method", for: .normal)
            confirmPurchase.isEnabled = false
            confirmPurchase.isUserInteractionEnabled = false
            confirmPurchase.alpha = 0.5
            pmIconWidthConstraint.constant = 0
            pmHeightConstraint.constant = 0
            pmLabel.text = "No payment methods added"

        }
        
    }
    @IBAction func donateTapped(_ sender: Any) {
        let tokenizationKey = "sandbox_4xthynmf_y9vk98z94ssm45zt"
        showDropIn(clientTokenOrTokenizationKey: tokenizationKey)
    }
    
    func showDropIn(clientTokenOrTokenizationKey: String) {
        let request =  BTDropInRequest()
        request.vaultCard = true
        request.cardholderNameSetting = .optional
        request.amount = donationAmountTF.text
        let dropIn = BTDropInController(authorization: clientTokenOrTokenizationKey, request: request)
        { [weak self] (controller, result, error) in
            if (error != nil) {
                print("ERROR")
                CustomAlert.showAlert(title: "Error", message: "Something went wrong!!!", cancelButtonTitle: "Ok")
            } else if (result?.isCancelled == true) {
                print("CANCELLED")
            } else if let result = result {
                // Use the BTDropInResult properties to update your UI
                let view = result.paymentIcon as? BTUIKVectorArtView
                self?.pmIconImgView.image = view?.image(of: CGSize(width: 50.0, height: 50.0))
                self?.methodDesc = result.paymentDescription
                self?.updateView(true)
            }
            controller.dismiss(animated: true, completion: nil)
        }
        BTUIKAppearance.darkTheme()

        self.present(dropIn!, animated: true, completion: nil)
    }
    
    @IBAction func confirmPurchaseTapped(_ sender: Any) {
        ARSLineProgress.show()
        
        let parameters: [String: String] = [
            "donor": UserDefaults.standard.string(forKey: Utils.donorIDKey) ?? "",
            "amount": donationAmountTF.text ?? "0",
        ]

        let urlString = Utils.donate
        
        let request = ParameterEncoding.json.encode(urlString, bodyParameters:parameters)
        _ = WebServiceManager.sharedInstance.fetchRequest(request).responseJSON {[weak self] (_, response, error) in
            guard let weakself = self else {return}
            
            DispatchQueue.main.async {
                guard let responseDict = response as? [String: Any] else {
                    ARSLineProgress.showFail()
                    print(error)
                    CustomAlert.showAlert(title: "Error!!!", message: "Please try again", cancelButtonTitle: "Ok")
                    return
                }
                
                if let result = responseDict["statusCode"] as? Double, result != 200{
                    ARSLineProgress.showFail()
                    print(error)
                    CustomAlert.showAlert(title: "Error!!!", message: "Please try again", cancelButtonTitle: "Ok")
                } else  {
                    ARSLineProgress.showSuccess()
                    print(response)
                    CustomAlert.showAlert(title: "Success!!!", message: "Thank you for your generous donation", cancelButtonTitle: "Ok")
                }
                
            }
        }

//        AF.request(urlString, method: .post, parameters: parameters)
//            .responseJSON { response in
//                switch response.result {
//                case .success:
//                    ARSLineProgress.showSuccess()
//                    print(response)
//                    CustomAlert.showAlert(title: "Success!!!", message: "Thank you for your generous donation", cancelButtonTitle: "Ok")
//
//                    break
//                case .failure(let error):
//                    ARSLineProgress.showFail()
//                    print(error)
//                    CustomAlert.showAlert(title: "Error!!!", message: "Please try again", cancelButtonTitle: "Ok")
//
//                }
//        }

    }
    

}
