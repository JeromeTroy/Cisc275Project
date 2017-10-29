package model;

import java.util.*;

public class MainGameModel {

	private FishCharacter fishy; // the main character
	private Timer timer; // countdown timer
	private double trashAmount; // level of the trash around the main character
	private double foodAmount; // level of food around the main character
	private StuffSet everyThing; // all the stuff in the ocean
	private int gameLengthSeconds;
	private Map theMap; // the map
	private MiniGameModel miniGame; // mini game
	private int trashAccumulation = 2; // sets the accumulation of trash
	private boolean gameOver;
	private boolean isCaught;

	public MainGameModel() {
		startGame();
		gameOver = false;
		everyThing = new StuffSet();
		trashAmount = 0;
		foodAmount = 0;
		gameLengthSeconds = 180;

		fishy = new FishCharacter();
		everyThing.add(fishy);

		theMap = new Map(1000, 100); // map 1000 units long, 100 units tall
	}

	// getters
	public FishCharacter getFishy() {
		return fishy;
	}

	public StuffSet getStuff() {
		return everyThing;
	}

	public MiniGameModel getMiniGame() {
		return miniGame;
	}

	public double getTrashAmount() {
		return trashAmount;
	}

	public double getFoodAmount() {
		return foodAmount;
	}

	public Map getMap() {
		return theMap;
	}

	public boolean getIsCaught() {
		return isCaught;
	}

	// setters
	public void setTrashAccumulation(int i) {
		trashAccumulation = i;
	}

	// adders
	public boolean addStuff(StuffInOcean s) {
		return everyThing.add(s);
	}

	/*
	 * accumulateTrash() - accumulate trash randomly generates amount of trash
	 * between 0-set trashAccumulation places trash at random location of vector
	 * <0-100,0-100> parameters - none input - none return - none output - none
	 */
	public void accumulateTrash() { // accumulate trash
		int incTrash = (int) (Math.random() * 100 % trashAccumulation) + 1; // TODO:
																			// how
																			// much
																			// trash
																			// needs
																			// to
																			// be
																			// generated
																			// per
																			// method
																			// call
		for (int i = 0; i < incTrash; i++) {
			addStuff(new Trash((int) Math.random() * 100, (int) Math.random() * 100));
			// TODO: replace randomly generated location with locally random
			// location
		}
	}

	/*
	 * removeTrash() - removes all of the trash from the stuffSet parameters -
	 * none input - none return - none output - none
	 */
	public void removeTrash() {
		ArrayList<Trash> allTrash = new ArrayList<>();
		for (StuffInOcean s : everyThing) {
			if (s.isTrash()) {
				allTrash.add((Trash) s);
			}
		}

		everyThing.removeAll(allTrash);
	}

	public boolean endGame() {
		gameOver = true;
		System.out.println("Game Over");
		return true;
	}

	public void startGame() {
		System.out.println("Game Start...");
	}

	public void update() {
		if (!isCaught) {
			System.out.println("Update Game Model");
		}
	}

}
