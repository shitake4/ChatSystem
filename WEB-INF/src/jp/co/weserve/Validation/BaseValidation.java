package jp.co.weserve.Validation;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.co.weserve.Exception.ReturnNonValueException;

public class BaseValidation {

  private ResourceBundle propaty = ResourceBundle.getBundle("SetPhrases");

  /**
   * @param sqlResult
   * @throws ReturnNonValueException.
   */
  public void sqlReturnValueNon(int sqlResult) throws ReturnNonValueException {
    if (sqlResult == 0) {
      throw new ReturnNonValueException();
    }
  }

  /**
   * @param value
   * @return.
   */
  public boolean isNull(String value) {
    if (value == null || value.equals("")) {
      return true;
    }
    return false;
  }

  /**
   * .
   * 
   * @param value
   * @return
   */
  public boolean isNull(Integer value) {
    if (value == null || value.intValue() == 0) {
      return true;
    }
    return false;
  }

  /**
   * 名前チェック.
   * 
   * @param name
   * @return
   */
  public String name(String name) {
    if (isNull(name)) {
      return propaty.getString("nameIsNull");
    }
    if (name.length() > 45) {
      return propaty.getString("nameLengthOver");
    }

    Pattern pattern = Pattern.compile(propaty.getString("containMark"));
    Matcher match = pattern.matcher(name);
    if (match.find()) {
      return propaty.getString("illegalName");
    }
    return null;
  }

  /**
   * パスワードチェック.
   * 
   * @param pass
   * @return
   */
  public String pass(String pass) {
    if (isNull(pass)) {
      return propaty.getString("passIsNull");
    }
    if (pass.length() > 255) {
      return propaty.getString("passLengthOver");
    }
    Pattern pattern = Pattern.compile(propaty.getString("notAlphaNumeric"));
    Matcher matcher = pattern.matcher(pass);
    if (matcher.find()) {
      return propaty.getString("illegalPass");
    }
    return null;
  }

  /**
   * メールチェック.
   * 
   * @param mail
   * @return
   */
  public String mail(String mail) {
    if (isNull(mail)) {
      return propaty.getString("mailIsNull");
    }
    return mail;
  }

  /**
   * 都道府県チェック.
   * 
   * @param prefecture
   * @return
   */
  public String prefecture(String prefecture) {
    if (isNull(prefecture)) {
      return propaty.getString("prefectureIsNull");
    }
    Pattern pattern = Pattern.compile(propaty.getString("notPrefecture"));
    Matcher matcher = pattern.matcher(prefecture);
    if (matcher.find()) {
      return propaty.getString("illegalPrefecture");
    }
    return null;
  }

  /**
   * 電話番号チェック.
   * 
   * @param tel
   * @return
   */
  public String tel(String tel) {
    if (isNull(tel)) {
      return propaty.getString("telIsNull");
    }
    Pattern pattern = Pattern.compile(propaty.getString("telFormat"));
    Matcher matcher = pattern.matcher(tel);
    if (matcher.find()) {
      return null;
    }
    return propaty.getString("notTelFormat");
  }

  /**
   * 年齢チェック.
   * 
   * @param age
   * @return
   */
  public String age(Integer age) {
    if (isNull(age)) {
      return propaty.getString("ageIsNull");
    }
    if (age > 100) {
      return propaty.getString("ageIsOver");
    }
    return null;
  }

  /**
   * チャットidチェック.
   * 
   * @param chatId
   * @return
   */
  public String chatId(Integer chatId) {
    if (isNull(chatId)) {
      return propaty.getString("chatIdIsNull");
    }
    return null;
  }

  /**
   * アカウントIdチェック.
   * 
   * @param accountId
   * @return
   */
  public String accountId(Integer accountId) {
    if (isNull(accountId)) {
      return propaty.getString("accountIdIsNull");
    }
    return null;
  }

  /**
   * 返信内容チェック.
   * 
   * @param content
   * @return
   */
  public String content(String content) {
    if (isNull(content)) {
      return propaty.getString("contentIsNull");
    }
    if (content.length() > 255) {
      return propaty.getString("contentLengthOver");
    }
    return content;
  }

  /**
   * URLチェック.
   * 
   * @param url
   * @return
   */
  public String url(String url) {
    if (isNull(url)) {
      return propaty.getString("urlIsNull");
    }
    return null;
  }
}
