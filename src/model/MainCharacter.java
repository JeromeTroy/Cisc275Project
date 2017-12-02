package model;

import java.util.*;
import java.math.*;
public class MainCharacter{
	
	/*
	 * This is the main character, which can be both the main fish and the scuba diver
	 * In the minigame
	 */
	
	// attributes
	private int radius = 10; 		// size of the fish
	private int speed = 5;			// speed of the fish
	private int angle; 				// angle (counterclockwise) from east facing
	
	private final double minStep = 3;
	
	// location
	private OurVector position;
		
	
	// Methods
	
	
	// constructors
	/**
	 * Constructor
	 * Sets location, score
	 */
	public MainCharacter(){	// TODO implement view parameters through controller
		position = new OurVector(); //TODO: changed initial location from 5,5 to 10,10
		// set orientation
		setAngle(0);
	}
	
	
	// given map
	/**
	 * Constructor
	 * @param map 		// map the fish is in
	 */
	public MainCharacter(Map map) {
		this();
		position.setY(map.getHeight()/2);
	}

	
	// given map, fish size, and speed
	/**
	 * Constructor
	 * @param map 		// map the fish is in
	 * @param size 		// size of the fish
	 * @param initSpeed 	// initial speed of the fish
	 */
	public MainCharacter(Map map, int size, int initSpeed) {
		this(map);
		setRadius(size);
		setSpeed(initSpeed);
	}
	
	
	// moving
	
	
	// vertially
	/** (non-Javadoc)
	 * MainCharacter Moving
	 * fish moves up and down 
	 */
	public void move() {
		double deltaY =  speed * Math.sin(Math.toRadians(angle));
		double deltaX = speed * Math.cos(Math.toRadians(angle));
		if (Math.abs(deltaX) > minStep) {
			getPosition().setX(position.getX() + deltaX);
		}
		if (Math.abs(deltaY) > minStep) {
			getPosition().setY(position.getY() + deltaY);
		}
	}
		
	
	// rotations
	/**
	 * Rotations of the fish
	 * @param degrees 	int 		degrees to rotate COUNTERCLOCKWISE
	 * The angle must be in the range [0,360), 
	 * this method ensures the same
	 */
	public void rotate(int degrees){
		setAngle(getAngle() + degrees);
		
		// verify angle is between 0 and 360
		if (getAngle() < 0){
			rotate(360);				// negative angle, normalize
		}else if (getAngle() >= 360){
			rotate(-360);				// angle too high, normalize to [0,360)
		}
	}

	
	// contacting
	/**
	 * Determines if the fish is in contact with another object
	 * @param v 		position of the object
	 * @param size 		size of the object
	 * @return 			if the fish ran into the object
	 */
	public boolean isContacting(int[] v, int size) {
		boolean contact = (getPosition().distFrom(v[0], v[1]) <= Math.pow(getRadius() + size,2));
		//boolean inFront = (v[0] >= getPosition().getX());
		//return (contact && inFront);
		return contact;
	}
	
	
	
	// printing
	
	
	// orientation
	/**
	 * For printing:
	 * getting the orientation of the fish
	 * to the nearest cardinal direction
	 * @return 	string 		orient 		orientation of the fish
	 */
	public String getOrientation(){
		String orient = "";
		if ((getAngle() >= 45/2) && (getAngle() <= (180-45/2))){
			orient += "north";
		}else if ((getAngle() >= (180 +45/2)) && (getAngle() <= (360 - 45/2))){
			orient += "south";
		}
		if ((getAngle() <= (90 - 45/2)) || (getAngle() >= (270 + 45/2))){
			orient += "east";
		}else if ((getAngle() >= (90 + 45/2)) && (getAngle() <= (270 - 45/2))){
			orient += "west";
		}
		return orient;
	}
	
	
	// printing character
	/**
	 * (non-Javadoc)
	 * @see model.StuffInOcean#toString()
	 * @see model.MainCharacter#getOrientation()
	 * @see model.MainCharacter#getName()
	 * Printing the fish itself
	 * @return location string of the fish
	 */
	@Override
	public String toString() {
		String location = "The fish is at "+ getPosition().toString() + " facing ";
		location += getOrientation();
		return location;
	}
	
	
	// getters
	
	
	// radius
	public int getRadius(){
		return radius;
	}
	
	
	// speed
	public int getSpeed(){
		return speed;
	}
	
	
	// angle
	public int getAngle(){
		return angle;
	}
	
	
	// position
	public OurVector getPosition() {
		return position;
	}
	
	
	// setters
	
	
	// speed
	/**
	 * Sets speed 
	 * @param l 		new speed
	 * If new speed is 0, keeps the old speed
	 */
	public void setSpeed(int l){
		if (l != 0) {
			speed = l;
		}
	}
	
	
	// angle
	public void setAngle(int a) {
		angle = a;
	}
	
	
	// size
	/**
	 * Will be used to set the fish size based on the image used
	 * @param r 		size of the fish
	 */
	public void setRadius(int r){
		radius = r;
	}

	
	
	
	
	
}
