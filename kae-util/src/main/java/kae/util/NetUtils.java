package kae.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * NetUtils
 *
 * @author Kapralov A. 12.09.2014 15:35
 */
public class NetUtils {

  public static String getMacAddress() throws UnknownHostException, SocketException {
    final Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
    while (networkInterfaces.hasMoreElements()) {
      NetworkInterface networkInterface = networkInterfaces.nextElement();
      final String macAddress = getMacAddress(networkInterface);
      if (macAddress != null) {
        return macAddress;
      }
    }
    return "";
  }

  public static String getMacAddress(InetAddress ip) throws SocketException {
    NetworkInterface network = NetworkInterface.getByInetAddress(ip);
    return getMacAddress(network);
  }

  public static String getMacAddress(NetworkInterface networkInterface) throws SocketException {
    byte[] mac = networkInterface.getHardwareAddress();
    if (mac == null) {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < mac.length; i++) {
      sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? ":" : ""));
    }
    return sb.toString();
  }
}
