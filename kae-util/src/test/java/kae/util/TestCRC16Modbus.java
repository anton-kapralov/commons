package kae.util;

import org.junit.Test;

/**
 * TestCRC16Modbus
 *
 * @author Kapralov A. 07.04.2014 18:58
 */
public class TestCRC16Modbus {

  @Test
  public void testCalculate() throws Exception {
    byte[] data = {0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39};
    final int crc16 = CRC16Modbus.calculate(data);
    //    Assert.assertEquals(0x4B37, crc16);
  }
}
