/*
 * 
 * 
 * Kapralov A.
 * 17.05.12
 */

package kae.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** @author A. Kapralov 17.05.12 15:46 */
public class DigestUtils {

  private static final char[] HEX_CHAR =
      {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

  public static String md5Hex(String string) {
    return encode(string, "MD5");
  }

  public static String sha1Hex(String string) {
    return encode(string, "SHA1");
  }

  private static String encode(String string, String algorithm) {
    try {
      MessageDigest md = MessageDigest.getInstance(algorithm);
      md.update(string.getBytes());
      return toHexString(md.digest());
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  private static String toHexString(byte[] buf) {
    StringBuilder strBuf = new StringBuilder(buf.length * 2);
    for (byte b : buf) {
      strBuf.append(HEX_CHAR[(b & 0xf0) >>> 4]); // fill left with zero bits
      strBuf.append(HEX_CHAR[b & 0x0f]);
    }
    return strBuf.toString();
  }
}
