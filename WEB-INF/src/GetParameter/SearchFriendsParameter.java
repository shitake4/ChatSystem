package GetParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DoValidation.SearchAccountValidation;
import Entity.ErrorMessage;
import Interface.BaseGetSearchParameter;

public class SearchFriendsParameter implements BaseGetSearchParameter{

	@Override
	public Object search(HttpServletRequest req) {
		// TODO Auto-generated method stub
		String searchName = req.getParameter("searchName");
		
		//バリデーション
		SearchAccountValidation  validation = new SearchAccountValidation();
		ErrorMessage error =  SearchAccountValidation.searchAccount(searchName);
		if(!(error.isEmpty())){
			HttpSession session = req.getSession();
			session.setAttribute("error", error);
			
		}
		return searchName;
	}

}
