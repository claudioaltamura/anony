package de.claudioaltamura.anony.ip;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class AnonymizeIpDefault implements AnonymizeIp {

	@Override
	public String anonymize(String ipAddress) throws AnonymizeException {
		String anonymizedIp = null;
		try {
			InetAddress ip = InetAddress.getByName(ipAddress);
			if(ip instanceof Inet4Address)
			{
				return maskInet4Address((Inet4Address)ip);
			} else if(ip instanceof Inet6Address) {
				throw new UnsupportedOperationException("not implemented yet");
			}
		} catch (UnknownHostException e) {
			throw new AnonymizeException("not a valid ip address " + ipAddress);
		}
		return anonymizedIp;
	}

	String maskInet4Address(InetAddress inet4Address) {
		String ip4Address = inet4Address.getHostAddress();
		String[] ipSplits = ip4Address.split("\\.");
		ipSplits[3] = "0";
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < ipSplits.length; i++) {
			sb.append(ipSplits[i]);
			if(i +1 < ipSplits.length)
				sb.append(".");
				
		}
		return sb.toString();
	}

}
