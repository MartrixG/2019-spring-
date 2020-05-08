package circularOrbit;

import exception.DependencyException;
import exception.GramarException;
import exception.InputException;
import physicalObject.Electronic;
import track.ElectronShells;
import track.Track;
import types.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import centralObject.AtomicNucleus;
import centralObject.CentralObject;


public class ConcreteCircularOrbitTest {
	@Test
	public void testGetShang() throws DependencyException, GramarException, InputException {
		CircularOrbit<CentralObject, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure.txt");
		assertEquals(true,(c.getShang()-3.708)<0.1);
	}

	@Test
	public void testGetType() throws DependencyException, GramarException, InputException {
		@SuppressWarnings("unchecked")
		CircularOrbit<CentralObject, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure.txt");
		assertEquals(ApplicationType.AtomStructure, c.getType());
	}

	@Test
	public void testAddTrack() throws GramarException, DependencyException, InputException {
		CircularOrbit<CentralObject, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure.txt");
		Track shell7=new ElectronShells(7);
		Track shell1=new ElectronShells(1);
		assertEquals(true, c.addTrack(shell7));
		try{
			assertEquals(false, c.addTrack(shell1));
		}catch(GramarException e){
			assert true;
		}
	}

	@Test
	public void testRemoveTrack() throws DependencyException, GramarException, InputException {
		@SuppressWarnings("unchecked")
		CircularOrbit<CentralObject, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure.txt");
		Track shell7=new ElectronShells(7);
		Track shell1=new ElectronShells(1);
		try{
			assertEquals(false, c.removeTrack(shell7));
		}catch(GramarException e) {
			assert true;
		}
		assertEquals(true, c.removeTrack(shell1));
	}

	@Test
	public void testAddCenterObject() throws DependencyException, GramarException, InputException {
		@SuppressWarnings("unchecked")
		CircularOrbit<CentralObject, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure.txt");
		CentralObject cen=new AtomicNucleus("Se");
		try{
			assertEquals(false, c.addCenterObject(cen));
		}catch(InputException e){
			assert true;
		}
	}

	@Test
	public void testAddPhysicalObject() throws DependencyException, GramarException, InputException {
		@SuppressWarnings("unchecked")
		CircularOrbit<CentralObject, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure.txt");
		Electronic e1=new Electronic(1);
		Electronic e2=new Electronic(50);
		Track shell1=new ElectronShells(1);
		assertEquals(true, c.addPhysicalObject(shell1, e1));
		assertEquals(true, c.addPhysicalObject(shell1, e2));
	}

	@Test
	public void testRemovePhysicalObject() throws DependencyException, GramarException, InputException {
		@SuppressWarnings("unchecked")
		CircularOrbit<CentralObject, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure.txt");
		Electronic e1=new Electronic(1);
		Electronic e2=new Electronic(50);
		assertEquals(true, c.removePhysicalObject(e1));
		try{
			assertEquals(false, c.removePhysicalObject(e2));
		}catch (GramarException e){
			assert true;
		}
	}

	@Test
	public void testGetPhysicalObjects() throws DependencyException, GramarException, InputException {
		CircularOrbit<CentralObject, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure_small.txt");
		Electronic e1=new Electronic(1);
		Electronic e2=new Electronic(2);
		List<Electronic> list=new ArrayList<Electronic>();
		list.add(e1);
		list.add(e2);
		assertEquals(list, c.getPhysicalObjects());
	}

	@Test
	public void testGetPhysicalObjectsTrack() throws DependencyException, GramarException, InputException {
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
	public void testGetCenterObject() throws DependencyException, GramarException, InputException {
		@SuppressWarnings("unchecked")
		CircularOrbit<CentralObject, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure.txt");
		CentralObject cen=new AtomicNucleus("Rb");
		assertEquals(cen, c.getCenterObject());
	}

	@Test
	public void testGetTracks() throws DependencyException, GramarException, InputException {
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

	@Test
	public void testIterator() throws DependencyException, GramarException, InputException {
		CircularOrbit<CentralObject, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure_small.txt");
		Iterator<Electronic> tmpE=c.iterator();
		Electronic e1=new Electronic(1);
		Electronic e2=new Electronic(2);
		if(tmpE.hasNext())
			assertEquals(e1,tmpE.next());
		if(tmpE.hasNext())
			assertEquals(e2,tmpE.next());
		assertEquals(null,tmpE.next());
	}

	@Test
	public void testGetPhysicalDistance() throws DependencyException, GramarException, InputException {
		CircularOrbit<CentralObject, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure_small.txt");
		assertEquals(true,c.getPhysicalDistance("1","2")+1==0);
	}

	@Test
	public void testSetPosition() throws DependencyException, GramarException, InputException {
		CircularOrbit<CentralObject, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure_small.txt");
		try{
			Electronic e1=new Electronic(1);
			c.setPosition(e1);
		}catch(Exception e){
			assert true;
		}
	}

	@Test
	public void testGetPhysicalPosition() throws DependencyException, GramarException, InputException {
		CircularOrbit<CentralObject, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure_small.txt");
		try{
			Electronic e1=new Electronic(1);
			Electronic e10=new Electronic(10);
			c.getPhysicalObjectPosition(e1);
		}catch (GramarException e){
			assert true;
		}
	}
}
