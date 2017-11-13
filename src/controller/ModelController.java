package controller;

import java.util.Scanner;

import model.MainModel;

/**
 * @author jerome
 * This class will be used to control the model
 * It will:
 * 	set up the model (parameters from view)
 * 	run the model
 * 	tell the other controllers about the model
 * 	no other controller will need to know about the model
 */
public class ModelController {

	private MainModel model; 		// model for the game
	
	// updating
	private int newSpeed; 			// updated speed
	private int deltaTheta; 		// updated angle
	
	// constructors
		
	
	/**
	 * Default constructor
	 * Defaulting to no view
	 */
	public ModelController() {
		model = new MainModel();
	}
	
	
	// setup
	/**
	 * Setting up the model from the view
	 * @param mainCharRad 		size of the main character
	 * @param foodSize 			size of the food
	 * @param trashSize 		" 	 "   "  trash
	 * @param mapHeight 		height of the map
	 * @param mapLength 		length  "  "   "
	 * @param mapUnique			unique lenght of the map
	 */
	public void setup(int mainCharRad, int foodSize, int trashSize, 
			int mapHeight, int mapLength, int mapUnique) {
		
		getModel().getMainCharacter().setRadius(mainCharRad);
		getModel().getStuffSet().setFoodSize(foodSize);
		getModel().getStuffSet().setTrashSize(trashSize);
		getModel().getMap().setHeight(mapHeight);
		getModel().getMap().setLength(mapLength);
		getModel().getMap().setUniqueLength(mapUnique);
	}
	
	
	// updating
	/**
	 * updating the model through this controller
	 */
	public void tick() {
		getModel().update(newSpeed,deltaTheta);
	}
	
	
	// getters
	
	
	/**
	 * get the current model
	 * @return 	the model
	 */
	public MainModel getModel() {
		return model;
	}
	
	
	// setters
	
	
	/**
	 * Sets the new speed to the specified value
	 * @param s 		next speed
	 */
	public void setNewSpeed(int s) {
		newSpeed = s;
	}
	
	
	/**
	 * Sets the angle changer to the specified value
	 * @param dt 		next angle change
	 */
	public void setDeltaTheta(int dt) {
		deltaTheta = dt;
	}
}
