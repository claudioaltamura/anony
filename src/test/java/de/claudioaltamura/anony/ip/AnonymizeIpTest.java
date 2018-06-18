package de.claudioaltamura.anony.ip;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AnonymizeIpTest {

	@Test
	public void testAnonymizeIpNull() throws AnonymizeException {
		assertThrows(AnonymizeException.class, () -> {AnonymizeIp.anonymize(null);});
	}
	
	@Test
	public void testAnonymizeIpV4Address() throws AnonymizeException {
		assertEquals("10.255.255.0", AnonymizeIp.anonymize("10.255.255.255"));
		assertEquals("127.0.0.0", AnonymizeIp.anonymize("127.0.0.1"));
		assertEquals("239.255.255.0", AnonymizeIp.anonymize("239.255.255.255"));
	}
	
	@Test
	public void testAnonymizeIpV6Address() throws AnonymizeException {
		assertEquals("2001:db8:85a3:8d3:0:0:0:0", AnonymizeIp.anonymize("2001:0db8:85a3:08d3:1319:8a2e:0370:7344"));
		assertEquals("1080:0:0:0:0:0:0:0", AnonymizeIp.anonymize("1080:0:0:0:8:800:200C:417A"));
	}

}
