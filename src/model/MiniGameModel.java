package model;

public class MiniGameModel extends MainGameModel {
	int mapHeight = 20;
	int mapLength = 20;
	
	public MiniGameModel(){
		//TODO: update actual parameters;
		super();
		setTrashAmount(10);
		getMap().setHeight(mapHeight);
		getMap().setLength(mapLength);
		getMap().setUniqueLength(mapLength);
		setTrashAccumulation(0);
		setGameLengthSeconds(20);
		
	}
	
	public MiniGameModel(int mapHeight, int mapLength){
		this();
		this.mapHeight = mapHeight;
		this.mapLength = mapLength;
		getMap().setHeight(mapHeight);
		getMap().setLength(mapLength);
		getMap().setUniqueLength(mapLength);
	}
	
	@Override
	public boolean addStuff(StuffInOcean s){
		return getEveryThing().add(s);
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}

	public int getMapLength() {
		return mapLength;
	}

	public void setMapLength(int mapLength) {
		this.mapLength = mapLength;
	}
	
	
}
