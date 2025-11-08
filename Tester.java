import java.io.*;
import javax.swing.*;

public class Tester
{
  public static void main (String[] args) throws IOException, NameNotFoundException, ValueGlitch
  {
//    while (true)
//    {
//      String file = "CheckersSave.dll", username = JOptionPane.showInputDialog("Input a username to read from " + file);
//      try
//      {
//        JOptionPane.showMessageDialog(null, username + " has won " + 
//                                      GameScore.getWins(file, username) + 
//                                      (GameScore.getWins(file, username) == 1 ? " time " : " times ") + "and lost " + 
//                                      GameScore.getLosses(file, username) + 
//                                      (GameScore.getLosses(file, username) == 1 ? " time " : " times."));
//      }
//      catch (NameNotFoundException ex)
//      {
//        JOptionPane.showMessageDialog(null, ex.getMessage(), "No such username", 0);
//      }
//    }
    
    for (int i=0; i < 128; i++)
    {
      System.out.println(i + ": " + (char)i);
    }
  }
}