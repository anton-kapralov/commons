/*
 * 
 * 
 * Kapralov A.
 * 22.03.13
 */

package kae.util.db;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author A. Kapralov
 *         22.03.13 16:37
 */
public class TestSQLUtils {

  @Test
  public void testMakeMask() throws Exception {
    Assert.assertEquals("%abc%", SQLUtils.makeMask("abc"));
  }

  @Test
  public void testMakeMaskForNull() throws Exception {
    Assert.assertEquals(null, SQLUtils.makeMask(null));
  }

}
