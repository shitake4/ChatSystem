package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebFilter("/chat/*")
public class LoginCheck implements Filter{

	private static Logger logger = LoggerFactory.getLogger(LoginCheck.class.getName());
 		
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		logger.info("ログインチェックを通過しました");
		HttpSession session = ((HttpServletRequest)arg0).getSession(true);
		Object loginUserInfo = session.getAttribute("loginUserInfo");
		if(loginUserInfo == null){
			String message = "セッションタイムアウトした為、ログイン画面に戻りました";
			session.setAttribute("result", message);
			((HttpServletResponse)arg1).sendRedirect("/ChatSystem/loginout/reload");
		}else{
			arg2.doFilter(arg0, arg1);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
