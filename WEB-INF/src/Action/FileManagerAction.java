package Action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
















import Entity.AllInfo;
import Entity.ChatContents;
import Entity.MultipleInfo;
import Model.FileWriteManager;
import Model.SelectDBManager;
import Model.TypeConvert;
import Model.UpdateDBManager;
import Model.getVariousDate;

public class FileManagerAction{
	
	private static List<MultipleInfo> parameterList = new ArrayList<MultipleInfo>();
	private static UpdateDBManager updateDBManager = new UpdateDBManager();
	private static SelectDBManager selectDBManager = new SelectDBManager();
	private static Logger logger = LoggerFactory.getLogger(LogInOutAction.class.getName());
	private static ResourceBundle propaty = ResourceBundle.getBundle("SetPhrases");


	public String upload(HttpServletRequest req){
  		String path = req.getServletContext().getRealPath("files");	
		DiskFileItemFactory factory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    
	    factory.setSizeThreshold(1024);
	    upload.setSizeMax(-1);
	    upload.setHeaderEncoding("UTF-8");
	    String filePath = null;
	    String fileName = null;
	    
	    try {
			List list = upload.parseRequest(req);
			Iterator iterator = list.iterator();
			
			while(iterator.hasNext()){
				FileItem fileItem = (FileItem)iterator.next();
				if(!(fileItem.isFormField())){
					fileName = fileItem.getName();
					if(!(fileItem == null || fileItem.equals(""))){
						fileName = (new File(fileName)).getName();
						try {
							fileItem.write(new File(path + "/" + fileName));
							filePath = path + "/" + fileName;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}else{
		        	MultipleInfo multipleInfo = new MultipleInfo();
		        	multipleInfo.setTitle(fileItem.getFieldName());
		        	multipleInfo.setValue(fileItem.getString());
		        	parameterList.add(multipleInfo);
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    int chatId = TypeConvert.ChatId(parameterList.get(0).getValue());
	    int accountId = TypeConvert.AccountId(parameterList.get(1).getValue());
	    req.setAttribute("chatId", chatId);
	    
	    int sqlResult = 0;
		try {
			sqlResult = UpdateDBManager.insertChatContent(chatId, fileName, accountId, filePath);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.warn(propaty.getString("sql"), e);
		}
		String url = null;
		if(sqlResult == 0){
			url = propaty.getString("errorJsp");
		}
	    return url;
	}

	public void download(HttpServletResponse resp,Object ob) throws UnsupportedEncodingException{
		AllInfo fileInfo = (AllInfo) ob;
		String fileName = fileInfo.getName();
		String encFileName = URLEncoder.encode(fileName, "UTF-8");
		String filePath = fileInfo.getUrl();
		resp.setContentType("application/force-download");
		resp.setHeader("Content-Disposition", "inline; filename=" + encFileName);
		logger.info("これからファイルダウンロードを行います");
		try {
			InputStream in = new FileInputStream(new File(filePath + fileName));
			OutputStream out = resp.getOutputStream();
			IOUtils.copy(in, out);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public AllInfo export(HttpServletRequest req,Object ob){
		int chatId = (int) ob;
		AllInfo fileInfo = new AllInfo();
		List<ChatContents> chatContents = new ArrayList<ChatContents>();
		try {
			chatContents = SelectDBManager.selectChatContents(chatId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String filePath = "D:\\development\\workspace\\ChatSystem\\files\\export\\";
		getVariousDate now = new getVariousDate();
		String fileName = now.yyyymmdd() + "_" + chatContents.get(0).getChatRoomName() + ".txt";
		
		fileInfo.setName(fileName);
		fileInfo.setUrl(filePath);
		BufferedWriter bufferedWriter = FileWriteManager.CreatBufferedWriter(filePath + fileName);
		logger.info("これからファイルの作成を致します");
		
		for(int chat_i = 0; chat_i<chatContents.size(); chat_i++){
			try {
				bufferedWriter.newLine();
				bufferedWriter.write("投稿番号：" + chat_i);
				bufferedWriter.newLine();
				bufferedWriter.write("-----------------------------------------------------");
				bufferedWriter.newLine();
				bufferedWriter.write("ルーム名：" + chatContents.get(chat_i).getChatRoomName());
				bufferedWriter.newLine();
				bufferedWriter.write("投稿時間：" + chatContents.get(chat_i).getTime());
				bufferedWriter.newLine();
				bufferedWriter.write("投稿者名：" + chatContents.get(chat_i).getName());
				bufferedWriter.newLine();
				bufferedWriter.write("内容：" + chatContents.get(chat_i).getReturnContent());
				bufferedWriter.newLine();
				bufferedWriter.write("-----------------------------------------------------");
				bufferedWriter.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			bufferedWriter.close();
			logger.info("ファイルを作成しました");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("ファイル作成に失敗しました", e);
		}
		return fileInfo;
	}
}
