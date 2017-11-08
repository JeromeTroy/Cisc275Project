package model;

import java.util.*;
import java.math.*;
public class MainCharacter{
	
	/*
	 * This is the main character, which can be both the main fish and the scuba diver
	 * In the minigame
	 */
	
	// attributes
	protected int radius = 1; 		// size of the fish
	private int speed = 1;			// speed of the fish
	private int angle; 				// angle (counterclockwise) from east facing
	
	private boolean isCaught; 		// whether the fish is caught in trash
	private OurVector position;
	
	// list of orientations Strings for printing
	private ArrayList<String> possibleOrientations = new ArrayList<String>();
	
	
	
	
	// Methods
	
	/**
	 * Constructor
	 * Sets location, score
	 */
	public MainCharacter(){	// TODO implement view parameters through controller
		
		position = new OurVector(); //TODO: changed initial location from 5,5 to 10,10
		//radius = step;
		
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
	
	public MainCharacter(Map map) {
		this();
		position.setY(map.getHeight()/2);
	}

	/** (non-Javadoc)
	 * MainCharacter Moving
	 * fish moves up and down 
	 */
	public void move() {
		int deltaY = (int) (speed * Math.sin(Math.toRadians(angle)));
		position.setY(position.getY() + deltaY);
	}
		
	/**
	 * Rotations of the fish
	 * @param degrees 	int 		degrees to rotate COUNTERCLOCKWISE
	 * The angle must be in the range [0,360), 
	 * this method ensures the same
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
	
	
	// TODO bounds handling
	
	/**
	 * For printing:
	 * getting the orientation of the fish
	 * to the nearest cardinal direction
	 * @return 	string 		orient 		orientation of the fish
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
	
	/**
	 * Determines if the fish is in contact with another object
	 * @param v 		position of the object
	 * @param size 		size of the object
	 * @return 			if the fish ran into the object
	 */
	public boolean isContacting(int[] v, int size) {
		boolean contact = (position.distFrom(v[0], v[1]) <= Math.pow(radius + size,2));
		boolean inFront = (v[0] > position.getX());
		return (contact && inFront);
	}
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
		String location = "The fish is at "+ position.toString() + " facing ";
		location += getOrientation();
		return location;
	}
	
	// getters
	
	public int getRadius(){
		return radius;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public int getAngle(){
		return angle;
	}
	// setters
	public void setSpeed(int l){
		speed = l;
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
	
	public void setCaught(boolean b){
		isCaught = b;
	}
	
}
