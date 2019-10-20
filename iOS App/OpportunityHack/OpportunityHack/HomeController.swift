//
//  ViewController.swift
//  OpportunityHack
//
//  Created by Ishan Sarangi on 10/19/19.
//  Copyright © 2019 Ishan Sarangi. All rights reserved.
//

import UIKit

class HomeController: UIViewController {

    @IBOutlet weak var registerButton: UIButton!
    @IBOutlet weak var loginButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    @IBAction func registerTapped(_ sender: Any) {
        let mainStoryboard = UIStoryboard(name: "Main", bundle: nil)
        if let registerVC = mainStoryboard.instantiateViewController(withIdentifier: "RegisterViewController") as? RegisterViewController {
            self.present(registerVC, animated: true)
        }
    }
    
    @IBAction func logInTapped(_ sender: Any) {
        let mainStoryboard = UIStoryboard(name: "Main", bundle: nil)
        if let loginVC = mainStoryboard.instantiateViewController(withIdentifier: "LoginViewController") as? LoginViewController {
            self.present(loginVC, animated: true)
        }
    }
    
}

