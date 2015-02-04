package Model;

import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mail {

	private static Logger logger = LoggerFactory.getLogger(Notice.class.getName());

	public void sendMail(String roomName,String fromName,String toMail) throws AddressException{
		logger.info("--------------------------------------メール処理開始--------------------------------------");
		//メール送信
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.host", "localhost");
		Session session = Session.getDefaultInstance(properties);
		
		MimeMessage mimeMessage = new MimeMessage(session);
		
		InternetAddress toAddress = new InternetAddress(toMail);
		logger.info("--------------------------------------あて先設定--------------------------------------");
		 try{
		      // 宛先の設定
		      mimeMessage.setRecipient(RecipientType.TO, toAddress);
		      // 送信元の設定
		      mimeMessage.setFrom(new InternetAddress("root@testserver.weserve.co.jp"));
		      // サブジェクトの設定
		      mimeMessage.setSubject(fromName + "さんがあなたをルームに追加しました");
		      // 本文の設定
			      mimeMessage.setText(""
				      		+ fromName + "さんがあなたを" + "\n"
				      		+ "ルーム：" + roomName
				      		+ "のメンバーに追加しました。" + "\n"
				      		);
//			      mimeMessage.setText(new Date().toString());
		      // 設定の保存
		      mimeMessage.saveChanges();
		      // メールの送信
		      Transport.send(mimeMessage);
		      logger.info("メール送信完了");
		    }catch(MessagingException e){
			  logger.error("送信失敗", e);
		      return;
		    }
		
	}
}
