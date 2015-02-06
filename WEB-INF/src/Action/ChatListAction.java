package Action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Entity.AccountInfo;
import Entity.AllInfo;
import Entity.ChatRoomList;
import Entity.JoinChatRoom;
import Interface.BaseAction;
import Model.SelectDBManager;
import Model.SetRequest;

public class ChatListAction extends HttpServlet implements BaseAction {

  private static Logger logger = LoggerFactory.getLogger(ChatListAction.class
      .getName());
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
    return null;
  }

  @Override
  public String reload(HttpServletRequest req, Object ob) {
    // TODO Auto-generated method stub
    AllInfo allInfo = (AllInfo) ob;
    List<ChatRoomList> chatList = new ArrayList<ChatRoomList>();
    ArrayList<AccountInfo> friendslist = new ArrayList<AccountInfo>();
    ArrayList<JoinChatRoom> joinChatRoomList = new ArrayList<JoinChatRoom>();
    int accountId = allInfo.getAccountId();

    // session から　requestにセット
    SetRequest setRequest = new SetRequest();
    setRequest.setError(req);
    setRequest.setStatus(req);
    setRequest.setSearchResult(req);

    String url = null;
    try {
      chatList = SelectDBManager.reloadChatRoom();
      friendslist = SelectDBManager.getFriendsList(accountId);
      joinChatRoomList = SelectDBManager.selectJoinRoom(accountId);

      req.setAttribute("friendslist", friendslist);
      req.setAttribute("chatList", chatList);
      req.setAttribute("joinChatRoomList", joinChatRoomList);
      url = property.getString("chatListJsp");
    } catch (SQLException e) {
      e.printStackTrace();
      url = property.getString("errorJsp");
    }
    return url;
  }
}
