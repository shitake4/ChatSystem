package GetParameter;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xerces.internal.util.URI;
import com.sun.org.apache.xerces.internal.util.URI.MalformedURIException;

import DoValidation.RoomMemberValidation;
import Entity.AllInfo;
import Entity.ErrorMessage;
import Interface.BaseGetParameter;
import Interface.BasePermitParameter;
import Model.GetLoginUserInfo;
import Model.TypeConvert;
import Model.UpdateDBManager;

public class SetRoomMemberParameter extends HttpServlet implements BaseGetParameter,BasePermitParameter{

	private static UpdateDBManager updateDb = new UpdateDBManager();
	
	@Override
	public Object register(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		String[] addChatMembers = req.getParameterValues("addChatMemberList");
		List<AllInfo> addChatMemberList = new ArrayList<AllInfo>();
		ErrorMessage error = new ErrorMessage();

		if(addChatMembers == null || addChatMembers.equals("")){
			error.setMessage("友達を選択してください");
		}else{
			for(int member_i = 0;member_i<addChatMembers.length; member_i++){
				AllInfo allInfo = new AllInfo();
				allInfo.setAccountId(Integer.parseInt(addChatMembers[member_i]));
				allInfo.setChatId(TypeConvert.getIntChatId(req));
				addChatMemberList.add(allInfo);
				error = DoValidation.RoomMemberValidation.checkAccountIdValidation(addChatMemberList);
			}
		}
		if(!(error.isEmpty())){
			HttpSession session = req.getSession();
			session.setAttribute("error", error);
		}
		return addChatMemberList;
	}

	@Override
	public Object moidfy(HttpServletRequest req) {
		// TODO Auto-generated method stub
		AllInfo allInfo = new AllInfo();
		allInfo.setAccountId(TypeConvert.getIntOtherAccount(req));
		allInfo.setChatId(TypeConvert.getIntChatId(req));
		
		//バリデーションチェック
		ErrorMessage error = RoomMemberValidation.RoomMemberValidation(allInfo);
		if(!(error.isEmpty())){
			HttpSession session = req.getSession();
			session.setAttribute("error", error);
		}
		return allInfo;
	}

	@Override
	public Object remove(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		int accountId = GetLoginUserInfo.getAccountId(req);
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
		allInfo.setUrl(url);
		allInfo.setChatId(chatId);
		
		//バリデーションチェック
		ErrorMessage error = RoomMemberValidation.RoomMemberValidation(allInfo);
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

	@Override
	public Object permit(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		AllInfo permitAccount = new AllInfo();
		permitAccount.setAccountId(TypeConvert.getIntAccountId(req));
		permitAccount.setChatId(TypeConvert.getIntChatId(req));
		
		//バリデーション
		ErrorMessage error = RoomMemberValidation.RoomMemberValidation(permitAccount);
		if(!(error.isEmpty())){
			HttpSession session = req.getSession();
			session.setAttribute("error", error);
		}
		return permitAccount;
	}
}
