//
//  File.swift
//  Checkers 2010
//
//  Created by Ky on 2025-11-05.
//

import Foundation



public extension Scanner {
    /// Loads the contents of the file with the given name into a new scanner
    ///
    /// - Parameter fileName: The name of a file whose contents you want to load. This is relative to the current working directory
    convenience init(fileName: String) throws {
        self.init(string: try String.init(contentsOf: URL(
            fileURLWithPath: fileName,
            relativeTo: URL(fileURLWithPath: FileManager.default.currentDirectoryPath)
        )))
    }
}



public extension Scanner {
    func nextLine() -> String? {
        scanUpToCharacters(from: .newlines)
    }
    
    
    /// scans until the next string is found, and returns it
    func next() -> String? {
        scanUpToCharacters(from: .whitespacesAndNewlines)
    }
    
    
    /// scans until the next integer is found, and returns it
    func nextInt() -> Int? {
        if let next = next(),
           let int = Int(next) {
            return int
        }
        else {
            return nil
        }
    }
}



public struct InputMismatchError: Error {}
