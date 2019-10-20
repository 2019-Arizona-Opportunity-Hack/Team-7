//
//  UIColorExtension.swift
//  OpportunityHack
//
//  Created by Ishan Sarangi on 10/19/19.
//  Copyright © 2019 Ishan Sarangi. All rights reserved.
//
import UIKit

extension UIColor {
    
    class func with8BitRed(_ red: CGFloat, green: CGFloat, blue: CGFloat, alpha: CGFloat) -> UIColor {
        return UIColor(red: (red/255.0), green: (green/255.0), blue: (blue/255.0), alpha: alpha)
    }
    
    class func greySeparator() -> UIColor {
        return UIColor.with8BitRed(220, green: 220, blue: 220, alpha: 1.0)
    }
    
    
    class func error() -> UIColor {
        return UIColor.with8BitRed(213, green: 0, blue: 0, alpha: 1.0)
    }
    
    class func peacockBlue() -> UIColor {
        return UIColor.with8BitRed(0, green: 85, blue: 183, alpha: 1.0)
    }
    
    class func textPlaceholder() -> UIColor {
        return UIColor.with8BitRed(176, green: 176, blue: 176, alpha: 1.0)
    }

}
