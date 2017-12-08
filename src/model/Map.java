package model;

/**
 * @author Group 4
 * The map of the game
 * No real detailed information other than the size of the map
 * Note: the default size will be the size of the minigame
 */
public class Map {
	

	// dimensions
	private int length; 			// total length of the map
	private int height; 			// total height of the map
	private int uniqueLength; 		// length of the map that is unique, beyond this will be periodic	
	
	// location tracking
	private OurVector origin; 		// location of the origin of the map relative to the fish
	
	
	// methods
	
	
	// Constructors
	
	
	/**
	 * Constructor
	 * @param l		int 		length (total)
	 * @param h		int 		height 
	 * @param ul 	int 		unique length
	 */
	public Map(int l, int h, int ul){
		setLength(l);
		setHeight(h);
		setUniqueLength(ul);
		origin = new OurVector();
		origin.setY(-getHeight()/2);
	}
	
	
	/**
	 * Constructor
	 * @param l	 	int 		length (both total and unique)
	 * @param h		int 		height
	 */
	public Map(int l, int h){
		setLength(l);
		setHeight(h);
		origin = new OurVector();
		setUniqueLength(getLength());
		origin.setY(-getHeight()/2);
	}
	
	
	// default
	/**
	 * Default constructor, sets default sizes
	 * Default sizes will also be the size of the minigame
	 */
	public Map() {
		// TODO: verify these numbers
		setLength(10000);
		setHeight(1000);
		setUniqueLength(getLength());
		origin = new OurVector();
		origin.setY(-getHeight()/2);
	}
	
	
	// moving
	/**
	 * Moving the map's origin
	 * Checks if the move is valid, and if it is it moves
	 * @param fishy 	MainCharacter
	 * @return boolean 	if the move is valid
	 * Note:
	 * 		if the move was valid it moves the origin
	 */
	public boolean moveMap(MainCharacter fishy){
		int speed = -fishy.getSpeed();						// fish's speed and orientation
		int angle = fishy.getAngle();
															// proposed move
		//double proposedX = getOrigin().getX() + speed*Math.cos(Math.toRadians(angle));		
		double proposedY = getOrigin().getY() + speed*Math.sin(Math.toRadians(angle));
		
		boolean validMove = ((0 >= proposedY) && (proposedY >= -getHeight()));		// is the move valid
		
		if (validMove){										// if so, execute
			getOrigin().setX(getOrigin().getX() - fishy.getmotion());
			getOrigin().setY((int) proposedY);		
		}
		//return validMove;									// return if the move was valid
		
		
		return true;
	}
	
	
	// printing
	/**
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * printing the map
	 * @see model.OurVector#toString()
	 * @return description of the map
	 */
	public String toString() {
		String mapString = "Length: " + getLength() + ", height: " + getHeight() + " unique length: " + getUniqueLength();
		mapString += "\n Origin at " + getOrigin().toString();
		mapString += "\n Length Remaining: " + getRemainingLength();
		return mapString;
	}
	
	
	// getters
	
	/**
	 * left over length of map
	 * @return
	 */
	public double getRemainingLength(){
		return (double)length + origin.getX();
	}
	
	// length
	/**
	 * length of map
	 * @return
	 */
	public int getLength(){
		return length;
	}
	
	
	// height
	/**
	 * height of map
	 * @return
	 */
	public int getHeight(){
		return height;
	}
	
	
	/**
	 *  unique length of map
	 * @return
	 */
	public int getUniqueLength() {
		return uniqueLength;
	}
	
	
	// origin
	/**
	 * location of the origin of the map (relative to fish)
	 * @return
	 */
	public OurVector getOrigin() {
		return origin;
	}
	
	
	// setters
	
	
	// length
	/**
	 * length of map
	 * @param l
	 */
	public void setLength(int l){
		length = l;
	}
	
	
	/**
	 *  height of map
	 * @param h
	 */
	public void setHeight(int h){
		height = h;
	}
	
	
	// unique length
	/**
	 * unique length of map
	 * @param u
	 */
	public void setUniqueLength(int u){
		uniqueLength = u;
	}
	
	
	
	

	
}
