package model;
<<<<<<< HEAD

import java.util.Vector;

=======
import java.util.*;
>>>>>>> b2f38653bf57906637a81056d34a442cc025117c
public class Map {

	private int length; 			// total length of the map
	private int height; 			// total height of the map
	private int uniqueLength; 		// length of the map that is unique, beyond this will be periodic
<<<<<<< HEAD
	private OurVector position;
	
	
=======
	private OurVector origin; 		// location of the origin of the map relative to the fish
>>>>>>> b2f38653bf57906637a81056d34a442cc025117c
	
	public int getLength(){
		return length;
	}
	
	public int getHeight(){
		return height;
	}
	
	public Map(int l, int h, int ul){
		length = l;
		height = h;
		uniqueLength = ul;
	}
	
	public Map(int l, int h){
		length = l;
		height = h;
	}
	
	public void setLength(int l){
		length = l;
	}
	
	public void setHeight(int h){
		height = h;
	}
	
	public void setUniqueLength(int u){
		uniqueLength = u;
	}
	
<<<<<<< HEAD
	public void getCenter(){
		int segment = Math.floorDiv(position.getX(), uniqueLength);
		//return new OurVector((segment*uniqueLength)+0.5*uniqueLength);
	}
	
//	public Vector getPosition(){
//		return position;
//	}
=======
	
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
	public boolean moveMap(FishCharacter fishy){
		int speed = -fishy.getSpeed();
		int angle = fishy.getAngle();
		
		double proposedX = origin.getX() + speed*Math.cos(Math.toRadians(angle));
		double proposedY = origin.getY() + speed*Math.sin(Math.toRadians(angle));
		
		boolean validMove = ((0 <= proposedY) && (proposedY <= height));
		
		if (validMove){
			origin.setX((int) proposedX);
			origin.setY((int) proposedY);		
		}
		return validMove;
	}
	
>>>>>>> b2f38653bf57906637a81056d34a442cc025117c
}
