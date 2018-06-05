package de.claudioaltamura.anony.ip;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AnonymizeIpTest {

	@Test
	public void testAnonymizeIpNull() throws AnonymizeException {
		assertThrows(AnonymizeException.class, () -> {AnonymizeIp.anonymize(null);});
	}
	
	@Test
	public void testMaskIpV4Address() throws AnonymizeException {
		assertEquals("127.0.0.0", AnonymizeIp.anonymize("127.0.0.1"));
	}

}
