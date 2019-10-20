//
//  DonorViewController.swift
//  OpportunityHack
//
//  Created by Ishan Sarangi on 10/19/19.
//  Copyright © 2019 Ishan Sarangi. All rights reserved.
//

import UIKit
import BraintreeDropIn
import Braintree
import Alamofire
import ARSLineProgress

class DonorViewController: UIViewController {
    
    var userName: String?
    var id : String?
    @IBOutlet weak var greetLabel: UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()

        greetLabel.text = userName?.isEmpty ?? true ? "Hello!!!" : "Hello \(String(describing: userName!))"
        navigationItem.rightBarButtonItem = UIBarButtonItem(title: "Logout", style: .plain, target: self, action: #selector(logoutTapped))
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
    }
    
    @objc func logoutTapped(sender: UIBarButtonItem){
        ViewManager.updateLoggedOutRootVC()
    }
    
    @IBAction func downloadTapped(_ sender: Any) {
        let destination = DownloadRequest.suggestedDownloadDestination(for: .documentDirectory)

        let url = Utils.donate
        
        AF.download(
            url,
            method: .get,
            parameters: nil,
            headers: nil,
            to: destination).downloadProgress(closure: { (progress) in
                ARSLineProgress.showWithProgressObject(progress)
                //progress closure
            }).response(completionHandler: { (DefaultDownloadResponse) in
                //here you able to access the DefaultDownloadResponse
                //result closure
                switch DefaultDownloadResponse.result {
                case .success:
                    ARSLineProgress.showSuccess()
                    break
                case .failure(let error):
                    CustomAlert.showAlert(title: "Error!!!", message: "Please try again", cancelButtonTitle: "Ok")
                    print(error)
                }
            })
    }
    
    @IBAction func donateTapped(_ sender: Any) {
        let mainStoryboard = UIStoryboard(name: "Main", bundle: nil)
        if let vc = mainStoryboard.instantiateViewController(withIdentifier: "PaymentViewController") as? PaymentViewController {
            vc.id = id
            self.navigationController?.pushViewController(vc, animated: true)
        }
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

//    var braintreeClient: BTAPIClient!

//    override func viewDidLoad() {
//        super.viewDidLoad()
//
//        self.braintreeClient = BTAPIClient(authorization: "sandbox_4xthynmf_y9vk98z94ssm45zt")
//
//        let customPayPalButton = UIButton(frame: CGRect(x: 0, y: 0, width: 60, height: 120))
//        customPayPalButton.addTarget(self, action: #selector(customPayPalButtonTapped(button:)), for: UIControl.Event.touchUpInside)
//        customPayPalButton.backgroundColor = UIColor.red
//        self.view.addSubview(customPayPalButton)
//    }

//    @objc func customPayPalButtonTapped(button: UIButton) {
//        let payPalDriver = BTPayPalDriver(apiClient: self.braintreeClient)
//        payPalDriver.viewControllerPresentingDelegate = self
//        payPalDriver.appSwitchDelegate = self
//
//        // Start the Vault flow, or...
//        payPalDriver.authorizeAccount() { (tokenizedPayPalAccount, error) -> Void in
////            ...
//        }
//
//        // ...start the Checkout flow
//        let payPalRequest = BTPayPalRequest(amount: "1.00")
//        payPalDriver.requestOneTimePayment(payPalRequest) { (tokenizedPayPalAccount, error) -> Void in
////            ...
//        }
//    }
//}
//
//extension DonorViewController: BTViewControllerPresentingDelegate {
//    func paymentDriver(_ driver: Any, requestsPresentationOf viewController: UIViewController) {
//        present(viewController, animated: true, completion: nil)
//    }
//
//    func paymentDriver(_ driver: Any, requestsDismissalOf viewController: UIViewController) {
//        viewController.dismiss(animated: true, completion: nil)
//    }
//}
//
//extension DonorViewController: BTAppSwitchDelegate {
//    func appSwitcherWillPerformAppSwitch(_ appSwitcher: Any) {
//        showLoadingUI()
//
//        NotificationCenter.default.addObserver(self, selector: #selector(hideLoadingUI), name: UIApplication.didBecomeActiveNotification, object: nil)
//    }
//
//    func appSwitcherWillProcessPaymentInfo(_ appSwitcher: Any) {
//        hideLoadingUI()
//    }
//
//    func appSwitcher(_ appSwitcher: Any, didPerformSwitchTo target: BTAppSwitchTarget) {
//
//    }
//
//    // MARK: - Private methods
//
//    func showLoadingUI() {
//        // ...
//    }
//
//    @objc func hideLoadingUI() {
//        NotificationCenter
//            .default
//            .removeObserver(self, name: UIApplication.didBecomeActiveNotification, object: nil)
//        // ...
//    }
//}
