package jp.co.weserve.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.weserve.Exception.TypeConvertException;
import jp.co.weserve.Validation.BaseValidation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Entity.AccountInfo;
import Exception.LoginException;

public class GetScopeValues {
  private static Logger logger = LoggerFactory.getLogger(GetScopeValues.class
      .getName());
  private static BaseValidation validation = new BaseValidation();

  /**
   * @param req
   * @throws TypeConvertException
   * @return .
   */
  public int reqOtherAccountId(HttpServletRequest req)
      throws TypeConvertException {
    String tmp = req.getParameter("friendsAccountId");
    if (validation.isNull(tmp)) {
      tmp = req.getParameter("memberAccountId");
    }
    if (validation.isNull(tmp)) {
      String message = "セッションスコープにはfriendsAccountIdまたはmemberAccountIdがありません";
      throw new TypeConvertException(message);
    }

    int otherAccountId = 0;
    try {
      otherAccountId = Integer.parseInt(tmp);
    } catch (NumberFormatException e) {
      throw new TypeConvertException(e);
    }
    return otherAccountId;
  }

  /**
   * @param req
   * @return
   * @throws LoginException.
   */
  public int sessAccountId(HttpServletRequest req) throws LoginException {
    HttpSession session = req.getSession();
    AccountInfo loginUserInfo = (AccountInfo) session
        .getAttribute("loginUserInfo");

    if (loginUserInfo == null || loginUserInfo.getAccountId() == 0) {
      throw new LoginException("セッションスコープにはログイン情報自体が無い。またはアカウントIdがありません");
    }
    int accountId = loginUserInfo.getAccountId();
    return accountId;
  }

  /**
   * @param req
   * @return
   * @throws LoginException.
   */
  public String sessLoginAccountName(HttpServletRequest req)
      throws LoginException {
    HttpSession session = req.getSession();
    AccountInfo loginUserInfo = (AccountInfo) session
        .getAttribute("loginUserInfo");

    if (loginUserInfo == null || loginUserInfo.getName().equals("")) {
      throw new LoginException("セッションスコープにはログイン情報自体が無い。またはユーザー名がありません");
    }
    String accountName = loginUserInfo.getName();
    return accountName;
  }

  /**
   * @param req
   * @throws TypeConvertException
   * @return.
   */
  public int reqAccountId(HttpServletRequest req) throws TypeConvertException {
    String tempAccountId = req.getParameter("accountId");
    if (tempAccountId == null || tempAccountId.equals("")) {
      String message = "リクエストスコープに保存されている。アカウントIdの中身が空です";
      throw new TypeConvertException(message);
    }

    int accountId = 0;
    try {
      accountId = Integer.parseInt(tempAccountId);
    } catch (NumberFormatException e) {
      throw new TypeConvertException(e);
    }
    return accountId;
  }

  /**
   * @param req
   * @throws TypeConvertException
   * @return.
   */
  public int reqChatId(HttpServletRequest req) throws TypeConvertException {
    String tempChatId = req.getParameter("chatId");
    if (tempChatId == null || tempChatId.equals("")) {
      String message = "リクエストスコープに保存されている。チャットIdが空です";
      throw new TypeConvertException(message);
    }

    int chatId = 0;
    try {
      chatId = Integer.parseInt(tempChatId);
    } catch (NumberFormatException e) {
      throw new TypeConvertException(e);
    }
    return chatId;
  }

  /**
   * @param req
   * @throws TypeConvertException
   * @return.
   */
  public int attriAccountId(HttpServletRequest req) throws TypeConvertException {
    String tempChatId = (String) req.getAttribute("AccountId");
    if (validation.isNull(tempChatId)) {
      String message = "セッションに保存されている。アカウントIdが空です";
      throw new TypeConvertException(message);
    }

    int accountId = 0;
    try {
      accountId = Integer.parseInt(tempChatId);
    } catch (NumberFormatException e) {
      throw new TypeConvertException(e);
    }
    return accountId;
  }

  /**
   * @param req
   * @throws TypeConvertException
   * @return.
   */
  public int attriChatId(HttpServletRequest req) throws TypeConvertException {
    String tempChatId = (String) req.getAttribute("chatId");
    if (validation.isNull(tempChatId)) {
      String message = "セッションに保存されている。チャットIdが空です。";
      throw new TypeConvertException(message);
    }
    int chatId = 0;
    try {
      chatId = Integer.parseInt(tempChatId);
    } catch (NumberFormatException e) {
      throw new TypeConvertException(e);
    }
    return chatId;
  }

  /**
   * @param req
   * @throws TypeConvertException
   * @return.
   */
  public int reqAge(HttpServletRequest req) throws TypeConvertException {
    String tempAge = req.getParameter("age");
    if (validation.isNull(tempAge)) {
      String message = "リクエストスコープにはageは空です。";
      throw new TypeConvertException(message);
    }

    int age = 0;
    try {
      age = Integer.parseInt(tempAge);
    } catch (NumberFormatException e) {
      throw new TypeConvertException(e);
    }
    return age;
  }
}
