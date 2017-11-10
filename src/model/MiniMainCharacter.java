package model;

public class MiniMainCharacter extends MainCharacter{

	/**
	 * Constructor
	 * @param map 		current minigame map
	 * Sets the position of the main character
	 */
	public MiniMainCharacter(Map map) {
		super(map);
		position.setX(map.getLength()/2);
	}
	
	/** (non-Javadoc)
	 * @see model.MainCharacter#move()
	 * Moves the character in y direction via super.move()
	 * And then moves in x and y since the map won't move
	 */
	@Override
	public void move() {
		super.move();
		int deltaX = (int) (speed * Math.cos(Math.toRadians(angle)));
		position.setX(position.getX() + deltaX);
	}
	
	/** (non-Javadoc)
	 * @see model.MainCharacter#isContacting(int[], int)
	 * Removes check for in front of character
	 */
	@Override 
	public boolean isContacting(int[] v, int size) {
		return (position.distFrom(v[0], v[1]) <= Math.pow(radius + size, 2));
	}
}
