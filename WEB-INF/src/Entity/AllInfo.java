package Entity;

public class AllInfo {
  // フィールド
  // accountTable
  private int accountId = 0;
  private String name = null;
  private String mail = null;
  private String pass = null;
  private String prefecture = null;
  private String tel = null;
  private int age = 0;

  // chat_roomTable
  private int chatId = 0;
  private String roomName = null;
  private String roomPass = null;

  // friends_listTable
  private int friendsAccountId = 0;

  // friends_request_listTable
  private int requestId = 0;

  // chat_contentsTable
  private String content = null;

  // room_member
  private int adminFlg = 0;

  // 転送用
  private String url = null;

  // バリデーション結果
  private boolean validStatus = true;

  // セッター、ゲッター
  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMail() {
    return mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  public String getPrefecture() {
    return prefecture;
  }

  public void setPrefecture(String prefecture) {
    this.prefecture = prefecture;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getChatId() {
    return chatId;
  }

  public void setChatId(int chatId) {
    this.chatId = chatId;
  }

  public String getRoomName() {
    return roomName;
  }

  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }

  public int getFriendsAccountId() {
    return friendsAccountId;
  }

  public void setFriendsAccountId(int friendsAccountId) {
    this.friendsAccountId = friendsAccountId;
  }

  public int getRequestId() {
    return requestId;
  }

  public void setRequestId(int requestId) {
    this.requestId = requestId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public boolean isValidStatus() {
    return validStatus;
  }

  public void setValidStatus(boolean validStatus) {
    this.validStatus = validStatus;
  }

  public String getRoomPass() {
    return roomPass;
  }

  public void setRoomPass(String roomPass) {
    this.roomPass = roomPass;
  }

  public int getAdminFlg() {
    return adminFlg;
  }

  public void setAdminFlg(int adminFlg) {
    this.adminFlg = adminFlg;
  }
}