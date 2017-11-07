package model;

import java.util.*;

public class StuffInOcean implements Comparable<StuffInOcean>{
	/**
	 * Super class for the fish, food and trash
	 */
	
	protected OurVector position; 	// position vector
	protected int radius; 			// size of object
	
	// Constructors
	/**
	 * Default constructor
	 */
	public StuffInOcean(){
		position = new OurVector();
	}
	
	/**
	 * Constructor
	 * @param xval 		x location of object
	 * @param yval 		y location
	 */
	public StuffInOcean(int xval, int yval){
		position = new OurVector(xval,yval);
	}
	
	/**
	 * Constructor
	 * @param v 		OurVector for position
	 */
	public StuffInOcean(OurVector v){
		position = v;
	}
	
	/**
	 * Constructor 
	 * @param xval 		x location
	 * @param yval 		y location
	 * @param rad 		size
	 */
	public StuffInOcean(int xval, int yval, int rad){
		position = new OurVector(xval,yval);
		radius = rad;
	}
	
	/**
	 * Constructor
	 * @param v 		OurVector for position
	 * @param rad	 	size
	 */
	public StuffInOcean(OurVector v, int rad){
		position = v;
		radius = rad;
	}
	
	/**
	 * Constructor
	 * @param rad 		size
	 */
	public StuffInOcean(int rad){
		radius = rad;
	}
	
	/**
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * @return description of object
	 * 		<Type> located at <x,y>
	 */
	public String toString(){
		return getName() + "located at " + position.toString();
		// getName() is polymorphic and so will allow us to see each name of  every object
	}
	
	/**
	 * @return Empty string
	 * @see model.Food#getName()
	 * @see model.Trash#getName()
	 * @see model.MainCharacter#getName()
	 */
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
	
	/**
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * @param s 		StuffInOcean to compare to
	 *  compares based on position vector
	 * @return 	int 	value of comparison
	 */
//	changed natural compare to for stuffInOcean
//	public int compareTo(StuffInOcean s){
//		return position.compareTo(s.getPosition());
//	}
	// TODO; verify this implementation
	public int compareTo(StuffInOcean s){
		return this.getPosition().distFrom(s.getPosition());
	}
	
	
	/**
	 * Collision detection
	 * Uses radii compared to separation distances
	 * @param s 		StuffInOcean 		stuff to see if collided
	 * @return boolean 	whether the 2 objects are collided
	 */
	public boolean isCollided(StuffInOcean s){
		int separation = position.distFrom(s.getPosition());
		int radiiSum = this.getRadius() + s.getRadius();
		return (separation <= radiiSum*radiiSum);
	}
	
	/**
	 * Moving
	 * Everything will move relative to the fish
	 * @param fish 		MainCharacter
	 */
	public void move(MainCharacter fish){
		int speed = -fish.getSpeed();
		int angle = fish.getAngle();
		
		int deltaX = (int) (speed*Math.cos(Math.toRadians(angle)));
		int deltaY = (int) (speed*Math.sin(Math.toRadians(angle)));
		
		position.setX(position.getX() + deltaX);
		position.setY(position.getY() + deltaY);
		
		
	}
}
