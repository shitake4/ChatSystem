package DoValidation;

import Entity.AllInfo;
import Entity.ErrorMessage;

public class ReadOnlyMyPageValidation {

  public static ErrorMessage reloadMypageValidation(AllInfo allInfo) {

    ErrorMessage error = new ErrorMessage();
    if (allInfo.getUrl() == null || allInfo.getUrl().equals("")) {
      error.setMessage("URLがありません。不正なエラーです");
      allInfo.setValidStatus(false);
    }

    return error;
  }
}
