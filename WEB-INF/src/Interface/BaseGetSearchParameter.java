package Interface;

import javax.servlet.http.HttpServletRequest;

public interface BaseGetSearchParameter {
  abstract public Object search(HttpServletRequest req);
}
