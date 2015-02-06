package GetParameter;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import Entity.AllInfo;
import Exception.LoginException;
import Interface.BaseGetParameter;
import Model.TypeConvert;

public class SetChatListPrameter extends HttpServlet implements
    BaseGetParameter {

  @Override
  public ArrayList<AllInfo> register(HttpServletRequest req) {
    return null;
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
    AllInfo user = new AllInfo();
    try {
      user.setAccountId(TypeConvert.getIntLoginAccountId(req));
    } catch (LoginException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return user;
  }
}
