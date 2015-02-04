package DoValidation;

import java.sql.SQLException;

import Entity.AllInfo;
import Entity.ErrorMessage;
import Model.SelectDBManager;

public class AccountManageValidation {
	public static ErrorMessage checkLoginAccount(AllInfo allInfo){
		ErrorMessage error = new ErrorMessage();
		
		String name = allInfo.getName();
		String pass = allInfo.getPass();
		boolean result = true;
		
		if(allInfo.getName() == null || allInfo.getName().equals("")){
			error.setMessage("名前が入力されていません");
			allInfo.setValidStatus(false);
		}else if(allInfo.getPass() == null || allInfo.getPass().equals("")){
			error.setMessage("パスワードが入力されていません");
			allInfo.setValidStatus(false);
		}else if(!(name.isEmpty() && pass.isEmpty())){
			try {
				result = SelectDBManager.checkOverlapAccout(name, pass);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(!result){
			error.setMessage("アカウント名かパスワードが違うようです");
			allInfo.setValidStatus(false);
		}
		return error;
	}
	
	public static ErrorMessage CreatAccountValidation(AllInfo allInfo){
		ErrorMessage error = new ErrorMessage();
		if(allInfo.getName() == null || allInfo.getName().equals("")){
			error.setMessage("名前を入力して下さい");
			allInfo.setValidStatus(false);
		}else if(allInfo.getMail() == null || allInfo.getMail().equals("")){
			error.setMessage("メールアドレスを入力して下さい");
			allInfo.setValidStatus(false);
		}else if(allInfo.getPass() == null || allInfo.getPass().equals("")){
			error.setMessage("パスワードを入力して下さい");
			allInfo.setValidStatus(false);
		}else if(allInfo.getPrefecture() == null || allInfo.getPrefecture().equals("")){
			error.setMessage("都道府県を入力して下さい");
			allInfo.setValidStatus(false);
		}else if(allInfo.getTel() == null || allInfo.getTel().equals("")){
			error.setMessage("電話番号を入力して下さい");
			allInfo.setValidStatus(false);
		}else if(allInfo.getAge() == 0){
			error.setMessage("年齢を入力して下さい");
			allInfo.setValidStatus(false);
		}
		return error;
	}
	
	public static ErrorMessage ChangeAccountValidation(AllInfo allInfo){
		ErrorMessage error = new ErrorMessage();
		if(allInfo.getMail() == null || allInfo.getMail().equals("")){
			error.setMessage("メールアドレスを入力して下さい");
			allInfo.setValidStatus(false);
		}else if(allInfo.getPass() == null || allInfo.getPass().equals("")){
			error.setMessage("パスワードを入力して下さい");
			allInfo.setValidStatus(false);
		}else if(allInfo.getPrefecture() == null || allInfo.getPrefecture().equals("")){
			error.setMessage("都道府県を入力して下さい");
			allInfo.setValidStatus(false);
		}else if(allInfo.getTel() == null || allInfo.getTel().equals("")){
			error.setMessage("電話番号を入力して下さい");
			allInfo.setValidStatus(false);
		}else if(allInfo.getAge() == 0){
			error.setMessage("年齢を入力して下さい");
			allInfo.setValidStatus(false);
		}
		return error;
	}	

}
