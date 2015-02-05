package Controller;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.org.apache.xerces.internal.util.URI;

import Action.AccountManageAction;
import Action.ChatContentAction;
import Action.ChatListAction;
import Action.ChatRoomAction;
import Action.FriendsListAction;
import Action.RoomMemberAction;
import Action.FriendsRequestListAction;
import Action.MyPageAction;
import Exception.LoginException;
import GetParameter.SetAccountManageParameter;
import GetParameter.SetChatListPrameter;
import GetParameter.SetChatContentParameter;
import GetParameter.SetFriendsListParameter;
import GetParameter.SetRoomMemberParameter;
import GetParameter.SetChatRoomParameter;
import GetParameter.SetFriendsRequestListParameter;
import GetParameter.SetMypageParameter;
import Interface.BaseAction;
import Interface.BaseGetParameter;
import Model.Forward;

public class MainController extends HttpServlet{
	private static HashMap<String, Interface.BaseAction> actionStore = new HashMap<String,Interface.BaseAction>();
	private static HashMap<String, Interface.BaseGetParameter> paramerterStore = new HashMap<String, BaseGetParameter>();
	private static Logger logger = LoggerFactory.getLogger(MainController.class.getName());
	
	static{
		actionStore.put("/chatList", new ChatListAction());
		actionStore.put("/chatRoom", new ChatRoomAction());
		actionStore.put("/chatContent", new ChatContentAction());
		actionStore.put("/myPage", new MyPageAction());
		actionStore.put("/accountManage", new AccountManageAction());
		actionStore.put("/friendsRequestList", new FriendsRequestListAction());
		actionStore.put("/friendsList", new FriendsListAction());
		actionStore.put("/roomMember", new RoomMemberAction());
		
		paramerterStore.put("/chatList", new SetChatListPrameter());
		paramerterStore.put("/chatRoom", new SetChatRoomParameter());
		paramerterStore.put("/chatContent", new SetChatContentParameter());
		paramerterStore.put("/myPage", new SetMypageParameter());
		paramerterStore.put("/accountManage", new SetAccountManageParameter());
		paramerterStore.put("/friendsRequestList", new SetFriendsRequestListParameter());
		paramerterStore.put("/friendsList", new SetFriendsListParameter());
		paramerterStore.put("/roomMember", new SetRoomMemberParameter());
	}
	
	
	private static void commonMethod(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, LoginException{
		req.setCharacterEncoding("UTF-8");
		String url = new URI(req.getHeader("referer")).getPath();
		if(url.contains("chatRoom")){
			String chatId = req.getParameter("chatId");
			url += "?chatId=" + chatId;
		}
		String action = req.getPathInfo();
		String method = req.getParameter("method");

		if(method == null || method.equals("")){
			method ="reload";
		}
		
		BaseAction controller = actionStore.get(action);
		BaseGetParameter getParameterAction = paramerterStore.get(action);
		HttpSession session = req.getSession();
		
		if(method.equals("register")){
			Object object = getParameterAction.register(req);
			if(session.getAttribute("error") == null){
				String returnUrl = controller.register(req, object);
				if(returnUrl != null){
					url = returnUrl;
				}
			}
		}else if(method.equals("modify")){
			Object object = getParameterAction.moidfy(req);
			if(session.getAttribute("error") == null){
				String returnUrl = controller.moidfy(req, object);
				if(returnUrl != null){
					url = returnUrl;
				}
			}
		}else if(method.equals("remove")){
			Object object = getParameterAction.remove(req);
			if(session.getAttribute("error") == null){
				String returnUrl = controller.remove(req, object);
				if(returnUrl != null){
				  
					url = returnUrl;
				}
			}
		}else if(method.equals("reload")){
			Object object = getParameterAction.reload(req);
			url = controller.reload(req, object);
			if(url.contains("error")){
				method = "error";
			}
		}

		if(method.equals("reload")){
			Forward forward = new Forward();
			forward.transiton(url, req, resp);
		}else{
			resp.sendRedirect(url);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			commonMethod(req, resp);
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("遷移エラー",e);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			commonMethod(req, resp);
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("遷移エラー",e);
		}
	}
}
