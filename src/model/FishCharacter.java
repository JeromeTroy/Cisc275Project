package model;

public class FishCharacter {
	
	// Attributes
	private int xloc; 	// left bound
	private int yloc; 	// upper bound
	private int xlen; 	// length in x direction
	private int ylen; 	// length in y direction
	
	private final int xIncr = 1;		// x increment when moving
	private final int yIncr = 1;		// y increment when moving
	
	// orientations
	private boolean north;				// is the fish facing north
	private boolean south;				// is the fish facing south
	private boolean east; 				// is the fish facing east
	private boolean west; 				// is the fish facing west
	
	private int score;	// player's score
	
	private boolean isCaught; 		// whether the fish is caught in trash
	
	
	// Methods
	
	public FishCharacter(){	// TODO implement view parameter
		/*
		 * Constructor
		 * Input:
		 * 		none
		 * Output:
		 * 		new FishCharacter
		 * Sets location, score
		 */
		
		xloc = 5;			// may need to be changed
		yloc = 5;
		xlen = 0; 			// TODO when implemented view, this value will be assigned
		ylen = 0;
		
		score = 0; 			// set score
		
		// set orientation
		east = true;
		west = false;
		north = false;
		south = false;
		isCaught = false;
	}
	
	public void move(){
		// moves the fish
		
		if (north) {
			yloc += yIncr;
		} else if (south) {
			yloc -= yIncr;
		}
		if (east) {
			xloc += xIncr;
		} else if (west) {
			xloc -= xIncr;
		}
	}
	
	// TODO implement rotation of fish to new orientation
	
	// TODO implement getting caught by trash
	
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
		String location = "The fish is at (" + xloc + ", " + yloc + ") facing ";
		location += getOrientation();
		return location;
	}
	
	
	// getters
	public int getxloc(){
		return xloc;
	}
	public int getyloc(){
		return yloc;
	}
	public int getScore(){
		return score;
	}
	
	// setters
	public void setxlen(int l){
		xlen = l;
	}
	public void setylen(int l){
		ylen = l;
	}
}
