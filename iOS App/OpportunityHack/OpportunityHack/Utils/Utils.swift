//
//  Utils.swift
//  OpportunityHack
//
//  Created by Ishan Sarangi on 10/20/19.
//  Copyright © 2019 Ishan Sarangi. All rights reserved.
//

import Foundation

struct Utils {
    static let host = "http://54.172.164.131:8080"
    static let login = host + "/login"
    static let register = host + "/app/register"
    static let donate = host + "/donation"
    static let pdfreport = host + "/pdfreport/" + (UserDefaults.standard.string(forKey: Utils.donorIDKey) ?? "")

    static let donorIDKey = "donorID"
}
