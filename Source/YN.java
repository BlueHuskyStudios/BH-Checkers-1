import javax.swing.*;
import java.awt.*;

public class YesNoBox
{
  public static boolean bool(String input, String prompt)
  {
    String yn = JOptionPane.showInputDialog(prompt, 3);
    if ((yn.charAt(0) + "").equalsIgnoreCase("y") || (yn.charAt(0) + "").equalsIgnoreCase("a") || yn.charAt(0) == 1)
      return true;
    else
      return false;
  }
  
  public static boolean bool(String input, String prompt, String title, int type)
  {
    String yn = JOptionPane.showInputDialog(null, prompt, title, type);
    if ((yn.charAt(0) + "").equalsIgnoreCase("y") || (yn.charAt(0) + "").equalsIgnoreCase("a") || yn.charAt(0) == 1)
      return true;
    else
      return false;
  }
}