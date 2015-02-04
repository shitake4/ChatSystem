package Action;

import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Entity.AccountInfo;
import Entity.AllInfo;
import Interface.BaseLoginout;
import Model.SelectDBManager;
import Model.SetRequest;

public class LogInOutAction implements BaseLoginout{
	
	private static Logger logger = LoggerFactory.getLogger(LogInOutAction.class.getName());
	private static ResourceBundle property = ResourceBundle.getBundle("SetPhrases");

	@Override
	public String login(HttpServletRequest req, Object ob){
		String url = null;
		AllInfo allInfo = (AllInfo) ob;

		String name = allInfo.getName();
		String pass = allInfo.getPass();
		
		if(allInfo.isValidStatus()){
			AccountInfo loginUserInfo = null;
			try {
				loginUserInfo = SelectDBManager.getLoginAccountInfo(name, pass);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.warn(property.getString("sql"), e);
			}
			
			if(loginUserInfo.getAccountId() == 0 || loginUserInfo.getName() == null || loginUserInfo.getName().equals("")){
				url = property.getString("errorJsp");
			}else{
				HttpSession validate = req.getSession(true);
				validate.invalidate();
				HttpSession session = req.getSession(true);
				session.setAttribute("loginUserInfo", loginUserInfo);
				session.removeAttribute("error");
				session.removeAttribute("result");
				url = property.getString("chatListServlet");
			}
		}else if(!(allInfo.isValidStatus())){
			url = property.getString("loginServlet");
		}
		return url;
	}
	
	@Override
	public String logout(HttpServletRequest req){
		String url = null;
		HttpSession session = req.getSession();
		session.invalidate();
		session = req.getSession(false);
		
		if(session == null){
			logger.info("セッションが破棄されました");
			session = req.getSession();
			String message = "ログアウトしました";
			session.setAttribute("result", message);
			url = property.getString("loginServlet");
		}else{
			logger.info("セッションが残っています");
			url = property.getString("errorJsp");
		}
		return url;
	}
	
	@Override
	public String reload(HttpServletRequest req){
		SetRequest setRequest = new SetRequest();
		setRequest.setError(req);
		setRequest.setStatus(req);
		String url = property.getString("loginJsp");
		return url;
	}
}