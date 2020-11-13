/*
 *
 *
 * Kapralov A.
 * 17.05.12
 */

package kae.util;

/** @author A. Kapralov 17.05.12 15:01 */
public class SecurityUtils {

  /**
   * Encode the password using formula: <code>sha-1(md5(md5(<b>password</b>) + <b>salt</b>))</code>
   *
   * @param password password for encode.
   * @param salt salt using in formula.
   * @return encoded password.
   */
  public static String encodePassword(String password, String salt) {
    return DigestUtils.sha1Hex(DigestUtils.md5Hex(DigestUtils.md5Hex(password) + salt));
  }
}
