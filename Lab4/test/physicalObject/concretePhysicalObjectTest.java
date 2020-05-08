package physicalObject;

import static org.junit.Assert.*;

import exception.InputException;
import org.junit.Test;
import types.socialNetworkTypes.PersonType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class concretePhysicalObjectTest {

	@Test
	public void testGetName() {
		Electronic cen=new Electronic(1);
		assertEquals("1", cen.getName());
	}

	@Test
	public void testHashCode(){
		Electronic cen=new Electronic(1);
		int hash=cen.getName().hashCode();
		assertEquals(hash, cen.hashCode());
	}

	@Test
	public void testEquales(){
		Electronic cen=new Electronic(1);
		Electronic cen2=new Electronic(2);
		Electronic cen1=new Electronic(1);
		assertEquals(true, cen.equals(cen1));
		assertEquals(false, cen.equals(cen2));
	}

	@Test
	public void testAthlete() throws InputException {
		String[] args={"a","1","aa","12","10"};
		List<String> arg=new ArrayList<>(Arrays.asList("a","1","aa","12","10"));
		String[] args1={"a","1.1","aa","12","10"};
		List<String> arg1=new ArrayList<>(Arrays.asList("a","1.1","aa","12","10"));
		Athlete a=new Athlete("a");
		try{
			Athlete a1=new Athlete(args);
			Athlete a2=new Athlete(arg);
			assert true;
		}catch (InputException e){
			assert true;
		}
		try{
			Athlete a1=new Athlete(args1);
			Athlete a2=new Athlete(arg1);
			assert true;
		}catch (InputException e){
			assert true;
		}
		Athlete a1=new Athlete(args);
		assertEquals(true, a1.getBestRecord()-10==0);
	}

	@Test
	public void testFriend() throws InputException {
		String[] args={"a","1","M"};
		List<String> arg=new ArrayList<>(Arrays.asList("a","1","M"));
		String[] args1={"a","1.1","M"};
		List<String> arg1=new ArrayList<>(Arrays.asList("a","1.1","M"));
		Friend a=new Friend("a");
		try{
			Friend a1=new Friend(args);
			Friend a2=new Friend(arg);
			assert true;
		}catch (InputException e){
			assert true;
		}
		try{
			Friend a1=new Friend(args1);
			Friend a2=new Friend(arg1);
			assert true;
		}catch (InputException e){
			assert true;
		}
		assertEquals(PersonType.Friend, a.getType());
	}
}
