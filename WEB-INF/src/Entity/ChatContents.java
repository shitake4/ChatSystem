package Entity;

public class ChatContents {
  String chatRoomName = null;
  String returnContent = null;
  String time = null;
  String name = null;
  String filePath = null;

  public String getChatRoomName() {
    return chatRoomName;
  }

  public void setChatRoomName(String chatRoomName) {
    this.chatRoomName = chatRoomName;
  }

  public String getReturnContent() {
    return returnContent;
  }

  public void setReturnContent(String returnContent) {
    this.returnContent = returnContent;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }
}
