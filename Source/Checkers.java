/* Plays Checkers
 *
 * Version: 1.1.10
 * Originally created by Supuhstar on 2010/02/15
 * Latest version finished on 2010/04/13
 *
 * Copyright Blue Husky Studios, � 2010 under Creative Commons 3.0 (CC-BY-SA)
 */

import java.io.*;
import javax.swing.*;

public class Checkers
{
  //Declarations:
  private static boolean computer = false, compVsComp = false, delimHit, doubleJumping = false, move = true, named1 = false, named2 = false, quit = false, turnOver = false, winner = false;
  private static char mark, notMark, mark1 = 'b', mark2 = 'r', where, EMPTY = ' ';
  private static char[][] board;
  private static int turns, to1, to2, from1, from2, pieces1, pieces2, piece, notPiece, p1wins, p2wins, numOfPlayers = 2;
  private static int[][] priority;
  private static PrintStream printer;
  private static String color, color1 = "black", color2 = "red", notColor, name, name1 = System.getProperty("user.name"), name2 = "Player 2", notName, /*square = "", */ spareStr = "", error, header;
  private static final int SIZE = /*Integer.parseInt(JOptionPane.showInputDialog(null, "size?", */8/*))*/;
  private static final String TITLE = GameProps.getProp("title"), VERSION = GameProps.getProp("version"), SAVEFILE = "CheckersSave.dll", ERROR_FILE = "errors.txt";

