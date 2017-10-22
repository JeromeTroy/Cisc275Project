package modelTest;

import static org.junit.Assert.*;

import org.junit.Test;

import model.*;


public class StuffSet_Test {

	@Test
	public void addStuff() {
		StuffSet stuff = new StuffSet();
		Trash t1 = new Trash(1,2);
		Trash t2 = new Trash(1,2);
		Food f1 = new Food(2,1);
		Food f2 = new Food(1,2);
		Trash t3 = new Trash(3,5);
		
		stuff.add(t1);
		stuff.add(t2);
		stuff.add(f1);
		stuff.add(f2);
		stuff.add(t3);
		
		System.out.println(stuff.size());
		System.out.println(stuff);
		
		assertEquals(stuff.size(),3);
	}

}
