public enum GameBoard
{
  public static func print(_ size: Int, _ board: [[String]]) -> String
  {
    var square = "";
    var bigCol = false;
    var col = UnicodeScalar("A");
    var col2 = UnicodeScalar("A");
    var row = 1;
    
    square += ("\n\n  ");
    for i in 0 ..< size
    {
      if (bigCol) {
        square += (col2);}
      else {
        square += (" ");}
      
      square += (col);
      col++;
      
      if (col > "Z" && !bigCol)
      {
        bigCol = true;
        col = "A";
      }
      if (col > "Z" && bigCol)
      {
        col = "A";
        col2++;
      }
      square += ("  ");
    }
    square += ("\n");
    
    for i in 0 ..< size
    {
      if (i < 10) {
        square += (" " + (i + 1));}
      else {
        square += (i + 1);}
      
      for j in 0 ..< size
      {
        square += (" " + board[i][j] + " ");
        if (j < (size - 1)) {
          square += ("|");}
      }
      square += ("\n");
      
      if (i < 10) {
        square += ("  ");}
      else {
        square += (" ");}
      
      if (i < (size - 1))
      {
        for j in 0 ..< size
        {
          square += ("---");
          if (j < size - 1) {
            square += ("+");}
        }
        square += ("\n");
      }
    }
    return square;
  }
  
  public static func print(_ size: Int, _ board: [[Character]]) -> String
  {
    var square = "";
    var bigCol = false;
    var col: UnicodeScalar = "A";
    var col2: UnicodeScalar = "A";
    var row = 1;
    
    square += "\n\n  ";
    for i in 0 ..< size
    {
      if (bigCol) {
        square += (col2);}
      else {
        square += (" ");}
      
      square += (col);
      col++;
      
      if (col > "Z" && !bigCol)
      {
        bigCol = true;
        col = "A";
      }
      if (col > "Z" && bigCol)
      {
        col = "A";
        col2++;
      }
      square += ("  ");
    }
    square += ("\n");
    
    for i in 0 ..< size
    {
      if (i < 10) {
        square += (" " + (i + 1));}
      else {
        square += (i + 1);}
      
      for j in 0 ..< size
      {
        square += (" " + board[i][j] + " ");
        if (j < (size - 1)) {
          square += ("|");}
      }
      square += ("\n");
      
      if (i < 10) {
        square += ("  ");}
      else {
        square += (" ");}
      
      if (i < (size - 1))
      {
        for j in 0 ..< size
        {
          square += ("---");
          if (j < size - 1) {
            square += ("+");}
        }
        square += ("\n");
      }
    }
    return square;
  }
  
  public static func print(_ size: Int, _ board: [[Int]]) -> String
  {
    var square = "";
    var bigCol = false;
    var col: UnicodeScalar = "A";
    var col2: UnicodeScalar = "A";
    var row = 1;
    
    square += "\n\n  ";
    for i in 0 ..< size
    {
      if (bigCol) {
        square += (col2);}
      else {
        square += (" ");}
      
      square += (col);
      col++;
      
      if (col > "Z" && !bigCol)
      {
        bigCol = true;
        col = "A";
      }
      if (col > "Z" && bigCol)
      {
        col = "A";
        col2++;
      }
      square += ("  ");
    }
    square += ("\n");
    
    for i in 0 ..< size
    {
      if (i < 10) {
        square += (" " + (i + 1));}
      else {
        square += (i + 1);}
      
      for j in 0 ..< size
      {
        square += (" " + board[i][j] + " ");
        if (j < (size - 1)) {
          square += ("|");}
      }
      square += ("\n");
      
      if (i < 10) {
        square += ("  ");}
      else {
        square += (" ");}
      
      if (i < (size - 1))
      {
        for j in 0 ..< size
        {
          square += ("---");
          if (j < size - 1) {
            square += ("+");}
        }
        square += ("\n");
      }
    }
    return square;
  }
  
  public static func miniPrint(_ board: [[String]]) -> String
  {
    var square = "";
    
    square += ("\n\n ");
    
    for i in 0 ..< board.count
    {
      square += UnicodeScalar(i + 65)!;
    }
    
    square += "\r\n";
    
    for i in 0 ..< board.count
    {
      square += i + 1;
      for j in 0 ..< board.count
      {
        square += board[i][j];
      }
      square += "\r\n";
    }
    
    return square;
  }
  
  public static func miniPrint(_ board: [[Character]]) -> String
  {
    var square = "";
    
    square += ("\n\n ");
    
    for i in 0 ..< board.count
    {
      square += UnicodeScalar(i + 65)!;
    }
    
    square += "\r\n";
    
    for i in 0 ..< board.count
    {
      square += i + 1;
      for j in 0 ..< board.count
      {
        square += board[i][j];
      }
      square += "\r\n";
    }
    
    return square;
  }
  
  public static func miniPrint(_ board: [[Int]]) -> String
  {
    var square = "";
    
    square += ("\n\n ");
    
    for i in 0 ..< board.count
    {
      square += UnicodeScalar(i + 65)!;
    }
    
    square += "\r\n";
    
    for i in 0 ..< board.count
    {
      square += i + 1;
      for j in 0 ..< board.count
      {
        square += board[i][j];
      }
      square += "\r\n";
    }
    
    return square;
  }
}
