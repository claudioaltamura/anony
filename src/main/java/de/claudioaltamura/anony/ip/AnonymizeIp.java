package de.claudioaltamura.anony.ip;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class AnonymizeIp {

  public static String anonymize(final String ipAddress) throws AnonymizeException {
    if (ipAddress == null || ipAddress.isEmpty()) {
      throw new AnonymizeException("ip address is null or empty!");
    }

    final String anonymizedIp = null;
    try {
      final InetAddress ip = InetAddress.getByName(ipAddress);
      if (ip instanceof Inet4Address) {
        return maskInet4Address((Inet4Address) ip);
      } else if (ip instanceof Inet6Address) {
        return maskInet6Address((Inet6Address) ip);
      }
    } catch (UnknownHostException e) {
      throw new AnonymizeException("not a valid ip address " + ipAddress);
    }
    return anonymizedIp;
  }

  private static String maskInet4Address(final InetAddress inet4Address) {
    final String ipV4Address = inet4Address.getHostAddress();
    final String[] ipSplits = ipV4Address.split("\\.");
    ipSplits[3] = "0";
    final StringBuilder sb = new StringBuilder();
    for (int i = 0; i < ipSplits.length; i++) {
      sb.append(ipSplits[i]);
      if (i + 1 < ipSplits.length)
        sb.append(".");
    }

    return sb.toString();
  }

  private static String maskInet6Address(final Inet6Address inet6Address)
      throws UnknownHostException {
    final byte[] ipV6Address = inet6Address.getAddress();
    byte[] maskedIpAddress = new byte[ipV6Address.length];
    System.arraycopy(ipV6Address, 0, maskedIpAddress, 0, ipV6Address.length);

    for (int i = maskedIpAddress.length - 1; i > 7; i--) {
      maskedIpAddress[i] = 0;
    }

    final InetAddress maskedIpv6Address = Inet6Address.getByAddress(maskedIpAddress);

    return maskedIpv6Address.getHostAddress();
  }

}
