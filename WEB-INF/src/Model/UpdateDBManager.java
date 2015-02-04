package Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UpdateDBManager extends BaseDBManager{

	private static Hash hash = new Hash();
	
	private static Logger logger = LoggerFactory.getLogger(UpdateDBManager.class.getName());
	
	//定数
	public static final String ADMIN_NAME = "チャットシステム";
	public static final String LEAVE_ROOM = "さんが退出しました";
	public static final String JOIN_ROOM = "さんが参加しました";
	public static final int ADMIN_CHAT_ID = 12;
	
	private static SelectDBManager selectDBManager = new SelectDBManager();
	
	public static int insertAccount(String name,String mail,String pass,String prefecture,String tel,int age,String accountUrl) throws SQLException{
		Connection conn = getConnection();
		String hashPass = Hash.encryptStr(pass);
		String sql = "INSERT into account (name,mail,pass,prefecture,tel,age,account_url) values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,name);
		pstmt.setString(2,mail);
		pstmt.setString(3,hashPass);
		pstmt.setString(4,prefecture);
		pstmt.setString(5,tel);
		pstmt.setInt(6,age);
		pstmt.setString(7, accountUrl);
		int sqlResult = pstmt.executeUpdate();
		conn.close();
		return sqlResult;
	}

	public static int creatChatRoom(String roomName,List<Integer> chatMember,String roomPass) throws SQLException{
		int lastChatId = SelectDBManager.selectLastChatId();
		lastChatId += 1;
		Connection conn = getConnection();
		String sql = "INSERT into chat_room(chat_id,room_name,room_pass) values(?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, lastChatId);
		pstmt.setString(2, roomName);
		pstmt.setString(3, roomPass);
		int sqlResult = pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		
		sqlResult = insertInitialChatRoomMember(lastChatId, chatMember);
		return sqlResult;
	}
	
	private static int insertInitialChatRoomMember(int lastChatId,List<Integer> chatMember) throws SQLException{
		int sqlResult = 0;
		for(int i =0; i<chatMember.size();i++){
			Connection conn = getConnection();
			String sql = "INSERT into room_member(chat_id,account_id) values(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lastChatId);
			pstmt.setLong(2, chatMember.get(i));
			sqlResult = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}
		return sqlResult;
	}
	
	public int giveAuthority(int accountId) throws SQLException{
		int chatId = SelectDBManager.selectLastChatId();
		Connection conn = getConnection();
		String sql ="update room_member set admin_flg = 1 where chat_id= ? and account_id= ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, chatId);
		pstmt.setInt(2, accountId);
		int sqlResult = pstmt.executeUpdate();
		return sqlResult;
	}
	
	
	public int deprivationAuthority(int chatId,int accountId) throws SQLException{
		Connection conn = getConnection();
		String sql ="update room_member set admin_flg = 0 where chat_id= ? and account_id= ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, chatId);
		pstmt.setInt(2, accountId);
		int sqlResult = pstmt.executeUpdate();
		return sqlResult;
	}
	
	public static int insertChatContent(int chatId,String content,int accountId,String filePath) throws SQLException{
		Connection conn = getConnection();
		String sql = "INSERT into chat_contents(chat_id,content,time,account_id,file_path)VALUES(?,?,?,?,?);";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, chatId);
		pstmt.setString(2, content);
		pstmt.setTimestamp(3, getTime());
		pstmt.setInt(4, accountId);
		pstmt.setString(5,filePath);
		int sqlResult = pstmt.executeUpdate();
		conn.close();
		return sqlResult;
	}
	
	public static int insertAdminConetentLeave(int chatId,String name) throws SQLException{
		String uniteContent = name+LEAVE_ROOM;
		Connection conn = getConnection();
		String sql = "INSERT into chat_contents(chat_id,content,time,account_id) VALUES(?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, chatId);
		pstmt.setString(2, uniteContent);
		pstmt.setTimestamp(3, getTime());
		pstmt.setInt(4, ADMIN_CHAT_ID);
		int sqlResult = pstmt.executeUpdate();
		return sqlResult;
	}
	
	public static void insertAdminContentJoin(int chatId,String name) throws SQLException{
		String uniteContent = name+JOIN_ROOM;
		Connection conn = getConnection();
		String sql = "INSERT into chat_contents(chat_id,content,time,account_id) VALUES(?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, chatId);
		pstmt.setString(2, uniteContent);
		pstmt.setTimestamp(3, getTime());
		pstmt.setInt(4, ADMIN_CHAT_ID);
		pstmt.executeUpdate();
		return;
	}
	
	private static Timestamp getTime(){
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		return timeStamp;
	}
	
	public static int changeAccountInfo(String mail,String pass,String prefecture,String tel,int age, int accountId,String accountUrl) throws SQLException{
		Connection conn = getConnection();
		String hashPass = Hash.encryptStr(pass);
		String sql = "UPDATE account SET mail = ?, pass = ?, prefecture = ?,tel = ?,age = ? ,account_url = ? where account_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mail);
		pstmt.setString(2, hashPass);
		pstmt.setString(3, prefecture);
		pstmt.setString(4, tel);
		pstmt.setInt(5, age);
		pstmt.setString(6, accountUrl);
		pstmt.setInt(7, accountId);
		int sqlResult = pstmt.executeUpdate();
		conn.close();
		return sqlResult;
	}
	
	public static int changeAccountInfoNonPass(String mail,String prefecture,String tel,int age, int accountId,String accountUrl) throws SQLException{
		Connection conn = getConnection();
		String sql = "UPDATE account SET mail = ?, prefecture = ?,tel = ?,age = ?, account_url = ? where account_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mail);
		pstmt.setString(2, prefecture);
		pstmt.setString(3, tel);
		pstmt.setInt(4, age);
		pstmt.setString(5, accountUrl);
		pstmt.setInt(6, accountId);
		int sqlResult = pstmt.executeUpdate();
		conn.close();
		return sqlResult;
	}

	public static int insertChatRoomMember(int chatId,int accountId) throws SQLException{
		Connection conn = getConnection();
		String sql = "insert into room_member(chat_id,account_id) values (?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, chatId);
		pstmt.setInt(2, accountId);
		int sqlResult = pstmt.executeUpdate();
		conn.close();
		return sqlResult;
	}
	
	public static int deleteChatRoomMember(int chatId,int accountId) throws SQLException{
		Connection conn = getConnection();
		String sql = "delete from room_member WHERE room_member.chat_id = ? AND room_member.account_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, chatId);
		pstmt.setInt(2, accountId);
		int sqlResult = pstmt.executeUpdate();
		conn.close();
		return sqlResult;
	}
	
	public static void deleteChatRoom(int chatId) throws SQLException{
		Connection conn = getConnection();
		String sql = "delete FROM chat.chat_room where chat_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, chatId);
		pstmt.executeUpdate();
		return;
	}
	
	public static int requestFriend(int accountId,int friendsAccountId) throws SQLException{
		Connection conn = getConnection();
		String sql = "INSERT INTO friends_request_list values(?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, friendsAccountId);
		pstmt.setInt(2, accountId);
		int sqlResult = pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		return sqlResult;
	}
	
	public static int agreeFriendRequest(int accountId,int friendsAccountId){
		try {
			deleteFriendsRequest(accountId, friendsAccountId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.warn("sqlエラー", e);
		}

		int sqlResult = 0;
		
		try {
			insertMyFriendList(accountId, friendsAccountId);
			insertOtherFriendList(accountId, friendsAccountId);
			sqlResult = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.warn("sqlエラー", e);
		}
		return sqlResult;
	}
	
	private static void deleteFriendsRequest(int accountId,int friendsAccountId) throws SQLException{
		Connection conn = getConnection();
		String sql = "DELETE from friends_request_list WHERE account_id = ? AND request_account_id = ?;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, accountId);
		pstmt.setInt(2, friendsAccountId);
		pstmt.executeUpdate();
		conn.close();
		return;
	}
	
	public static int quitFriends(int accountId, int friendsAccountId){
		int sqlResult = 0;
		try {
			deleteMyfriendsList(accountId, friendsAccountId);
			deleteOtherfriendsList(accountId, friendsAccountId);
			sqlResult = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.warn("sqlエラー", e);
		}
		
		return sqlResult;
	}
	
	private static void deleteMyfriendsList(int accountId,int friendsAccountId) throws SQLException{
		Connection conn = getConnection();
		String sql = "DELETE from friends_list WHERE account_id = ? AND friends_account_id = ?;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, accountId);
		pstmt.setInt(2, friendsAccountId);
		pstmt.executeUpdate();
		conn.close();
		return;
	}
	
	private static void deleteOtherfriendsList(int accountId,int friendsAccountId) throws SQLException{
		Connection conn = getConnection();
		String sql = "DELETE from friends_list WHERE account_id = ? AND friends_account_id = ?;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, friendsAccountId);
		pstmt.setInt(2, accountId);
		pstmt.executeUpdate();
		conn.close();
		return;
	}
	
	private static void insertMyFriendList(int accountId,int friendsAccountId) throws SQLException{
		Connection conn = getConnection();
		String sql = "INSERT INTO friends_list values(?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, accountId);
		pstmt.setInt(2, friendsAccountId);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		return;
	}

	private static void insertOtherFriendList(int accountId,int friendsAccountId) throws SQLException{
		Connection conn = getConnection();
		String sql = "INSERT INTO friends_list values(?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, friendsAccountId);
		pstmt.setInt(2, accountId);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		return;
	}
	
	public int giveAdminAuthority(int chatId,int accountId) throws SQLException{
		Connection conn = getConnection();
		String sql = "update room_member set admin_flg = 1 where chat_id = ? and account_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, chatId);
		pstmt.setInt(2, accountId);
		int sqlResult = pstmt.executeUpdate();
		return sqlResult;
	}
}
