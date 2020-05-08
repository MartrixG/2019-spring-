package centralObject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ConcreteCentralObjectTest {

	@Test
	void testGetName() {
		CentralObject cen=new AtomicNucleus("Rb");
		assertEquals("Rb", cen.getName());
	}

}
