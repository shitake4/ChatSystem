package GetParameter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DoValidation.ChatRoomContentValidation;
import Entity.AllInfo;
import Entity.ErrorMessage;
import Interface.BaseGetParameter;
import Model.GetLoginUserInfo;
import Model.TypeConvert;

public class SetChatContentParameter extends HttpServlet implements BaseGetParameter{

	@Override
	public AllInfo register(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		int chatId = TypeConvert.getIntChatId(req);
		int accountId = GetLoginUserInfo.getAccountId(req);
		String content = req.getParameter("content");
		
		AllInfo allInfo = new AllInfo();
		allInfo.setChatId(chatId);
		allInfo.setAccountId(accountId);
		allInfo.setContent(content);
		
		//バリデーション
		ErrorMessage error = ChatRoomContentValidation.registerChatContent(allInfo);
		if(!(error == null || error.isEmpty())){
			HttpSession session = req.getSession();
			session.setAttribute("error", error);
		}
		return allInfo;
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
		return null;
	}
}
