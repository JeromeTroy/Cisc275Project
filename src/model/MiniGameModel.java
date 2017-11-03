package model;

public class MiniGameModel extends MainGameModel {
	/*
	 * The mini game, which is based on the main game
	 */
		
	public MiniGameModel(){
		//TODO: update actual parameters;
		super();						// create the instance
		setTrashAmount(10);				// preset amount of trash	;
		setTrashAccumulation(0);
		setGameLengthSeconds(20);
		
	}
	
	public MiniGameModel(int mapHeight, int mapLength){
		this();
		theMap = new Map(mapLength, mapHeight, mapLength);
	}
	
	@Override
	public boolean addStuff(StuffInOcean s){
		return everyThing.add(s);
	}

	public int getMapHeight() {
		return theMap.getHeight();
	}

	public void setMapHeight(int mapHeight) {
		theMap.setHeight(mapHeight);
	}

	public int getMapLength() {
		return theMap.getLength();
	}

	public void setMapLength(int mapLength) {
		theMap.setLength(mapLength);
	}
	
	public void update() {
		//if (!isCaught) {
			System.out.println("Update MiniGame Model");
		//}
	}
	
	
	
	
}
