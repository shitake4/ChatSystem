package Entity;

public class ChatRoomInfo {
  // フィールド
  private int chatId = 0;
  private String roomName = null;

  // メソッド
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

}
