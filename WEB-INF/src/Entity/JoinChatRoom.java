package Entity;

public class JoinChatRoom {
	
	//フィールド
	private int chatId = 0;
	private String roomName = null;

	//セッター、ゲッター
	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getChatId() {
		return chatId;
	}

	public void setChatId(int chatId) {
		this.chatId = chatId;
	}
	
}
