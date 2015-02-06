package DoValidation;

import Entity.AllInfo;
import Entity.ErrorMessage;

public class ChatRoomContentValidation {

  public static ErrorMessage registerChatContent(AllInfo allInfo) {
    ErrorMessage error = new ErrorMessage();
    if (allInfo.getChatId() == 0) {
      error.setMessage("chatIdがありません。不正なエラーです");
      allInfo.setValidStatus(false);
    } else if (allInfo.getAccountId() == 0) {
      error.setMessage("アカウントIdがありません。不正なエラーです");
      allInfo.setValidStatus(false);
    } else if (allInfo.getContent() == null || allInfo.getContent().equals("")) {
      error.setMessage("返信内容がありません。入力してください");
      allInfo.setValidStatus(false);
    }
    return error;
  }

  public static ErrorMessage leave(AllInfo allInfo) {
    ErrorMessage error = new ErrorMessage();

    if (allInfo.getChatId() == 0) {
      error.setMessage("chatIdがありません。不正なエラーです");
      allInfo.setValidStatus(false);
    } else if (allInfo.getAccountId() == 0) {
      error.setMessage("アカウントIdがありません。不正なエラーです");
      allInfo.setValidStatus(false);
    } else if (allInfo.getName() == null || allInfo.getName().equals("")) {
      error.setMessage("名前がありません。不正なエラーです");
      allInfo.setValidStatus(false);
    }
    return error;
  }
}
