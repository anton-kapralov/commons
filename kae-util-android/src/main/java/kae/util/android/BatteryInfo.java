/*
 *
 *
 * Kapralov A.
 * 06.05.13
 */

package kae.util.android;

/** @author A. Kapralov 06.05.13 18:05 */
public class BatteryInfo {

  private int chargeRate;

  private boolean charging;

  public BatteryInfo() {}

  public BatteryInfo(int chargeRate, boolean charging) {
    this.chargeRate = chargeRate;
    this.charging = charging;
  }

  public int getChargeRate() {
    return chargeRate;
  }

  public void setChargeRate(int chargeRate) {
    this.chargeRate = chargeRate;
  }

  public boolean isCharging() {
    return charging;
  }

  public void setCharging(boolean charging) {
    this.charging = charging;
  }
}
