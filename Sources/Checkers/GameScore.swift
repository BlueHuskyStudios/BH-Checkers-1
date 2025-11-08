import Foundation
import SimpleLogging
import OptionalTools
import StringIntegerAccess
public class GameScore
{
  public static func tryScore(_ fileName: String, _ username: String, _ win: Int, _ lose: Int) throws -> String
  {//System.out.println("tryScore");
    if try addScore(fileName, username, win, lose).lowercased() == ("Username \"" + username + "\" already exists.").lowercased()
    {
        log(error: "Changing score of " + username + ".");
        try changeScore(fileName, username, win, lose);
    }
    
    return "Score added/changed";
  }//END tryScore METHOD
  
  public static func changeScore(_ fileName: String, _ username: String, _ win: Int, _ lose: Int) throws -> String
  {//System.out.println("changeScore");
    var scan: Scanner
    var usernames: [String?]
    var wins: [Int], losses: [Int];
    var fileLength = 0;
    
    var username = fixName(username);
    
    do
    {
      scan = try Scanner(fileName: fileName);
    }
    catch // is FileNotFoundException
    {
      log(error: "File not found: " + fileName);
//      return "File not found: " + fileName;
      
      
      try ("//BEGIN\r\n" + "//END")
        .write(toFile: fileName, atomically: true, encoding: .utf8)
    
      scan = try Scanner(fileName: fileName);
    }
    
    var fileContents = "", fileLine = scan.nextLine();
    while fileLine != "//END" //First sweep gets the length of the file
    {
      fileLine = scan.nextLine();
      if fileLine != "//END" {
        fileLength++;}
    }
    
    usernames = .init(repeating: nil, count: fileLength);
    wins = .init(repeating: 0, count: fileLength);
    losses = .init(repeating: 0, count: fileLength);
    
    scan = try Scanner(fileName: fileName); //RESET THE SCANNER FOR THE SECOND SWEEP
    let spare = scan.next();
    
    
    for i in 0 ..< fileLength //Second sweep makes arrays of all values
    {
      do
      {
        usernames[i] = try scan.next().unwrappedOrThrow(error: InputMismatchError())
        //System.out.print(usernames[i]);
        wins[i] = try scan.nextInt().unwrappedOrThrow(error: InputMismatchError())
        //System.out.print(wins[i]);
        losses[i] = try scan.nextInt().unwrappedOrThrow(error: InputMismatchError())
        //System.out.println(losses[i]);
      }
      catch is InputMismatchError
      {
        if usernames[i] == username
        {
          if (YesNoBox.bool("Unexpected glitch when reading \(usernames[i] ?? "(null)")'s scores.\nThey may not be present. Would you like to reset \(usernames[i] ?? "(null)")'s scores in the file \"\(fileName)\" to \(win) wins and \(lose) losses?\n\n(NOTE: The file is corrupt and the program you are trying to use may not work without resetting these scores. It is reccommended that you click Yes)", "Error from GameScore", 0))
          {
            wins[i] = win;
            losses[i] = lose;
          }
        }
        else if (YesNoBox.bool("Unexpected glitch when reading \(usernames[i] ?? "(null)")'s scores.\nThey may not be present. Would you like to reset \(usernames[i] ?? "(null)")'s scores in the file \"\(fileName)\" to 0 wins and 0 losses?\n\n(NOTE: The file is corrupt and the program you are trying to use may not work without resetting these scores. It is reccommended that you click Yes)", "Error from GameScore", 0))
        {
          wins[i] = 0;
          losses[i] = 0;
        }
        else {
          JOptionPane.showMessageDialog(nil,"CAUTION: This program may not work if this file is corrupt. You are warned.", "WARNING from GameScore", 0);}
      }
    }
    
    for i in 0 ..< fileLength
    {
      if (usernames[i].equalsIgnoreCase(username))
      {
        wins[i] += win;
        losses[i] += lose;
        break;
      }
    }
    
    for i in 0 ..< fileLength
    {
      fileContents += "\(usernames[i] ?? "(null)")\t\(wins[i]) \(losses[i])\r\n";
    }
    
    
    //System.out.println(fileContents);
    
    
    try ("//BEGIN\r\n" + fileContents + "//END")
      .write(toFile: fileName, atomically: true, encoding: .utf8)
    return "Scores successfully changed.";
  }//END changeScore METHOD
  
  
  public static func addScore(_ fileName: String, _ username: String, _ win: Int, _ lose: Int) throws -> String
  {//System.out.println("addScore");
    var scan: Scanner
    var usernames: [String?]
    var wins: [Int], losses: [Int];
    var fileLength = 0;
    
    var username = fixName(username);
    
    do
    {
      scan = try Scanner(fileName: fileName);
    }
    catch let ex
    {
      log(error: ex, "File not found: " + fileName);
//      return "File not found: " + fileName;
      
      try ("//BEGIN\r\n" + "//END")
        .write(toFile: fileName, atomically: true, encoding: .utf8)
      
    
      scan = try Scanner(fileName: fileName);
    }
    
    var fileContents = "", fileLine = scan.nextLine();
    while fileLine != "//END" //First sweep gets the length of the file
    {
      fileLine = scan.nextLine();
      if fileLine != "//END" {
        fileLength++;}
    }
    
    usernames = .init(repeating: nil, count: fileLength);
    wins = .init(repeating: .init(), count: fileLength);
    losses = .init(repeating: .init(), count: fileLength);
    
    scan = try Scanner(fileName: fileName); //RESET THE SCANNER FOR THE SECOND SWEEP
    let spare = scan.next();
    
    
    for i in 0 ..< fileLength //Second sweep makes arrays of all values
    {
      do
      {
        usernames[i] = try scan.next().unwrappedOrThrow(error: InputMismatchError())
        //System.out.print(usernames[i]);
        wins[i] = try scan.nextInt().unwrappedOrThrow(error: InputMismatchError())
        //System.out.print(wins[i]);
        losses[i] = try scan.nextInt().unwrappedOrThrow(error: InputMismatchError())
        //System.out.println(losses[i]);
      }
      catch is InputMismatchError
      {
        if (YesNoBox.bool("Unexpected glitch when reading \(usernames[i] ?? "(null)")'s scores.\nThey may not be present. Would you like to reset \(usernames[i] ?? "(null)")'s scores in the file \"\(fileName)\" to 0 wins and 0 losses?\n\n(NOTE: The file is corrupt and the program you are trying to use may not work without resetting these scores. It is reccommended that you click Yes)", "Error from GameScore", 0))
        {
          wins[i] = 0;
          losses[i] = 0;
        }
        else {
          JOptionPane.showMessageDialog(nil,"CAUTION: This program may not work if this file is corrupt. You are warned.", "WARNING from GameScore", 0);}
      }
    }
    
    for i in 0 ..< fileLength
    {
      if usernames[i]?.equalsIgnoreCase(username) ?? false
      {
        log(error: "Username \"" + username + "\" already exists.");
        return ("Username \"" + username + "\" already exists.");
      }
    }
    
    
    for i in 0 ..< fileLength
    {
      fileContents += "\(usernames[i] ?? "(null)")\t\(wins[i]) \(losses[i])\r\n";
    }
    
    fileContents += "\(username)\t\(win) \(lose)\r\n";
    
    //System.out.println(fileContents);
    
    
    try ("//BEGIN\r\n" + fileContents + "//END")
      .write(toFile: fileName, atomically: true, encoding: .utf8)
    return ("Score successfully added.");
  }//END addScore METHOD
  
