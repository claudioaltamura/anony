package de.claudioaltamura.anony.ip;

import static org.junit.jupiter.api.Assertions.*;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.jupiter.api.Test;

class AnonymizeIpTest {

	@Test
	public void testMaskIpV4Address() throws UnknownHostException {
		InetAddress inet4Address = Inet4Address.getByName("127.0.0.1");
		assertEquals("127.0.0.0", AnonymizeIp.maskInet4Address(inet4Address));
	}

}
