package GetParameter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xerces.internal.util.URI;
import com.sun.org.apache.xerces.internal.util.URI.MalformedURIException;

import DoValidation.FriendsListValidation;
import Entity.AllInfo;
import Entity.ErrorMessage;
import Interface.BaseGetParameter;
import Model.GetLoginUserInfo;
import Model.TypeConvert;

public class SetFriendsListParameter extends HttpServlet implements BaseGetParameter{

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
		
		int accountId = GetLoginUserInfo.getAccountId(req);
		int friendsAccountId = TypeConvert.getIntOtherAccount(req);
		int chatId = TypeConvert.getIntChatId(req);
		String url = null;
		try {
			url = new URI(req.getHeader("referer")).getPath();
		} catch (MalformedURIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AllInfo allInfo = new AllInfo();
		allInfo.setAccountId(accountId);
		allInfo.setFriendsAccountId(friendsAccountId);
		allInfo.setChatId(chatId);
		allInfo.setUrl(url);
		
		//バリデーションチェック
		ErrorMessage error = FriendsListValidation.friendsListRemoveValidation(allInfo);
		if(!(error.isEmpty())){
			HttpSession session = req.getSession();
			session.setAttribute("error", error);
		}
		return allInfo;
	}

	@Override
	public Object reload(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

}
