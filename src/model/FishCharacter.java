package model;

import java.util.*;
import java.math.*;
public class FishCharacter extends StuffInOcean{
	protected int radius = 1;
	// Attributes //TODO: remove comments
	
	private int step = 1;
	
	// orientations
	private int angle; 					// angle (counterclockwise) from east facing
	
	private int score;			// player's score
	private ArrayList<String> possibleOrientations = new ArrayList<String>();
	
	private boolean isCaught; 		// whether the fish is caught in trash
	
	
	// Methods
	
	/*
	 * Constructor
	 * Input:
	 * 		none
	 * Output:
	 * 		new FishCharacter
	 * Sets location, score
	 */
	public FishCharacter(){	// TODO implement view parameters through controller
		
		position = new OurVector(10,10); //TODO: changed initial location from 5,5 to 10,10
		//radius = step;
		score = 0; 			// set score
		
		// set orientation
		isCaught = false;
		angle = 0;
		possibleOrientations.add("north");
		possibleOrientations.add("northwest");
		possibleOrientations.add("west");
		possibleOrientations.add("southwest");
		possibleOrientations.add("south");
		possibleOrientations.add("southeast");
		possibleOrientations.add("east");
		possibleOrientations.add("northeast");
	}
	
	/*
	 * Moving the fish
	 * This method DOES NOT handle bounds, this is dealt with at the caller
	 * Input:
	 * 		None
	 * Output:
	 * 		None
	 * moves the fish
	 */
	public void move(Map m) {
		// moves the fish	
		
		// temporary storage
		int prevX = position.getX();
		int prevY = position.getY();
		
		// moving
		int deltaX = (int) Math.cos(Math.toRadians(angle))*step;
		int deltaY = (int) Math.sin(Math.toRadians(angle))*step;
		position.setX(position.getX() + deltaX);
		position.setY(position.getY() + deltaY);
		
		// bounds handling
		if ((position.getX() > m.getLength()) || (position.getX() < 0)){
			position.setX(prevX);
		}
		if ((position.getY() > m.getHeight()) || (position.getY() < 0)){
			position.setY(prevY);
		}
	}
	
	public String getName(){
		return "Fish ";
	}
	
	public boolean isFish(){
		return true;
	}
	
	
	
	/*
	 * Rotations of the fish
	 * Input:
	 * 		degrees		int 		degrees to rotate COUNTERCLOCKWISE
	 * Output:
	 * 		None
	 */
	public void rotate(int degrees){
		angle += degrees;
		if (angle < 0){
			rotate(360);
		}else if (angle >= 360){
			rotate(-360);
		}
	}
	
	// TODO verify this implementation of contact and getting caught
	
	public boolean isCaught(StuffInOcean s){
		return (s.isTrash() && isCollided(s));
	}
	
	// TODO bounds handling
	
	public String getOrientation(){
		String orient = "";
		if ((angle >= 45/2) && (angle <= (180-45/2))){
			orient += "north";
		}else if ((angle >= (180 +45/2)) && (angle <= (360 - 45/2))){
			orient += "south";
		}
		if ((angle <= (90 - 45/2)) || (angle >= (270 + 45/2))){
			orient += "east";
		}else if ((angle >= (90 + 45/2)) && (angle <= (270 - 45/2))){
			orient += "west";
		}
		return orient;
	}
	
	public String toString() {
		String location = "The fish is at "+ position.toString() + " facing ";
		location += getOrientation();
		return location;
	}
	
	
	// getters
	public int getScore(){
		return score;
	}
	
	public int getRadius(){
		return radius;
	}
	
	// setters
	public void setStepSize(int l){
		step = l;
	}
	
	public void setRadius(int r){
		radius = r;
	}

	public ArrayList<String> getPossibleOrientations() {
		return possibleOrientations;
	}

	public boolean getIsCaught() {
		return isCaught;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int a) {
		angle = a;
		
	}

	public int getStepSize() {
		return step;
	}
	
}
