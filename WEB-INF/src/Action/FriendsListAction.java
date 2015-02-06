package Action;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Entity.AllInfo;
import Interface.BaseAction;
import Model.UpdateDBManager;

public class FriendsListAction implements BaseAction {

  private static Logger logger = LoggerFactory
      .getLogger(FriendsListAction.class.getName());
  private static ResourceBundle property = ResourceBundle
      .getBundle("SetPhrases");

  @Override
  public String register(HttpServletRequest req, Object ob) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String moidfy(HttpServletRequest req, Object ob) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String remove(HttpServletRequest req, Object ob) {
    // TODO Auto-generated method stub

    AllInfo allInfo = (AllInfo) ob;

    int accountId = allInfo.getAccountId();
    int friendsAccountId = allInfo.getFriendsAccountId();
    int chatId = allInfo.getChatId();
    String url = allInfo.getUrl();

    int sqlResult = UpdateDBManager.quitFriends(accountId, friendsAccountId);

    if (sqlResult != 0) {
      String message = "フォローを解除しました";
      HttpSession session = req.getSession();
      session.setAttribute("result", message);
      if (url.contains("chatRoom")) {
        url += "?chatId=" + chatId;
      }
    } else {
      url = property.getString("errorJsp");
    }
    return url;
  }

  @Override
  public String reload(HttpServletRequest req, Object ob) {
    // TODO Auto-generated method stub
    return null;
  }

}
