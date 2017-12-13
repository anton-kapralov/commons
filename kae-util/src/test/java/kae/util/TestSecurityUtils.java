/*
 *
 *
 * Kapralov A.
 * 17.05.12
 */

package kae.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author A. Kapralov 17.05.12 15:12
 */
public class TestSecurityUtils {

  @Test
  public void testEncodePassword() throws Exception {
    final String encodedPassword = SecurityUtils.encodePassword("123456", "7890");
    Assert.assertEquals("c3c42e47ae38c43cc0b3f04e7aff7f03515ac8fd", encodedPassword);
  }

}
