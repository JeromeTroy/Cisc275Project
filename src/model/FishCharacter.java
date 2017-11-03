package model;

import java.util.*;
import java.math.*;
public class FishCharacter extends StuffInOcean{
	
	/*
	 * This is the main character, which can be both the main fish and the scuba diver
	 * In the minigame
	 */
	
	// attributes
	protected int radius = 1; 		// size of the fish
	private int step = 1;			// speed of the fish
	private int angle; 				// angle (counterclockwise) from east facing
	private int score;				// player's score
	private boolean isCaught; 		// whether the fish is caught in trash
	
	// list of orientations Strings for printing
	private ArrayList<String> possibleOrientations = new ArrayList<String>();
	
	
	
	
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
	 * Moving (overriding StuffInOcean move()
	 * Currently prevents the fish from moving
	 */
	@Override
	public void move(FishCharacter fishy) {
		// TODO: discuss and determine how it will move
	}
	
	/*
	 * (non-Javadoc)
	 * @see model.StuffInOcean#getName()
	 * Getting the name of the character
	 * Overrides StuffInOcean so we can see who's who
	 */
	@Override
	public String getName(){
		return "Fish ";
	}
	
	/*
	 * (non-Javadoc)
	 * @see model.StuffInOcean#isFish()
	 * Tells the computer that this is the fish
	 */
	@Override
	public boolean isFish(){
		return true;
	}
	
	
	
	/*
	 * Rotations of the fish
	 * Input:
	 * 		degrees		int 		degrees to rotate COUNTERCLOCKWISE
	 * Output:
	 * 		None
	 * The angle must be in the range [0,360), 
	 * this method ensures that
	 */
	public void rotate(int degrees){
		angle += degrees;				// change angle
		if (angle < 0){
			rotate(360);				// negative angle, normalize
		}else if (angle >= 360){
			rotate(-360);				// angle too high, normalize to [0,360)
		}
	}
	
	// TODO verify this implementation of contact and getting caught
	
	/*
	 * Is the fish caught
	 * Input:
	 * 		StuffInOcean s
	 * Output:
	 * 		N/A
	 * assigns caught value to isCaught parameter
	 */
	public void isCaught(StuffInOcean s){
		isCaught = (s.isTrash() && isCollided(s));
	}
	
	// TODO bounds handling
	
	/*
	 * For printing:
	 * getting the orientation of the fish
	 * to the nearest cardinal direction
	 */
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
	
	/*
	 * (non-Javadoc)
	 * @see model.StuffInOcean#toString()
	 * Printing the fish itself
	 */
	@Override
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
	
	public int getSpeed(){
		return step;
	}
	
	public int getAngle(){
		return angle;
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

	public void setAngle(int a) {
		angle = a;	
	}

	public int getStepSize() {
		return step;
	}
	
	public void setCaught(boolean b){
		isCaught = b;
	}
	
}
