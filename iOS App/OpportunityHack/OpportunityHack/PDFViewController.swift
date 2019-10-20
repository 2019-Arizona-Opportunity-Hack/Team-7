//
//  PDFViewController.swift
//  OpportunityHack
//
//  Created by Ishan Sarangi on 10/20/19.
//  Copyright © 2019 Ishan Sarangi. All rights reserved.
//
import UIKit
import PDFKit

class PDFViewController: UIViewController {
    
    var pdfView = PDFView()
    var pdfURL: URL!

    override func viewDidLoad() {
        super.viewDidLoad()
        
        view.addSubview(pdfView)
        
        if let document = PDFDocument(url: pdfURL) {
            pdfView.document = document
        }
        
        DispatchQueue.main.asyncAfter(deadline: .now()+3) {
            self.dismiss(animated: true, completion: nil)
        }
    }
    
    override func viewDidLayoutSubviews() {
        pdfView.frame = view.frame
    }
}
