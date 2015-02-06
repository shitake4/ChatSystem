package DoValidation;

import Entity.AllInfo;
import Entity.ErrorMessage;

public class MyPageValidation {

  public static ErrorMessage reloadMypageValidation(AllInfo allInfo) {

    ErrorMessage error = new ErrorMessage();
    if (allInfo.getAccountId() == 0) {
      error.setMessage("アカウントIdがありません。不正なエラーです");
      allInfo.setValidStatus(false);
    }

    return error;
  }
}
