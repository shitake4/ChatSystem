package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseDBManager {

  private static Logger logger = LoggerFactory.getLogger(BaseDBManager.class
      .getName());
  static {
    /*
     * ドライバーの設定からMysqlの接続まで
     */
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
    } catch (InstantiationException | IllegalAccessException
        | ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      logger.warn("データベース接続エラー", e);
    }
  }

  protected static Connection getConnection() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost/chat", "root",
          "root");
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      logger.warn("データベース接続エラー", e);
    }
    return conn;
  }
}
