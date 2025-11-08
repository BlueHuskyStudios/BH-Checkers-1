

public enum GameProps
{
    public static var gameProp: [[String]] = [
        ["title", "version"],
        ["Blue Husky's Checkers","1.1.10+Swift.2025"]]
    
    public static func getProp(_ key: String) -> String
    {
        print(gameProp.count)
        for i in 0 ..< gameProp.count
        {
            if (gameProp[i][0] == key) {
                return gameProp[i][0]
            }
        }
        return "Property not found: " + key;
    }
}
