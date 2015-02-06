package Interface;

import javax.servlet.http.HttpServletRequest;

public interface BaseAction {
  abstract public String register(HttpServletRequest req, Object ob);

  abstract public String moidfy(HttpServletRequest req, Object ob);

  abstract public String remove(HttpServletRequest req, Object ob);

  abstract public String reload(HttpServletRequest req, Object ob);
}
