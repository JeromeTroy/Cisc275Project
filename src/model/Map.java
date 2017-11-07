package model;

import java.util.Vector;

import java.util.*;

public class Map {
	/**
	 * The map of the game
	 * No real detailed information other than the size of the map
	 */

	private int length; 			// total length of the map
	private int height; 			// total height of the map
	private int uniqueLength; 		// length of the map that is unique, beyond this will be periodic	
	
	private OurVector origin; 		// location of the origin of the map relative to the fish
	
	// Constructors
	
	
	/**
	 * Constructor
	 * @param l		int 		length (total)
	 * @param h		int 		height 
	 * @param ul 	int 		unique length
	 */
	public Map(int l, int h, int ul){
		length = l;
		height = h;
		uniqueLength = ul;
		origin = new OurVector();
	}
	
	
	/**
	 * Constructor
	 * @param l	 	int 		length (both total and unique)
	 * @param h		int 		height
	 */
	public Map(int l, int h){
		length = l;
		height = h;
		origin = new OurVector();
		uniqueLength = length;
	}
	
	public Map() {
		length = 100;
		height = 100;
		uniqueLength = length;
		origin = new OurVector();
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
		double proposedX = origin.getX() + speed*Math.cos(Math.toRadians(angle));		
		double proposedY = origin.getY() + speed*Math.sin(Math.toRadians(angle));
		
		boolean validMove = ((0 <= proposedY) && (proposedY <= height));		// is the move valid
		
		if (validMove){										// if so, execute
			origin.setX((int) proposedX);
			origin.setY((int) proposedY);		
		}
		return validMove;									// return if the move was valid
	}

	/**
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * printing the map
	 * @see model.OurVector#toString()
	 * @return description of the map
	 */
	public String toString() {
		String mapString = "Length: " + length + ", height: " + height + "unique length: " + uniqueLength;
		mapString += "\n Origin at " + origin.toString();
		return mapString;
	}
}
