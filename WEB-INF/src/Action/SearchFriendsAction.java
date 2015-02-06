package Action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Entity.AllInfo;
import Interface.BaseSearchAction;
import Model.SelectDBManager;

public class SearchFriendsAction implements BaseSearchAction {

  private static SelectDBManager selectDB = new SelectDBManager();
  private static ResourceBundle propaty = ResourceBundle
      .getBundle("SetPhrases");

  @Override
  public String search(HttpServletRequest req, Object ob) {
    // TODO Auto-generated method stub
    String searchName = (String) ob;
    List<AllInfo> searchResult = new ArrayList<AllInfo>();

    if (searchName == null || searchName.equals("")) {
      try {
        searchResult = selectDB.searchAllAccountData();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } else {
      try {
        searchResult = selectDB.searchAccountData(searchName);
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    String url = null;
    HttpSession session = req.getSession();
    if ((searchResult.get(0).getName() == null)
        && (searchResult.get(0).getUrl() == null)) {
      session.setAttribute("result", propaty.getString("searchResultNull"));
    } else {
      session.setAttribute("result", propaty.getString("searchResult"));
      session.setAttribute("searchResult", searchResult);
    }
    return url;
  }
}
