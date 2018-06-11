package de.claudioaltamura.anony.ip;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.BitSet;

public class AnonymizeIp {

	private static final int LAST_80_BYTES = 45;

	public static String anonymize(final String ipAddress) throws AnonymizeException {
		if(ipAddress == null || ipAddress.isEmpty())
		{
			throw new AnonymizeException("ip address is null or empty!");
		}

		String anonymizedIp = null;
		try {
			InetAddress ip = InetAddress.getByName(ipAddress);
			if(ip instanceof Inet4Address)
			{
				return maskInet4Address((Inet4Address)ip);
			} else if(ip instanceof Inet6Address) {
				return maskInet6Address((Inet6Address)ip);
			}
		} catch (UnknownHostException e) {
			throw new AnonymizeException("not a valid ip address " + ipAddress);
		}
		return anonymizedIp;
	}

	private static String maskInet4Address(InetAddress inet4Address) {
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

	private static String maskInet6Address(Inet6Address inet6Address) throws UnknownHostException {
		byte[] address = inet6Address.getAddress();
		
		BitSet bitSet = BitSet.valueOf(address);
		printBitSet(bitSet);
		for(int i = bitSet.length(); i > LAST_80_BYTES - 1; i--) {
			bitSet.clear(i);
		}
		printBitSet(bitSet);
		
        InetAddress maskedIpv6Address = Inet6Address.getByAddress(bitSet.toByteArray());
        
		return maskedIpv6Address.getHostAddress();
	}

	private static void printBitSet(BitSet bitSet) {
		bitSet.stream().forEach((i)->System.out.print(Integer.toString(i, 2) + " "));
		System.out.println("\n");
	}

}
