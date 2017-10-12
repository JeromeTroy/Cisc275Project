package model;

import java.util.*;
public class FishCharacter extends StuffInOcean{
	
	// Attributes
	
	private int xIncr = 1;		// x increment when moving
	private int yIncr = 1;		// y increment when moving
	
	// orientations
	private boolean north;				// is the fish facing north
	private boolean south;				// is the fish facing south
	private boolean east; 				// is the fish facing east
	private boolean west; 				// is the fish facing west
	
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
		radius = xIncr;
		score = 0; 			// set score
		
		// set orientation
		east = true;
		west = false;
		north = false;
		south = false;
		isCaught = false;
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
		boolean badMove = false;
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
	}
	
	// TODO implement rotation of fish to new orientation
	/*
	 * Rotations of the fish
	 * Input:
	 * 		degrees		int 		degrees to rotate COUNTERCLOCKWISE
	 * Output:
	 * 		None
	 */
	public void rotate(int degrees){
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
	}
	
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
	// TODO verify this implementation of contact and getting caught
	
	public boolean isCaught(StuffInOcean s){
		return (s.isTrash() && isCollided(s));
	}
	
	// TODO bounds handling
	
	public String getOrientation(){
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
	public void setXIncr(int l){
		xIncr = l;
	}
	public void setYIncr(int l){
		yIncr = l;
	}
}
