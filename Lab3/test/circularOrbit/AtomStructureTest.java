package circularOrbit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import centralObject.AtomicNucleus;
import centralObject.CentralObject;
import physicalObject.Electronic;
import track.ElectronShells;
import types.ApplicationType;

class AtomStructureTest {

	@Test
	void testTrans() {
		@SuppressWarnings("unchecked")
		CircularOrbit<CentralObject, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure.txt");
		Electronic e1=new Electronic(1);
		ElectronShells shell5=new ElectronShells(5);
		ElectronShells shell1=new ElectronShells(1);
		assertEquals(shell1, c.trans(e1, shell5));
	}

	@Test
	void testUNDO() {
		@SuppressWarnings("unchecked")
		CircularOrbit<AtomicNucleus, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure.txt");
		Electronic e1=new Electronic(1);
		ElectronShells shell5=new ElectronShells(5);
		c.trans(e1, shell5);
		assertEquals(false, ((AtomStructure)c).UNDO(2));
	}

}
