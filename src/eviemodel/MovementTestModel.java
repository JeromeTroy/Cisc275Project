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
	
	public void moveFishy(int newx, int newy){
		fishy.moveToPoint(newx, newy);
	}
	
	public void printFishy(){
		System.out.println(fishy.toString());
	}
	
}
