package model;

public class Tutorial extends MainModel{
	String mode;
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}


	boolean accumulateTrash = false;
	boolean accumulateFood = true;
	boolean runMode = true;
	int foodCollect = 10;
	
	public Tutorial() {
		super();
		mode = "collectFood";
		getStuffSet().setAccumulationValue(20);
		setStartingFood(8);
		accumulateAll();
		//getStuffSet().getFood().clear();
		//getStuffSet().getTrash().clear();
		//setStartingTrash(1);
		//setStartingFood(1);
//		accumulateAll();
//		contactedTrash = false;
//		contactedFood = false;
//		inMiniGame = true;
	}
	
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
	
	
	public void accumulate() {
		// assume not added
		boolean trashAdded = false;
		boolean foodAdded = false;
		
		// get locations (initialize x)
		
		if (accumulateTrash){
		int[] trashLoc = {accumulationDist, 0};
		// add trash first
		while (!trashAdded) {
			trashLoc[1] = randint(250, getMap().getHeight()-100); 		// random y location
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
	}
	
	
//	public void accumulateAll() {
//		int[] trashLoc = {2*getMap().getLength()/3,2*getMap().getHeight()/3};
//		int[] foodLoc = {2*getMap().getLength()/3,getMap().getHeight()/3};
//		getStuffSet().add(trashLoc,"trash");
//		getStuffSet().add(foodLoc, "food");
//	}
	
	public void update(int newSpeed, int deltaTheta) {
		//set mode
		if (mode == "collectFood"){
			if (foodCollect == 0){
				mode = "hitTrash";
				accumulateTrash = true;
				accumulateFood = true;
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
				mode = "hitTrash";
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			setInMiniGame(!getMiniGame().getMiniGameOver());
			
			// minigame is over
			if (!getInMiniGame()) {
				System.out.println("Mini game over");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				getStuffSet().removeAllTrash(); 			// eliminate all trash in the main game
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mode = "tutorialOver";
			} 
		}
		// time updating
		timeIncr();
		if (!getGameOver()) {
			setGameOver(getRemainingTime() <= 0);
			if (getGameOver()) {
				setHasWon(false);
			}
		}
	}
	
//	public void setContact(String s) {
//		if (s.equals("trash")) {
//			contactedTrash = true;
//		}else if (s.equals("food")) {
//			contactedFood = true;
//		}
//	}
//	
//	public void setContactedFood(boolean b) {
//		contactedFood = b;
//	}
//	
//	public void setContactedTrash(boolean b) {
//		contactedTrash = b;
//	}
//	
//	public boolean getContactedTrash() {
//		return contactedTrash;
//	}
//	
//	public boolean getContactedFood() {
//		return contactedFood;
//	}

}