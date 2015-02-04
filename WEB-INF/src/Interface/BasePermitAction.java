package Interface;

import javax.servlet.http.HttpServletRequest;

public interface BasePermitAction {
	abstract public String permit(HttpServletRequest req,Object object);
}
