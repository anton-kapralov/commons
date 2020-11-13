/*
 *
 *
 * Kapralov A.
 * 22.06.2010
 */

package kae.util;

import java.util.Calendar;
import java.util.Date;

/** @author A. Kapralov 22.06.2010 15:03:10 */
public class DateUtils {

  public static String toUnixTimeString(Date date) {
    return date != null ? String.valueOf(date.getTime() / 1000) : null;
  }

  public static Date extractDate(Date source) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(source);

    set(calendar, 0, 0, 0, 0);

    return calendar.getTime();
  }

  public static void set(Calendar calendar, int hour, int minute, int second, int millisecond) {
    calendar.set(Calendar.HOUR_OF_DAY, hour);
    calendar.set(Calendar.MINUTE, minute);
    calendar.set(Calendar.SECOND, second);
    calendar.set(Calendar.MILLISECOND, millisecond);
  }

  public static Date nextDate(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    calendar.add(Calendar.DATE, 1);

    return calendar.getTime();
  }

  public static Date previousDate(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    calendar.add(Calendar.DATE, -1);

    return calendar.getTime();
  }

  /**
   * Используется для тех случаев, когда тип даты является наследником от java.util.Date, но не
   * сериализуется.
   */
  public static Date recreate(Date source) {
    return source != null ? new Date(source.getTime()) : null;
  }
}
