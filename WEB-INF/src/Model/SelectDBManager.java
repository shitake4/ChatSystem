package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import Entity.AccountInfo;
import Entity.AllInfo;
import Entity.ChatContents;
import Entity.ChatRoomInfo;
import Entity.ChatRoomList;
import Entity.JoinChatRoom;

public class SelectDBManager extends BaseDBManager {

  private static Hash hash = new Hash();

  public static List<ChatRoomList> reloadChatRoom() throws SQLException {
    Connection conn = getConnection();
    String sql = "SELECT  rm.chat_id, a.name, cr.room_name "
        + "FROM (account a INNER JOIN room_member rm ON a.account_id = rm.account_id)"
        + "INNER JOIN chat_room cr ON rm.chat_id = cr.chat_id";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    ResultSet rs = pstmt.executeQuery();

    List<ChatRoomList> chatAllList = new ArrayList<ChatRoomList>();
    List<Integer> hashList = new ArrayList<Integer>();
    Map<Integer, ChatRoomList> chatListIndex = new HashMap<Integer, ChatRoomList>();
    while (rs.next()) {
      int a = rs.getInt("chat_id");
      if (chatListIndex.containsKey(a)) {
        chatListIndex.get(a).setChatMember(rs.getString("name"));
      } else {
        ChatRoomList chatList = new ChatRoomList();
        chatList.setChatId(rs.getInt("chat_id"));
        chatList.setRoomName(rs.getString("room_name"));
        chatList.setChatMember(rs.getString("name"));
        chatListIndex.put(a, chatList);
        hashList.add(a);
      }
    }
    Object[] keys = chatListIndex.keySet().toArray();
    Arrays.sort(keys);

    for (int i = 0; i < keys.length; i++) {
      chatAllList.add(chatListIndex.get(hashList.get(i)));
    }
    return chatAllList;
  }

  public static ArrayList<AccountInfo> selectChatRoomMember(int chatId)
      throws SQLException {
    Connection conn = getConnection();
    String sql = "SELECT  account.account_id,account.name,room_member.admin_flg FROM room_member "
        + "INNER JOIN account ON room_member.account_id = account.account_id WHERE room_member.chat_id = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, chatId);
    ResultSet rs = pstmt.executeQuery();
    ArrayList<AccountInfo> chatMemberList = new ArrayList<AccountInfo>();
    while (rs.next()) {
      AccountInfo chatMember = new AccountInfo();
      chatMember.setAccountId(rs.getInt("account_id"));
      chatMember.setName(rs.getString("name"));
      chatMember.setAdminFlg(rs.getInt("admin_flg"));
      chatMemberList.add(chatMember);
    }
    rs.close();
    conn.close();
    return chatMemberList;
  }

  public static ArrayList<AccountInfo> reloadMember() throws SQLException {
    Connection conn = getConnection();
    String sql = "SELECT account_id,name FROM account";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    ResultSet rs = pstmt.executeQuery();

    ArrayList<AccountInfo> memberList = new ArrayList<AccountInfo>();
    while (rs.next()) {
      AccountInfo accountInfo = new AccountInfo();
      accountInfo.setAccountId(rs.getInt("account_id"));
      accountInfo.setName(rs.getString("name"));
      memberList.add(accountInfo);
    }
    return memberList;
  }

  public static int selectLastChatId() throws SQLException {
    Connection conn = getConnection();
    String sql = "SELECT MAX(chat_id) FROM chat_room";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    ResultSet rs = pstmt.executeQuery();
    int lastChatId = 0;
    while (rs.next()) {
      lastChatId = rs.getInt(1);
    }
    return lastChatId;
  }

  public static boolean checkOverlapAccout(String name, String pass)
      throws SQLException {
    Connection conn = getConnection();
    String hashPass = Hash.encryptStr(pass);
    String sql = "SELECT account_id FROM account WHERE name=? AND pass=?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, name);
    pstmt.setString(2, hashPass);
    ResultSet rs = pstmt.executeQuery();

    boolean sqlResult = rs.last();
    return sqlResult;
  }

