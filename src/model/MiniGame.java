package model;

/**
 * @author jerome
 * MiniGame model
 */
public class MiniGame extends MainModel{

	// amount of trash to start with
	private int startingTrash = 2;
	private int startingFood = 0;
	
	// methods
	
	
	// constructors
	
	
	// default
	/**
	 * Constructor
	 */
	public MiniGame() {
		super();
		inMiniGame = true;
		everyThing = new MiniStuffSet();
		fishy = new MiniMainCharacter(map);
		accumulateAll();
	}
	
	
	// set amount of starting trash
	/**
	 * Constructor - sets amount of trash
	 * @param num 	amount of trash to start with
	 */
	public MiniGame(int num) {
		this();
		everyThing = new MiniStuffSet();
		setStartingTrash(num);
		accumulateAll();
	}
	
	
	// trash and food accumulation
	
	
	// initial setting
	/** (non-Javadoc)
	 * Presets all the trash
	 */
	public void accumulateAll() {
		for (int i=0; i<getStartingTrash(); i++) {
			boolean trashAdded = false;
			int[] trashLoc = {0, 0};
			while (!trashAdded) {
				trashLoc[0] = MainModel.randint(0, getMap().getLength());
				trashLoc[1] = MainModel.randint(0, getMap().getHeight());
				trashAdded = getStuffSet().add(trashLoc,"trash");
			}
		}
	}
	
	
	// in game accumulation
	/** (non-Javadoc)
	 * @see model.MainModel#accumulate()
	 * This prevents the minigame from accumulating more trash
	 */
	@Override
	public void accumulate() {/* do nothing */}
	
	
	
	// printing
	/** (non-Javadoc)
	 * @see model.MainModel#toString()
	 * Prints out the minigame, with the tag mini game in front
	 */
	@Override
	public String toString() {
		String str = "Mini game: \n";
		str += super.toString();
		return str;
	}
	
	
	// updater
	/** (non-Javadoc)
	 * @see model.MainModel#update(int, int)
	 * Update the position of all objects
	 */
	@Override
	public void update(int newSpeed, int deltaTheta) {
		// setup
		getMainCharacter().setSpeed(newSpeed);
		getMainCharacter().setAngle(deltaTheta);
		
		// if move is allowed
//		if (getMap().moveMap(getMainCharacter())) {
//			// move everything
//			System.out.println("Valid move");
//			//getStuffSet().move(getMainCharacter());
//			getMainCharacter().move();
//		}
		
		// move not allowed
//		else {
//			System.out.println("Invalid move, not moving");
//		}
		
		// display the state of the world
		System.out.println(this);
		
		// check collisions
		String collision = getStuffSet().whatCollided(getMainCharacter());
		System.out.println("Checking collisions");
		System.out.println("Collisions: " + collision);
		
		// collision detected
		if (collision.equals("trash")) {
			setStartingTrash(getStartingTrash() - 1);
		}
	}
	
	
	// getters
	
	
	// mini gameover
	/**
	 * Determines if the minigame is over
	 * @return 			whether the minigame is over
	 */
	public boolean getMiniGameOver() {
		return (getStartingTrash() == 0);
	}
	
	
	// trash initialization
	/**
	 * Trash initialization amount
	 * @return 		 initial amount of trash
	 */
	public int getStartingTrash() {
		return startingTrash;
	}
	
	
	// setters
	/**
	 * Set starting amount of trash
	 * @param num 		amount of starting trash
	 */
	public void setStartingTrash(int num) {
		startingTrash = num;
	}
	
	public void setStartingFood(int num) {
		startingFood = num;
	}
}