  public static func getScore(_ fileName: String, _ username: String) throws -> String
  {
    var scan: Scanner
    var usernames: [String?]
    var wins: [Int], losses: [Int];
    var fileLength = 0, win = -1, lose = -1;
    
    let username = fixName(username);
    
    do
    {
      scan = try Scanner(fileName: fileName);
    }
    catch let ex
    {
      log(error: "File not found: " + fileName);
//      return "File not found: " + fileName;
      
      
      try ("//BEGIN\r\n" + "//END")
        .write(toFile: fileName, atomically: true, encoding: .utf8)
      
    scan = try Scanner(fileName: fileName);
    }
    
    var fileContents = "", fileLine = scan.nextLine();
    while fileLine != "//END" //First sweep gets the length of the file
    {
      fileLine = scan.nextLine();
      if fileLine != "//END" {
        fileLength++;}
    }
    
    usernames = .init(repeating: nil, count: fileLength);
    wins = .init(repeating: .init(), count: fileLength);
    losses = .init(repeating: .init(), count: fileLength);
    
    scan = try Scanner(fileName: fileName); //RESET THE SCANNER FOR THE SECOND SWEEP
    let spare = scan.next();
    
    
    for i in 0 ..< fileLength //Second sweep makes arrays of all values
    {
      do
      {
        usernames[i] = try scan.next().unwrappedOrThrow(error: InputMismatchError())
        //System.out.print(usernames[i]);
        wins[i] = try scan.nextInt().unwrappedOrThrow(error: InputMismatchError())
        //System.out.print(wins[i]);
        losses[i] = try scan.nextInt().unwrappedOrThrow(error: InputMismatchError())
        //System.out.println(losses[i]);
      }
      catch is InputMismatchError
      {
        try changeScore(fileName, usernames[i]!, 0, 0);
      }
    }
    
    for i in 0 ..< fileLength
    {
      if usernames[i]?.equalsIgnoreCase(username) ?? false
      {
        win = wins[i];
        lose = losses[i];
        break;
      }
    }
    
    for i in 0 ..< fileLength
    {
      if usernames[i]?.equalsIgnoreCase(username) ?? false {
        break;}
      
      if (i == fileLength)
      {
        log(error: "No such username!");
        return ("No such username!");
    } }
    return (win + " " + lose);
  }//END getScore METHOD
  
