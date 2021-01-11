package business;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ownUtil.PlausiException;

class FreizeitbadTest {
	private static Freizeitbad freizeitbad;
	
	@BeforeAll
	public static void test() throws PlausiException {
		freizeitbad = new Freizeitbad("Stadtbad","7.00","17.00","24","25");
	}
	@Test
	void tempTest() {
		assertTrue(freizeitbad.getTemperatur() == 25, "Temperatur beträgt nicht 25° sondern: " + freizeitbad.getTemperatur());
	}

}
