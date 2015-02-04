package Interface;

import javax.servlet.http.HttpServletRequest;

public interface BaseGetParameter {
	abstract public Object register(HttpServletRequest req);
	abstract public Object moidfy(HttpServletRequest req);
	abstract public Object remove(HttpServletRequest req);
	abstract public Object reload(HttpServletRequest req);
}
