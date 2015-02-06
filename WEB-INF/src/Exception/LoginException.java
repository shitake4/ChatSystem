package Exception;

public class LoginException extends Exception {
  private int code;

  public LoginException(String message) {
    super(message);

  }
}