import java.util.Properties;

public class GameProps
{
  public static String[][] gameProp;
  
  public GameProps()
  {
    gameProp = new String[][] {
      {"title", "version"},
      {"Blue Husky's Checkers","1.1.10"}};
  }
  
  public static String getProp(String key)
  {
    props
    System.out.println(gameProp.length);
    for (int i=0; i < gameProp.length; i++)
    {
      if (gameProp[i][0] == key)
        return gameProp[i][0];
    }
    return "Property not found: " + key;
  }
  
  public static void main(String[] args)
  {
    System.out.println(getProp("title"));
  }
}