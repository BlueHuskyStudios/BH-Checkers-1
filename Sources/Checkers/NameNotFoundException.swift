public struct NameNotFoundException: Error
{
  private let message: String
  
  init (_ username: String)
  {
    message = ("Cannot find username \"" + username + "\"");
  }
}
