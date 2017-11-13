package controller;

//TODO: organize imports
import model.*;
import javax.swing.*;
import view.*;
import java.util.*;
import java.util.Timer;

public class MainController {

	private ModelController modelControl; 		// the model controller
	//private ViewController viewControl;		// the view controller
	private boolean usingView; 					// flag of whether the view is in use
	
	
	// methods
	
	
	
	/**
	 * running the whole game
	 * @param args 
	 */
	public static void main(String[] args) {
		MainController game = new MainController(false);
		game.initializeModel();
		while (!game.getModelController().getModel().getGameOver()) {
			game.updateModel();
		}
	}
	

	// constructors
	
	
	/**
	 * default - no view 
	 */
	public MainController() {
		setUsingView(false);
		modelControl = new ModelController();
	}
	
	
	
	/**
	 * sets whether the view is in use
	 * @param b 		is the view in use
	 */
	public MainController(boolean b) {
		this();
		setUsingView(b);
		modelControl = new ModelController();
		// TODO: if usingView, initialize it and the model parameters from it
	}
	
	
	
	// methods
	
	
	/**
	 * Initialize the model
	 */
	private void initializeModel() {
		int fishySize;
		int trashSize;
		int foodSize;
		int mapHeight;
		int mapLength;
		int mapUnique;
		if (isUsingView()) {
			// TODO: replace these placeholder values
			fishySize = 1; trashSize = 1; foodSize = 1;
			mapHeight = 1; mapLength = 1; mapUnique = 1;
		}else {
			fishySize = 1; trashSize = 1; foodSize = 1;
			mapHeight = 100; mapLength = 100; mapUnique = 100;
		}
		getModelController().setup(fishySize, foodSize, trashSize, mapHeight, mapLength, mapUnique);
	}
	
	
	
	/**
	 * Update the model
	 */
	private void updateModel() {
		int newSpeed;
		int deltaTheta;
		if (isUsingView()) {
			// TODO: get view params to update values
			newSpeed = 0;
			deltaTheta = 0;
		}else {
			Scanner sc = new Scanner(System.in);
			String angle = sc.nextLine();
			String speed = sc.nextLine();
			try {
				getModelController().setDeltaTheta(Integer.parseInt(angle));
			}catch(NumberFormatException ex) {
				getModelController().setDeltaTheta(0);
			}
			try {
				getModelController().setNewSpeed(Integer.parseInt(speed));
			}catch(NumberFormatException ex) {
				getModelController().setNewSpeed(0);
			}
		}
		getModelController().tick();
	}
	
	
	// getters
	
	
	public ModelController getModelController() {
		return modelControl;
	}
	
	
	
	/**
	 * get if the view is being used
	 * @return
	 */
	public boolean isUsingView() {
		return usingView;
	}


	// setters
	
	
	public void setUsingView(boolean b) {
		usingView = b;
	}
}
