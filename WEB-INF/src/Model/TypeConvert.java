package Model;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Entity.AccountInfo;
import Exception.LoginException;

public class TypeConvert extends HttpServlet{
	
	private TypeConvert(){
	}
	
	public static int getIntOtherAccount(HttpServletRequest req){
		String tmpOtherAccountIdountId = req.getParameter("friendsAccountId");
		if(tmpOtherAccountIdountId == null || tmpOtherAccountIdountId.equals("")){
			tmpOtherAccountIdountId = req.getParameter("memberAccountId");
		}
		int otherAccountId = 0;
		if(!(tmpOtherAccountIdountId == null || tmpOtherAccountIdountId.equals(""))){
			otherAccountId = Integer.parseInt(tmpOtherAccountIdountId);
		}
		return otherAccountId;
	}
	
	public static int getIntLoginAccountId(HttpServletRequest req) throws LoginException{
		HttpSession session = req.getSession();
		AccountInfo loginUserInfo = (AccountInfo) session.getAttribute("loginUserInfo");
		int accountId = 0;
		if(loginUserInfo == null || loginUserInfo.equals("")){
			throw new LoginException("ログイン情報がありません");
		}else{
			accountId = loginUserInfo.getAccountId();
		}
		return accountId;
	}
	
	public static String LoginAccountName(HttpServletRequest req) throws LoginException{
		HttpSession session = req.getSession();
		AccountInfo loginUserInfo = (AccountInfo) session.getAttribute("loginUserInfo");
		String accountName = null;
		if(loginUserInfo == null || loginUserInfo.equals("")){
			throw new LoginException("ログイン情報がありません");
		}else{
			accountName = loginUserInfo.getName();
		}
		return accountName;
	}
	
	public static int getIntAccountId(HttpServletRequest req){
		String tempAccountId = req.getParameter("accountId");
		int accountId = 0;
		if(!(tempAccountId == null || tempAccountId.equals(""))){
			accountId = Integer.parseInt(tempAccountId);
		}else{
			System.out.println("チャットIDをint型に出来ず");
		}
		return accountId;
	}
	
	public static int getIntChatId(HttpServletRequest req){
		String tempChatId = req.getParameter("chatId");
		int chatId = 0;
		if(!(tempChatId == null || tempChatId.equals(""))){
			chatId = Integer.parseInt(tempChatId);
		}else{
			System.out.println("チャットIDをint型に出来ず");
		}
		return chatId;
	}
	public static int getAttriAccountId(HttpServletRequest req){
		String tempChatId = (String) req.getAttribute("AccountId");
		int accountId = 0;
		if(!(tempChatId == null || tempChatId.equals(""))){
			accountId = Integer.parseInt(tempChatId);
		}else{
			System.out.println("チャットIDをint型に出来ず");
		}
		return accountId;
	}
	
	public static int getAttriChatId(HttpServletRequest req){
		String tempChatId = (String) req.getAttribute("chatId");
		int chatId = 0;
		if(!(tempChatId == null || tempChatId.equals(""))){
			chatId = Integer.parseInt(tempChatId);
		}else{
			System.out.println("チャットIDをint型に出来ず");
		}
		return chatId;
	}
	
	public static int AccountId(String tempAccountId){
		int accountId = 0;
		if(!(tempAccountId == null || tempAccountId.equals(""))){
			accountId = Integer.parseInt(tempAccountId);
		}else{
			System.out.println("チャットIDをint型に出来ず");
		}
		return accountId;
	}
	
	public static int ChatId(String tempChatId){
		int chatId = 0;
		if(!(tempChatId == null || tempChatId.equals(""))){
			chatId = Integer.parseInt(tempChatId);
		}else{
			System.out.println("チャットIDをint型に出来ず");
		}
		return chatId;
	}
	
	public static int getIntAge(HttpServletRequest req){
		String tempAge = req.getParameter("age");
		int age =0;
		if (!(tempAge == null || tempAge.equals(""))){
				age = Integer.parseInt(tempAge);
		}
		return age;
	}
}