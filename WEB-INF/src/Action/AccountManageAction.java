package Action;

import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Entity.AllInfo;
import Interface.BaseAction;
import Model.AccountUrlManager;
import Model.SelectDBManager;
import Model.UpdateDBManager;

public class AccountManageAction implements BaseAction {

  private static Logger logger = LoggerFactory
      .getLogger(AccountManageAction.class.getName());
  private static ResourceBundle property = ResourceBundle
      .getBundle("SetPhrases");
  private static SelectDBManager selectDb = new SelectDBManager();

  @Override
  public String register(HttpServletRequest req, Object ob) {
    // TODO Auto-generated method stub
    String url = null;
    AllInfo allInfo = (AllInfo) ob;
    AccountUrlManager accountUrl = new AccountUrlManager();
    int methodResult = accountUrl.insertAccountUrl(ob);

    if (allInfo.isValidStatus()) {
      if (methodResult != 0) {
        HttpSession session = req.getSession();
        session.setAttribute("result", "アカウントの作成に成功しました。");
        url = property.getString("loginServlet");
      } else {
        url = property.getString("signUpJsp");
      }

    } else if (!(allInfo.isValidStatus())) {
      url = property.getString("signUpJsp");
      logger.info(property.getString("validation"));
    }
    return url;
  }

  @Override
  public String moidfy(HttpServletRequest req, Object ob) {
    // TODO Auto-generated method stub

    String url = null;

    AllInfo allInfo = (AllInfo) ob;
    String mail = allInfo.getMail();
    String pass = allInfo.getPass();
    String prefecture = allInfo.getPrefecture();
    String tel = allInfo.getTel();
    int age = allInfo.getAge();
    int accountId = allInfo.getAccountId();
    String accountUrl = "/" + allInfo.getUrl();

    int sqlResult = 0;
    if (pass == null || pass.equals("")) {
      try {
        sqlResult = UpdateDBManager.changeAccountInfoNonPass(mail, prefecture,
            tel, age, accountId, accountUrl);
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } else {
      try {
        sqlResult = UpdateDBManager.changeAccountInfo(mail, pass, prefecture,
            tel, age, accountId, accountUrl);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    if (sqlResult == 0) {
      HttpSession session = req.getSession();
      String message = "会員情報更新に失敗しました。";
      session.setAttribute("error", message);
    } else if (sqlResult != 0) {
      String result = "会員情報の更新が完了致しました。";
      HttpSession session = req.getSession();
      session.setAttribute("result", result);
      url = property.getString("myPageServlet");
    }
    return url;
  }

  @Override
  public String remove(HttpServletRequest req, Object ob) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String reload(HttpServletRequest req, Object ob) {
    // TODO Auto-generated method stub
    return null;
  }
}
