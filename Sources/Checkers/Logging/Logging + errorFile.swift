//
//  Logging + errorFile.swift
//  Checkers 2010
//
//  Created by Ky on 2025-11-04.
//

import Foundation

import SimpleLogging



private let ERROR_FILE = "errors.txt"



extension LogChannel {
    
    public static var errorFile: LogChannel {
        .init(name: ERROR_FILE, location: .file(path: ERROR_FILE), lowestAllowedSeverity: .warning)
    }
}
