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
	ov1 = new OurVector(2,5);	
	ov2 = new OurVector(4,1);
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
	public void testToString() {
		System.out.println(ov1.getX());
		assertEquals(ov1.toString(), "<2,5>");
		assertEquals(ov2.toString(), "<4,1>");
	}
	
	@Test
	public void testDotWith() {
		assertEquals(ov1.dotWith(ov2), 13);
		assertEquals(ov2.dotWith(ov1), 13);
	}
	
	@Test
	public void tesDistFrom() {
		assertEquals(ov1.distFrom(ov2), 20);
		assertEquals(ov2.distFrom(ov1), 20);
	}
	
	@Test
	public void testCompareTo() {
		//TODO
	}
	
}
