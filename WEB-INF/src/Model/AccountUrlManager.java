package Model;

import java.sql.SQLException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Action.AccountManageAction;
import Entity.AllInfo;

public class AccountUrlManager {

	private static Logger logger = LoggerFactory.getLogger(AccountManageAction.class.getName());
	private static ResourceBundle property = ResourceBundle.getBundle("SetPhrases");
	private static SelectDBManager selectDb = new SelectDBManager();
	
	public int insertAccountUrl(Object ob){

		AllInfo allInfo = (AllInfo) ob;
		
		String name = allInfo.getName();
		String mail = allInfo.getMail();
		String pass = allInfo.getPass();
		String prefecture = allInfo.getPrefecture();
		String tel = allInfo.getTel();
		int age = allInfo.getAge();
		

		boolean result = false;
		String accountUrl = null;
		
		for(int sql_i = 0; !result ;sql_i++){
			accountUrl = "/" + name + sql_i;
			try {
				result = selectDb.checkAccountUrl(accountUrl);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		int methodResult = 0;
		try {
			methodResult = UpdateDBManager.insertAccount(name, mail, pass, prefecture, tel, age, accountUrl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.warn(property.getString("sql"), e);
			methodResult = 0;
		}
	return methodResult;
	}
}
