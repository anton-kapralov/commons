/*
 *
 *
 * Kapralov A.
 * 04.06.13
 */

package kae.util.android;

/**
 * @author A. Kapralov
 * 04.06.13 15:50
 */
public class DeviceId {

  public final DeviceIdType type;

  public final String value;

  public DeviceId(DeviceIdType type, String value) {
    this.type = type;
    this.value = value;
  }

}
