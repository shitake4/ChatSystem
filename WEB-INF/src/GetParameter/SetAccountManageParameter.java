package GetParameter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DoValidation.AccountManageValidation;
import Entity.AllInfo;
import Entity.ErrorMessage;
import Interface.BaseGetParameter;
import Model.GetLoginUserInfo;
import Model.TypeConvert;

public class SetAccountManageParameter extends HttpServlet implements BaseGetParameter{

	@Override
	public Object register(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		String name = req.getParameter("name");
		String mail = req.getParameter("mail");
		String pass = req.getParameter("pass");
		String prefecture = req.getParameter("prefecture");
		String tel = req.getParameter("tel");
		int age = TypeConvert.getIntAge(req);
		
		AllInfo allInfo = new AllInfo();
		allInfo.setName(name);
		allInfo.setMail(mail);
		allInfo.setPass(pass);
		allInfo.setPrefecture(prefecture);
		allInfo.setTel(tel);
		allInfo.setAge(age);
		
		//バリデーション
		ErrorMessage error = AccountManageValidation.CreatAccountValidation(allInfo);
		if(!(error.isEmpty())){
			HttpSession session = req.getSession();
			session.setAttribute("error", error);
		}
		return allInfo;
	}

	@Override
	public Object moidfy(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		int accountId = GetLoginUserInfo.getAccountId(req);
		String mail = req.getParameter("mail");
		String pass1 = req.getParameter("newPass1");
		String pass2 = req.getParameter("newPass2");
		String prefecture = req.getParameter("prefecture");
		String tel = req.getParameter("tel");
		int age = TypeConvert.getIntAge(req);
		String accountUrl = req.getParameter("accountUrl");
		
		if(!(pass1.equals(pass2))){
			HttpSession session = req.getSession();
			ErrorMessage error = new ErrorMessage();
			error.setMessage("パスワードが一致しておりません");
			session.setAttribute("error", error);
		}

		AllInfo allInfo = new AllInfo();
		allInfo.setAccountId(accountId);
		allInfo.setMail(mail);
		allInfo.setPass(pass1);
		allInfo.setPrefecture(prefecture);
		allInfo.setTel(tel);
		allInfo.setAge(age);
		allInfo.setUrl(accountUrl);
		
		//バリデーション
		ErrorMessage error = new ErrorMessage();
		if(!(pass1 == null || pass1.equals(""))){
			error = AccountManageValidation.ChangeAccountValidation(allInfo);
		}
		if(!(error.isEmpty())){
			HttpSession session = req.getSession();
			session.setAttribute("error", error);
		}
		return allInfo;
	}

	@Override
	public Object remove(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object reload(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}
}
