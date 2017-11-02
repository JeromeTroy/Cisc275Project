package modelTest;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import model.*;

public class FishCharacterTest {
	static MainCharacter f1;
	static MainCharacter f2;
	static MainCharacter f3;
	static MainCharacter f4;
	static MainCharacter f5;
	static Map m1;
	
	
	@BeforeClass
	public static void setUp() throws Exception {
		f1 = new MainCharacter();
		f2 = new MainCharacter();
		f3 = new MainCharacter();
		f4 = new MainCharacter();
		f5 = new MainCharacter();
		m1 = new Map(100,100);
		System.out.println(f1.getPossibleOrientations());
	}

	@Test
	public void testConstructor() {
		assertEquals(f1.getPosition().toString(), "<10,10>");
		assertEquals(f1.getScore(), 0);
		assertEquals(f1.getIsCaught(), false);
		assertEquals(f1.getAngle(), 0);
		assertEquals(f1.getPossibleOrientations().toString(), "[north, northwest, west, southwest, south, southeast, east, northeast]");
	}
	
	@Test
	public void testMove() {
		f2.move();
		assertEquals(f2.getPosition().toString(), "<11,10>");
		f2.move();
		f2.move();
		f2.move();
		assertEquals(f2.getPosition().toString(), "<14,10>");
		f2.setAngle(90);
		f2.move();
		f2.move();
		f2.move();
		assertEquals(f2.getPosition().toString(), "<14,13>");
	}
	
	@Test
	public void testRotate() {
		f3.rotate(90);
		assertEquals(f3.getAngle(), 90);
		f3.rotate(180);
		assertEquals(f3.getAngle(), 270);
		f3.rotate(100);
		assertEquals(f3.getAngle(),10);
		f3.rotate(-50);
		assertEquals(f3.getAngle(),320);
	}
	
	@Test
	public void testGetOrientaion() {
		f4.setAngle(90);
		assertEquals(f4.getOrientation(), "north");
		f4.setAngle(130);
		assertEquals(f4.getOrientation(), "northwest");
		f4.setAngle(182);
		assertEquals(f4.getOrientation(), "west");
		f4.setAngle(212);
		assertEquals(f4.getOrientation(), "southwest");
		f4.setAngle(274);
		assertEquals(f4.getOrientation(), "south");
		f4.setAngle(308);
		assertEquals(f4.getOrientation(), "southeast");
		f4.setAngle(359);
		assertEquals(f4.getOrientation(), "east");
		f4.setAngle(45);
		assertEquals(f4.getOrientation(), "northeast");
	}
	
	@Test
	public void testToString() {
		assertEquals(f5.toString(), "The fish is at <10,10> facing east");
	}
	
	@Test
	public void testGetName() {
		assertEquals(f5.getName(), "Fish ");
	}
	
	@Test
	public void testIsFish() {
		assertEquals(f5.isFish(), true);
	}
	
	@Test
	public void testRadius() {
		assertEquals(f5.getRadius(), 1);
		f5.setRadius(10);
		assertEquals(f5.getRadius(), 10);
	}
	
	@Test
	public void testStepSize() {
		assertEquals(f5.getStepSize(), 1);
		f5.setStepSize(10);
		assertEquals(f5.getStepSize(), 10);
	}

}
