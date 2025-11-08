package filesaver;

import java.io.*;
import java.util.Scanner;

public class ScoreSaver
{
  public static void main(String[] args) throws FileNotFoundException, IOException
  {
    String fileName = "Data.txt";
    Scanner scan = new Scanner(new File(fileName));
    String fileContents = "", fileLine = scan.nextLine();

    if (fileLine.equals("//BEGIN"))
    {
      while (!fileLine.equals("//END"))
      {
        fileLine = scan.nextLine();

       if (!fileLine.equals("//END"))
         fileContents += ("\r\n" + fileLine);
      }
    }


    System.out.println(fileContents);
    FileWriter writeToFile = new FileWriter(fileName);
    FileWriter addToFile = new FileWriter(fileName, true);
    String testString = "Mill \t50 0\r\nAnt\t0 9";
    System.out.println("\r\n" + testString);
    fileContents += "\r\n" + testString + "\r\n//END";
    writeToFile.write("//BEGIN");
    writeToFile.write(fileContents);
    writeToFile.append((char)10);
    //addToFile.write("\n//END");
    writeToFile.close();
    System.out.println("Program successfully terminated.");
    }
  }

