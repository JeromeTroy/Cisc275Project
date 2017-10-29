package eviemodel;

import model.FishCharacter;

public class MovementTestModel {
	private FishCharacter fishy; 		// the main character
	private TestMap map;
	
	public MovementTestModel(){
		fishy = new FishCharacter();
		map = new TestMap();
	}
	
	public FishCharacter getFishy(){
		return fishy;
	}
	
}