  public static ArrayList<ChatContents> selectChatContents(int chatId)
      throws SQLException {
    Connection conn = getConnection();
    String sql = "SELECT  cr.room_name,a.name,cc.content,cc.time,cc.file_path "
        + "FROM(chat_room cr INNER JOIN chat_contents cc ON cr.chat_id = cc.chat_id)"
        + "INNER JOIN account a ON cc.account_id = a.account_id "
        + "WHERE cr.chat_id = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, chatId);
    ResultSet rs = pstmt.executeQuery();
    ArrayList<ChatContents> chatContentsList = new ArrayList<ChatContents>();
    while (rs.next()) {
      ChatContents ChatContents = new ChatContents();
      ChatContents.setChatRoomName(rs.getString("room_name"));
      ChatContents.setReturnContent(rs.getString("content"));
      ChatContents.setTime(rs.getString("time"));
      ChatContents.setName(rs.getString("name"));
      ChatContents.setFilePath(rs.getString("file_path"));
      chatContentsList.add(ChatContents);
    }
    rs.close();
    conn.close();
    return chatContentsList;
  }

  public static AccountInfo getLoginAccountInfo(String name, String pass)
      throws SQLException {
    Connection conn = getConnection();
    String hashPass = Hash.encryptStr(pass);
    String sql = "SELECT account_id,name FROM chat.account WHERE name = ? AND pass = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, name);
    pstmt.setString(2, hashPass);
    ResultSet rs = pstmt.executeQuery();

    AccountInfo loginUserInfo = new AccountInfo();
    while (rs.next()) {
      loginUserInfo.setAccountId(rs.getInt("account_id"));
      loginUserInfo.setName(rs.getString("name"));
    }
    return loginUserInfo;
  }

  public static ChatRoomInfo selectChatInfo(int chatId) throws SQLException {
    Connection conn = getConnection();
    String sql = "SELECT chat_id,room_name FROM chat_room WHERE chat_id = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, chatId);
    ResultSet rs = pstmt.executeQuery();
    ChatRoomInfo chatRoomInfo = new ChatRoomInfo();
    while (rs.next()) {
      chatRoomInfo.setChatId(rs.getInt("chat_id"));
      chatRoomInfo.setRoomName(rs.getString("room_name"));
    }
    return chatRoomInfo;
  }

  public static AllInfo selectAccountAllInfo(int accountId) throws SQLException {
    // 置換
    String regex = "/";
    Pattern p = Pattern.compile(regex);

    Connection conn = getConnection();
    String sql = "SELECT * FROM account WHERE account_id = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, accountId);
    ResultSet rs = pstmt.executeQuery();
    AllInfo chatMember = new AllInfo();
    while (rs.next()) {
      chatMember.setAccountId(rs.getInt("account_id"));
      chatMember.setName(rs.getString("name"));
      chatMember.setMail(rs.getString("mail"));
      chatMember.setPrefecture(rs.getString("prefecture"));
      chatMember.setTel(rs.getString("tel"));
      chatMember.setAge(rs.getInt("age"));
      chatMember
          .setUrl(p.matcher(rs.getString("account_url")).replaceFirst(""));
    }
    rs.close();
    conn.close();
    return chatMember;
  }

  public static AllInfo getAccountAllInfo(String url) throws SQLException {
    Connection conn = getConnection();
    String sql = "SELECT * FROM account WHERE account_url = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, url);
    ResultSet rs = pstmt.executeQuery();
    AllInfo accountAllInfo = new AllInfo();
    while (rs.next()) {
      accountAllInfo.setAccountId(rs.getInt("account_id"));
      accountAllInfo.setName(rs.getString("name"));
      accountAllInfo.setMail(rs.getString("mail"));
      accountAllInfo.setPrefecture(rs.getString("prefecture"));
      accountAllInfo.setTel(rs.getString("tel"));
      accountAllInfo.setAge(rs.getInt("age"));
    }
    rs.close();
    conn.close();
    return accountAllInfo;
  }

