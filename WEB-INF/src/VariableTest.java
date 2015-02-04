import javax.mail.internet.AddressException;
import javax.servlet.http.*;

import Model.Notice;

public class VariableTest extends HttpServlet {
<<<<<<< HEAD
  public static void main(String[] args) {
  }
=======
	
	
	public static void main(String[] args) {
//		String message = "aaaaaaaaaaaaaimportantData=bbbbbbbbbbbbbbbbimportantData=ccccccccccccc";
//		String[] tmp = message.split("importantData=",0);
//		System.out.println(tmp[0]);
//		System.out.println(tmp[1]);
//		System.out.println(tmp[2]);
		
		String test1 = "おはようございます";
		System.out.println("test1" + test1);
		String test2 = test1.toString();
		System.out.println("test2" + test2);
		String test3 = test1;
		System.out.println("test3" + test3);
		test1 = "午後になりました";
		System.out.println("test1" + test1);
		System.out.println("test2" + test2);
		System.out.println("test3" + test3);

//
//		
//		
//		
//		Notice notice = new Notice();
//		int accountId = 0;
//		try {
//			notice.sendMail(accountId, null, null);
//		} catch (AddressException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	
//  public void doPost(HttpServletRequest req, 
//                     HttpServletResponse res) 
//                     throws ServletException, IOException {
//	List<MultipleInfo> parameterList = new ArrayList<MultipleInfo>();
//
//    //(1)アップロードファイルを格納するPATHを取得
////    String path = getServletContext().getRealPath("files");
//    String path = "D:\\development\\workspace\\ChatSystem\\files";
//
//    //(2)ServletFileUploadオブジェクトを生成
//    DiskFileItemFactory factory = new DiskFileItemFactory();
//    ServletFileUpload upload = new ServletFileUpload(factory);
//    
//    //(3)アップロードする際の基準値を設定
//    factory.setSizeThreshold(1024);
//    upload.setSizeMax(-1);
//    upload.setHeaderEncoding("UTF-8");
//
//    try {
//      //(4)ファイルデータ(FileItemオブジェクト)を取得し、
//      //   Listオブジェクトとして返す
//      List list = upload.parseRequest(req);
//
//      //(5)ファイルデータ(FileItemオブジェクト)を順に処理
//      Iterator iterator = list.iterator();
//      while(iterator.hasNext()){
//        FileItem fItem = (FileItem)iterator.next();
//      
//        //(6)ファイルデータの場合、if内を実行
//        if(!(fItem.isFormField())){
//          //(7)ファイルデータのファイル名(PATH名含む)を取得
//          String fileName = fItem.getName();
//          if((fileName != null) && (!fileName.equals(""))){
//            //(8)PATH名を除くファイル名のみを取得
//            fileName=(new File(fileName)).getName();
//            //(9)ファイルデータを指定されたファイルに書き出し
//            fItem.write(new File(path + "/" + fileName));
//
//          }
//        }else{
//        	MultipleInfo multipleInfo = new MultipleInfo();
//        	multipleInfo.setTitle(fItem.getFieldName());
//        	multipleInfo.setValue(fItem.getString());
//        	parameterList.add(multipleInfo);
//        }
//      }
//    }catch (FileUploadException e) {
//      e.printStackTrace();
//    }catch (Exception e) {
//      e.printStackTrace();
//    }
//    
//
//    //(10)upFile.htmlページに戻る
//    res.sendRedirect("/ChatSystem/loginout/reload");
//  }
>>>>>>> 7e2b1ad7c15112fff7deac8dcc727b49b88e35bd
}