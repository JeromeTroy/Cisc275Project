package model;

/**
 * @author Group 4
 * extension of MainModel to provide tutorial behavior
 *
 */
public class Tutorial extends MainModel{
	String mode;
	boolean accumulateTrash = false;
	boolean accumulateFood = true;
	
	
	boolean runMode = true;
	int foodCollect = 5;
	int tmp = foodCollect; //stores initial val of food collect
	
	/**
	 * Constructor
	 */
	public Tutorial() {
		super();
		
		mode = "collectFood";
		getStuffSet().setAccumulationValue(20);
		setStartingFood(8);
		accumulateAll();
	}
	
	/**
	 * accumulate all the food and trash
	 * @see model.MainModel#accumulateAll()
	 */
	public void accumulateAll() {
		
		for (int i=0; i<getStartingFood(); i++) {
			boolean foodAdded = false;
			int[] foodLoc = {0, 0};
			while (!foodAdded) {
				foodLoc[0] = MainModel.randint(100, getMap().getLength()-20);
				foodLoc[1] = MainModel.randint(250, getMap().getHeight()-100);
				foodAdded = getStuffSet().add(foodLoc,"food");
				//System.out.println("trash"+trashLoc[0]+"/"+getMap().getLength());
			}
		}
	}
	
	
	/**
	 * accumulation of food and trash
	 * @see model.MainModel#accumulate()
	 */
	public void accumulate() {
		// assume not added
		boolean trashAdded = false;
		boolean foodAdded = false;
		
		// get locations (initialize x)
		
		if (accumulateTrash){
		int[] trashLoc = {accumulationDist, 0};
		// add trash first
		while (!trashAdded) {
			trashLoc[1] = randint(250, getMap().getHeight()); 		// random y location
			trashAdded = everyThing.add(trashLoc, "trash");			// try to add
		}
		}
		
		if (accumulateFood){
		int[] foodLoc = {accumulationDist, 0};
		// add the food
		while (!foodAdded) {
			foodLoc[1] = randint(250,getMap().getHeight());			// random y location
			foodAdded = everyThing.add(foodLoc, "food"); 			// try to add
		}
		}
		if (accumulateTrash) {
			trashAdded = false;
			//for (int i=0; i<1000; i++) {
				int[] trashLoc = {accumulationDist, 0};
				while (!trashAdded) {
					trashLoc[1] = randint(250, getMap().getHeight()); 		// random y location
					trashAdded = everyThing.add(trashLoc, "trash");	
				}
			//}
		}
	}
	
	/**
	 * updating the tutorial
	 * @see model.MainModel#update(int, int)
	 */
	public void update(int newSpeed, int deltaTheta) {
		//set mode
		if (mode == "collectFood" || mode == "collectFoodAgain"){
			if (foodCollect == 0){
				mode = "hitTrash";
				accumulateTrash = true;
				accumulateFood = false;
				//murderOfTrash = true;
			}
		} else if (mode == "hitTrash"){
			
		} else if (mode == "tutorialOver"){
			runMode = false;
			setInMiniGame(false);
		} else if (mode == "miniGameOver"){
			setInMiniGame(false);
		}
				
		
		if (!getInMiniGame()) { 			 		// in the main game
			
			// setup			getMainCharacter().setSpeed(newSpeed);
			getMainCharacter().setAngle(deltaTheta);
			
			// if move allowed
			//if (getMap().moveMap(getMainCharacter())) {
				getMap().moveMap(getMainCharacter());
				System.out.println("Valid move");
				
				// move everything and fish
				getStuffSet().move(getMainCharacter());
				
				if (newSpeed > minSpeed) {
					getMainCharacter().setSpeed(newSpeed/5000);
					getMainCharacter().move();
				}
			//}
			
			// move not allowed
			/*else {
				System.out.println("Invalid move, not moving");
			}*/
			
			// time to accumulate
			if (getStuffSet().shouldAccumulate()) {
				accumulate();
			}
			
			// display the state
			System.out.println(this);
			
			// collision checking
			String collision = everyThing.whatCollided(fishy);
			System.out.println("Checking collisions");
			System.out.println("Collisions: " + collision);
			
			// collision with trash
			if (collision.equals("trash")) {
				decreaseScore(); 				// lose points
				miniGame = new MiniGame(miniWidth,miniHeight); 		// start minigame
				mode = "inMiniGame";
				
				setInMiniGame(true);
			}
			// collision with food
			else if (collision.equals("food")) {
				increaseScore();// gain points
				remainingTime += foodTime;
				foodCollect -= 1;
			}
			
			// check if game over
			setGameOver(-getMap().getOrigin().getX() >= getMap().getLength());
			if (getGameOver()) {
				setHasWon(true);
				getStuffSet().clearAll();
			}
		}
		// in the minigame
		else {
			// update the mini game
			getMiniGame().update(newSpeed, deltaTheta);
			
			// check if we should still be in the minigame
			if (!getMiniGame().getMiniGameOver() == false){
				mode = "miniGameOver";
			}
			setInMiniGame(!getMiniGame().getMiniGameOver());
			
			// minigame is over
			if (!getInMiniGame()) {
				//mode = "miniGameOver";
				System.out.println("Mini game over");
				getStuffSet().removeAllTrash(); 			// eliminate all trash in the main game
				
				mode = "collectFoodAgain";
				foodCollect = tmp;
			} 
		}
		
	}
	
	/**
	 * get food to collect
	 * @return
	 */
	public int getCollectFood(){
		return foodCollect;
	}
	
	/**
	 * current mode of tutorial
	 * @return
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * current mode of tutorial
	 * @param mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

}