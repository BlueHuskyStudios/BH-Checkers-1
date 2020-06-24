import java.util.Properties;

public class GameProps
{
  public static String[][] gameProp = new String[][] {
      {"title", "Blue Husky's Checkers"},
      {"version","1.1.10"}};
  
  
  public static String getProp(String key)
  {
    for (int i=0; i < gameProp.length; i++)
    {
      if (gameProp[i][0] == key)
        return gameProp[i][1];
    }
    return "Property not found: " + key;
  }
  
  public static void main(String[] args)
  {
    System.out.println(GameProps.getProp("title") + ", version " + GameProps.getProp("version"));
  }
}