  public static func getWins(_ fileName: String, _ username: String) throws -> Int
  {
    var scan: Scanner;
    var usernames: [String?];
    var wins: [Int], losses: [Int];
    var fileLength = 0, win = -1, lose = -1;
    
    let username = fixName(username);
    
    do
    {
      scan = try Scanner(fileName: fileName);
    }
    catch //is FileNotFoundException
    {
      log(error: "File not found: " + fileName);
//      return "File not found: " + fileName;
      
      
      try ("//BEGIN\r\n" + "//END")
        .write(toFile: fileName, atomically: true, encoding: .utf8)
      
      scan = try Scanner(fileName: fileName);
    }
    
    var fileContents = "", fileLine = scan.nextLine();
    while fileLine != "//END" //First sweep gets the length of the file
    {
      fileLine = scan.nextLine();
      if fileLine != "//END" {
        fileLength++;}
    }
    
    usernames = .init(repeating: nil, count: fileLength);
    wins = .init(repeating: .init(), count: fileLength);
    losses = .init(repeating: .init(), count: fileLength);
    
    scan = try Scanner(fileName: fileName); //RESET THE SCANNER FOR THE SECOND SWEEP
    let spare = scan.next();
    
    
    
    for i in 0 ..< fileLength //Second sweep makes arrays of all values
    {
      do
      {
        usernames[i] = try scan.next().unwrappedOrThrow(error: InputMismatchError())
        //System.out.print(usernames[i]);
        wins[i] = try scan.nextInt().unwrappedOrThrow(error: InputMismatchError())
        //System.out.print(wins[i]);
        losses[i] = try scan.nextInt().unwrappedOrThrow(error: InputMismatchError())
        //System.out.println(losses[i]);
      }
      catch is InputMismatchError
      {
        _ = try changeScore(fileName, usernames[i]!, 0, 0);
      }
    }
    
    for i in 0 ..< fileLength
    {
      if usernames[i]?.equalsIgnoreCase(username) ?? false
      {
        win = wins[i];
        lose = losses[i];
        break;
      }
    }
    
    for i in 0 ..< fileLength
    {
      if usernames[i]?.equalsIgnoreCase(username) ?? false {
        break;}
      if (i == fileLength)
      {
        throw NameNotFoundException(username);
      }
    }
    return (win);
  }//END getLosses METHOD
  
