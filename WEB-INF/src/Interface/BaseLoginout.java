package Interface;

import javax.servlet.http.HttpServletRequest;

public interface BaseLoginout {

	abstract public String login(HttpServletRequest req, Object ob); 
	abstract public String logout(HttpServletRequest req);
	abstract public String reload(HttpServletRequest req);
}
