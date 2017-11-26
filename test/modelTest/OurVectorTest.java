package modelTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class OurVectorTest {
	OurVector ov1;
	OurVector ov2;
	
	@Before
	public void setUp() throws Exception {
	ov1 = new OurVector();	
	ov2 = new OurVector();
	ov1.setX(2);
	ov1.setY(5);
	ov2.setX(4);
	ov2.setY(1);
	}

	@Test
	public void norm2() {
		assertEquals(ov1.norm2(), 29);
		assertEquals(ov2.norm2(), 17);
	}
	
	@Test 
	public void testGetAndSetXAndY() {
		assertEquals(ov1.getX(), 2);
		assertEquals(ov2.getX(), 4);
		assertEquals(ov1.getY(), 5);
		assertEquals(ov2.getY(), 1);
		
		ov1.setX(7);
		ov1.setY(2);;
		ov2.setX(3);
		ov2.setY(8);
		assertEquals(ov1.getX(), 7);
		assertEquals(ov2.getX(), 3);
		assertEquals(ov1.getY(), 2);
		assertEquals(ov2.getY(), 8);
	}
	
	@Test
	public void testDistBetween() {
		assertEquals(ov1.distBetween(ov1.getX(), ov1.getY(), 3, 6), 2);
		assertEquals(ov2.distBetween(ov2.getX(), ov2.getY(), 3, 6), 42);
	}
	
	@Test
	public void testToString() {
		System.out.println(ov1.getX());
		assertEquals(ov1.toString(), "<2,5>");
		assertEquals(ov2.toString(), "<4,1>");
	}
	
	
	@Test
	public void tesDistFrom() {
		assertEquals(ov1.distFrom(4,1), 20);
		assertEquals(ov2.distFrom(2,5), 20);
	}
	
	@Test
	public void testCompareTo() {
		//TODO
	}
	
}
