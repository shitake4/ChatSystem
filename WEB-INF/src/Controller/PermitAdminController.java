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

import Action.RoomMemberAction;
import Exception.LoginException;
import GetParameter.SetRoomMemberParameter;
import Interface.BasePermitAction;
import Interface.BasePermitParameter;
import Model.Forward;

public class PermitAdminController extends HttpServlet{
	private static HashMap<String, Interface.BasePermitAction> actionStore = new HashMap<String,Interface.BasePermitAction>();
	private static HashMap<String, Interface.BasePermitParameter> paramerterStore = new HashMap<String, BasePermitParameter>();
	private static Logger logger = LoggerFactory.getLogger(PermitAdminController.class.getName());
	
	static{
		actionStore.put("/roomMember", new RoomMemberAction());
		
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
		
		BasePermitAction controller = actionStore.get(action);
		BasePermitParameter getParameterAction = paramerterStore.get(action);
		HttpSession session = req.getSession();
		
		if(method.equals("permit")){
			Object object = getParameterAction.permit(req);
			if(session.getAttribute("error") == null){
				String returnUrl = controller.permit(req,object);
				if(returnUrl != null){
					url = returnUrl;
				}
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
