package Model;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Entity.AccountInfo;

public class GetLoginUserInfo extends HttpServlet{

	private GetLoginUserInfo(){
		
	}
	
	private static AccountInfo getLoginUserInfo(HttpServletRequest req){
		HttpSession session = req.getSession();
		AccountInfo loginUserInfo = (AccountInfo) session.getAttribute("loginUserInfo");
		return loginUserInfo;
	}
	
	public static int getAccountId(HttpServletRequest req){
		AccountInfo loginUserInfo = getLoginUserInfo(req);
		int accountId = loginUserInfo.getAccountId();
		return accountId;
	}
	
	public static String getName(HttpServletRequest req){
		AccountInfo loginUserInfo = getLoginUserInfo(req);
		String name = loginUserInfo.getName();
		return name;
	}
}
