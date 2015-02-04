package DoValidation;

import java.util.List;

import Entity.AllInfo;
import Entity.ErrorMessage;

public class RoomMemberValidation {

	public static ErrorMessage RoomMemberValidation(AllInfo allInfo){
		ErrorMessage error = new ErrorMessage();
		
		if(allInfo.getAccountId() == 0){
			error.setMessage("アカウントIdが空です。不正なエラーです。");
			allInfo.setValidStatus(false);
		}else if(allInfo.getChatId() == 0){
			error.setMessage("チャットIdが空です。不正なエラーです");
			allInfo.setValidStatus(false);
		}
		return error;
	}
	
	public static ErrorMessage checkAccountIdValidation(List<AllInfo> list){
		ErrorMessage error = new ErrorMessage();
		
		for(int i = 0; i<list.size(); i++){
			if(list.get(i).getAccountId() == 0){
				error.setMessage("アカウントIdが空です。不正なエラーです。");
			}
		}
		return error;
	}
	
	

}