  public static func getLosses(_ fileName: String, _ username: String) throws -> Int
  {
    var scan: Scanner
    var usernames: [String?]
    var wins: [Int], losses: [Int];
    var fileLength = 0, win = -1, lose = -1;
    
    let username = fixName(username);
    
    do
    {
      scan = try Scanner(fileName: fileName);
    }
    catch //is FileNotFoundException
    {
      log(error: "File not found: " + fileName);
//      return "File not found: " + fileName;
      
      
      try ("//BEGIN\r\n" + "//END")
        .write(toFile: fileName, atomically: true, encoding: .utf8)
      
      scan = try Scanner(fileName: fileName);
    }
    
    var fileContents = "", fileLine = scan.nextLine();
    while fileLine != "//END" //First sweep gets the length of the file
    {
      fileLine = scan.nextLine();
      if fileLine != "//END" {
        fileLength++;}
    }
    
    usernames = .init(repeating: nil, count: fileLength);
    wins = .init(repeating: .init(), count: fileLength);
    losses = .init(repeating: .init(), count: fileLength);
    
    scan = try Scanner(fileName: fileName); //RESET THE SCANNER FOR THE SECOND SWEEP
    let spare = scan.next();
    
    
    for i in 0 ..< fileLength //Second sweep makes arrays of all values
    {
      do
      {
        usernames[i] = try scan.next().unwrappedOrThrow(error: InputMismatchError())
        //System.out.print(usernames[i]);
        wins[i] = try scan.nextInt().unwrappedOrThrow(error: InputMismatchError())
        //System.out.print(wins[i]);
        losses[i] = try scan.nextInt().unwrappedOrThrow(error: InputMismatchError())
        //System.out.println(losses[i]);
      }
      catch is InputMismatchError
      {
        _ = try changeScore(fileName, usernames[i]!, 0, 0);
      }
    }
    
    
    for i in 0 ..< fileLength
    {
      if usernames[i]?.equalsIgnoreCase(username) ?? false
      {
        win = wins[i];
        lose = losses[i];
        break;
      }
    }
    
    for i in 0 ..< fileLength
    {
      if usernames[i]?.equalsIgnoreCase(username) ?? false {
        break;}
      
      if i == fileLength
      {
        throw NameNotFoundException(username);
    } }
    return (lose);
  }//END getLosses METHOD
  
  public static func allScores(_ fileName: String) throws -> String
  {//System.out.println("allScores");
    var scan: Scanner;
    var usernames: [String?];
    var wins: [Int], losses: [Int];
    var fileLength = 0, win = -1, lose = -1;
    do
    {
      scan = try Scanner(fileName: fileName);
    }
    catch // is FileNotFoundException
    {
      log(error: "File not found: " + fileName);
//      return "File not found: " + fileName;
      
      
      try("//BEGIN\r\n" + "//END")
        .write(toFile: fileName, atomically: true, encoding: .utf8)
      
      scan = try Scanner(fileName: fileName);
    }
    
    var fileContents = "", fileLine = scan.nextLine();
    while fileLine != "//END" //First sweep gets the length of the file
    {
      fileLine = scan.nextLine();
      if fileLine != "//END" {
        fileLength++;}
    }
    if fileLength == 0 {
      return "There are no scores in the scoreboard!\nSave yours and get 1st place ;3";}
    
    usernames = .init(repeating: nil, count: fileLength);
    wins = .init(repeating: .init(), count: fileLength);
    losses = .init(repeating: .init(), count: fileLength);
    
    scan = try Scanner(fileName: fileName); //RESET THE SCANNER FOR THE SECOND SWEEP
    let spare = scan.next();
    
    
    for i in 0 ..< fileLength //Second sweep makes arrays of all values
    {
      do
      {
        usernames[i] = try scan.next().unwrappedOrThrow(error: InputMismatchError())
        //System.out.print(usernames[i]);
        wins[i] = try scan.nextInt().unwrappedOrThrow(error: InputMismatchError())
        //System.out.print(wins[i]);
        losses[i] = try scan.nextInt().unwrappedOrThrow(error: InputMismatchError())
        //System.out.println(losses[i]);
      }
      catch is InputMismatchError
      {
        _ = try changeScore(fileName, usernames[i]!, 0, 0);
      }
    }
    
    usernames = readNames(usernames);
    
    fileContents = "Username\t Wins\t Losses\n";
    for i in 0 ..< fileLength
    {
      fileContents += "\(i + 1): \(usernames[i] ?? "(null)")\(usernames[i]!.count < 5 ? "\t\t" : "\t ")\(wins[i])\t \(losses[i])\n";
    }
    
    return (fileContents);
  }//END allScores METHOD
  
