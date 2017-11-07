package model;

/**
 * @author jerome
 * MainModel class, simplified using flyWeight
 */
public class MainModel {

	private MainCharacter fishy;
	private StuffSet everyThing;
	private Map map;
	
	/**
	 * Constructor
	 */
	public MainModel() {
		fishy = new MainCharacter();
		everyThing = new StuffSet();
		map = new Map();
	}
	
	/**
	 * Constructor
	 * @param length 		length of the map
	 * @param height		height of the map
	 */
	public MainModel(int length, int height) {
		fishy = new MainCharacter();
		everyThing = new StuffSet();
		map = new Map(length,height);
	}
	
	/**
	 * Constructor
	 * @param length		length of the map
	 * @param height 		height of the map
	 * @param unlen			unique length of the map
	 */
	public MainModel(int length, int height, int unlen) {
		fishy = new MainCharacter();
		everyThing = new StuffSet();
		map = new Map(length, height, unlen);
	}
	
	
	/**
	 * Updating the model
	 * Only updates if move is allowed
	 */
	public void update() {
		if (map.moveMap(fishy)) {
			System.out.println("Valid move");
			everyThing.move(fishy);
			fishy.move();
		}
		else {
			System.out.println("Invalid move, not moving");
		}
	}
	
	
	/**
	 * Updating the model by rotating fish
	 * @param deltaTheta 		angle to rotate the fish
	 */
	public void update(int deltaTheta) {
		fishy.rotate(deltaTheta);
		update();
	}
	
	
	/**
	 * Updating model by rotating fish and changing its speed
	 * @param newSpeed 			new speed for the fish
	 * @param deltaTheta		angle to rotate the fish
	 */
	public void update(int newSpeed, int deltaTheta) {
		fishy.setSpeed(newSpeed);
		update(deltaTheta);
	}
	
	public String toString() {
		String str = fishy.toString();
		str += "\n" + everyThing.toString();
		str += "\n" + map.toString();
		return str;
	}
	
}
