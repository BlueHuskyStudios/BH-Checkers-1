import Foundation

public class YesNoBox
{
  public static func bool(_ prompt: String) -> Bool
  {
    let yn = JOptionPane.showConfirmDialog(nil, prompt);
    //System.out.println("yn: " + yn);
    
    if (yn == 0) {
      return true;}
    
    return false;
  }
  
  public static func bool(_ prompt: String, _ title: String, _ type: Int) -> Bool
  {
    let yn = JOptionPane.showConfirmDialog(nil, prompt, title, 0, type);
    //System.out.println("yn: " + yn);
    
    if (yn == 0) {
      return true;}
    
    return false;
  }
  
  public static func integer(_ prompt: String, _ title: String, _ type: Int) -> Int
  {
    return JOptionPane.showConfirmDialog(nil, prompt, title, 0, type);
  }
  
  public static func integer(_ prompt: String) -> Int
  {
    return JOptionPane.showConfirmDialog(nil, prompt);
  }
}
