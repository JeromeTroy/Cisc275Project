package model;
import java.util.*;

public class MainGameModel {
	
	private FishCharacter mainCharacter; 		// the main character
	private Timer timer;						// countdown timer
	private double trashAmount;					// level of the trash around the main character
	private double foodAmount;					// level of food around the main character
	private Collection<Stuff> trashList;		// list of all trash components - stuff = both trash and food
	
	private MiniGameModel miniGame;				// mini game

}
