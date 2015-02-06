package Action;

import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Entity.AllInfo;
import Interface.BaseAction;
import Interface.BasePermitAction;
import Model.SelectDBManager;
import Model.UpdateDBManager;

public class RoomMemberAction implements BaseAction, BasePermitAction {

  private static Logger logger = LoggerFactory.getLogger(RoomMemberAction.class
      .getName());
  private static ResourceBundle property = ResourceBundle
      .getBundle("SetPhrases");
  private static UpdateDBManager UpdateDBManager = new UpdateDBManager();

  @Override
  public String register(HttpServletRequest req, Object ob) {
    // TODO Auto-generated method stub

    List<AllInfo> addChatMemberList = (List<AllInfo>) ob;
    int chatId = addChatMemberList.get(0).getChatId();
    int sumSqlResult = 0;
    for (int members_i = 0; members_i < addChatMemberList.size(); members_i++) {
      try {
        int sqlResult = Model.UpdateDBManager.insertChatRoomMember(chatId,
            addChatMemberList.get(members_i).getAccountId());
        sumSqlResult += sqlResult;
        logger.info("--------------------------sqlをループで回してます。sql結果："
            + sqlResult + "--------------------------");
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        logger.error(property.getString("sql"), e);
      }
    }

    if (sumSqlResult != 0) {
      HttpSession session = req.getSession();
      String message = "メンバーに追加しました";
      session.setAttribute("result", message);
    }
    String url = null;
    return url;
  }

  @Override
  public String moidfy(HttpServletRequest req, Object ob) {
    // TODO Auto-generated method stub

    AllInfo allInfo = (AllInfo) ob;
    int memberAccountId = allInfo.getAccountId();
    int chatId = allInfo.getChatId();
    int sqlResult = 0;
    try {
      sqlResult = Model.UpdateDBManager.deleteChatRoomMember(chatId,
          memberAccountId);
      String message = "メンバーを外しました";
      HttpSession session = req.getSession();
      session.setAttribute("result", message);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      logger.warn(property.getString("sqlDelete"), e);
    }
    logger.info("sql結果は" + sqlResult + "です");
    return null;
  }

  @Override
  public String remove(HttpServletRequest req, Object ob) {
    // TODO Auto-generated method stub
    AllInfo allInfo = (AllInfo) ob;

    int accountId = allInfo.getAccountId();
    int chatId = allInfo.getChatId();
    String url = allInfo.getUrl();

    int sqlResult = 0;
    try {
      sqlResult = Model.UpdateDBManager.deleteChatRoomMember(chatId, accountId);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      logger.warn(property.getString("sql"), e);
    }
    if (sqlResult != 0) {

      boolean absence = true;
      try {
        absence = SelectDBManager.checkAbsenceChatRoomMember(chatId);
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        logger.warn(property.getString("sql"), e);
      }
      if (!absence) {
        try {
          Model.UpdateDBManager.deleteChatRoom(chatId);
          HttpSession session = req.getSession();
          String message = "ルームメンバーがいなくなった為、ルームを削除しました";
          session.setAttribute("result", message);
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
          logger.warn(property.getString("sql"), e);
        }
      } else if (absence) {
        HttpSession session = req.getSession();
        String message = "ルームメンバーから抜けました";
        session.setAttribute("result", message);
      }
      if (url.contains("chatRoom")) {
        url = property.getString("chatListServlet");
      } else {

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

  @Override
  public String permit(HttpServletRequest req, Object object) {
    // TODO Auto-generated method stub
    // 管理者権限の追加
    AllInfo permitAccountInfo = (AllInfo) object;
    int accountId = permitAccountInfo.getAccountId();
    int chatId = permitAccountInfo.getChatId();

    int sqlResult = 0;
    try {
      sqlResult = UpdateDBManager.giveAdminAuthority(chatId, accountId);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      logger.error(property.getString("sql"), e);
    }
    String url = null;
    if (sqlResult == 0) {
      url = property.getString("errorJsp");
    } else {
      HttpSession session = req.getSession();
      String message = "管理者に設定致しました";
      session.setAttribute("result", message);
    }
    return url;
  }
}
