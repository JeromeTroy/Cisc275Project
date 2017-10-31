package modelTest;

import org.junit.*;

import static org.junit.Assert.*;

import org.junit.Test;

import model.*;


public class StuffSetTest {
	StuffSet s;
	FishCharacter f;
	
	@Before
	public void buildStuffSet(){
		
		Trash t1 = new Trash(1,2);
		Trash t2 = new Trash(1,2);
		Food f1 = new Food(2,1);
		Food f2 = new Food(1,2);
		Food f3 = new Food (0,0);
		Trash t3 = new Trash(3,5);
		f = new FishCharacter();
		s = new StuffSet(f);
		
		s.add(t1);
		s.add(t2);
		s.add(f1);
		s.add(f2);
		s.add(f3);
		s.add(t3);
		//s.add(f);
		
		//System.out.println(s);
		//list contains [The fish is at <10, 10> facing east, Trash located at <3, 5>, 
		//               Trash located at <1, 2>, Food located at <0, 0>]
		// Fish 1; Food 1; Trash 2
	}
	
	
	
	@Test
	public void testAdd() {
		assertEquals(s.size(),5);
		assertTrue(s.get(0) == f);
		assertFalse(s.add(new Food(0,0)));
		Food f1 = new Food(100,100);
		assertTrue(s.add(f1));
		assertTrue(s.get(s.size()-1) == f1);
		
	}
	
	@Test
	public void testAdd_noFish(){
		StuffSet s1 = new StuffSet();
		Trash t1 = new Trash(1,2);
		Trash t2 = new Trash(0,0);
		
		s1.add(t1);
		s1.add(t2);
		
		assertTrue(s1.get(0) == t1);
		assertTrue(s1.get(1) == t2);
		
	}
	
	

}
