package jp.co.weserve.Validation;

import Entity.AllInfo;

public class SharedValidation {

  private BaseValidation validation = new BaseValidation();

  /**
   * chatIdとaccountIdのチェック.
   * 
   * @param allInfo
   * @return
   */
  public String checkChatAcc(AllInfo allInfo) {
    String errorMessage = null;

    Integer chatId = allInfo.getChatId();
    Integer accountId = allInfo.getAccountId();

    errorMessage = validation.chatId(chatId);
    if (errorMessage == null) {
      errorMessage = validation.accountId(accountId);
    }
    return errorMessage;
  }

  /**
   * アカウントIdチェック.
   * 
   * @param allInfo
   * @return
   */
  public String checkAcc(AllInfo allInfo) {
    String errorMessage = null;

    Integer accountId = allInfo.getAccountId();

    errorMessage = validation.accountId(accountId);
    return errorMessage;
  }
}
