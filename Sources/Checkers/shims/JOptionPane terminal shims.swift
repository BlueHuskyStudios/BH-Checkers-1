//
//  use terminal instead of JOptionPane.swift
//  Checkers 2010
//
//  Created by Ky on 2025-11-05.
//

import Foundation



public enum JOptionPane {}



public extension JOptionPane {
    static func showMessageDialog(_:Never?, _ message: String, _ title: String, _: Int) {
        print("""
            
            
            
            \(message)
            
            """)
    }
    
    
    static func showConfirmDialog(_:Never?, _ prompt: String, _ title: String = "", _: Int? = nil, _: Int? = nil) -> Int {
        print("""
            
            
            
            \(prompt)
            Y/N?
            """)
        let readLine = readLine(strippingNewline: false)!
        switch readLine.lowercased() {
        case "y":
            return 0
        case "n":
            return 1
            
        default:
            print("Please enter Y or N")
            return showConfirmDialog(nil, prompt, title, 0)
        }
    }
}