  public static func sortScores(_ fileName: String) throws -> String
  {//System.out.println("sortScores");
    var scan: Scanner;
    var usernames: [String?], oUser: [String?];
    var wins: [Int], losses: [Int], order: [Int], oWin: [Int], oLose: [Int];
    var fileLength = 0, highScore = 0;
    do
    {
      scan = try Scanner(fileName: fileName);
    }
    catch // is FileNotFoundException
    {
      log(error: "File not found: " + fileName);
//      return "File not found: " + fileName;
      
      
      try ("//BEGIN\r\n" + "//END")
        .write(toFile: fileName, atomically: true, encoding: .utf8)
      
      scan = try Scanner(fileName: fileName);
    }
    
    var fileContents = "", fileLine = scan.nextLine();
    while fileLine != "//END" //First sweep gets the length of the file
    {
      fileLine = scan.nextLine();
      if fileLine != "//END" {
        fileLength++;}
    }
    
    usernames = .init(repeating: nil, count: fileLength);
    wins = .init(repeating: .init(), count: fileLength);
    losses = .init(repeating: .init(), count: fileLength);
    order = .init(repeating: .init(), count: fileLength);
    oUser = .init(repeating: nil, count: fileLength);
    oWin = .init(repeating: .init(), count: fileLength);
    oLose = .init(repeating: .init(), count: fileLength);
    
    scan = try Scanner(fileName: fileName); //RESET THE SCANNER FOR THE SECOND SWEEP
    let spare = scan.next();
    
    
    for i in 0 ..< fileLength //Second sweep makes arrays of all values
    {
      do
      {
        
        usernames[i] = try scan.next().unwrappedOrThrow(error: InputMismatchError())
        oUser[i] = usernames[i];
        //System.out.print(usernames[i]);
        wins[i] = try scan.nextInt().unwrappedOrThrow(error: InputMismatchError())
        oWin[i] = wins[i];
        //System.out.print(wins[i]);
        losses[i] = try scan.nextInt().unwrappedOrThrow(error: InputMismatchError())
        oLose[i] = losses[i];
        //System.out.println(losses[i]);
      }
      catch is InputMismatchError
      {
        //System.err.println(wins[i] + " " + losses[i]);
        _ = try changeScore(fileName, usernames[i]!, (wins[i] != 0 ? wins[i] : 0), (losses[i] != 0 ? losses[i] : 0));
      }
    }
    
    for i in 0 ..< fileLength
    {
      for j in 0 ..< fileLength - 1
      {
        if oWin[j] < oWin[j + 1]
        {
          var spareInt = oWin[j];
          oWin[j] = oWin[j + 1];
          oWin[j + 1] = spareInt;
          spareInt = oLose[j];
          oLose[j] = oLose[j + 1];
          oLose[j + 1] = spareInt;
          let spareStr = oUser[j];
          oUser[j] = oUser[j + 1];
          oUser[j + 1] = spareStr;
        }
      }
    }
    
    fileContents = "";
    for i in 0 ..< fileLength
    {
      fileContents += "\(oUser[i] ?? "(null)")\t\(oWin[i]) \(oLose[i])\r\n";
    }
    
    //fileContents += username + "\t" + win + " " + lose + "\r\n";
    
    //System.out.println(fileContents);
    
    
    try ("//BEGIN\r\n" + fileContents + "//END")
      .write(toFile: fileName, atomically: true, encoding: .utf8);
    return ("Scores successfully sorted.");
  }//END sortScores METHOD
  
