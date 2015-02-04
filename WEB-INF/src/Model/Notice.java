package Model;

import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.mail.internet.AddressException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Entity.AllInfo;

public class Notice {

	private static Logger logger = LoggerFactory.getLogger(Notice.class.getName());
	private static ResourceBundle propaty = ResourceBundle.getBundle("SetPhrases");
	private static SelectDBManager selectDBManager = new SelectDBManager();
	private static AllInfo allInfo = new AllInfo();
	
	public void sendMail(int accountId,String roomName,String fromName) throws AddressException{
		try {
			allInfo = selectDBManager.getMailAndName(accountId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(propaty.getString("sql"), e);
		}
		logger.info("これからメール処理に入ります");
		Mail mail = new Mail();
		String toMail = allInfo.getMail();
		mail.sendMail(roomName, fromName,toMail);
	}
}