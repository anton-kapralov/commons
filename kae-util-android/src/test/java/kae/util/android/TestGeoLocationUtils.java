package kae.util.android;

import org.junit.Assert;
import org.junit.Test;

/**
 * TestGeoLocationUtils
 *
 * @author Kapralov A. 30.05.2014 00:25
 */
public class TestGeoLocationUtils {

  @Test
  public void testDistance() throws Exception {
    final double distance =
        GeoLocationUtils.distance(
            57.627488018453484, 39.94131512625865, 57.623988474940155, 39.853424501258644);
    Assert.assertEquals(5253.249758721331, distance, 0.00000000001);
  }
}