  public static func topScores(_ fileName: String, _ scores: Int) throws -> String
  {//System.out.println("topScores");
    _ = try sortScores(fileName);
    
    var scan: Scanner;
    var usernames: [String?];
    var wins: [Int], losses: [Int];
    var fileLength = 0, win = -1, lose = -1;
    do
    {
      scan = try Scanner(fileName: fileName);
    }
    catch // is FileNotFoundException
    {
      log(error: "File not found: " + fileName);
//      return "File not found: " + fileName;
      
      
      try ("//BEGIN\r\n" + "//END")
        .write(toFile: fileName, atomically: true, encoding: .utf8)
      
      scan = try Scanner(fileName: fileName);
    }
    
    var fileContents = "", fileLine = scan.nextLine();
    while fileLine != "//END" //First sweep gets the length of the file
    {
      fileLine = scan.nextLine();
      if fileLine != "//END" {
        fileLength++;}
    }
    
    if fileLength == 0 {
      return "There are no scores in the scoreboard!\nSave yours and get 1st place ;3";}
    usernames = .init(repeating: nil, count: fileLength);
    wins = .init(repeating: .init(), count: fileLength);
    losses = .init(repeating: .init(), count: fileLength);
    
    scan = try Scanner(fileName: fileName); //RESET THE SCANNER FOR THE SECOND SWEEP
    let spare = scan.next();
    
    
    for i in 0 ..< fileLength //Second sweep makes arrays of all values
    {
      do
      {
        usernames[i] = try scan.next().unwrappedOrThrow(error: InputMismatchError())
        //System.out.print(usernames[i]);
        wins[i] = try scan.nextInt().unwrappedOrThrow(error: InputMismatchError())
        //System.out.print(wins[i]);
        losses[i] = try scan.nextInt().unwrappedOrThrow(error: InputMismatchError())
        //System.out.println(losses[i]);
      }
      catch is InputMismatchError
      {
        log(error: wins[i] + " " + losses[i]);
        _ = try changeScore(fileName, usernames[i]!, (wins[i] != 0 ? wins[i] : 0), (losses[i] != 0 ? losses[i] : 0));
      }
    }
    
    fileContents = "Username\t Wins\t Losses\n";
    
    //for (i=0; i < fileLength && i < scores; i++)
    for i in 0 ..< min(fileLength, scores)
    {
      fileContents += "\(i + 1): \(usernames[i] ?? "(null)")\(usernames[i]!.count < 5 ? "\t\t " : (usernames[i]!.count >= 13 ? " " : "\t "))\(wins[i])\t \(losses[i])\n\r";
    }
    
    return (fileContents);
  }//END topScores METHOD
  
  public static func fixName (_ user: String) -> String
  {//System.out.println("fixName");
    var username = user;
    
    for i in 0 ..< username.count
    {//System.out.println("fixName username: " + username + "; username.charAt(" + i + "): " + username.charAt(i));
      if (username[i] == " " || username[i] == "\t" || username[i] == "\n" || username[i] == "\r" || username[i] == "\u{8}") {
        username = username[0..<i] + "_" + username[(i + 1) ..< username.count];}
    }
    return username;
  }
  
  public static func readNames (_ users: [String?]) -> [String?]
  {//System.out.println("readNames");
    var usernames = users;
    
    for i in 0 ..< usernames.count
    {
      for j in 0 ..< usernames[i]!.count
      {//System.out.println("readNames usernames[" + i + "]: " + usernames[i] + "; usernames[" + i + "].charAt(" + j + "): " + usernames[i].charAt(j));
        if (usernames[i]![j] == "_") {
          usernames[i] = usernames[i]![0 ..< j] + " " + usernames[i]![(j + 1) ..< usernames[i]!.count];}
      }
    }
    return usernames;
  }
}
  
