package model;

import java.util.*;
public class StuffInOcean implements Comparable<StuffInOcean>{
	/*
	 * Super class for the fish, food and trash
	 */
	
	protected OurVector position; 	// position vector
	protected int radius; 			// size of object
	
	// Constructors
	// default
	public StuffInOcean(){
		position = new OurVector();
	}
	
	// given position
	public StuffInOcean(int xval, int yval){
		position = new OurVector(xval,yval);
	}
	
	// given position vector
	public StuffInOcean(OurVector v){
		position = v;
	}
	
	// given position and size
	public StuffInOcean(int xval, int yval, int rad){
		position = new OurVector(xval,yval);
		radius = rad;
	}
	
	// given position vector and size
	public StuffInOcean(OurVector v, int rad){
		position = v;
		radius = rad;
	}
	
	// given only size
	public StuffInOcean(int rad){
		radius = rad;
	}
	
	/*
	 * printing(non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Input:
	 * 		None
	 * Output:
	 * 		<Type> located at <x,y>
	 */
	public String toString(){
		return getName() + "located at " + position.toString();
		// getName() is polymorphic and so will allow us to see each name of  every object
	}
	
	// method to get the name of the object (fish, food, trash)
	public String getName(){
		return "";
	}
	
	// getters
	public OurVector getPosition(){
		return position;
	}
	
	public boolean isFood(){
		return false;
	}
	
	public boolean isFish(){
		return false;
	}
	
	public boolean isTrash(){
		return false;
	}
	
	public int getRadius(){
		return radius;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * 
	 *  For comparing
	 *  compares based on position vector
	 *  Input:
	 *  	o 		Object 		object to be compared to
	 *  Output:
	 *  	int 	value of comparison
	 */
//	changed natural compare to for stuffInOcean
//	public int compareTo(StuffInOcean s){
//		return position.compareTo(s.getPosition());
//	}
	// TODO; verify this implementation
	public int compareTo(StuffInOcean s){
		return this.getPosition().distFrom(s.getPosition());
	}
	
	
	/*
	 * Collision detection
	 * Uses radii compared to separation distances
	 * Input:
	 * 		s 		StuffInOcean 		stuff to see if collided
	 * Output:
	 * 		boolean 	whether the 2 objects are collided
	 */
	public boolean isCollided(StuffInOcean s){
		int separation = position.distFrom(s.getPosition());
		int radiiSum = this.getRadius() + s.getRadius();
		return (separation <= radiiSum*radiiSum);
	}
	
	/*
	 * Moving
	 * Everything will move relative to the fish
	 */
	public void move(FishCharacter fish){
		int speed = -fish.getSpeed();
		int angle = fish.getAngle();
		
		int deltaX = (int) (speed*Math.cos(Math.toRadians(angle)));
		int deltaY = (int) (speed*Math.sin(Math.toRadians(angle)));
		
		position.setX(position.getX() + deltaX);
		position.setY(position.getY() + deltaY);
		
		
	}
}
