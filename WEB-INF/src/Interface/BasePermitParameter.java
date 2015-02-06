package Interface;

import javax.servlet.http.HttpServletRequest;

public interface BasePermitParameter {
  abstract public Object permit(HttpServletRequest req);
}