  public static void main(String[] args) throws IOException//, FileNotFoundException
  {
    //Begin error reporting
    printer = new PrintStream(ERROR_FILE);

    System.setErr(printer);

    System.err.println("This is the error-reporting file for " + TITLE + " (version " + VERSION + ")\n\r\n\r");
    System.err.println("               program run from: " + System.getProperty("user.dir"));
    System.err.println("program used to run the program: " + System.getProperty("java.class.path"));
    System.err.println("                        OS name: " + System.getProperty("os.name") + " (version " + System.getProperty("os.version") + ")");
    System.err.println("                OS Architecture: " + System.getProperty("os.arch"));
    System.err.println("  Java Virtural Machine version: " + System.getProperty("java.vm.version"));
    System.err.println("           Java runtime version: " + System.getProperty("java.runtime.version"));
    System.err.println("                        Country: " + System.getProperty("user.country"));
    System.err.println("                      Time Zone: " + System.getProperty("user.timezone"));
    System.err.println("                       Language: " + System.getProperty("user.language"));
    System.err.println("                       Username: " + System.getProperty("user.name"));
    System.err.println("--------------------------------------------------------------------------------------------------------------------------------");

    ////*TC*/System.setErr(new PrintStream(new OutputStream()));

//    System.setOut(new PrintStream(new File("cmd.exe")));

    //End error reporting

    //Instantiations:
    turns = 0; pieces1 = 0; pieces2 = 0; p1wins = 0; p2wins = 0;
    /*square = "";*/spareStr = "";
    priority = new int[SIZE][SIZE];
    board = new char[SIZE][SIZE];

    setup();

    while (!quit)
    {//*TC*/System.out.println(1);
      pieces1 = 0;
      pieces2 = 0;
      //
      for (int i=0; i < SIZE; i++) //SETS ALL SQUARES AS EMPTY
      {
        for (int j=0; j < SIZE; j++)
          board[i][j] = (EMPTY);
      }
      System.err.println("Board initialized successfully");

      for (int i=0; i < (SIZE / 2) - 1; i++) //SETS PLAYER 2'S PIECES
      {
        for (int j=1; j < SIZE; j++, j++)
        {
          if (j == 1 && i == 1)
            j--;
          board[i][j] = mark2;
          pieces2++;
        }
      }
      System.err.println("Player 2's pieces cuccessfully placed");

      for (int i=SIZE; i > SIZE - ((SIZE / 2) - 1); i--) //SETS PLAYER 1'S PIECES
      {
        for (int j=0; j < SIZE; j++, j++)
        {
          if (j == 0 && i == SIZE - 1)
            j++;
          board[i - 1][j] = mark1;
          pieces1++;
        }
      }
      System.err.println("Player 1's pieces cuccessfully placed");

      //
      /*
       //BEGIN TESTING BOARD
       //System.out.println("Test board"); ////////////////////////////////////////////////////////////////////////////TESTING CODE
       //      board = new char[][] {
       //        {EMPTY, mark2, EMPTY, EMPTY, EMPTY, mark2, EMPTY, mark2},
       //        {mark2, EMPTY, mark2, EMPTY, mark2, EMPTY, mark2, EMPTY},
       //        {EMPTY, mark1, EMPTY, mark2, EMPTY, EMPTY, EMPTY, mark2},
       //        {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, mark2, EMPTY},
       //        {EMPTY, EMPTY, EMPTY, mark1, EMPTY, EMPTY, EMPTY, mark1},
       //        {mark2, EMPTY, mark1, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
       //        {EMPTY, mark1, EMPTY, mark1, EMPTY, mark1, EMPTY, mark1},
       //        {mark1, EMPTY, mark1, EMPTY, mark1, EMPTY, EMPTY, EMPTY}};
       //
       //      pieces1 = 11;
       //      pieces2 = 11;

       board = new char[][] {
       {EMPTY, (char)(mark1 - 32), EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
       {EMPTY, EMPTY, mark2, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
       {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
       {EMPTY, EMPTY, mark2, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
       {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
       {EMPTY, EMPTY, mark2, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
       {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
       {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY}};
       //board = newBoard;
       //

       board = new char[][] {
       // A      B      C      D      E      F      G      H
       {EMPTY, EMPTY, EMPTY, mark2, EMPTY, mark2, EMPTY, mark2},// 1
       {mark2, EMPTY, mark2, EMPTY, mark2, EMPTY, mark2, EMPTY},// 2
       {EMPTY, mark2, EMPTY, mark2, EMPTY, mark2, EMPTY, mark2},// 3
       {mark1, EMPTY, mark2, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},// 4
       {EMPTY, EMPTY, EMPTY, mark1, EMPTY, mark1, EMPTY, EMPTY},// 5
       {EMPTY, EMPTY, mark2, EMPTY, EMPTY, EMPTY, mark1, EMPTY},// 6
       {EMPTY, mark1, EMPTY, mark1, EMPTY, mark1, EMPTY, mark1},// 7
       {mark1, EMPTY, mark1, EMPTY, mark1, EMPTY, mark1, EMPTY}};//8

       //      pieces1 = 1;
       //      pieces2 = 3;

       move = false;
       //END TESTING BOARD
       */

      //BEGIN GAME

      while (!winner && !quit)
      {//*TC*/System.out.println(2);
        //System.out.print("\n  ");
        countPieces();

        for (turns=0; !quit && pieces1 > 0 && pieces2 > 0; turns++)
        {//*TC*/System.out.println(3);
          //TC//System.out.println(mark1 + "'s: " + pieces1 + "\n" + mark2 + "'s: " + pieces2);
//          if (move)
//            owner = true;
//          else
//            owner = false;

          //BEGIN BOARD CREATION
          System.out.print(GameBoard.print(SIZE, board));
          System.err.println(GameBoard.miniPrint(board));
          //END BOARD CREATION


          //BEGIN TURN
          if (computer)
          {
            if (compVsComp)
              computerTurn();
            else
            {
              if (move)
                playerTurn();
              else
                computerTurn();
            }
          }
          else
            playerTurn();

          move = !move;
          //END TURN
        }
      }
      //END GAME
      //move = !move;

      System.out.print(GameBoard.print(SIZE, board));

      //BEGIN FINAL STATEMENT

      countPieces();

      header = "Game over";
      if (turns == ((SIZE * SIZE) - 1) && !winner)
        JOptionPane.showMessageDialog(null, "Sorry... no one wins. :/", header + " - " + TITLE, 1);
      else if (move)
      {
        JOptionPane.showMessageDialog(null, name1 + " wins!", header + " - " + TITLE, 1);
        p1wins++;
      }
      else
      {
        JOptionPane.showMessageDialog(null, name2 + " wins!", header + " - " + TITLE, 1);
        p2wins++;
      }

      System.out.println("\n SCORE: \n" +
                         " " + mark1 + " | " + mark2 + "\n" +
                         "---+---");
      if (p1wins < 9)
        System.out.print(" " + p1wins + " ");
      else if (p1wins < 99)
        System.out.print(" " + p1wins);
      else
        System.out.print(p1wins);
      System.out.print("|");
      if (p2wins < 9)
        System.out.print(" " + p2wins + " ");
      else if (p2wins < 99)
        System.out.print(" " + p2wins);
      else
        System.out.print(p2wins);
      System.out.println("\n   |\n");

      if (!YesNoBox.bool("Would you like to play again?", TITLE, 3))
      {
        System.err.println("User opted to NOT play again");
        if (YesNoBox.bool("Would you like to save your score?", TITLE, 3))
        {
          System.err.println("User opted to save score");
          try
          {
            quit = false;

            if (named1 && named2)
            {
              System.err.println("Usernames have been entered previously, saving scores with " + name1 + " and " + name2 + ".");
              GameScore.tryScore(SAVEFILE, name1, p1wins, p2wins);
              GameScore.tryScore(SAVEFILE, name2, p2wins, p1wins);
            }

            while (!named1 && !quit)
            {
              System.err.print("PROMPT - What is P1's name? ");
              name1 = JOptionPane.showInputDialog("What is Player 1's username?");

              if (name1 == null)
              {
                named1 = false;
                System.err.println("\n\rPROMPT - P1 don't save score? ");
                quit = YesNoBox.bool("Are you sure you don't want to save your score?", "Quit? - " + TITLE, 0);
              }
              else
              {
                named1 = true;
                System.err.println(name1 + "\nSaving P1 score...");
                GameScore.tryScore(SAVEFILE, name1, p1wins, p2wins);
              }
            }

            quit = false;

            while (!named2 && !quit)
            {
              System.err.print("PROMPT - What is P2's name? ");
              name2 = JOptionPane.showInputDialog("What is Player 2's username?");

              if (name2 == null)
              {
                named2 = false;
                System.err.println("\n\rPROMPT - P2 don't save score?");
                quit = YesNoBox.bool("Are you sure you don't want to save your score?", "Quit? - " + TITLE, 0);
              }
              else
              {
                named2 = true;
                System.err.println(name2 + "\nSaving P2 score...");
                GameScore.tryScore(SAVEFILE, name2, p2wins, p1wins);
              }
            }
          }
          catch(java.io.IOException ex)
          {
            System.err.println("No file named \"" + SAVEFILE + "\" in \"" + System.getProperty("user.dir") + "\".");
          }
        }
        else
        {
          System.err.println("User opted to NOT save score");
        }
        if (YesNoBox.bool("Would you like to see the scoreboard?", TITLE, 3))
        {
          System.err.println("User opted to see the scoreboard");
          try
          {
            System.out.println(GameScore.topScores(SAVEFILE, 5));
            System.err.println(GameScore.topScores(SAVEFILE, 5));
            JOptionPane.showMessageDialog(null, GameScore.topScores(SAVEFILE, 5) + "\n\nPress OK to end the program.", "Scoreboard - " + TITLE, -1);
          }
          catch(java.io.IOException ex)
          {
            System.err.println("No file named \"" + SAVEFILE + "\" in \"" + System.getProperty("user.dir") + "\".");
            JOptionPane.showMessageDialog(null, "Could not find the save file. Are you sure you downloaded and extracted all the files?", "404 - " + TITLE, 0);
          }
        }
        else
          System.err.println("User opted to NOT see the scoreboard");

        break;
      }
      else
      {
        System.err.println("User opted to play again");
        quit = false;
      }

      winner = false;

      //END FINAL STATEMENT
    }
    System.err.println("Program terminated regularly");
    System.out.println("Program Teminated. Closing...");
    quit = false;
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////USEFUL METHODS////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  private static void countPieces()
  {//*TC*/System.err.println("BEGIN countPieces()");
    pieces1 = 0;
    pieces2 = 0;
    for (int i=0; i < SIZE; i++)
    {
      //TC//System.out.println(GameBoard.miniPrint(board));

      for (int j=0; j < SIZE; j++)
      {
        if (board[i][j] == mark1 || board[i][j] == mark1 - 32)
          pieces1++;
        else if (board[i][j] == mark2 || board[i][j] == mark2 - 32)
          pieces2++;
        //TC//System.out.println(location(i, j) + ": (" + board[i][j] + " == " + mark1 + " || " + board[i][j] + " == " + (mark1 - 32) + ") ? (" + (board[i][j] == mark1) + " || " + (board[i][j] == mark1 - 32) + "), pieces1 = " + pieces1);
        //TC//System.out.println(location(i, j) + ": (" + board[i][j] + " == " + mark2 + " || " + board[i][j] + " == " + (mark2 - 32) + ") ? (" + (board[i][j] == mark2) + " || " + (board[i][j] == mark2 - 32) + "), pieces2 = " + pieces2 + "\n");
      }
    }

    if (pieces1 == 0)
    {
      winner = true;
      turnOver = true;
      move = false;
    }
    else if (pieces2 == 0)
    {
      winner = true;
      turnOver = true;
      move = true;
    }
    //*TC*/System.err.println("END countPieces(" + pieces1 + ", " + pieces2 + ")");
  }

  private static String location()
  {//*TC*/System.err.println("BEGIN location()");
    //*TC*/System.err.println("END location(" + ((char)(from2 + 65) + "-" + (from1 + 1)) + ")");
    return (char)(from2 + 65) + "-" + (from1 + 1);
  }
  private static String location(int f1, int f2)
  {//*TC*/System.err.println("BEGIN location(" + f1 + ", " + f2 + ")");
    //*TC*/System.err.println("END location()");
    return (char)(f2 + 65) + "-" + (f1 + 1);
  }

  private static boolean isJumpable()
  {//*TC*/System.out.println("BEGIN isJumpable()");
    if ((to1 == from1 + 2 && to2 == from2 + 2) ||
        (to1 == from1 + 2 && to2 == from2 - 2) ||
        (to1 == from1 - 2 && to2 == from2 + 2) ||
        (to1 == from1 - 2 && to2 == from2 - 2))
    {
      if (board[(to1 + from1) / 2][(to2 + from2) / 2] == notMark && board[to1][to2] == EMPTY)
      {
        //*TC*/System.out.println("END isJumpable(true)");
        return true;
      }
      if (board[(to1 + from1) / 2][(to2 + from2) / 2] == (notMark - 32) && board[to1][to2] == EMPTY)
      {
        //*TC*/System.out.println("END isJumpable(true)");
        return true;
      }
    }
    //*TC*/System.out.println("END isJumpable(false)");
    return false;
  }

  private static void doubleJump()
  {//*TC*/System.out.println("BEGIN doubleJump()");
    System.out.print(GameBoard.print(SIZE, board));

    //*TC*/System.err.println("PROMPT - another jump?");
    if (YesNoBox.bool("You can make another jump! Would you like to do that?", header + " - " + TITLE, 3))
    {

      from1 = to1;
      from2 = to2;
      while (!turnOver)
      {
        //*TC*/System.err.println("PROMPT - move to where?");
        spareStr = JOptionPane.showInputDialog(null, error + "To where do you want to move the \"" + board[from1][from2] + "\" at " + location() + "?", header + " - " + TITLE, 3);
        if (spareStr == null)
        {
          //*TC*/System.err.println("PROMPT - end turn?");
          turnOver = YesNoBox.bool("Are you sure you want to end your turn?", "End turn? - " + TITLE, 0);
        }
        else if (spareStr.length() < 2)
        {
          //*TC*/System.err.println("PROMPT - incorrect format");
          JOptionPane.showMessageDialog(null, "I'm sorry, but that is the incorrect format.  be sure that it is typed like this: \"LETTER NUMBER\", with only a space between them (Example: \"A 1\"). Try placing your \"" + mark + "\" again.", "Error - " + TITLE, 0);
        }
        else
        {
          for (int i=0; i < spareStr.length(); i++)
          {
            if (spareStr.charAt(i) == ' ')
              delimHit = true;
            else
              delimHit = false;

            if (!delimHit)
            {
              if (spareStr.charAt(i) >= '0' && spareStr.charAt(i) <= '9')
                to1 = Integer.parseInt(spareStr.charAt(i) + "") - 1;
              else if (spareStr.charAt(i) >= 'a' && spareStr.charAt(i) <= 'z')
                to2 = (int)spareStr.charAt(i) - 97;
              else if (spareStr.charAt(i) >= 'A' && spareStr.charAt(i) <= 'Z')
                to2 = (int)spareStr.charAt(i) - 65;
            }
          }
          if (to1 < 0 || to1 > SIZE || to2 < 0 || to2 > SIZE)
          {
            //*TC*/System.err.println("PROMPT - don't go outside");
            JOptionPane.showMessageDialog(null, "Can't move to a space outside the board", "Illegal move! - " + TITLE, 0);
          }
          //System.err.println("((" + move + " && (" + (to1 > from1) + ")) || (" + !move + " && (" + (to1 < from1) + "))) && !(" + (board[from1][to1] >= 'A') + " && " + (board[from1][to1] <= 'Z') + ")");
          else if (((move && (to1 > from1)) || (!move && (to1 < from1))) && !isKing())
          {
            //*TC*/System.err.println("PROMPT - need  a king");
            JOptionPane.showMessageDialog(null, "Can't move backwards without a king", "Illegal move! - " + TITLE, 0);
          }
          else if (to1 > SIZE || to2 > SIZE || to1 < 0 || to2 < 0 || (!move && to1 != from1 + 1) || (move && to1 != from1 - 1) || (to2 != from2 + 1 && to2 != from2 - 1))
          {
            if (isJumpable())
            {
              //*TC*/System.err.println("PROMPT - " + notName + "'s " + board[(to1 + from1) / 2][(to2 + from2) / 2] + " taken");
              JOptionPane.showMessageDialog(null, "You've taken " + notName + "'s \"" + board[(to1 + from1) / 2][(to2 + from2) / 2] + "\"!", header + " - " + TITLE, 2);
              board[to1][to2] = board[from1][from2];
              board[from1][from2] = EMPTY;
              board[(to1 + from1) / 2][(to2 + from2) / 2] = EMPTY;

              from1 = to1;
              from2 = to2;

              notPiece--;

              if (notPiece == 0)
                winner = true;
              //TC//System.out.print("checkKing()");
              checkKing();

              //TC//System.out.print("isDoubleJumpable()");
              if (isDoubleJumpable())
                doubleJump();
              else
                turnOver = true;
            }
            else
            {
              //*TC*/System.err.println("PROMPT - you can't place your \"" + mark + "\" there.");
              JOptionPane.showMessageDialog(null, "I'm sorry, but you can't place it there. Try finding somewhere else to place your \"" + board[from1][from2] + "\".\nIf you're sure that you can place your \"" + board[from1][from2] + "\" at " + spareStr + ", be sure that it is typed like this: \"Letter Number\", with only a space between them (Example: \"A 1\").", "Error - " + TITLE, 0);
            }
          }
          else if (board[to1][to2] != EMPTY)
          {
            //*TC*/System.err.println("PROMPT - already a piece there");
            JOptionPane.showMessageDialog(null, "I'm sorry, but there's already a \"" + board[to1][to2] + "\" there. Try finding somewhere else to place this piece, or choose a new " + color + " piece to move.", "Illegal move! - " + TITLE, 0);
          }
          else
          {
            //*TC*/System.err.println("PROMPT - jump or end");
            JOptionPane.showMessageDialog(null, "I'm sorry, but you must jump a piece or end your turn.", "Illegal move! - " + TITLE, 0);
          }
        }
      }
    }
    else
      turnOver = true;
    //*TC*/System.out.println("END doubleJump()");
  }

  private static boolean isDoubleJumpable()
  {//*TC*/System.out.println("BEGIN isDoubleJumpable()");
    //*TC*/System.out.println("(((" + move + " || " + isKing2() + ") && " + (to1 + 2 <= SIZE) + ") || ((" + !move + " || " + isKing2() + ") && " + (to1 - 2 >= 0) + "))");// &&\n" +
//                       (to2 + 2 <= SIZE) + " && " + (to2 - 2 >= 0));
    //*TC*/if((((move || isKing2()) && to1 + 2 <= SIZE ) || ((!move || isKing()) && to1 - 2 >= 0)));// &&
//       to2 + 2 <= SIZE && to2 - 2 >= 0)
    {
      try
      {
        //System.out.println ("((" + !move + " || " + isKing() + ") && " + (board[to1 + 1][to2 + 1] == notMark) + " && " + (board[to1 + 2][to2 + 2] == EMPTY));
        if ((!move || isKing2()) && board[to1 + 1][to2 + 1] == notMark && board[to1 + 2][to2 + 2] == EMPTY)
        {
          doubleJumping = true;
          //*TC*/System.out.println("END doubleJumpable(true)");
          return true;
        }
      }
      catch (ArrayIndexOutOfBoundsException ex)
      {System.err.println("ArrayIndexOutOfBoundsException");
      }
      try
      {
        //System.out.println ("((" + move + " || " + isKing() + ") && " + (board[to1 - 1][to2 + 1] == notMark) + " && " + (board[to1 - 2][to2 + 2] == EMPTY));
        if ((move || isKing2()) && board[to1 - 1][to2 + 1] == notMark && board[to1 - 2][to2 + 2] == EMPTY)
        {
          doubleJumping = true;
          //*TC*/System.out.println("END doubleJumpable(true)");
          return true;
        }
      }
      catch (ArrayIndexOutOfBoundsException ex)
      {System.err.println("ArrayIndexOutOfBoundsException");
      }
      try
      {
        //System.out.println ("((" + !move + " || " + isKing() + ") && " + (board[to1 + 1][to2 - 1] == notMark) + " && " + (board[to1 + 2][to2 - 2] == EMPTY));
        if ((!move || isKing2()) && board[to1 + 1][to2 - 1] == notMark && board[to1 + 2][to2 - 2] == EMPTY)
        {
          doubleJumping = true;
          //*TC*/System.out.println("END doubleJumpable(true)");
          return true;
        }
      }
      catch (ArrayIndexOutOfBoundsException ex)
      {System.err.println("ArrayIndexOutOfBoundsException");
      }
      try
      {
        //System.out.println ("((" + !move + " || " + isKing() + ") && " + (board[to1 - 1][to2 - 1] == notMark) + " && " + (board[to1 - 2][to2 - 2] == EMPTY));
        if ((move || isKing2()) && board[to1 - 1][to2 - 1] == notMark && board[to1 - 2][to2 - 2] == EMPTY)
        {
          doubleJumping = true;
          //*TC*/System.out.println("END doubleJumpable(true)");
          return true;
        }
      }
      catch (ArrayIndexOutOfBoundsException ex)
      {System.err.println("ArrayIndexOutOfBoundsException");
      }
    }

    doubleJumping = false;

    //*TC*/System.out.println("END isDoubleJumpable(false)");
    return false;
  }

  private static void checkKing()
  {//*TC*/System.out.println("BEGIN checkKing()");
    if ((to1 == 0 || to1 == SIZE-1) && (board[to1][to2] >= 'a' && board[to1][to2] <= 'z'))
    {
      board[to1][to2] = (char)(mark - 32);
      //*TC*/System.err.println("PROMPT - kinged");
      JOptionPane.showMessageDialog(null, name + " has been kinged! (kings are represented by capital letters)", "Kinged! - " + TITLE, 2);

      if (isDoubleJumpable())
        doubleJump();
    }
    //*TC*/System.out.println("END checkKing()");
  }

  private static boolean isKing()
  {//*TC*/System.out.println("BEGIN isKing()");
    if (board[from1][from2] >= 'A' && board[from1][from2] <= 'Z')
    {//*TC*/System.out.println("END isKing(true)");
      return true;
    }
    //*TC*/System.out.println("END isKing(false)");
    return false;
  }
  private static boolean isKing(int f1, int f2)
  {//*TC*/System.out.println("BEGIN isKing()");
    if (board[f1][f2] >= 'A' && board[f1][f2] <= 'Z')
    {//*TC*/System.out.println("END isKing(true)");
      return true;
    }
    //*TC*/System.out.println("END isKing(false)");
    return false;
  }

  private static boolean isKing2()
  {//*TC*/System.out.println("BEGIN isKing2()");
    if (board[to1][to2] >= 'A' && board[to1][to2] <= 'Z')
    {//*TC*/System.out.println("END isKing2(true)");
      return true;
    }
    //*TC*/System.out.println("END isKing2(false)");
    return false;
  }

  private static void setup()
  {//*TC*/System.out.println("BEGIN setup()");
    JOptionPane.showMessageDialog(null, "Welcome to " + TITLE + ". Please follow the instructions and enjoy your game.", TITLE + " " + VERSION, -1);

    while (!quit)
    {
      try
      {
        System.err.print("PROMPT - How many playing? ");
        spareStr = JOptionPane.showInputDialog(null, "How many people are going to be playing?", TITLE, 1);
        if (spareStr == null)
        {
          System.err.println("\n\rPROMPT - Quit? ");
          if (YesNoBox.bool("Are you sure you want to quit?", "Quit - " + TITLE, 0))
          {
            System.err.println("User opted to quit before inputing number of players. ");
            quit = true;
          }
        }
        else
        {
          numOfPlayers = Integer.parseInt(spareStr);
          if (numOfPlayers > 2 || numOfPlayers < 0)
            JOptionPane.showMessageDialog(null, "Checkers is a 2-player game. Please input the number of human players.", "User error - " + TITLE, 0);
          else if (YesNoBox.bool("There will be " + numOfPlayers + " human player" + (numOfPlayers == 1 ? "" : "s") + " and " + (2 - numOfPlayers) + " computer player" + (numOfPlayers == 1 ? "" : "s") + ".\n Is this okay?", TITLE, 3))
          {
            named1 = true;
            named2 = true;
            if (numOfPlayers == 0)
              compVsComp = true;
            if (numOfPlayers <= 1)
              computer = true;
            break;
          }
        }
      }
      catch (NumberFormatException ex)
      {
        JOptionPane.showMessageDialog(null, "Checkers is a 2-player game. Please input the number of human players as an integer.", "User error - " + TITLE, 0);
        System.err.println("User did not input an integer when asked how many people will play");
      }
    }

    System.err.println("User opted to have " + numOfPlayers + " human players");

    if (!compVsComp)
    {
      while (!quit)
      {
        System.err.print("PROMPT - Player 1 username? ");
        name1 = JOptionPane.showInputDialog(null, "Would you like a username, Player 1? (leave blank to be called " + name1 + ")", TITLE, 3);
        if (name1 == null)
        {
          quit = YesNoBox.bool("Are you sure you want to quit?", TITLE, 0);
          if (quit)
          {
            System.err.println("\n\rUser opted to quit at the Player 1 username input screen");
            break;
          }
        }
        else if (name1.equals(""))
        {
          name1 = System.getProperty("user.name");
          break;
        }
        else
        {
          named1 = true;
          break;
        }
      }

      System.err.println("User opted to name player 1 " + name1);

      while (!quit)
      {
        System.err.print("PROMPT - Player 1 color? ");
        color1 = JOptionPane.showInputDialog(null, "What is your color, " + name1 + "? (leave blank for default)", TITLE, 3);
        if (color1 == null)
        {
          quit = YesNoBox.bool("Are you sure you want to quit?", TITLE, 0);
          if (quit)
            break;
        }
        else if (color1.equals(""))
        {
          color1 = "black";
          break;
        }
        else if ((color1 != null && !color1.equals("") && color1.length() != 0) && !(color1.charAt(0) >= 'A' && (color1.charAt(0) <= 'Z')) && !(color1.charAt(0) >= 'a' && (color1.charAt(0) <= 'z')))
        {
          JOptionPane.showMessageDialog(null, "Please type a color that starts with a letter. (As far as I know, that's all of them)", "Error - " + TITLE, 0);
        }
        else
          break;

        if (color1 != null && !color1.equals("") && color1.length() != 0)
          mark1 = color1.charAt(0);

        if (mark1 >= 'A' && mark1 <= 'Z')
          mark1 = (char)((int)mark1 + 32);
      }

      System.err.println("User opted to set player 1's color set to " + color1 + " (" + mark1 + ").");//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

      if (!computer && !compVsComp)
      {
        while (!quit)
        {
          name2 = JOptionPane.showInputDialog(null, "Would you like a username, Player 2? (leave blank to be called " + name2 + ")", TITLE, 3);
          if (name2 == null)
          {
            quit = YesNoBox.bool("Are you sure you want to quit?", "Quit? - " + TITLE, 0);
            if (quit)
              break;
          }
          else if (name2.equalsIgnoreCase(name1))
          {
            JOptionPane.showMessageDialog(null, "Player 1's username is already " + name1 + ". Please choose a different one.", "Error - " + TITLE, 0);
          }
          else if (name2.equals(""))
          {
            name2 = "Player 2";
            break;
          }
          else
          {
            named2 = true;
            break;
          }
        }

        while (!quit)
        {
          color2 = JOptionPane.showInputDialog(null, "What is your color, " + name2 + "? (leave blank for default)", TITLE, 3);
          if (color2 == null)
          {
            quit = YesNoBox.bool("Are you sure you want to quit?", "Quit? - " + TITLE, 0);
            if (quit)
              break;
          }
          else if ((color2 != null && color2 != "" && color2.length() != 0) && !(color2.charAt(0) >= 'A' && (color2.charAt(0) <= 'Z')) && !(color2.charAt(0) >= 'a' && (color2.charAt(0) <= 'z')))
          {
            JOptionPane.showMessageDialog(null, "Please type a color that starts with a letter. (As far as I know, that's all of them)", "Error - " + TITLE, 0);
          }
          else if ((color2.equals("") && mark2 != mark1) || color2 == null || color2.length() == 0)
          {
            if (color1.equalsIgnoreCase("red"))
              color2 = "black";
            else
              color2 = "red";
            break;
          }
          else
          {
            if (color2.charAt(0) == mark1 || color2.charAt(0) == mark1 - 32)
              JOptionPane.showMessageDialog(null, name1 + "'s color starts with \"" + mark1 + "\". Please choose a color that starts\nwith a different letter.", "Error - " + TITLE, 0);
            else
              break;
          }
        }
        if (color2 != null && !color2.equals("") && color2.length() != 0)
          mark2 = color2.charAt(0);

        if (mark2 >= 'A' && mark2 <= 'Z')
          mark2 = (char)(mark2 + 32);
        //System.out.println ("Player 2's mark set to " + mark2 + ".");///////////////////////////////////////////////////TESTING CODE
      }
      else
      {
        name2 = (compVsComp ? "Computer 2" : "the computer");
        color2 = "red";
        mark2 = 'r';
      }
    }
    else
    {
      name1 = "Computer 1";
      color1 = "black";
      mark1 = 'b';
    }
    //*TC*/System.out.println("END setup()");
  }

  public static void playerTurn()
  {//*TC*/System.out.println("BEGIN playerTurn()");
    //System.out.println("move is " + move);
    if (move)
    {
      color = color1;
      mark = mark1;
      name = name1;
      notName = name2;
      notColor = color2;
      notMark = mark2;
      notPiece = pieces2;
      piece = pieces1;
    }
    else
    {
      color = color2;
      mark = mark2;
      name = name2;
      notName = name1;
      notColor = color1;
      notMark = mark1;
      notPiece = pieces1;
      piece = pieces2;
    }


    error = "";
    from1 = 0;
    from2 = 0;
    header = name + "'s turn";
    spareStr = "";
    to1 = 0;
    to2 = 0;
    turnOver = false;
    where = 32;

    while (!turnOver)
    {//*TC*/System.out.println(4);///////////////////////////////////////////////////////////////////////////////////TESTING CODE
      //*TC*/System.err.println("PROMPT - move from where?");
      spareStr = JOptionPane.showInputDialog(null, error + "Which " + color + " piece are you going to move, " + name + "?", header + " - " + TITLE, 3);
      try
      {
        if (spareStr == null)
        {
          //*TC*/System.err.println("PROMPT - forfeit?");
          quit = YesNoBox.bool("Are you sure you want to forfeit? (" + notName + " will win)", "End game? - " + TITLE, 0);
          turnOver = quit;
        }
        else if (spareStr.length() < 2)
        {
          //*TC*/System.err.println("PROMPT - incorrect format");
          JOptionPane.showMessageDialog(null, "I'm sorry, but that is the incorrect format.  be sure that it is typed like this: \"LETTER NUMBER\", with only a space between them (Example: \"A 1\"). Try placing your " + color + " piece again.", "Error - " + TITLE, 0);
        }
        else
        {
          for (int i=0; i < spareStr.length(); i++)
          {
            if (spareStr.charAt(i) >= '0' && spareStr.charAt(i) <= '9'&& spareStr.charAt(i) >= 'a' && spareStr.charAt(i) <= 'z' && spareStr.charAt(i) >= 'A' && spareStr.charAt(i) <= 'Z')
              delimHit = true;
            else
              delimHit = false;

            if (!delimHit)
            {
              if (spareStr.charAt(i) >= '0' && spareStr.charAt(i) <= '9')
                from1 = Integer.parseInt(spareStr.charAt(i) + "") - 1;
              else if (spareStr.charAt(i) >= 'a' && spareStr.charAt(i) <= 'z')
                from2 = (int)spareStr.charAt(i) - 97;
              else if (spareStr.charAt(i) >= 'A' && spareStr.charAt(i) <= 'Z')
                from2 = (int)spareStr.charAt(i) - 65;
            }
          }
          if (!(board[from1][from2] == mark || board[from1][from2] == (char)(mark - 32)))
          {
            //*TC*/System.err.println("PROMPT - there is not a \"" + mark + "\" at " + spareStr);
            JOptionPane.showMessageDialog(null, "I'm sorry, but there is not a " + color + " piece at " + spareStr + ". Please choose a square with a " + color + " piece.", "Error - " + TITLE, 0);
          }
          else
          {
            //*TC*/System.err.println("PROMPT - move to where?");
            spareStr = JOptionPane.showInputDialog(null, error + "To where do you want to move the \"" + board[from1][from2] + "\" at " + location() + "?", header + " - " + TITLE, 3);
            if (spareStr == null)
            {
              //*TC*/System.err.println("PROMPT - forfeit?");
              quit = YesNoBox.bool("Are you sure you want to forfeit? (" + notName + " will win)", "End game? - " + TITLE, 0);
              turnOver = quit;
            }
            else if (spareStr.length() < 2)
            {
              //*TC*/System.err.println("PROMPT - incorrect format");
              JOptionPane.showMessageDialog(null, "I'm sorry, but that is the incorrect format.  be sure that it is typed like this: \"LETTER NUMBER\", with only a space between them (Example: \"A 1\"). Try placing your \"" + mark + "\" again.", "Error - " + TITLE, 0);
            }
            else
            {
              for (int i=0; i < spareStr.length(); i++)
              {
                if (spareStr.charAt(i) == ' ')
                  delimHit = true;
                else
                  delimHit = false;

                if (!delimHit)
                {
                  if (spareStr.charAt(i) >= '0' && spareStr.charAt(i) <= '9')
                    to1 = Integer.parseInt(spareStr.charAt(i) + "") - 1;
                  else if (spareStr.charAt(i) >= 'a' && spareStr.charAt(i) <= 'z')
                    to2 = (int)spareStr.charAt(i) - 97;
                  else if (spareStr.charAt(i) >= 'A' && spareStr.charAt(i) <= 'Z')
                    to2 = (int)spareStr.charAt(i) - 65;
                }
              }
              if (to1 < 0 || to1 > SIZE || to2 < 0 || to2 > SIZE)
              {
                //*TC*/System.err.println("PROMPT - don't go outside");
                JOptionPane.showMessageDialog(null, "Can't move to a space outside the board", "Illegal move! - " + TITLE, 0);
              }
              //System.err.println("((" + move + " && (" + (to1 > from1) + ")) || (" + !move + " && (" + (to1 < from1) + "))) && !(" + (board[from1][to1] >= 'A') + " && " + (board[from1][to1] <= 'Z') + ")");
              else if (((move && (to1 > from1)) || (!move && (to1 < from1))) && !isKing())
              {
                //*TC*/System.err.println("PROMPT - need a king");
                JOptionPane.showMessageDialog(null, "Can't move backwards without a king", "Illegal move! - " + TITLE, 0);
              }
              else if (to1 > SIZE || to2 > SIZE || to1 < 0 || to2 < 0 || (!move && to1 != from1 + 1) || (move && to1 != from1 - 1) || (to2 != from2 + 1 && to2 != from2 - 1))
              {
                if (isJumpable())
                {
                  //*TC*/System.err.println("PROMPT - " + notName + "'s " + board[(to1 + from1) / 2][(to2 + from2) / 2] + " taken");
                  JOptionPane.showMessageDialog(null, "You've taken " + notName + "'s \"" + board[(to1 + from1) / 2][(to2 + from2) / 2] + "\"!", header + " - " + TITLE, 2);
                  board[to1][to2] = board[from1][from2];
                  board[from1][from2] = EMPTY;
                  board[(to1 + from1) / 2][(to2 + from2) / 2] = EMPTY;

                  notPiece--;

                  if (notPiece == 0)
                    winner = true;
                  //*TC*/System.out.print("checkKing()");
                  checkKing();

                  System.out.print(GameBoard.print(SIZE, board));
                  //*TC*/System.out.print("isDoubleJumpable()");
                  if (isDoubleJumpable())
                    doubleJump();
                  else
                    turnOver = true;
                }
                else if(isKing() &&
                        (Math.abs(to1 - from1) == 1 &&
                         Math.abs(to2 - from2) == 1))
                {
                  board[to1][to2] = board[from1][from2];
                  board[from1][from2] = EMPTY;
                  //*TC*/System.out.println("King-only move made");
                  break;
                }
                else
                {
                  //*TC*/System.err.println("PROMPT - can't place there");
                  JOptionPane.showMessageDialog(null, "I'm sorry, but you can't place it there. Try finding somewhere else to place your \"" + board[from1][from2] + "\".\nIf you're sure that you can place your \"" + board[from1][from2] + "\" at " + spareStr + ", be sure that it is typed like this: \"Letter Number\", with only a space between them (Example: \"A 1\").", "Error - " + TITLE, 0);
                }
              }
              else if (board[to1][to2] != EMPTY)
              {
                //*TC*/System.err.println("PROMPT - already a piece there");
                JOptionPane.showMessageDialog(null, "I'm sorry, but there's already a \"" + board[to1][to2] + "\" there. Try finding somewhere else to place this piece, or choose a new " + color + " piece to move.", "Illegal move! - " + TITLE, 0);
              }
              else if (Math.abs(to1 - from1) != 1 ||
                       Math.abs(to2 - from2) != 1)
              {
                //*TC*/System.err.println("PROMPT - move diagonally");
                JOptionPane.showMessageDialog(null, "I'm sorry, but you must move diagonally. Try finding somewhere else to place this piece, or choose a new " + color + " piece to move.", "Illegal move! - " + TITLE, 0);
              }
              else
              {
                board[to1][to2] = board[from1][from2];
                board[from1][from2] = EMPTY;
                checkKing();
                break;
              }
            }
          }
        }
      }
      catch (ArrayIndexOutOfBoundsException ex)
      {System.err.println("ArrayIndexOutOfBoundsException");
        //*TC*/System.err.println("PROMPT - can't move outside");
        JOptionPane.showMessageDialog(null, "Can't move to or from a space outside the board", "Illegal move! - " + TITLE, 0);
      }
    }
    if (move)
    {
      pieces1 = piece;
      pieces2 = notPiece;
    }
    else
    {
      pieces2 = piece;
      pieces1 = notPiece;
    }
    //*TC*/System.out.println("END playerTurn()");
  }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////COMPUTER TURN//////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  public static void computerTurn()
  {//*TC*/System.out.println("BEGIN computerTurn()");
    //JOptionPane.showMessageDialog(null, "The computer's turn will begin when you close this dialog.", name + "'s turn beginning - " + TITLE, 1);

    if (move)
    {
      mark = mark1;
      notMark = mark2;
      piece = pieces1;
      notPiece = pieces2;
      color = color1;
      notColor = color2;
      name = name1;
      notName = name2;
    }
    else
    {
      mark = mark2;
      notMark = mark1;
      piece = pieces2;
      notPiece = pieces1;
      color = color2;
      notColor = color1;
      name = name2;
      notName = name1;
    }

    from1 = 0;
    from2 = 0;
    to1 = 0;
    to2 = 0;
    turnOver = false;

    //*TC*/System.out.println("mark = " + mark + "\t" + "notMark = " + notMark + "\t" + "piece = " + piece + "\t" + "notPiece = " + notPiece + "\t" + "color = " + color + "\t" + "notColor = " + notColor + "\t" + "name = " + name + "\t" + "notName = " + notName + "\t");
    //*TC*/System.err.println("BEGIN computerTurn() prioritizing");
    for (int i=0; i < SIZE; i++)
    {
      for (int j=0; j < SIZE; j++)
      {
        from1 = i;
        from2 = j;

        if (isMyPiece())
        {
          priority[i][j] = 1;

          if (isMovable())
          {
            priority[i][j]++;

            if (compJumpable())
            {
              priority[i][j]++;

              if (isDoubleJumpable())
              {
                priority[i][j]++;
              }
            }
          }
          else
          {
            priority[i][j] = 0;
          }

          if (isInDanger())
            priority[i][j] = -1;
        }
        else
          priority[i][j] = 0;
      }
    }
    //*TC*/System.err.println("END computerTurn() prioritizing");
    //*TC*/System.out.print(GameBoard.print(SIZE, priority));
    //*TC*/System.err.println("BEGIN computerTurn() move-making");
    for (int i=0; i < SIZE && !turnOver; i++)
    {
      for (int j=0; j < SIZE && !turnOver; j++)
      {
        if (priority[i][j] >= 3)
        {
          from1 = i;
          from2 = j;
          compMove();
        }
      }
    }
    for (int i=0; i < SIZE && !turnOver; i++)
    {
      for (int j=0; j < SIZE && !turnOver; j++)
      {
        if (priority[i][j] == -2)
        {
          from1 = i;
          from2 = j;
          compDefend();
        }
      }
    }
    for (int i=0; i < SIZE && !turnOver; i++)
    {
      for (int j=0; j < SIZE && !turnOver; j++)
      {
        if (priority[i][j] == 2)
        {
          from1 = i;
          from2 = j;
          compMove();
        }
      }
    }
    for (int i=0; i < SIZE && !turnOver; i++)
    {
      for (int j=0; j < SIZE && !turnOver; j++)
      {
        if (priority[i][j] == -1)
        {
          from1 = i;
          from2 = j;
          compDefend();
        }
      }
    }
    for (int i=0; i < SIZE && !turnOver; i++)
    {
      for (int j=0; j < SIZE && !turnOver; j++)
      {
        if (priority[i][j] == 1)
        {
          from1 = i;
          from2 = j;
          compMove();
        }
      }
    }

    for (int i=0; i < SIZE && !turnOver; i++)
    {
      for (int j=0; j < SIZE && !turnOver; j++)
      {
        if (priority[i][j] != 0)
        {
          from1 = i;
          from2 = j;
          compForceMove();
        }
      }
    }

    checkKing();

    //*TC*/System.err.println("END computerTurn() move-making");
    if (!turnOver)
      JOptionPane.showMessageDialog(null, name + " gives up! " + notName + " wins!", notName + " wins! - " + TITLE, 2);

    turnOver = true;
    ///*TC*/JOptionPane.showMessageDialog(null, "computerTurn() method in Checkers has not yet been written.\nThis is the END.", "Beta vesion (" + VERSION + ") incomplete - " + TITLE, 0);
    //*TC*/System.out.println("END computerTurn()");
  }

  private static boolean isMyPiece()
  {//*TC*/System.out.println("BEGIN isMyPiece(" + location() + ")");
    //*TC*/System.out.println("END isMyPiece(" + ((board[from1][from2] == mark) || (board[from1][from2] == (char)(mark - 32))) + ")");
    return (board[from1][from2] == mark) || (board[from1][from2] == (char)(mark - 32));
  }

  private static boolean isMovable()
  {//*TC*/System.out.println("BEGIN isMovable(" + location() + ")");

    if ((from1 + 1 < SIZE && from2 + 1 < SIZE && board[from1 + 1][from2 + 1] == EMPTY) || (from1 - 1 >= 0 && from2 + 1 < SIZE && board[from1 - 1][from2 + 1] == EMPTY) || (from1 + 1 < SIZE && from2 - 1 >= 0 && board[from1 + 1][from2 - 1] == EMPTY) || (from1 - 1 >= 0 && from2 - 1 >= 0 && board[from1 - 1][from2 - 1] == EMPTY))
    {
      //*TC*/System.out.println("END isMovable(true 1)");
      return true;
    }

    if ((from1 + 1 < SIZE && from2 + 1 < SIZE) && (board[from1 + 1][from2 + 1] != EMPTY))
    {
      if ((from1 + 2 < SIZE && from2 + 2 < SIZE) && (board[from1 + 2][from2 + 2] == EMPTY && board[from1 + 1][from2 + 1] == notMark))
      {
        //*TC*/System.out.println("END isMovable(true 2)");
        return true;
      }
    }
    else if ((from2 + 1 < SIZE && from1 - 1 >= 0) && (board[from1 - 1][from2 + 1] != EMPTY))
    {
      if ((from2 + 2 < SIZE && from1 - 2 >= 0) && (board[from1 - 2][from2 + 2] == EMPTY && board[from1 - 1][from2 + 1] == notMark))
      {
        //*TC*/System.out.println("END isMovable(true 3)");
        return true;
      }
    }
    else if ((from1 + 1 < SIZE && from2 - 1 >= 0) && (board[from1 + 1][from2 - 1] != EMPTY))
    {
      if ((from2 + 2 < SIZE && from1 - 2 >= 0) && (board[from1 + 2][from2 - 2] == EMPTY && board[from1 + 1][from2 - 1] == notMark))
      {
        //*TC*/System.out.println("END isMovable(true 4)");
        return true;
      }
    }
    else if ((from1 - 1 < 0 && from2 - 1 >= 0) && (board[from1 - 1][from2 - 1] != EMPTY))
    {
      if ((from1 - 2 < 0 && from2 - 2 >= 0) && (board[from1 - 2][from2 - 2] == EMPTY && board[from1 - 1][from2 - 1] == notMark))
      {
        //*TC*/System.out.println("END isMovable(true 5)");
        return true;
      }
    }

    //*TC*/System.out.println("END isMovable(false)");
    return false;
  }
  private static boolean compJumpable()
  {//*TC*/System.out.println("BEGIN compJumpable(" + location() + ")");

    //*TC*/System.out.print("((" + (!move) + " || " + isKing() + ") && " + (from1 + 2 < SIZE) + " && " + (from2 + 2 < SIZE) + " && " + ((from1 + 2 < SIZE && from2 + 2 < SIZE) ? (board[from1 + 2][from2 + 2] == EMPTY) : "null") + " && " + ((from1 + 2 < SIZE && from2 + 2 < SIZE) ? (board[from1 + 1][from2 + 1] == notMark) : "null") + ") && !((" + (from1 + 3 < SIZE && from2 + 3 < SIZE) + ") && " + (from1 + 3 < SIZE && from2 + 3 < SIZE ? board[from1 + 3][from2 + 3] != notMark : "null") + ")) || ");
    //*TC*/System.out.print("((" + (!move) + " || " + isKing() + ") && " + (from1 + 2 < SIZE) + " && " + (from2 - 2 >= 0) + " && " + ((from1 + 2 < SIZE && from2 - 2 >= 0) ? (board[from1 + 2][from2 - 2] == EMPTY) : "null") + " && " + ((from1 + 2 < SIZE && from2 - 2 >= 0) ? (board[from1 + 1][from2 - 1] == notMark) : "null") + ") && !((" + (from1 + 3 < SIZE && from2 - 3 < SIZE) + ") && " + (from1 + 3 < SIZE && from2 - 3 >= 0 ? board[from1 + 3][from2 - 3] != notMark : "null") + ")) || ");
    //*TC*/System.out.print("((" + (move) + " || " + isKing() + ") && " + (from1 - 2 >= 0) + " && " + (from2 + 2 < SIZE) + " && " + ((from1 - 2 >= 0 && from2 + 2 < SIZE) ? (board[from1 - 2][from2 + 2] == EMPTY) : "null") + " && " + ((from1 - 2 >= 0 && from2 + 2 < SIZE) ? (board[from1 - 1][from2 + 1] == notMark) : "null") + ") && !((" + (from1 - 3 < SIZE && from2 + 3 < SIZE) + ") && " + (from1 - 3 >= 0 && from2 + 3 < SIZE ? board[from1 - 3][from2 + 3] != notMark : "null") + ")) || ");
    //*TC*/System.out.println("((" + (move) + " || " + isKing() + ") && " + (from1 - 2 >= 0) + " && " + (from2 - 2 >= 0) + " && " + ((from1 - 2 >= 0 && from2 - 2 >= 0) ? (board[from1 - 2][from2 - 2] == EMPTY) : "null") + " && " + ((from1 - 2 >= 0 && from2 - 2 >= 0) ? (board[from1 - 1][from2 - 1] == notMark) : "null") + ") && !((" + (from1 - 3 < SIZE && from2 - 3 < SIZE) + ") && " + (from1 - 3 >= 0 && from2 - 3 >= 0 ? board[from1 - 3][from2 - 3] != notMark : "null") + "))");

    if (((!move || isKing()) && from1 + 2 < SIZE && from2 + 2 < SIZE && board[from1 + 2][from2 + 2] == EMPTY && board[from1 + 1][from2 + 1] == notMark && !((from1 + 3 < SIZE && from2 + 3 < SIZE) && board[from1 + 3][from2 + 3] != notMark)) ||
        ((!move || isKing()) && from1 + 2 < SIZE && from2 - 2 >= 0 && board[from1 + 2][from2 - 2] == EMPTY   && board[from1 + 1][from2 - 1] == notMark && !((from1 + 3 < SIZE && from2 - 3 >= 0)   && board[from1 + 3][from2 - 3] != notMark)) ||
        ((move || isKing())  && from1 - 2 >= 0   && from2 + 2 < SIZE && board[from1 - 2][from2 + 2] == EMPTY && board[from1 - 1][from2 + 1] == notMark && !((from1 - 3 >= 0 && from2 + 3 < SIZE)   && board[from1 - 3][from2 + 3] != notMark)) ||
        ((move || isKing())  && from1 - 2 >= 0   && from2 - 2 >= 0 && board[from1 - 2][from2 - 2] == EMPTY   && board[from1 - 1][from2 - 1] == notMark && !((from1 - 3 >= 0 && from2 - 3 >= 0)     && board[from1 - 3][from2 - 3] != notMark)))
    {
      //*TC*/System.out.println("END compJumpable(true)");
      return true;
    }
    //*TC*/System.out.println("END compJumpable(false)");
    return false;
  }

  private static boolean isInDanger()
  {//*TC*/System.out.println("BEGIN isInDanger(" + location() + ")");
    if (!isOnEdge())
    {
      if ((from1 + 1 < SIZE && from2 + 1 < SIZE && from1 - 1 >= 0 && from2 - 1 >= 0) && ((board[from1 + 1][from2 + 1] == EMPTY && board[from1 - 1][from2 - 1] == notMark && (!move || isKing(from1 - 1, from2 - 1))) ||
                                                                                         (board[from1 + 1][from2 - 1] == EMPTY && board[from1 - 1][from2 + 1] == notMark && (!move || isKing(from1 - 1, from2 + 1))) ||
                                                                                         (board[from1 - 1][from2 + 1] == EMPTY && board[from1 + 1][from2 - 1] == notMark && ( move || isKing(from1 + 1, from2 - 1))) ||
                                                                                         (board[from1 - 1][from2 - 1] == EMPTY && board[from1 + 1][from2 + 1] == notMark && ( move || isKing(from1 + 1, from2 + 1)))))
      {
        //*TC*/System.out.println("END isInDanger(true)");
        return true;
      }
    }
    //*TC*/System.out.println("END isInDanger(false)");
    return false;
  }

  private static boolean seeDanger(int t1, int t2)
  {//*TC*/System.out.println("BEGIN seeDanger(" + location(t1, t2) + ")");
    //*TC*/System.out.println("((" + (t1 + 1 < SIZE) + " && " + (t2 + 1 < SIZE) + " && " + (t1 - 1 >= 0) + " && " + (t2 - 1 >= 0) + ") && ((" + (t1 + 1 < SIZE && t2 + 1 < SIZE ? board[t1 + 1][t2 + 1] == notMark : "null") + " && (" + (t1 - 1 == from1) + " && " + (t2 - 1 == from2) + ")) || (" + (t1 - 1 >= 0 && t2 - 1 >= 0 ? board[t1 - 1][t2 - 1] == notMark : "null") + " && (" + (t1 + 1 == from1) + " && " + (t2 + 1 == from2) + ")))) || ");
    //*TC*/System.out.println("((" + (t1 + 1 < SIZE) + " && " + (t2 - 1 >= 0) + " && " + (t1 - 1 >= 0) + " && " + (t2 + 1 < SIZE) + ") && ((" + (t1 + 1 < SIZE && t2 - 1 >= 0 ? board[t1 + 1][t2 - 1] == notMark : "null") + " && (" + (t1 - 1 == from1) + " && " + (t2 + 1 == from2) + ")) || (" + (t1 - 1 >= 0 && t2 + 1 < SIZE ? board[t1 - 1][t2 + 1] == notMark : "null") + " && (" + (t1 + 1 == from1) + " && " + (t2 - 1 == from2) + ")))) || ");
    //*TC*/System.out.println("((" + (t1 - 1 >= 0) + " && " + (t2 + 1 < SIZE) + " && " + (t1 + 1 < SIZE) + " && " + (t2 - 1 >= 0) + ") && ((" + (t1 - 1 >= 0 && t2 + 1 < SIZE ? board[t1 - 1][t2 + 1] == notMark : "null") + " && (" + (t1 + 1 == from1) + " && " + (t2 - 1 == from2) + ")) || (" + (t1 + 1 < SIZE && t2 - 1 >= 0 ? board[t1 + 1][t2 - 1] == notMark : "null") + " && (" + (t1 - 1 == from1) + " && " + (t2 + 1 == from2) + ")))) || ");
    //*TC*/System.out.println("((" + (t1 - 1 >= 0) + " && " + (t2 - 1 >= 0) + " && " + (t1 + 1 < SIZE) + " && " + (t2 + 1 < SIZE) + ") && ((" + (t1 - 1 >= 0 && t2 - 1 >= 0 ? board[t1 - 1][t2 - 1] == notMark : "null") + " && (" + (t1 + 1 == from1) + " && " + (t2 + 1 == from2) + ")) || (" + (t1 + 1 < SIZE && t2 + 1 < SIZE ? board[t1 + 1][t2 + 1] == notMark : "null") + " && (" + (t1 - 1 == from1) + " && " + (t2 - 1 == from2) + ")))) || ");
    if (((t1 + 1 < SIZE && t2 + 1 < SIZE && t1 - 1 >= 0   && t2 - 1 >= 0)   && ((board[t1 + 1][t2 + 1] == notMark && (t1 - 1 == from1 && t2 - 1 == from2)) || (board[t1 - 1][t2 - 1] == notMark && (t1 + 1 == from1 && t2 + 1 == from2)))) ||
        ((t1 + 1 < SIZE && t2 - 1 >= 0   && t1 - 1 >= 0   && t2 + 1 < SIZE) && ((board[t1 + 1][t2 - 1] == notMark && (t1 - 1 == from1 && t2 + 1 == from2)) || (board[t1 - 1][t2 + 1] == notMark && (t1 + 1 == from1 && t2 - 1 == from2)))) ||
        ((t1 - 1 >= 0   && t2 + 1 < SIZE && t1 + 1 < SIZE && t2 - 1 >= 0)   && ((board[t1 - 1][t2 + 1] == notMark && (t1 + 1 == from1 && t2 - 1 == from2)) || (board[t1 + 1][t2 - 1] == notMark && (t1 - 1 == from1 && t2 + 1 == from2)))) ||
        ((t1 - 1 >= 0   && t2 - 1 >= 0   && t1 + 1 < SIZE && t2 + 1 < SIZE) && ((board[t1 - 1][t2 - 1] == notMark && (t1 + 1 == from1 && t2 + 1 == from2)) || (board[t1 + 1][t2 + 1] == notMark && (t1 - 1 == from1 && t2 - 1 == from2)))))
    {
      //*TC*/System.out.println("END seeDanger(true)");
      return true;
    }

    //*TC*/System.out.println("END seeDanger(false)");
    return false;
  }

  private static boolean isOnEdge()
  {//*TC*/System.out.println("BEGIN isOnEdge(" + location() + ")");
    //*TC*/System.out.println("END isOnEdge(" + (from1 == 0 || from1 == SIZE || from2 == 0 || from2 == SIZE) + ")");
    return (from1 == 0 || from1 == SIZE || from2 == 0 || from2 == SIZE);
  }

  private static boolean isOccupied(int f1, int f2)
  {//*TC*/System.out.println("BEGIN isOccupied(" + location(f1, f2) + ")");
    if (f1 >= 0 && f1 < SIZE && f2 >= 0 && f2 < SIZE && board[f1][f2] != EMPTY)
    {
      //*TC*/System.out.println("END isOccupied(true)");
      return true;
    }
    //*TC*/System.out.println("END isOccupied(false)");
    return false;
  }

  private static boolean isOnBoard(int f1, int f2)
  {//*TC*/System.out.println("BEGIN isOccupied(" + location(f1, f2) + ")");
    if (f1 >= 0 && f1 < SIZE && f2 >= 0 && f2 < SIZE)
    {
      //*TC*/System.out.println("END isOccupied(true)");
      return true;
    }
    //*TC*/System.out.println("END isOccupied(false)");
    return false;
  }

  private static void compMove()
  {//*TC*/System.out.println("BEGIN compMove()");
    //*TC*/System.err.println("This is the part where the computer makes a regular move or attack move with (" + location() + ").");
    if (compJumpable())
    {
      if (isDoubleJumpable())
        compDoubleJump();
      else
        compJump();
    }
    else
    {
      if ((!move || isKing()) && !seeDanger(from1 + 1, from2 + 1) && !isOccupied(from1 + 1, from2 + 1) && isOnBoard(from1 + 1, from2 + 1))
      {
        to1 = from1 + 1;
        to2 = from2 + 1;
        board[to1][to2] = board[from1][from2];
        board[from1][from2] = EMPTY;

        turnOver = true;
        //*TC*/System.out.println("END compMove(" + location(from1, from2) + " to " + location(to1, to2) + ")");
        return;
      }
      if ((!move || isKing()) && !seeDanger(from1 + 1, from2 - 1) && !isOccupied(from1 + 1, from2 - 1) && isOnBoard(from1 + 1, from2 - 1))
      {
        to1 = from1 + 1;
        to2 = from2 - 1;
        board[to1][to2] = board[from1][from2];
        board[from1][from2] = EMPTY;

        turnOver = true;
        //*TC*/System.out.println("END compMove(" + location(from1, from2) + " to " + location(to1, to2) + ")");
        return;
      }
      if ((move || isKing()) && !seeDanger(from1 - 1, from2 + 1) && !isOccupied(from1 - 1, from2 + 1) && isOnBoard(from1 - 1, from2 + 1))
      {
        to1 = from1 - 1;
        to2 = from2 + 1;
        board[to1][to2] = board[from1][from2];
        board[from1][from2] = EMPTY;

        turnOver = true;
        //*TC*/System.out.println("END compMove(" + location(from1, from2) + " to " + location(to1, to2) + ")");
        return;
      }
      if ((move || isKing()) && !seeDanger(from1 - 1, from2 - 1) && !isOccupied(from1 - 1, from2 - 1) && isOnBoard(from1 - 1, from2 - 1))
      {
        to1 = from1 - 1;
        to2 = from2 - 1;
        board[to1][to2] = board[from1][from2];
        board[from1][from2] = EMPTY;

        turnOver = true;
        //*TC*/System.out.println("END compMove(" + location(from1, from2) + " to " + location(to1, to2) + ")");
        return;
      }
    }

    //*TC*/System.out.println("END compMove()");
  }

  private static void compForceMove()
  {//*TC*/System.out.println("BEGIN compForceMove()");
    //*TC*/System.err.println("This is the part where the computer makes any move possible with (" + location() + ").");
    if (compJumpable())
    {
      if (isDoubleJumpable())
        compDoubleJump();
      else
        compJump();
    }
    else
    {
      if ((!move || isKing()) && !isOccupied(from1 + 1, from2 + 1) && isOnBoard(from1 + 1, from2 + 1))
      {
        to1 = from1 + 1;
        to2 = from2 + 1;
        board[to1][to2] = board[from1][from2];
        board[from1][from2] = EMPTY;

        turnOver = true;
        //*TC*/System.out.println("END compForceMove(" + location(from1, from2) + " to " + location(to1, to2) + ")");
        return;
      }
      if ((!move || isKing()) && !isOccupied(from1 + 1, from2 - 1) && isOnBoard(from1 + 1, from2 - 1))
      {
        to1 = from1 + 1;
        to2 = from2 - 1;
        board[to1][to2] = board[from1][from2];
        board[from1][from2] = EMPTY;

        turnOver = true;
        //*TC*/System.out.println("END compForceMove(" + location(from1, from2) + " to " + location(to1, to2) + ")");
        return;
      }
      if ((move || isKing()) && !isOccupied(from1 - 1, from2 + 1) && isOnBoard(from1 - 1, from2 + 1))
      {
        to1 = from1 - 1;
        to2 = from2 + 1;
        board[to1][to2] = board[from1][from2];
        board[from1][from2] = EMPTY;

        turnOver = true;
        //*TC*/System.out.println("END compForceMove(" + location(from1, from2) + " to " + location(to1, to2) + ")");
        return;
      }
      if ((move || isKing()) && !isOccupied(from1 - 1, from2 - 1) && isOnBoard(from1 - 1, from2 - 1))
      {
        to1 = from1 - 1;
        to2 = from2 - 1;
        board[to1][to2] = board[from1][from2];
        board[from1][from2] = EMPTY;

        turnOver = true;
        //*TC*/System.out.println("END compForceMove(" + location(from1, from2) + " to " + location(to1, to2) + ")");
        return;
      }
    }

    //*TC*/System.out.println("END compForceMove()");
  }

  private static void compDefend()
  {//*TC*/System.out.println("BEGIN compDefend()");
    //*TC*/System.err.println("This is the part where the computer makes a defensive move with (" + location() + ").");

    turnOver = true;

    //*TC*/System.out.println("END compDefend()");
  }

  private static void compJump()
  {//*TC*/System.out.println("BEGIN compJump(" + location() + ")");
    //*TC*/System.out.println("if(" + (from1 + 2 < SIZE) + " && " + (from2 + 2 < SIZE) + " && " + (from1 + 2 < SIZE && from2 + 2 < SIZE ? board[from1 + 2][from2 + 2] == EMPTY : "null") + " && " + (from1 + 2 < SIZE && from2 + 2 < SIZE ? board[from1 + 1][from2 + 1] == notMark : "null") + " && (" + !move + " || " + (from1 + 2 < SIZE && from2 + 2 < SIZE ? isKing(from1 + 1, from2 + 1) : "null") + "))");
    if (from1 + 2 < SIZE && from2 + 2 < SIZE && board[from1 + 2][from2 + 2] == EMPTY && board[from1 + 1][from2 + 1] == notMark && (!move || isKing(from1 + 1, from2 + 1)))
    {
      //*TC*/System.err.println("Now Jumping (" + board[from1][from2] + " at " + location() + " to jump the " + board[from1 + 1][from2 + 1] + " at " + location(from1 + 1, from2 + 1) + ")");
      to1 = from1 + 2;
      to2 = from2 + 2;
    }
    //*TC*/System.out.println("if(" + (from1 + 2 < SIZE) + " && " + (from2 - 2 < SIZE) + " && " + (from1 + 2 < SIZE && from2 - 2 >= 0 ? board[from1 + 2][from2 - 2] == EMPTY : "null") + " && " + (from1 + 2 < SIZE && from2 - 2 >= 0 ? board[from1 + 1][from2 - 1] == notMark : "null") + " && (" + !move + " || " + (from1 + 2 < SIZE && from2 - 2 >= 0 ? isKing(from1 + 1, from2 - 1) : "null") + "))");
    if (from1 + 2 < SIZE && from2 - 2 >= 0 && board[from1 + 2][from2 - 2] == EMPTY && board[from1 + 1][from2 - 1] == notMark && (!move || isKing(from1 + 1, from2 - 1)))
    {
      //*TC*/System.err.println("Now Jumping (" + board[from1][from2] + " at " + location() + " to jump the " + board[from1 + 1][from2 - 1] + " at " + location(from1 + 1, from2 - 1) + ")");
      to1 = from1 + 2;
      to2 = from2 - 2;
    }
    //*TC*/System.out.println("if(" + (from1 - 2 < SIZE) + " && " + (from2 + 2 < SIZE) + " && " + (from1 - 2 >= 0 && from2 + 2 < SIZE ? board[from1 - 2][from2 + 2] == EMPTY : "null") + " && " + (from1 - 2 >= 0 && from2 + 2 < SIZE ? board[from1 - 1][from2 + 1] == notMark : "null") + " && (" + !move + " || " + (from1 - 2 >= 0 && from2 + 2 < SIZE ? isKing(from1 - 1, from2 + 1) : "null") + "))");
    if (from1 - 2 >= 0 && from2 + 2 < SIZE && board[from1 - 2][from2 + 2] == EMPTY && board[from1 - 1][from2 + 1] == notMark && (move || isKing(from1 - 1, from2 + 1)))
    {
      //*TC*/System.err.println("Now Jumping (" + board[from1][from2] + " at " + location() + " to jump the " + board[from1 - 1][from2 + 1] + " at " + location(from1 - 1, from2 + 1) + ")");
      to1 = from1 - 2;
      to2 = from2 + 2;
    }
    //*TC*/System.out.println("if(" + (from1 - 2 < SIZE) + " && " + (from2 - 2 < SIZE) + " && " + (from1 - 2 >= 0 && from2 - 2 >= 0 ? board[from1 - 2][from2 - 2] == EMPTY : "null") + " && " + (from1 - 2 >= 0 && from2 - 2 >= 0 ? board[from1 - 1][from2 - 1] == notMark : "null") + " && (" + !move + " || " + (from1 - 2 >= 0 && from2 - 2 >= 0 ? isKing(from1 - 1, from2 - 1) : "null") + "))");
    if  (from1 - 2 >= 0 && from2 - 2 >= 0 && board[from1 - 2][from2 - 2] == EMPTY && board[from1 - 1][from2 - 1] == notMark && (move || isKing(from1 - 1, from2 - 1)))
    {
      //*TC*/System.err.println("Now Jumping (" + board[from1][from2] + " at " + location() + " to jump the " + board[from1 - 1][from2 - 1] + " at " + location(from1 - 1, from2 - 1) + ")");
      to1 = from1 - 2;
      to2 = from2 - 2;
    }
    else
    {
      System.err.println("A strange error has occurred in compJump when trying to jump from " + location());
      JOptionPane.showMessageDialog(null, "A strange error has occurred in compJump when trying to jump from " + location() + ". Please inform Blue Husky Gaming of this glitch.", "ERROR - " + TITLE + " (version " + VERSION + ")", 0);
    }
    if (!seeDanger(to1, to2))
    {
      JOptionPane.showMessageDialog(null, name + " has jumped " + notName + "'s " + board[(from1 + to1) / 2][(from2 + to2) / 2] + " at " + location((from1 + to1) / 2,(from2 + to2) / 2) + "!", name + "'s move - " + TITLE, 2);
      board[to1][to2] = board[from1][from2];
      board[from1][from2] = EMPTY;
      board[(from1 + to1) / 2][(from2 + to2) / 2] = EMPTY;

      turnOver = true;
    }
    else
    {
      //*TC*/System.err.println("JUMP ABORTED! (found danger if the " + board[from1][from2] + " at " + location() + " were to move to " + location(to1, to2) + ")");
    }

    //*TC*/System.out.println("END compJump()");
  }

  private static void compDoubleJump()
  {//*TC*/System.out.println("BEGIN compDoubleJump()");
    //*TC*/System.err.println("This is the part where the computer makes a double-jump with (" + location() + ").");

    turnOver = true;

    //*TC*/System.out.println("END compDoubleJump()");
  }
}
