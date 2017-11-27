package modelTest;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import model.*;

public class MainCharacterTest {
	static MainCharacter f1;
	static MainCharacter f2;
	static MainCharacter f3;
	static MainCharacter f4;
	static MainCharacter f5;
	static MainCharacter f6;
	static MainCharacter f7;
	static MainCharacter f8;
	static MainCharacter f9;
	static Map m1;
	
	
	@BeforeClass
	public static void setUp() throws Exception {
		f1 = new MainCharacter();
		f2 = new MainCharacter();
		f3 = new MainCharacter();
		f4 = new MainCharacter();
		f5 = new MainCharacter();
		m1 = new Map(100,100);
		f6 = new MainCharacter(m1);
		f7 = new MainCharacter(m1, 10, 5);
		f8 = new MainCharacter();
		f9 = new MainCharacter();
	}

	@Test
	public void testConstructor() {
		assertEquals(f1.getPosition().toString(), "<0,0>");
		assertEquals(f1.getAngle(), 0);
	}
	
	@Test
	public void testMapConstructor() {
		assertEquals(f6.getPosition().getY(), m1.getHeight()/2);
	}
	
	@Test
	public void testConstructorThree() {
		assertEquals(f7.getRadius(), 10);
		assertEquals(f7.getSpeed(), 5);
	}
	
	@Test
	public void testMove() {
		assertEquals(f2.getPosition().toString(), "<0,0>");
		f2.move();
		f2.move();
		assertEquals(f2.getPosition().toString(), "<0,0>");
		f2.setAngle(90);
		f2.move();
		f2.move();
		f2.move();
		assertEquals(f2.getPosition().toString(), "<0,3>");
		f2.setAngle(270);;
		f2.move();
		f2.move();
		assertEquals(f2.getPosition().toString(), "<0,1>");
	}
	
	@Test
	public void testIsContacting() {
		int[] a = {0,0};
		int[] b = {25,25};
		int[] c = {-1,0};
		int[] d = {-25,25};
		assertEquals(f8.isContacting(a, 4), true);
		assertEquals(f8.isContacting(b, 4), false);
		assertEquals(f8.isContacting(c, 4), false);
		assertEquals(f8.isContacting(d, 4), false);
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
}
