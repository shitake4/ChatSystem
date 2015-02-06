package DoValidation;

import Entity.AllInfo;
import Entity.ErrorMessage;

public class FriendsListValidation {

  public static ErrorMessage friendsListRegistValidation(AllInfo allInfo) {
    ErrorMessage error = new ErrorMessage();

    if (allInfo.getAccountId() == 0) {
      error.setMessage("アカウントIdが空です。不正なエラーです。");
      allInfo.setValidStatus(false);
    } else if (allInfo.getFriendsAccountId() == 0) {
      error.setMessage("フレンズIdが空です。不正なエラーです");
      allInfo.setValidStatus(false);
    }
    return error;
  }

  public static ErrorMessage friendsRequestListModifyValidation(AllInfo allInfo) {
    ErrorMessage error = new ErrorMessage();

    if (allInfo.getAccountId() == 0) {
      error.setMessage("アカウントIdが空です。不正なエラーです。");
      allInfo.setValidStatus(false);
    } else if (allInfo.getFriendsAccountId() == 0) {
      error.setMessage("フレンズIdが空です。不正なエラーです");
      allInfo.setValidStatus(false);
    }
    return error;
  }

  public static ErrorMessage friendsListRemoveValidation(AllInfo allInfo) {
    ErrorMessage error = new ErrorMessage();

    if (allInfo.getAccountId() == 0) {
      error.setMessage("アカウントIdが空です。不正なエラーです。");
      allInfo.setValidStatus(false);
    } else if (allInfo.getFriendsAccountId() == 0) {
      error.setMessage("フレンズIdが空です。不正なエラーです");
      allInfo.setValidStatus(false);
    }
    return error;
  }
}
