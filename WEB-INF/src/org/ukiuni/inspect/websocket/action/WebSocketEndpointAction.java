package org.ukiuni.inspect.websocket.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.sun.org.apache.bcel.internal.generic.NEW;

import Action.ChatContentAction;
import Entity.AllInfo;
import Model.TypeConvert;
import Model.UpdateDBManager;

@ServerEndpoint("/chat/loadMessage")
public class WebSocketEndpointAction{
	public static List<Session> sessions = new ArrayList<Session>();

	@OnOpen
	public void onOpen(Session session) {
		// 開始時
		sessions.add(session);
	}

	@OnMessage
	public void onMessage(String message) throws IOException {
//		String[] tmp = message.split("importantData=",0);
//		message = tmp[0];
//		String tmpChatId = tmp[1];
//		String tmpAccountId =tmp[2];
//		int chatId = TypeConvert.ChatId(tmpChatId);
//		int accountId = TypeConvert.AccountId(tmpAccountId);
//		UpdateDBManager up = new UpdateDBManager();
//		String filePath = null;
//		try {
//			up.insertChatContent(chatId, message, accountId, filePath);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// クライアントからの受信時
		for (Session session : sessions) {
//			session.getBasicRemote().sendText("{\"command\":\"message\", \"text\": \"" + message.replace("\\", "\\\\").replace("\"", "\\\"") + "\"}");
			session.getBasicRemote().sendText(message);			
		}
<<<<<<< HEAD
=======
		String test1 = "おはようございます";
		System.out.println(test1);
		String test2 = test1.toString();
		String test3 = test1;
		test1 = "午後になりました";
>>>>>>> 7e2b1ad7c15112fff7deac8dcc727b49b88e35bd
		
	}

	@OnError
	public void onError(Throwable t) throws IOException {
		// エラー発生時
		for (Session session : sessions) {
			session.getBasicRemote().sendText("{\"command\":\"error\", \"text\": \"" + t.getMessage().replace("\\", "\\\\").replace("\"", "\\\"") + "\"}");
		}
	}

	@OnClose
	public void onClose(Session session) {
		// 完了時
		sessions.remove(session);
	}
}
