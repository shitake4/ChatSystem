package jp.co.weserve.Exception;

import java.io.IOException;

public class CanNotContinueException extends IOException {
  public CanNotContinueException(String message) {
    super(message);
  }

  public CanNotContinueException(Throwable e) {
    // TODO Auto-generated constructor stub
    super(e);
  }
}
