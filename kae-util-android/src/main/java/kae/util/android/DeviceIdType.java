/*
 *
 *
 * Kapralov A.
 * 04.06.13
 */

package kae.util.android;

/** @author A. Kapralov 04.06.13 13:46 */
public enum DeviceIdType {
  ANDROID_ID(0),

  IMEI(1),

  IMSI(2),

  UUID(1024);

  public final int id;

  private DeviceIdType(int id) {
    this.id = id;
  }
}
