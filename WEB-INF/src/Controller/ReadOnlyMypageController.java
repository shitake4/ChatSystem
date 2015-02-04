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

import Action.ReadOnlyMypageAction;
import Exception.LoginException;
import GetParameter.ReadOnlyMypageParameter;
import Interface.BaseAction;
import Interface.BaseGetParameter;
import Model.Forward;

public class ReadOnlyMypageController extends HttpServlet{
	private static HashMap<String, Interface.BaseAction> actionStore = new HashMap<String,Interface.BaseAction>();
	private static HashMap<String, Interface.BaseGetParameter> paramerterStore = new HashMap<String, BaseGetParameter>();
	private static Logger logger = LoggerFactory.getLogger(ReadOnlyMypageController.class.getName());
	
	static{
		actionStore.put("/read", new ReadOnlyMypageAction());
		
		paramerterStore.put("/read", new ReadOnlyMypageParameter());
	}
	
	
	private static void commonMethod(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, LoginException{
		req.setCharacterEncoding("UTF-8");
//		String url = new URI(req.getHeader("referer")).getPath();
//		String action = req.getPathInfo();
		String url = null;
		String action = req.getServletPath();
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
