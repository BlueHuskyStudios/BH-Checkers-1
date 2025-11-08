//
//  Introspection + Checkers.swift
//  Checkers 2010
//
//  Created by Ky on 2025-11-04.
//

import Foundation

import CollectionTools
import Introspection



public extension Introspection {
    static var username: String {
        ProcessInfo.processInfo.userName.nonEmptyOrNil
            ?? NSFullUserName().nonEmptyOrNil
            ?? "mysterious entity"
    }
}
