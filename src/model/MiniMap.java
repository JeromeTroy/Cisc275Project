package model;

public class MiniMap extends Map{

	
	public MiniMap(int width, int height) {
		super(width,height);
	}

	public MiniMap() {
		super();
	}

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
