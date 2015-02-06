package GetParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DoValidation.AccountManageValidation;
import Entity.AllInfo;
import Entity.ErrorMessage;
import Interface.BaseGetLoginoutParameter;

public class SetLogInOutParameter implements BaseGetLoginoutParameter {

  @Override
  public Object login(HttpServletRequest req) {

    String name = req.getParameter("name");
    String pass = req.getParameter("pass");
    String url = req.getPathInfo();

    AllInfo allInfo = new AllInfo();
    allInfo.setName(name);
    allInfo.setPass(pass);
    allInfo.setUrl(url);

    // バリデーション
    ErrorMessage error = AccountManageValidation.checkLoginAccount(allInfo);

    if (!(error.isEmpty())) {
      HttpSession session = req.getSession();
      session.setAttribute("error", error);
    }
    return allInfo;
  }
}