package GetParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DoValidation.FileManageValidation;
import Entity.AllInfo;
import Entity.ErrorMessage;
import Model.TypeConvert;

public class FileManagerParameter {

  private static FileManageValidation validation = new FileManageValidation();

  public AllInfo download(HttpServletRequest req) {
    String filePath = req.getParameter("filePath");
    String fileName = req.getParameter("fileName");

    AllInfo fileInfo = new AllInfo();
    fileInfo.setName(fileName);
    fileInfo.setUrl(filePath);

    return fileInfo;
  }

  public int export(HttpServletRequest req) {
    int chatId = TypeConvert.getIntChatId(req);

    ErrorMessage error = validation.chatId(chatId);
    if (!(error.isEmpty())) {
      HttpSession session = req.getSession();
      session.setAttribute("error", error);
    }

    return chatId;
  }
}
