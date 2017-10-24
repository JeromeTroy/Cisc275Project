package modelTest;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import model.*;

public class FoodTest {
	static StuffInOcean s;
	static Food f;
	
	
	@BeforeClass
	public static void setUp() throws Exception {
		s = new Food(5,3);
		f = new Food(5,3);
	}

	@Test
	public void testGetName() {
		assertEquals(s.getName(), "Food ");
		assertEquals(f.getName(), "Food ");
	}

	@Test
	public void testIsFood() {
		assertTrue(s.isFood());
		assertTrue(f.isFood());
	}

	@Test
	public void testGetRadius() {
		assertEquals(s.getRadius(),1);
		assertEquals(f.getRadius(),1);
	}

	@Test
	public void testFood() {
		Food ff = new Food();
		StuffInOcean sf = new Food();
		assertEquals("Test x:",sf.getPosition().getX(),0);
		assertEquals("Test y:",sf.getPosition().getY(),0);
		
		assertEquals("Test x:",ff.getPosition().getX(),0);
		assertEquals("Test y:",ff.getPosition().getY(),0);
	}

	@Test
	public void testFoodIntInt() {
		Food ff = new Food(5,4);
		Food ff1 = new Food(1,3);
		StuffInOcean sf = new Food(4,5);
		assertEquals("Test x:",sf.getPosition().getX(),4);
		assertEquals("Test y:",sf.getPosition().getY(),5);
		
		assertEquals("Test x:",ff.getPosition().getX(),5);
		assertEquals("Test y:",ff.getPosition().getY(),4);
		
		assertEquals("Test x:",ff1.getPosition().getX(),1);
		assertEquals("Test y:",ff1.getPosition().getY(),3);
	}

	@Test
	public void testFoodVector() {
		Vector v = new Vector(12,13);
		Food f1 = new Food(v);
		StuffInOcean s1 = new Food(v);
		
		assertEquals("Test x:",f1.getPosition().getX(),12);
		assertEquals("Test y:",f1.getPosition().getY(),13);
		
		assertEquals("Test x:",s1.getPosition().getX(),12);
		assertEquals("Test y:",s1.getPosition().getY(),13);
		
		
	}

	@Test
	public void testToString() {
		System.out.println(f);
		assertEquals(f.toString(),"Food located at <5,3>");
		assertEquals(s.toString(),"Food located at <5,3>");
	}


	@Test
	public void testIsFish() {
		assertFalse(s.isFish());
		assertFalse(f.isFish());
	}

	@Test
	public void testIsTrash() {
		assertFalse(s.isTrash());
		assertFalse(f.isTrash());
	}

}
