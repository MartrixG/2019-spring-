package centralObject;

import exception.InputException;
import org.junit.Test;
import types.socialNetworkTypes.PersonType;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ConcreteCentralObjectTest {


	@Test
	public void testAtomGetName1() throws InputException {
		CentralObject cen=new AtomicNucleus("Rb");
		assertEquals("Rb", cen.getName());
	}

	@Test
	public void testAtomGetName2() throws InputException {
		try{
			CentralObject cen=new AtomicNucleus("RB");
			assertEquals("RB", cen.getName());
		}catch (InputException e){
			assert true;
		}
	}

	@Test
	public void testAtomGetName3() throws InputException {
		try{
			CentralObject cen=new AtomicNucleus("rB");
			assertEquals("rB", cen.getName());
		}catch(InputException e){
			assert true;
		}
	}

	@Test
	public void testAtomGetName4() throws InputException {
		try{
			CentralObject cen=new AtomicNucleus("rBb");
			assertEquals("rBb", cen.getName());
		}catch(InputException e){
			assert true;
		}
	}

	@Test
	public void testUserGetName1() throws InputException {
		CentralObject cen=new CentralUser(Arrays.asList("TommyWong","30","M"));
		assertEquals("TommyWong", cen.getName());
	}

	@Test
	public void testUserGetName2() throws InputException {
		try{
			CentralObject cen=new CentralUser(Arrays.asList("TommyWong","3.2","M"));
			assertEquals("TommyWong", cen.getName());
		}catch(InputException e){
			assert true;
		}
	}

	@Test
	public void testUserGetType() throws InputException {
		CentralUser cen=new CentralUser(Arrays.asList("TommyWong","30","M"));
		assertEquals(PersonType.CentralUser, cen.getType());
	}

	@Test
	public void testNullGetType(){
		CentralObject cen=new TrackGameNull();
		assertEquals("null", cen.getName());
	}

	@Test
	public void testOverride() throws InputException {
		CentralObject cen=new AtomicNucleus("Rb");
		CentralObject cen1=new AtomicNucleus("Rb");
		int hash=cen.hashCode();
		assertEquals(hash, cen.hashCode());
		assertEquals(true,cen.equals(cen1));
	}
}
