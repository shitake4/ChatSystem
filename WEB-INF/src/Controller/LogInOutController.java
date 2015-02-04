package Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Action.AccountManageAction;
import Action.ChatRoomAction;
import Action.LogInOutAction;
import Entity.AllInfo;
import GetParameter.SetAccountManageParameter;
import GetParameter.SetChatRoomParameter;
import GetParameter.SetLogInOutParameter;
import Interface.BaseAction;
import Interface.BaseGetLoginoutParameter;
import Interface.BaseGetParameter;
import Interface.BaseLoginout;

public class LogInOutController extends HttpServlet{

	private static Logger logger = LoggerFactory.getLogger(LogInOutController.class);
	private static ResourceBundle property = ResourceBundle.getBundle("SetPhrases");
	private static HashMap<String, BaseLoginout> actionStore = new HashMap<String, BaseLoginout>();
	private static HashMap<String, BaseGetLoginoutParameter> parameterStore = new HashMap<String, BaseGetLoginoutParameter>();
	
	static{
		actionStore.put("loginout", new LogInOutAction());
		actionStore.put("chatRoom", new ChatRoomAction());
		
		parameterStore.put("loginout", new SetLogInOutParameter());
		parameterStore.put("chatRoom", new SetChatRoomParameter());
	}
	
	private void commonMethod(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		
		req.setCharacterEncoding("UTF-8");
		String URI = req.getRequestURI();
		String action = null;
		if(URI.contains("chatRoom")){
			action = "chatRoom";
		}else if(URI.contains("loginout")){
			action = "loginout";
		}else if(URI.contains("signUp")){
			action = "loginout";
		}
		
		String method = req.getParameter("method");
		String url = null;
		
		if(method == null ||method.equals("")){
			method = "reload";
		}
		
		BaseLoginout controller = actionStore.get(action);
		BaseGetLoginoutParameter parameter = parameterStore.get(action);
		
		
		if(method.equals("register")){
			BaseGetParameter getAccountParameter = new SetAccountManageParameter();
			BaseAction accountBaseAction = new AccountManageAction();
			AllInfo allInfo = (AllInfo) getAccountParameter.register(req);
			url = accountBaseAction.register(req, allInfo);
		}else if(method.equals("login")){
			Object ob = parameter.login(req);
			url = controller.login(req, ob);
		}else if(method.equals("logout")){
			url = controller.logout(req);
		}else if(method.equals("reload")){
			url = controller.reload(req);
		}
		
		if(method.equals("reload")){
			RequestDispatcher dispatcher = req.getRequestDispatcher(url);
			dispatcher.forward(req, resp);
		}else{
			resp.sendRedirect(url);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp){
		// TODO Auto-generated method stub
		try {
			commonMethod(req, resp);
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.warn(property.getString("transition"), e);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp){
		// TODO Auto-generated method stub
		try {
			commonMethod(req, resp);
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.warn(property.getString("transition"), e);
		}
	}
}