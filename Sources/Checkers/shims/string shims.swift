//
//  string shims.swift
//  Checkers 2010
//
//  Created by Ky on 2025-11-04.
//

import Foundation



extension Character {
    init(codepoint: UInt32) {
        self.init(UnicodeScalar(codepoint)!)
    }
    
    
    var codepoint: UInt32 {
        self.unicodeScalars.first!.value
    }
}



@available(*, unavailable, renamed: "UnicodeScalar", message: "Probably best to just use scalars")
func codepoint(_ character: Character) -> UInt32 {
    character.codepoint
}



extension String {
    static func += (lhs: inout String, rhs: Element) {
        lhs.append(rhs)
    }
    
    
    static func += (lhs: inout String, rhs: UnicodeScalar) {
        lhs += Character(rhs)
    }
    
    
    static func + (lhs: String, rhs: CustomStringConvertible) -> String {
        lhs + rhs.description
    }
    
    
    static func + (lhs: CustomStringConvertible, rhs: String) -> String {
        lhs.description + rhs
    }
    
    
    static func += (lhs: inout String, rhs: CustomStringConvertible) {
        lhs += rhs.description
    }
}



extension StringProtocol {
    func equalsIgnoreCase<Other: StringProtocol>(_ other: Other) -> Bool {
        lowercased() == other.lowercased()
    }
}



extension Optional<StringProtocol> {
    func equalsIgnoreCase<Other: StringProtocol>(_ other: Other?) -> Bool {
        switch (self, other) {
        case (.none, .none):
            true
        
        case (.none, .some),
            (.some, .none):
            false
            
        case (.some(let lhs), .some(let rhs)):
            lhs.equalsIgnoreCase(rhs)
        }
    }
    
    
    func equalsIgnoreCase<Other: StringProtocol>(_ other: Other) -> Bool {
        switch self {
        case .none:          false
        case .some(let lhs): lhs.equalsIgnoreCase(other)
        }
    }
}



extension UnicodeScalar: @retroactive Strideable {
    public func distance(to other: Unicode.Scalar) -> Stride {
        self.value.distance(to: other.value)
    }
    
    
    public func advanced(by n: Stride) -> Unicode.Scalar {
        self.advanced(by: n)
    }
    
    
    
    public typealias Stride = UInt64.Stride
}



//extension UnicodeScalar: @retroactive Comparable {
//    public static func < (lhs: Unicode.Scalar, rhs: Unicode.Scalar) -> Bool {
//        lhs.value < rhs.value
//    }
//}
