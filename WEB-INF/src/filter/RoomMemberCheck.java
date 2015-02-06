package filter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Entity.ErrorMessage;
import Exception.LoginException;
import Model.SelectDBManager;
import Model.TypeConvert;

//@WebFilter("/chat/chatRoom/*")
public class RoomMemberCheck implements Filter {

  private static Logger logger = LoggerFactory.getLogger(RoomMemberCheck.class
      .getName());
  private static SelectDBManager selectDBManager = new SelectDBManager();
  private static ResourceBundle propaty = ResourceBundle
      .getBundle("SetPhrases");

  @Override
  public void destroy() {
    // TODO Auto-generated method stub

  }

  @Override
  public void doFilter(ServletRequest arg0, ServletResponse arg1,
      FilterChain arg2) throws IOException, ServletException {
    // TODO Auto-generated method stub
    logger.info("メンバーチェックフィルタを通過しました");
    int chatId = TypeConvert.getIntChatId((HttpServletRequest) arg0);
    int accountId = 0;
    try {
      accountId = TypeConvert.getIntLoginAccountId((HttpServletRequest) arg0);
    } catch (LoginException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    boolean sqlResult = true;
    try {
      sqlResult = SelectDBManager.checkRoomMemberOrNot(chatId, accountId);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    if (!sqlResult) {
      logger.info("メンバにいない為、チャットリストに戻りました");
      HttpSession session = ((HttpServletRequest) arg0).getSession(true);
      ErrorMessage error = new ErrorMessage();
      error.setMessage("ルームメンバーから外れています");
      session.setAttribute("error", error);
      ((HttpServletResponse) arg1).sendRedirect(propaty
          .getString("chatListServlet"));
    } else {
      arg2.doFilter(arg0, arg1);
    }
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {
    // TODO Auto-generated method stub

  }

}
