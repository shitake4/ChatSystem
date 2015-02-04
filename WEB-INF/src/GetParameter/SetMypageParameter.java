package GetParameter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DoValidation.MyPageValidation;
import Entity.AllInfo;
import Entity.ErrorMessage;
import Interface.BaseGetParameter;
import Model.GetLoginUserInfo;

public class SetMypageParameter extends HttpServlet implements BaseGetParameter{

	@Override
	public Object register(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object moidfy(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object remove(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object reload(HttpServletRequest req) {
		// TODO Auto-generated method stub
		//UserID受け取り
		
		int accountId = GetLoginUserInfo.getAccountId(req);
		
		AllInfo allInfo = new AllInfo();
		allInfo.setAccountId(accountId);

		//バリデーション
		ErrorMessage error = MyPageValidation.reloadMypageValidation(allInfo);
		if(!(error.isEmpty())){
			HttpSession session = req.getSession();
			session.setAttribute("error", error);
		}
		return allInfo;
	}
}
