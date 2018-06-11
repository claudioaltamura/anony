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
		assertEquals("127.0.0.0", AnonymizeIp.anonymize("127.0.0.1"));
	}
	
	@Test
	public void testAnonymizeIpV6Address() throws AnonymizeException {
		//assertEquals("127.0.0.0", AnonymizeIp.anonymize("0:0:0:0:0:ffff:4e32:e0ce"));
		assertEquals("127.0.0.0", AnonymizeIp.anonymize("2001:0db8:85a3:08d3:1319:8a2e:0370:7344"));
	}

}
