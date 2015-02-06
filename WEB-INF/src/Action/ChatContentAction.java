package Action;

import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Entity.AllInfo;
import Interface.BaseAction;
import Model.UpdateDBManager;

public class ChatContentAction implements BaseAction {

  private static Logger logger = LoggerFactory
      .getLogger(ChatContentAction.class.getName());
  private static ResourceBundle property = ResourceBundle
      .getBundle("SetPhrases");

  @Override
  public String register(HttpServletRequest req, Object ob) {
    // TODO Auto-generated method stub
    AllInfo allInfo = (AllInfo) ob;
    int chatId = allInfo.getChatId();
    int accountId = allInfo.getAccountId();
    String content = allInfo.getContent();

    int sqlResult = 0;
    try {
      String filePass = null;
      sqlResult = UpdateDBManager.insertChatContent(chatId, content, accountId,
          filePass);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      logger.warn(property.getString("sql"), e);
    }

    String url = null;
    if (sqlResult != 0) {
      url = "/ChatSystem/chat/chatRoom?chatId=" + chatId;
      HttpSession session = req.getSession();
      session.setAttribute("chatId", chatId);
    } else {
      url = property.getString("errorJsp");
    }
    return url;
  }

  @Override
  public String moidfy(HttpServletRequest req, Object ob) {
    // TODO Auto-generated method stub
    return null;
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
