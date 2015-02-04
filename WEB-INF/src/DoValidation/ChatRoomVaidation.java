package DoValidation;

import java.util.ArrayList;

import Entity.AllInfo;
import Entity.ErrorMessage;

public class ChatRoomVaidation {
	
	public static ErrorMessage validationRegisterChatRomm(ArrayList<AllInfo> allInfoList){
		ErrorMessage error = new ErrorMessage();
			for(int i = 0; i <allInfoList.size(); i++){
				if(allInfoList.get(i).getRoomName() == null || allInfoList.get(i).getRoomName().equals("")){
					error.setMessage("ルーム名を入力してください");
					allInfoList.get(i).setValidStatus(false);
				}
				if(allInfoList.get(i).getAccountId() == 0){
					error.setMessage("チャットメンバーがいません");
					allInfoList.get(i).setValidStatus(false);
				}
			}
		return error;
	}
}