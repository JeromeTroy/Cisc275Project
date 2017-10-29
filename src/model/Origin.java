package model;

public class Origin extends StuffInOcean{

	public String getName() {
		return "Relative origin";
	}
	
	/*
	 * This will be used to detect whether the move about to be done is valid
	 * If it is a valid move, the controller will allow the move, otherwise it will 
	 * be prevented
	 */
	public boolean canMove(FishCharacter fish, Map m){
		int speed = -fish.getSpeed();
		int angle = fish.getAngle();
		
		double potentialX = position.getX() + speed*Math.cos(Math.toRadians(angle));
		double potentialY = position.getY() + speed*Math.sin(Math.toRadians(angle));
		
		boolean validX = ((0 > potentialX) && (-m.getLength() < potentialX));
		boolean validY = ((0 > potentialY) && (-m.getHeight() < potentialY));
		
		return (validX && validY);
	}
}
