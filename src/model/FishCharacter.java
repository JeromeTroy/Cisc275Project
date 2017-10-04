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
	
	// TODO verify this implementation of contact and getting caught
	public boolean isContact(Stuff s){
		boolean inXRange = false;
		boolean inYRange = false;
		if ((getLowerX() <= s.getUpperX()) && (getLowerX() >= s.getLowerX())){
			inXRange = true;
		}
		else if ((getUpperX() >= s.getUpperX()) && (getUpperX() <= s.getLowerX())){
			inXRange = true;
		}
		if ((getLowerY() <= s.getUpperY()) && (getLowerY() >= s.getLowerY())){
			inYRange = true;
		}
		else if ((getUpperY() >= s.getUpperY()) && (getUpperY() <= s.getLowerY())){
			inYRange = true;
		}
		return (inXRange && inYRange);
	}
	
	public boolean isCaught(Stuff s){
		return (s.isTrash() && isContact(s));
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
		String location = "The fish is at (" + xloc + ", " + yloc + ") facing ";
		location += getOrientation();
		return location;
	}
	
	
	// getters
	public int getLowerX(){
		return xloc;
	}
	public int getUpperY(){
		return yloc;
	}
	public int getUpperX(){
		return xloc + xlen;
	}
	public int getLowerY(){
		return yloc + ylen;
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
