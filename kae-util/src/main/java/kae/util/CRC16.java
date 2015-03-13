package kae.util;

/**
 * CRC16
 *
 * @author Kapralov A.
 *         07.04.2014 12:48
 */
public class CRC16 {

  /**
   * value contains the currently computed CRC, set it to 0 initally
   */
  public int value;

  public CRC16() {
    value = 0;
  }

  /**
   * update CRC with byte b
   */
  public void update(byte aByte) {
    value = update(value, aByte);
  }

  private static int update(int value, byte aByte) {
    int a, b;

    a = (int) aByte;
    for (int count = 7; count >= 0; count--) {
      a = a << 1;
      b = (a >>> 8) & 1;
      if ((value & 0x8000) != 0) {
        value = ((value << 1) + b) ^ 0x1021;
      } else {
        value = (value << 1) + b;
      }
    }

    return value & 0xffff;
  }

  /**
   * reset CRC value to 0
   */
  public void reset() {
    value = 0;
  }

  public static int calculate(byte[] bytes, int offset, int length) {
    int value = 0;

    for (int i = offset; i < length; i++) {
      value = update(value, bytes[i]);
    }

    return value;
  }

}
