package circularOrbit;

import circularOrbit.*;
import physicalObject.Electronic;
import track.ElectronShells;
import track.Track;
import types.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import centralObject.AtomicNucleus;
import centralObject.CentralObject;

class ConcreteCircularOrbitTest {
	
	@Test
	void testGetType() {
		@SuppressWarnings("unchecked")
		CircularOrbit<CentralObject, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure.txt");
		assertEquals(ApplicationType.AtomStructure, c.getType());
	}

	@Test
	void testAddTrack() {
		@SuppressWarnings("unchecked")
		CircularOrbit<CentralObject, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure.txt");
		Track shell7=new ElectronShells(7);
		Track shell1=new ElectronShells(1);
		assertEquals(true, c.addTrack(shell7));
		assertEquals(false, c.addTrack(shell1));
	}

	@Test
	void testRemoveTrack() {
		@SuppressWarnings("unchecked")
		CircularOrbit<CentralObject, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure.txt");
		Track shell7=new ElectronShells(7);
		Track shell1=new ElectronShells(1);
		assertEquals(false, c.removeTrack(shell7));
		assertEquals(true, c.removeTrack(shell1));
	}

	@Test
	void testAddCenterObject() {
		@SuppressWarnings("unchecked")
		CircularOrbit<CentralObject, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure.txt");
		CentralObject cen=new AtomicNucleus("Se");
		assertEquals(false, c.addCenterObject(cen));
	}

	@Test
	void testAddPhysicalObject() {
		@SuppressWarnings("unchecked")
		CircularOrbit<CentralObject, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure.txt");
		Electronic e1=new Electronic(1);
		Electronic e2=new Electronic(50);
		Track shell1=new ElectronShells(1);
		assertEquals(true, c.addPhysicalObject(shell1, e1));
		assertEquals(true, c.addPhysicalObject(shell1, e2));
	}

	@Test
	void testRemovePhysicalObject() {
		@SuppressWarnings("unchecked")
		CircularOrbit<CentralObject, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure.txt");
		Electronic e1=new Electronic(1);
		Electronic e2=new Electronic(50);
		assertEquals(true, c.removePhysicalObject(e1));
		assertEquals(false, c.removePhysicalObject(e2));
	}

	@Test
	void testGetPhysicalObjectsTrack() {
		@SuppressWarnings("unchecked")
		CircularOrbit<CentralObject, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure.txt");
		Electronic e1=new Electronic(1);
		Electronic e2=new Electronic(2);
		Track shell1=new ElectronShells(1);
		List<Electronic> list=new ArrayList<Electronic>();
		list.add(e1);
		list.add(e2);
		assertEquals(list, c.getPhysicalObjects(shell1));
	}

	@Test
	void testGetCenterObject() {
		@SuppressWarnings("unchecked")
		CircularOrbit<CentralObject, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure.txt");
		CentralObject cen=new AtomicNucleus("Rb");
		assertEquals(cen, c.getCenterObject());
	}

	@Test
	void testGetTracks() {
		@SuppressWarnings("unchecked")
		CircularOrbit<CentralObject, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure.txt");
		Track shell1=new ElectronShells(1);
		Track shell2=new ElectronShells(2);
		Track shell3=new ElectronShells(3);
		Track shell4=new ElectronShells(4);
		Track shell5=new ElectronShells(5);
		List<ElectronShells> list=new ArrayList<ElectronShells>();
		list.add((ElectronShells) shell1);
		list.add((ElectronShells) shell2);
		list.add((ElectronShells) shell3);
		list.add((ElectronShells) shell4);
		list.add((ElectronShells) shell5);
		assertEquals(list, c.getTracks());
	}

}
