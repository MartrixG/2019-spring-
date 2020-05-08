package physicalObject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import centralObject.AtomicNucleus;
import centralObject.CentralObject;

class ConcretePhysicalObjectTest {

	@Test
	void testGetName() {
		Electronic cen=new Electronic(1);
		assertEquals("1", cen.getName());
	}

}
