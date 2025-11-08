//
//  operator shims.swift
//  Checkers 2010
//
//  Created by Ky on 2025-11-04.
//

import Foundation



postfix operator ++
postfix operator --



public extension Strideable {
    
    static func +=(lhs: inout Self, rhs: Stride) {
        lhs = lhs.advanced(by: rhs)
    }
    
    
    /// `i++` is the same as `i += 1`. You get no return value, fuck you.
    static postfix func ++(lhs: inout Self) {
        lhs += 1
    }
    
    
    static func -=(lhs: inout Self, rhs: Stride) {
        lhs = lhs.advanced(by: -rhs)
    }
    
    
    /// `i-` is the same as `i -= 1`. You get no return value, fuck you.
    static postfix func --(lhs: inout Self) {
        lhs -= 1
    }
}
