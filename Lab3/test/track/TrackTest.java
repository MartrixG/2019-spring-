package track;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import types.TrackType;

class TrackTest {

	@Test
	void testGetTrackType() {
		ElectronShells shell=new ElectronShells(1);
		assertEquals((int)1, (int)shell.noNumber);
	}

	@Test
	void testGetNoNumber() {
		ElectronShells shell=new ElectronShells(1);
		assertEquals(TrackType.CIRCLE.toString(), shell.getTrackType());
	}

}
