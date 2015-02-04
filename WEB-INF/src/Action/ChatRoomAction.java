package Action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Entity.AccountInfo;
import Entity.AllInfo;
import Entity.ChatContents;
import Entity.ChatRoomInfo;
import Entity.ErrorMessage;
import Interface.BaseAction;
import Interface.BaseLoginout;
import Model.GetLoginUserInfo;
import Model.Notice;
import Model.SelectDBManager;
import Model.UpdateDBManager;
import Model.SetRequest;

public class ChatRoomAction implements BaseAction,BaseLoginout{
	
	private static Logger logger = LoggerFactory.getLogger(ChatRoomAction.class.getName());
	private static ResourceBundle property = ResourceBundle.getBundle("SetPhrases");
	private static UpdateDBManager UpdateDBManager =  new UpdateDBManager();
	private static SelectDBManager SelectDBManager = new SelectDBManager();


	@Override
	public String register(HttpServletRequest req,Object ob) {
		// TODO Auto-generated method stub
		ArrayList<AllInfo> accountAllInfo = (ArrayList<AllInfo>) ob;
		int sqlResult = 0;
		String url = null;
			//DB入れる用変数
		String roomName = accountAllInfo.get(0).getRoomName();
		String roomPass = accountAllInfo.get(0).getRoomPass();
		List<Integer> chatMember = new ArrayList<Integer>();

		for(int i = 0; i < accountAllInfo.size();i++){
			chatMember.add(i, accountAllInfo.get(i).getAccountId());
		}
		try {
			sqlResult = Model.UpdateDBManager.creatChatRoom(roomName, chatMember,roomPass);
			int accountId = accountAllInfo.get(0).getAccountId();
			sqlResult = UpdateDBManager.giveAuthority(accountId);
			HttpSession session = req.getSession();
			String message = "チャットルームを作成しました";
			Notice notice = new Notice();
			String fromName = SelectDBManager.getAccountName(accountId);
			for(int i = 1; i < chatMember.size();i++){
				int memmberId = chatMember.get(i);
				try {
					
					notice.sendMail(memmberId, roomName, fromName);
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					logger.error(property.getString("sql"), e);
				}
			}
			session.setAttribute("result", message);
			url = property.getString("chatListServlet");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.warn(property.getString("sql"), e);
		}
		if(sqlResult == 0){
			logger.warn(property.getString("sqlInsert"));
		}else{
			logger.info(property.getString("SuccessSql"));
		}
		return url;
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
	public String reload(HttpServletRequest req, Object ob) {
		// TODO Auto-generated method stub
		AllInfo allInfo = (AllInfo) ob;
		String url = null;

		int chatId = allInfo.getChatId();
		int accountId = allInfo.getAccountId();
		
		if(chatId == 0){
			HttpSession session = req.getSession();
			chatId = (int) session.getAttribute("chatId");
		}
		int sqlResult = 0;
			try{
				sqlResult = Model.UpdateDBManager.insertChatRoomMember(chatId, accountId);
			}catch(SQLException e){
				
			}
		if(sqlResult != 0){
			String joinMessage = "チャットルームに参加しました。";
			String name = GetLoginUserInfo.getName(req);
			try {
				Model.UpdateDBManager.insertAdminContentJoin(chatId, name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.warn(property.getString("sql"), e);
			}
			HttpSession session = req.getSession();
			session.setAttribute("joinMessage", joinMessage);
		}
		try{
			List<ChatContents> chatContentsList = new ArrayList<ChatContents>();
			ArrayList<AccountInfo> chatMemberList = new ArrayList<AccountInfo>();
			ChatRoomInfo chatRoomInfo = new ChatRoomInfo();
			AllInfo authorityInfo = new AllInfo();
			ArrayList<AccountInfo> chatRoomfriendsList = new ArrayList<AccountInfo>();

			chatContentsList = Model.SelectDBManager.selectChatContents(chatId);
			chatMemberList = Model.SelectDBManager.selectChatRoomMember(chatId);
			chatRoomInfo = Model.SelectDBManager.selectChatInfo(chatId);
			authorityInfo = SelectDBManager.chechChatRoomAuthority(chatId, accountId);
			chatRoomfriendsList = Model.SelectDBManager.getChatRoomFriendsList(chatId,accountId);
			
			if(chatRoomfriendsList.size() == 0){
				logger.info("friendsListは空です");
				String tmp = null;
				req.setAttribute("friendsListIsNull", tmp);
			}else{
				logger.info("friendsListは中身が入ってます");
				int tmp = 1;
				req.setAttribute("friendsListIsNull", tmp);
			}
			
			//session から　requestにセット
			SetRequest setRequest = new SetRequest();
			setRequest.setError(req);
			setRequest.setStatus(req);
			setRequest.setJoinMessage(req);
			
			Collections.reverse(chatContentsList);
			req.setAttribute("friendsList", chatRoomfriendsList);
			req.setAttribute("chatContentsList", chatContentsList);
			req.setAttribute("chatMemberList", chatMemberList);
			req.setAttribute("chatRoomInfo", chatRoomInfo);
			req.setAttribute("authorityInfo", authorityInfo);
			url = property.getString("chatRoomJsp");
		}catch(SQLException e){
			url= property.getString("errorJsp");
			logger.error(property.getString("sql"), e);
		}
		return url;
	}

	@Override
	public String login(HttpServletRequest req, Object ob) {
		// TODO Auto-generated method stub
		
		AllInfo allInfo = (AllInfo) ob;
		int chatId = allInfo.getChatId();
		String roomPass = allInfo.getRoomPass();
		String accountName = allInfo.getName();
		int accountId = allInfo.getAccountId();
		
		int sqlResult = 0;
		SelectDBManager dbManager = new SelectDBManager();
		if(roomPass == null || roomPass.equals("")){
			try {
				sqlResult = dbManager.checkRoomPassIsNull(chatId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.warn(property.getString("sqlSelect"), e);
			}
		}else{
			try {
				sqlResult = dbManager.checkRoomPass(chatId, roomPass);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.warn(property.getString("sqlSelect"), e);
			}
		}

		String url = null;
		//sqlチェック
		if(sqlResult ==0){
			ErrorMessage error = new ErrorMessage();
			error.setMessage("パスワードが一致しません");
			HttpSession session = req.getSession();
			session.setAttribute("error", error);
			url = property.getString("chatListServlet");
		}else{
			boolean Result = false;
			try {
				Result = Model.SelectDBManager.checkRoomMemberOrNot(chatId, accountId);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(!Result){
				try {
					Model.UpdateDBManager.insertAdminContentJoin(chatId, accountName);
					Model.UpdateDBManager.insertChatRoomMember(chatId, accountId);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			req.setAttribute("chatId", chatId);
			url = property.getString("chatRoomServlet") + "?chatId=" + chatId;
		}
		return url;
	}

	@Override
	public String logout(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String reload(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}
}