/*
 *
 *
 * Kapralov A.
 * 04.03.13
 */

package kae.util;

import org.junit.Assert;
import org.junit.Test;

/** @author A. Kapralov 04.03.13 12:30 */
public class TestDigestUtils {

  @Test
  public void testMd5Hex() throws Exception {
    String md5HexString = DigestUtils.md5Hex("1234567890");
    Assert.assertEquals("e807f1fcf82d132f9bb018ca6738a19f", md5HexString);
  }

  @Test
  public void testSha1Hex() throws Exception {
    String sha1HexString = DigestUtils.sha1Hex("1234567890");
    Assert.assertEquals("01b307acba4f54f55aafc33bb06bbbf6ca803e9a", sha1HexString);
  }
}
