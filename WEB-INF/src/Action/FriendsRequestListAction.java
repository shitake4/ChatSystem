package Action;

import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Entity.AllInfo;
import Entity.ErrorMessage;
import Interface.BaseAction;
import Model.UpdateDBManager;

public class FriendsRequestListAction implements BaseAction{

	private static Logger logger = LoggerFactory.getLogger(FriendsRequestListAction.class.getName());
	private static ResourceBundle property = ResourceBundle.getBundle("SetPhrases");
	
	@Override
	public String register(HttpServletRequest req, Object ob) {
		// TODO Auto-generated method stub
		AllInfo allInfo  = (AllInfo) ob;
		
		int accountId = allInfo.getAccountId();
		int friendsAccountId = allInfo.getFriendsAccountId();
		int chatId = allInfo.getChatId();
		String url = allInfo.getUrl();
		url += "?chatId=" + chatId;
		
		if(accountId == friendsAccountId){
			String message = "自分のアカウントです。リクエストを申請できません";
			HttpSession session = req.getSession();
			ErrorMessage error = new ErrorMessage();
			error.setMessage(message);
			session.setAttribute("error", error);
		}else{
			try {
				int sqlResult = UpdateDBManager.requestFriend(accountId, friendsAccountId);
				if(sqlResult !=0){
					String message = "友達申請を致しました。";
					HttpSession session = req.getSession();
					session.setAttribute("result", message);
				}else{
					logger.warn(property.getString("illegal"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				String message = "すでに友達申請しております";
				HttpSession session = req.getSession();
				session.setAttribute("result", message);
			}
		}
		return url;
	}

	@Override
	public String moidfy(HttpServletRequest req, Object ob) {
		// TODO Auto-generated method stub
		AllInfo allInfo = (AllInfo) ob;

		int accountId = allInfo.getAccountId();
		int friendsAccountId = allInfo.getFriendsAccountId();
		String url = allInfo.getUrl();
		
		int sqlResult = UpdateDBManager.agreeFriendRequest(accountId, friendsAccountId);
		if(sqlResult != 0){
			String message = "友達申請を承認しました";
			HttpSession session = req.getSession();
			session.setAttribute("result", message);
		}else{
			url = property.getString("errorJsp");
		}
		return url;
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