  public static ArrayList<AccountInfo> getFriendsList(int accountId)
      throws SQLException {
    Connection conn = getConnection();
    String sql = "SELECT fl.friends_account_id,ac.name FROM friends_list fl INNER JOIN account ac ON fl.friends_account_id = ac.account_id WHERE fl.account_id = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, accountId);
    ResultSet rs = pstmt.executeQuery();
    ArrayList<AccountInfo> friendsList = new ArrayList<AccountInfo>();
    while (rs.next()) {
      AccountInfo friends = new AccountInfo();
      friends.setAccountId(rs.getInt("friends_account_id"));
      friends.setName(rs.getString("name"));
      friendsList.add(friends);
    }
    rs.close();
    conn.close();
    return friendsList;
  }

  public static ArrayList<AccountInfo> getChatRoomFriendsList(int chatId,
      int accountId) throws SQLException {
    Connection conn = getConnection();
    String sql = "select friends_list.friends_account_id,account.name from (friends_list INNER JOIN account on friends_list.friends_account_id = account.account_id)"
        + "left join(select * from room_member where chat_id= ?) members on friends_list.friends_account_id = members.account_id where friends_list.account_id = ? and members.account_id is null";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, chatId);
    pstmt.setInt(2, accountId);
    ResultSet rs = pstmt.executeQuery();
    ArrayList<AccountInfo> friendsList = new ArrayList<AccountInfo>();
    while (rs.next()) {
      AccountInfo friends = new AccountInfo();
      friends.setAccountId(rs.getInt("friends_account_id"));
      friends.setName(rs.getString("name"));
      friendsList.add(friends);
    }
    rs.close();
    conn.close();
    return friendsList;
  }

  public static ArrayList<AccountInfo> getFriendsRequestList(int accountId)
      throws SQLException {
    Connection conn = getConnection();
    String sql = "SELECT fl.request_account_id,ac.name FROM friends_request_list fl INNER JOIN account ac ON fl.request_account_id = ac.account_id WHERE fl.account_id = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, accountId);
    ResultSet rs = pstmt.executeQuery();
    ArrayList<AccountInfo> friendsList = new ArrayList<AccountInfo>();
    while (rs.next()) {
      AccountInfo friends = new AccountInfo();
      friends.setAccountId(rs.getInt("request_account_id"));
      friends.setName(rs.getString("name"));
      friendsList.add(friends);
    }
    rs.close();
    conn.close();
    return friendsList;
  }

  public static ArrayList<AccountInfo> getSendingFriendsRequestList(
      int accountId) throws SQLException {
    Connection conn = getConnection();
    String sql = "SELECT fl.account_id,ac.name FROM friends_request_list fl INNER JOIN account ac ON fl.account_id = ac.account_id WHERE fl.request_account_id = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, accountId);
    ResultSet rs = pstmt.executeQuery();
    ArrayList<AccountInfo> sendFriendsList = new ArrayList<AccountInfo>();
    while (rs.next()) {
      AccountInfo friends = new AccountInfo();
      friends.setAccountId(rs.getInt("account_id"));
      friends.setName(rs.getString("name"));
      sendFriendsList.add(friends);
    }
    rs.close();
    conn.close();
    return sendFriendsList;
  }

  public static ArrayList<JoinChatRoom> selectJoinRoom(int accountId)
      throws SQLException {
    Connection conn = getConnection();
    String sql = "SELECT chat_room.room_name,chat_room.chat_id FROM chat_room INNER JOIN room_member ON chat_room.chat_id = room_member.chat_id WHERE room_member.account_id = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, accountId);
    ResultSet rs = pstmt.executeQuery();
    ArrayList<JoinChatRoom> joinChatRoomList = new ArrayList<JoinChatRoom>();
    while (rs.next()) {
      JoinChatRoom joinChatRoom = new JoinChatRoom();
      joinChatRoom.setChatId(rs.getInt("chat_id"));
      joinChatRoom.setRoomName(rs.getString("room_name"));
      joinChatRoomList.add(joinChatRoom);
    }
    rs.close();
    conn.close();
    return joinChatRoomList;
  }

  public static boolean checkAbsenceChatRoomMember(int chatId)
      throws SQLException {
    Connection conn = getConnection();
    String sql = "SELECT * FROM room_member where chat_id = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, chatId);
    ResultSet rs = pstmt.executeQuery();
    int result = 0;
    rs.last();
    result = rs.getRow();

    boolean absence = true;
    if (result == 0) {
      absence = false;
    }
    return absence;
  }

  public static boolean checkRoomMemberOrNot(int chatId, int accountId)
      throws SQLException {
    Connection conn = getConnection();
    String sql = "SELECT * FROM room_member where chat_id = ? and account_id = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, chatId);
    pstmt.setInt(2, accountId);
    ResultSet rs = pstmt.executeQuery();
    int result = 0;
    rs.last();
    result = rs.getRow();

    boolean absence = true;
    if (result == 0) {
      absence = false;
    }
    return absence;
  }

  public int checkRoomPass(int chatId, String roomPass) throws SQLException {
    Connection conn = getConnection();
    String sql = "select chat_id from chat_room where chat_id = ? and room_pass = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, chatId);
    pstmt.setString(2, roomPass);
    ResultSet rs = pstmt.executeQuery();
    rs.last();
    int sqlResult = rs.getRow();
    return sqlResult;
  }

  public int checkRoomPassIsNull(int chatId) throws SQLException {
    Connection conn = getConnection();
    String sql = "select chat_id from chat_room where chat_id = ? and room_pass is null";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, chatId);
    ResultSet rs = pstmt.executeQuery();
    rs.last();
    int sqlResult = rs.getRow();
    return sqlResult;
  }

  public AllInfo chechChatRoomAuthority(int chatId, int accountId)
      throws SQLException {
    Connection conn = getConnection();
    String sql = "select admin_flg from room_member where account_id = ? and chat_id = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, accountId);
    pstmt.setInt(2, chatId);
    ResultSet rs = pstmt.executeQuery();
    AllInfo authorityInfo = new AllInfo();
    while (rs.next()) {
      authorityInfo.setAdminFlg(rs.getInt("admin_flg"));
    }
    return authorityInfo;
  }

  public AllInfo getMailAndName(int accountId) throws SQLException {
    Connection conn = getConnection();
    String sql = "select mail,name from account where account_id=?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, accountId);
    ResultSet rs = pstmt.executeQuery();
    AllInfo allInfo = new AllInfo();
    while (rs.next()) {
      allInfo.setMail(rs.getString("mail"));
      allInfo.setName(rs.getString("name"));
    }
    return allInfo;
  }

  public String getAccountName(int accountId) throws SQLException {
    Connection conn = getConnection();
    String sql = "select name from account where account_id = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, accountId);
    ResultSet rs = pstmt.executeQuery();
    String name = null;
    while (rs.next()) {
      name = rs.getString("name");
    }
    return name;
  }

  public List<AllInfo> searchAccountData(String searchName) throws SQLException {
    Connection conn = getConnection();
    String sql = "select name,account_url from account where name = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, searchName);
    ResultSet rs = pstmt.executeQuery();
    List<AllInfo> searchResult = new ArrayList<AllInfo>();
    while (rs.next()) {
      AllInfo searchAccountInfo = new AllInfo();
      searchAccountInfo.setName(rs.getString("name"));
      searchAccountInfo.setUrl(rs.getString("account_url"));
      searchResult.add(searchAccountInfo);
    }
    return searchResult;
  }

  public List<AllInfo> searchAllAccountData() throws SQLException {
    Connection conn = getConnection();
    String sql = "select name,account_url from account";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    ResultSet rs = pstmt.executeQuery();
    List<AllInfo> searchResult = new ArrayList<AllInfo>();
    while (rs.next()) {
      AllInfo searchAccountInfo = new AllInfo();
      searchAccountInfo.setName(rs.getString("name"));
      searchAccountInfo.setUrl(rs.getString("account_url"));
      searchResult.add(searchAccountInfo);
    }
    return searchResult;
  }

  public boolean checkAccountUrl(String accountUrl) throws SQLException {
    Connection conn = getConnection();
    String sql = "select account_url from account where account_url = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, accountUrl);
    ResultSet rs = pstmt.executeQuery();
    rs.last();
    if (rs.getRow() != 0) {
      return false;
    } else {
      return true;
    }
  }
}