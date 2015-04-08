package jp.co.weserve.Exception;

import java.io.IOException;

public class ReturnNonValueException extends IOException {

  /**
   * .
   */
  private static final long serialVersionUID = -7186792967181747500L;

  public ReturnNonValueException() {
    // TODO Auto-generated constructor stub
    super();
  }

  public ReturnNonValueException(String message) {
    // TODO Auto-generated constructor stub
    super(message);
  }

  public ReturnNonValueException(Throwable e) {
    // TODO Auto-generated constructor stub
    super(e);
  }
}
