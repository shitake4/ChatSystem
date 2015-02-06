package Interface;

import javax.servlet.http.HttpServletRequest;

public interface BaseSearchAction {
  abstract public String search(HttpServletRequest req, Object ob);
}
