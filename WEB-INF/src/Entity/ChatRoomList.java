package Entity;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomList {
  // フィールド
  int chatId = 0;
  String roomName = null;
  List<String> chatMember = new ArrayList<String>();

  // セッター、ゲッター
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

  public List<String> getChatMember() {
    return chatMember;
  }

  public void setChatMember(String name) {
    this.chatMember.add(name);
  }

}
