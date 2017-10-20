package model;

import java.util.*;
import java.math.*;
public class FishCharacter extends StuffInOcean{
	
	// Attributes
	
	/*
	private int xIncr = 1;		// x increment when moving
	private int yIncr = 1;		// y increment when moving
	*/
	private int step = 1;
	
	// orientations
	/*
	private boolean north;				// is the fish facing north
	private boolean south;				// is the fish facing south
	private boolean east; 				// is the fish facing east
	private boolean west; 				// is the fish facing west
	*/
	
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
		
		position = new Vector(5,5);
		radius = step;
		score = 0; 			// set score
		
		// set orientation
		/*
		east = true;
		west = false;
		north = false;
		south = false;
		isCaught = false;
		*/
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
	public void move() {
		// moves the fish	
		/*
		if (north) {
			position.setY(position.getY() - yIncr);
		} else if (south) {
			position.setY(position.getY() + yIncr);
		}
		if (east) {
			position.setX(position.getX() + xIncr);
		} else if (west) {
			position.setX(position.getX() - xIncr);
		}
		*/
		int deltaX = (int) Math.cos(Math.toRadians(angle))*step;
		int deltaY = (int) Math.sin(Math.toRadians(angle))*step;
		position.setX(position.getX() + deltaX);
		position.setY(position.getY() + deltaY);
	}
	
	public String getName(){
		return "Fish ";
	}
	
	/*
	 * Rotations of the fish
	 * Input:
	 * 		degrees		int 		degrees to rotate COUNTERCLOCKWISE
	 * Output:
	 * 		None
	 */
	public void rotate(int degrees){
		/*
		int currIndex = possibleOrientations.indexOf(getOrientation());
		String newOrientation = "";
		if (degrees < 45){
			newOrientation = getOrientation();
			extractOrientationFromString(newOrientation);
		}else if ((degrees > 45) && (degrees < 90)){
			newOrientation = possibleOrientations.get(currIndex + 1);
			extractOrientationFromString(newOrientation);
		}else if ((degrees > 90) && (degrees < 135)){
			newOrientation = possibleOrientations.get(currIndex + 2);
			extractOrientationFromString(newOrientation);
		}else if ((degrees > 135) && (degrees < 180)){
			newOrientation = possibleOrientations.get(currIndex + 3);
			extractOrientationFromString(newOrientation);
		}else if (degrees > 180){
			north = !north;
			south = !south;
			east = !east;
			west = !west;
			rotate(degrees - 180);
		}else if (degrees < 0){
			rotate(degrees + 360);
		}else{
			System.out.println("invalid degrees, do not know how to handle, degrees = " + degrees);
		}
		*/
		angle += degrees;
		if (angle < 0){
			rotate(360);
		}else if (angle >= 360){
			rotate(-360);
		}
	}
	
	/*
	public void extractOrientationFromString(String or){
		north = or.startsWith("north");
		south = or.startsWith("south");
		east = or.endsWith("east");
		west = or.endsWith("west");
		if (north && south){ 
			north = false;
			south = false;
		}
		if (east && west){
			west = false;
		}
	}
	*/
	
	// TODO verify this implementation of contact and getting caught
	
	public boolean isCaught(StuffInOcean s){
		return (s.isTrash() && isCollided(s));
	}
	
	// TODO bounds handling
	
	public String getOrientation(){
		/*
		String orient = "";
		if (north) {
			orient += "north";
		}else if (south) {
			orient += "south";
		}
		if (east) {
			orient += "east";
		}else if (west) {
			orient += "west";
		}
		return orient;
		*/
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
	
	// setters
	public void setStepSize(int l){
		step = l;
	}
}
