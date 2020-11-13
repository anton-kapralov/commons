/*
 *
 *
 * Kapralov A.
 * 15.03.14
 */

package kae.util;

import java.util.Date;

/** @author A. Kapralov 15.03.14 13:43 */
public class TimeSpan {

  public final Date startDate;
  public final Date endDate;

  public TimeSpan(Date startDate, Date endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
