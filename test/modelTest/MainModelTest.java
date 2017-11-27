package modelTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class MainModelTest {
	MainModel m;
	int numFood;
	int numTrash;
	int numFish;
	@Before
	public void setUp() throws Exception {
		
		//create MainGameModel
		m = new MainModel();
		
		//count items in stuffSet
		count();
	}

	@Test
	public void testAddStuff() {
		assertEquals("Inititial Size of StuffSet",m.getStuffSet().size(),1);
		
		//add more items to stuff set
		m.addStuff(new Food(1,2));
		m.addStuff(new Food(10,10));
		m.addStuff(new Trash(1,2));
		m.addStuff(new Trash(0,0));
		
		
		assertEquals("Final Size of StuffSet", m.getStuffSet().size(),3);
	}

	@Test
	public void testAccumulate() {
		assertEquals(m.getStuffSet().size(),1);
		int initialSize = m.getStuffSet().size();
		int initialTrash = numTrash;
		assertEquals(numFood,0);
		assertEquals(numFish,1);
		assertEquals(numTrash,0);
		m.accumulate();
		assertTrue((initialSize<m.getStuffSet().size()));
		count();
		assertEquals(numFood,0);
		assertEquals(numFish,1);
		assertTrue(initialTrash<=numTrash);
	}

	@Test
	public void testRemoveTrash() {
		m.addStuff(new Food(1,2));
		m.addStuff(new Food(10,10));
		m.addStuff(new Trash(1,2));
		m.addStuff(new Trash(0,0));
		m.addStuff(new Trash(50,50));
		assertEquals("Initial Size of StuffSet", m.getStuffSet().size(),4);
		count();
		assertEquals(numFood,1);
		assertEquals(numFish,1);
		assertEquals(numTrash,2);
		m.removeTrash();
		assertEquals("Final Size of StuffSet", m.getStuffSet().size(),2);
		count();
		assertEquals(numFood,1);
		assertEquals(numFish,1);
		assertEquals(numTrash,0);
		
	}
	
	
	//method used to count each item type in the StuffSet
	void count(){
		this.numFood=0;
		this.numTrash=0;
		this.numFish=0;
		for (StuffInOcean s: m.getStuffSet()){
			if (s.isFish()){
				numFish++;
			} else if (s.isFood()){
				numFood++;
			} else if (s.isTrash()){
				numTrash++;
			}
		}
	}

	}
