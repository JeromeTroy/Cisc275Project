package model;

import java.util.Vector;

import java.util.*;
public class Map {
	/*
	 * The map of the game
	 * No real detailed information other than the size of the map
	 */

	private int length; 			// total length of the map
	private int height; 			// total height of the map
	private int uniqueLength; 		// length of the map that is unique, beyond this will be periodic	
	
	private OurVector origin; 		// location of the origin of the map relative to the fish
	
	// Constructors
	
	// for given length, height, uniqueLength
	public Map(int l, int h, int ul){
		length = l;
		height = h;
		uniqueLength = ul;
	}
	
	// for given only a length and a height
	public Map(int l, int h){
		length = l;
		height = h;
	}
	
	// getters
	public int getLength(){
		return length;
	}
	
	public int getHeight(){
		return height;
	}
	
	// setters
	public void setLength(int l){
		length = l;
	}
	
	public void setHeight(int h){
		height = h;
	}
	
	public void setUniqueLength(int u){
		uniqueLength = u;
	}
	
	/*
	public void getCenter(){
		int segment = Math.floorDiv(origin.getX(), uniqueLength);
		//return new OurVector((segment*uniqueLength)+0.5*uniqueLength);
	}
	*/
	
//	public Vector getPosition(){
//		return position;
//	}
	
	/*
	 * Moving the map's origin
	 * Checks if the move is valid, and if it is it moves
	 * Input:
	 * 		FishCharacter fishy
	 * Output:
	 * 		boolean indicating if the move was valid
	 * Note:
	 * 		if the move was valid it moves the origin
	 */
	public boolean moveMap(MainCharacter fishy){
		int speed = -fishy.getSpeed();						// fish's speed and orientation
		int angle = fishy.getAngle();
															// proposed move
		double proposedX = origin.getX() + speed*Math.cos(Math.toRadians(angle));		
		double proposedY = origin.getY() + speed*Math.sin(Math.toRadians(angle));
		
		boolean validMove = ((0 <= proposedY) && (proposedY <= height));		// is the move valid
		
		if (validMove){										// if so, execute
			origin.setX((int) proposedX);
			origin.setY((int) proposedY);		
		}
		return validMove;									// return if the move was valid
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * printing
	 */
	public String toString() {
		String mapString = "Length: " + length + ", height: " + height + "unique length: " + uniqueLength;
		mapString += "\n Origin at " + origin.toString();
		return mapString;
	}
}
