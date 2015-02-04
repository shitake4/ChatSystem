package Controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xerces.internal.util.URI;
import com.sun.org.apache.xerces.internal.util.URI.MalformedURIException;

import Action.FileManagerAction;
import GetParameter.FileManagerParameter;

public class FileManagerController extends HttpServlet{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static FileManagerAction fileManage = new FileManagerAction();
	private static FileManagerParameter fileParameter = new FileManagerParameter();

	/**
	 * @param req
	 * @param resp
	 * @throws UnsupportedEncodingException
	 */
	private static void commonMethod(HttpServletRequest req,HttpServletResponse resp) throws UnsupportedEncodingException{
		req.setCharacterEncoding("UTF-8");
		String url = null;
		String action = req.getParameter("action");
		Object chatId = req.getParameter("chatId");
		
		//enctype(multipleを使っている為、null)
		if(action == null){
			HttpSession session = req.getSession();
			url = fileManage.upload(req);
			if(url == null || url.equals("")){
				try {
					url = new URI(req.getHeader("referer")).getPath();
				} catch (MalformedURIException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
				chatId = req.getAttribute("chatId");
				String message = "ファイルをアップロードしました";
				session.setAttribute("result", message);
		}else if(action.equals("download")){
			Object object = fileParameter.download(req);
			fileManage.download(resp,object);
		}else if(action.equals("export")){
			HttpSession session = req.getSession();
			FileManagerParameter parameter = new FileManagerParameter();
			Object object = parameter.export(req);
			if(session.getAttribute("error") == null){
				Object object2 = fileManage.export(req,object);
				fileManage.download(resp, object2);
			}
		}
		if(url == null){
			//ダウンロードの場合はコミット済みのため、ここを通過
		}else{
			if(url.contains("chatRoom")){
				try {
					resp.sendRedirect(url + "?chatId=" + chatId);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
				try {
					dispatcher.forward(req, resp);
				} catch (ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		commonMethod(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		commonMethod(req, resp);
	}
}
