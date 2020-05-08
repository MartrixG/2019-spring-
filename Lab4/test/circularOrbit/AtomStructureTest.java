package circularOrbit;

import static org.junit.Assert.*;

import exception.DependencyException;
import exception.GramarException;
import exception.InputException;
import org.junit.Test;

import centralObject.AtomicNucleus;
import centralObject.CentralObject;
import physicalObject.Electronic;
import relationship.SocialNetworkRelationship;
import track.ElectronShells;
import types.ApplicationType;

public class AtomStructureTest {

	@Test
	public void testTrans() throws GramarException, DependencyException, InputException {
		@SuppressWarnings("unchecked")
		CircularOrbit<CentralObject, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure.txt");
		Electronic e1=new Electronic(1);
		ElectronShells shell5=new ElectronShells(5);
		ElectronShells shell1=new ElectronShells(1);
		assertEquals(shell1, c.trans(e1, shell5));
	}

	@Test
	public void testUNDO1() throws GramarException, DependencyException, InputException {
		@SuppressWarnings("unchecked")
		CircularOrbit<AtomicNucleus, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure.txt");
		Electronic e1=new Electronic(1);
		ElectronShells shell5=new ElectronShells(5);
		c.trans(e1, shell5);
		try{
			assertEquals(false, ((AtomStructure)c).UNDO(2));
		}catch (GramarException e){
			assert(true);
		}
	}

	@Test
	public void testUNDO2() throws GramarException, DependencyException, InputException {
		@SuppressWarnings("unchecked")
		CircularOrbit<AtomicNucleus, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure.txt");
		Electronic e1=new Electronic(1);
		ElectronShells shell5=new ElectronShells(5);
		c.trans(e1, shell5);
		try{
			assertEquals(true, ((AtomStructure)c).UNDO(0));
		}catch (GramarException e){
			assert(true);
		}
	}

	@Test
	public void testInit1() throws GramarException, DependencyException, InputException {
		try{
			CircularOrbit<AtomicNucleus, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure_error1.txt");
			assert true;
		}catch (InputException e){
			assert true;
		}
	}

	@Test
	public void testInit2() throws GramarException, DependencyException, InputException {
		try{
			CircularOrbit<AtomicNucleus, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure_error2.txt");
			assert true;
		}catch (InputException e){
			assert true;
		}
	}

	@Test
	public void testInit3() throws GramarException, DependencyException, InputException {
		try{
			CircularOrbit<AtomicNucleus, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure_error3.txt");
			assert true;
		}catch (InputException e){
			assert true;
		}
	}

	@Test
	public void testInit4() throws GramarException, DependencyException, InputException {
		try{
			CircularOrbit<AtomicNucleus, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure_error4.txt");
			assert true;
		}catch (InputException e){
			assert true;
		}
	}

	@Test
	public void testOhters() throws GramarException, DependencyException, InputException {
		CircularOrbit<AtomicNucleus, Electronic> c=CircularOrbit.newCircularOrbit(ApplicationType.AtomStructure, "src\\source_DATA\\AtomicStructure.txt");
		Electronic e1=new Electronic(1);
		Electronic e2=new Electronic(2);
		CentralObject cen=new AtomicNucleus("Rb");
		assertEquals(10000,c.getLogicalDistance("a","b"));
		assertEquals(false, c.addRelationC2P((AtomicNucleus) cen,e1,new SocialNetworkRelationship("1")));
		assertEquals(false,c.addRelationP2P(e1,e2,new SocialNetworkRelationship("1")));
	}
}
