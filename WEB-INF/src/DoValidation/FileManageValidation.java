package DoValidation;

import Entity.ErrorMessage;

public class FileManageValidation {
	
	public ErrorMessage chatId(int chatId){
		ErrorMessage error = new ErrorMessage();
		
		if(chatId == 0){
			error.setMessage("chatIdがゼロです。エラーが発生しております。");
		}
		return error;
	}
}
