package Model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Entity.AllInfo;

public class SetRequest {

	public void setError(HttpServletRequest req){
		HttpSession session = req.getSession();
		Object error = session.getAttribute("error");
		if(!(error == null || error.equals(""))){
			req.setAttribute("error", error);
			session.removeAttribute("error");
		}
	}
	
	public void setStatus(HttpServletRequest req){
		HttpSession session = req.getSession();
		String result = (String) session.getAttribute("result");
		if(!(result == null || result.equals(""))){
			req.setAttribute("result", result);
			session.removeAttribute("result");
		}
	}
	
	public void setJoinMessage(HttpServletRequest req){
		HttpSession session = req.getSession();
		String joinMessage = (String) session.getAttribute("joinMessage");
		if(!(joinMessage == null || joinMessage.equals(""))){
			req.setAttribute("joinMessage", joinMessage);
			session.removeAttribute("joinMessage");
		}
	}
	
	public void setSearchResult(HttpServletRequest req){
		HttpSession session = req.getSession();
		List<AllInfo> searchResult = (List<AllInfo>) session.getAttribute("searchResult");
		if(searchResult != null){
			req.setAttribute("searchResult", searchResult);
			session.removeAttribute("searchResult");
		}
	}
}
