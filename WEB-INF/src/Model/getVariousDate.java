package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class getVariousDate {

  private Date now = new Date();

  public Object yyyymmdd() {
    SimpleDateFormat format = new SimpleDateFormat("yyyymmdd");
    Object date = format.format(now);
    return date;
  }

  public Object yyyy_mm_dd_ja() {
    SimpleDateFormat format = new SimpleDateFormat("yyyy年mm月dd日");
    Object date = format.format(now);
    return date;
  }

  public Object yyyy_mm_dd_HH_mm_ss() {
    SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd HH:mm:ss");
    Object date = format.format(now);
    return date;
  }
}
