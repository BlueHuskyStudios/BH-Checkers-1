// swift-tools-version: 6.2
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "Checkers 2010",
    platforms: [
        .macOS(.v10_15),
    ],
    dependencies: [
        .package(url: "https://github.com/RougeWare/Swift-Simple-Logging", from: "0.5.2"),
        .package(url: "https://github.com/RougeWare/Swift-Introspection.git", from: "1.2.1"),
        .package(url: "https://github.com/RougeWare/Swift-Collection-Tools.git", from: "3.2.0"),
        .package(url: "https://github.com/RougeWare/Swift-Optional-Tools", from: "1.2.0"),
    ],
    targets: [
        // Targets are the basic building blocks of a package, defining a module or a test suite.
        // Targets can depend on other targets in this package and products from dependencies.
        .executableTarget(
            name: "Checkers",
            dependencies: [
                .product(name: "SimpleLogging", package: "Swift-Simple-Logging"),
                .product(name: "Introspection", package: "Swift-Introspection"),
                .product(name: "CollectionTools", package: "Swift-Collection-Tools"),
                .product(name: "OptionalTools", package: "Swift-Optional-Tools"),
            ],
            swiftSettings: [
                .defaultIsolation(MainActor.self)
            ]
        ),
    ]
)
