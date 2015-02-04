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

import Action.SearchFriendsAction;
import Exception.LoginException;
import GetParameter.SearchFriendsParameter;
import Interface.BaseGetSearchParameter;
import Interface.BaseSearchAction;

public class SearchController extends HttpServlet{
	private static HashMap<String, BaseSearchAction> actionStore = new HashMap<String,Interface.BaseSearchAction>();
	private static HashMap<String, Interface.BaseGetSearchParameter> paramerterStore = new HashMap<String, BaseGetSearchParameter>();
	private static Logger logger = LoggerFactory.getLogger(SearchController.class.getName());
	
	static{
		actionStore.put("searchFriends", new SearchFriendsAction());
		
		paramerterStore.put("searchFriends", new SearchFriendsParameter());
	}
	
	
	private static void commonMethod(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, LoginException{
		req.setCharacterEncoding("UTF-8");
		String url = new URI(req.getHeader("referer")).getPath();
		if(url.contains("chatRoom")){
			String chatId = req.getParameter("chatId");
			url += "?chatId=" + chatId;
		}
		String action = req.getParameter("action");
		String method = req.getParameter("method");
		
		BaseSearchAction controller = actionStore.get(action);
		BaseGetSearchParameter getParameterAction = paramerterStore.get(action);
		HttpSession session = req.getSession();
		
		if(method.equals("search")){
			Object object = getParameterAction.search(req);
			if(session.getAttribute("error") == null){
				String returnUrl = controller.search(req, object);
				if(returnUrl != null){
					url = returnUrl;
				}
			}
		}
		resp.sendRedirect(url);
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
