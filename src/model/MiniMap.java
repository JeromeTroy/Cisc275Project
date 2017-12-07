package model;

/**
 * @author Group 4
 * Extention of map to provide minigame behavior
 *
 */
public class MiniMap extends Map{

	
	/**
	 * Constructor
	 * @param width		width of map
	 * @param height	height of map
	 */
	public MiniMap(int width, int height) {
		super(width,height);
	}

	/**
	 * default constructor
	 */
	public MiniMap() {
		super();
	}

	/** 
	 * moving the map w.r.t. the main character
	 * @see model.Map#moveMap(model.MainCharacter)
	 */
	public boolean moveMap(MainCharacter fishy){
		int speed = -fishy.getSpeed();						// fish's speed and orientation
		int angle = fishy.getAngle();
															// proposed move
		double proposedX = getOrigin().getX() + speed*Math.cos(Math.toRadians(angle));		
		double proposedY = getOrigin().getY() + speed*Math.sin(Math.toRadians(angle));
		
		boolean validMove = ((0 >= proposedY) && (proposedY >= -getHeight()));		// is the move valid
		
		if (validMove){										// if so, execute
			getOrigin().setX((int) proposedX);
			getOrigin().setY((int) proposedY);		
		}
		return validMove;									// return if the move was valid
		
	}
}
