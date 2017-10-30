package modelTest;
import model.*;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import model.FishCharacter;
import model.Food;
import model.StuffInOcean;

public class DistToFishComparatorTest  {
	static FishCharacter f;
	static StuffInOcean s1;
	static StuffInOcean s2;
	static StuffInOcean s3;
	static DistToFishComparator d;
	

	
	@BeforeClass
	public static void setUp() throws Exception {
		s1 = new Food(15,15);
		s2 = new Trash(1,1);
		s3 = new Food(15,15);
		d = new DistToFishComparator(f);
	}

	@Test
	public void compare() {
		assertEquals(d.compare(s1, s2), -1);
		assertEquals(d.compare(s2, s3), 1);
		assertEquals(d.compare(s1, s3), 0);
	}


}
