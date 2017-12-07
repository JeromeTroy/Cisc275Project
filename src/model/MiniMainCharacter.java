package model;

/**
 * @author Group 4
 * Extention of main character to provide minigame behavior
 */
public class MiniMainCharacter extends MainCharacter{

	
	// methods
	
	
	// constructor
	/**
	 * Constructor
	 * @param map 		current minigame map
	 * Sets the position of the main character
	 */
	public MiniMainCharacter(Map map) {
		super(map);
		getPosition().setX(map.getLength()/2);
	}
	
	
	// mover
	/** (non-Javadoc)
	 * @see model.MainCharacter#move()
	 * Moves the character in y direction via super.move()
	 * And then moves in x and y since the map won't move
	 */
	@Override
	public void move() {
		super.move();
		int deltaX = (int) (getSpeed() * Math.cos(Math.toRadians(getAngle())));
		getPosition().setX(getPosition().getX() + deltaX);
	}
	
	
	// contact
	/** (non-Javadoc)
	 * @see model.MainCharacter#isContacting(int[], int)
	 * Removes check for in front of character
	 */
	@Override 
	public boolean isContacting(int[] v, int size) {
		return (getPosition().distFrom(v[0], v[1]) <= Math.pow(getRadius() + size, 2));
	}
}
