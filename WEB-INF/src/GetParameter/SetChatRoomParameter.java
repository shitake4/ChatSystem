package GetParameter;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DoValidation.ChatRoomVaidation;
import Entity.AllInfo;
import Entity.ErrorMessage;
import Exception.LoginException;
import Interface.BaseGetLoginoutParameter;
import Interface.BaseGetParameter;
import Model.GetLoginUserInfo;
import Model.TypeConvert;

public class SetChatRoomParameter implements BaseGetParameter,
    BaseGetLoginoutParameter {

  @Override
  public ArrayList<AllInfo> register(HttpServletRequest req) {
    ArrayList<AllInfo> allInfoList = new ArrayList<AllInfo>();
    AllInfo user = new AllInfo();
    user.setRoomName(req.getParameter("roomName"));
    user.setAccountId(GetLoginUserInfo.getAccountId(req));
    user.setRoomPass(req.getParameter("roomPass"));
    allInfoList.add(user);

    // リストメンバーがいた場合は追加する
    String[] chatMembers = req.getParameterValues("chatMemberList");
    if (chatMembers != null) {
      for (int i = 0; i < chatMembers.length; i++) {
        AllInfo other = new AllInfo();
        other.setRoomName(req.getParameter("roomName"));
        other.setAccountId(Integer.parseInt(chatMembers[i]));
        allInfoList.add(other);
      }
    }

    // バリデーション
    ErrorMessage error = ChatRoomVaidation
        .validationRegisterChatRomm(allInfoList);
    if (!(error.isEmpty())) {
      HttpSession session = req.getSession();
      session.setAttribute("error", error);
    }
    return allInfoList;
  }

  @Override
  public AllInfo moidfy(HttpServletRequest req) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public AllInfo remove(HttpServletRequest req) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public AllInfo reload(HttpServletRequest req) {
    // TODO Auto-generated method stub

    AllInfo allinfo = new AllInfo();

    allinfo.setAccountId(GetLoginUserInfo.getAccountId(req));
    allinfo.setChatId(TypeConvert.getIntChatId(req));
    allinfo.setName(GetLoginUserInfo.getName(req));

    return allinfo;
  }

  @Override
  public Object login(HttpServletRequest req) {
    // TODO Auto-generated method stub
    HttpSession session = req.getSession();
    int chatId = TypeConvert.getIntChatId(req);
    String roomPass = req.getParameter("roomPass");
    int accountId = 0;
    try {
      accountId = TypeConvert.getIntLoginAccountId(req);
    } catch (LoginException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    String name = null;
    try {
      name = TypeConvert.LoginAccountName(req);
    } catch (LoginException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    AllInfo allInfo = new AllInfo();
    allInfo.setChatId(chatId);
    allInfo.setRoomPass(roomPass);
    allInfo.setName(name);
    allInfo.setAccountId(accountId);
    return allInfo;
  }
}
