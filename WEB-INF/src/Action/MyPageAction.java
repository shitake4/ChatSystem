package Action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Entity.AccountInfo;
import Entity.AllInfo;
import Entity.JoinChatRoom;
import Interface.BaseAction;
import Model.SelectDBManager;
import Model.SetRequest;

public class MyPageAction implements BaseAction{

	private static Logger logger = LoggerFactory.getLogger(MyPageAction.class.getName());
	private static ResourceBundle property = ResourceBundle.getBundle("SetPhrases");

	@Override
	public String register(HttpServletRequest req,Object ob) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String moidfy(HttpServletRequest req,Object ob) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String remove(HttpServletRequest req,Object ob) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String reload(HttpServletRequest req,Object ob) {
		// TODO Auto-generated method stub
		AllInfo allInfo = (AllInfo) ob;
		int accountId = allInfo.getAccountId();
		
		//初期化処理
		AllInfo accountAllInfo = new AllInfo();
		ArrayList<AccountInfo> friendsList = new ArrayList<AccountInfo>();
		ArrayList<AccountInfo> friendsRequestList = new ArrayList<AccountInfo>();
		ArrayList<JoinChatRoom> joinChatRoomList = new ArrayList<JoinChatRoom>();
		
		String url = null;
		try {
			accountAllInfo = SelectDBManager.selectAccountAllInfo(accountId);
			friendsList = SelectDBManager.getFriendsList(accountId);
			friendsRequestList = SelectDBManager.getFriendsRequestList(accountId);
			joinChatRoomList = SelectDBManager.selectJoinRoom(accountId);

			url = property.getString("myPageJsp");

			//session から　requestにセット
			SetRequest setRequest = new SetRequest();
			setRequest.setError(req);
			setRequest.setStatus(req);

			req.setAttribute("accountAllInfo", accountAllInfo);
			req.setAttribute("friendsRequestList", friendsRequestList);
			req.setAttribute("friendsList", friendsList);
			req.setAttribute("joinChatRoomList", joinChatRoomList);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.warn(property.getString("sql"), e);
			url = property.getString("errorJsp");
		}
		return url;
	}
}
