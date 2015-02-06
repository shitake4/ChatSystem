package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriteManager {

  private FileWriteManager() {
    // TODO Auto-generated constructor stub
  }

  public static BufferedWriter CreatBufferedWriter(String fileName) {
    // TODO Auto-generated constructor stub
    File file = new File(fileName);
    FileWriter fileWriter = null;
    try {
      fileWriter = new FileWriter(file);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    return bufferedWriter;
  }
}
