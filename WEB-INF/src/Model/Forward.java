package Model;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Forward {

  private static Logger logger = LoggerFactory.getLogger(Forward.class
      .getName());

  public void transiton(String url, HttpServletRequest req,
      HttpServletResponse resp) {

    if (!(url == null || url.equals(""))) {
      RequestDispatcher dispatcher = req.getServletContext()
          .getRequestDispatcher(url);
      try {
        dispatcher.forward(req, resp);
      } catch (ServletException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        logger.debug("遷移エラー", e);

      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        logger.debug("遷移エラー", e);
      }
    } else {
      try {
        resp.sendRedirect("/error.jsp");
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        logger.debug("遷移エラー", e);
      }
    }

  }
}
