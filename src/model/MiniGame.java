package model;

/**
 * @author jerome
 * MiniGame model
 */
public class MiniGame extends MainModel{

	private int startingTrash = 1;
	
	/**
	 * Constructor
	 */
	public MiniGame() {
		super();
		everyThing = new MiniStuffSet();
		fishy = new MiniMainCharacter(map);
		accumulateAll();
	}
	
	/**
	 * Constructor - sets amount of trash
	 * @param num 	amount of trash to start with
	 */
	public MiniGame(int num) {
		this();
		everyThing = new MiniStuffSet();
		startingTrash = num;
		accumulateAll();
	}
	
	/** (non-Javadoc)
	 * Presets all the trash
	 */
	public void accumulateAll() {
		for (int i=0; i<startingTrash; i++) {
			boolean trashAdded = false;
			int[] trashLoc = {0, 0};
			while (!trashAdded) {
				trashLoc[0] = MainModel.randint(0, map.getLength());
				trashLoc[1] = MainModel.randint(0, map.getHeight());
				trashAdded = everyThing.add(trashLoc,"trash");
			}
		}
	}
	
	/** (non-Javadoc)
	 * @see model.MainModel#accumulate()
	 * This prevents the minigame from accumulating more trash
	 */
	@Override
	public void accumulate() {/* do nothing */}
	
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
	
	@Override
	public void update(int newSpeed, int deltaTheta) {
		fishy.setSpeed(newSpeed);
		fishy.rotate(deltaTheta);
		if (map.moveMap(fishy)) {
			System.out.println("Valid move");
			everyThing.move(fishy);
			fishy.move();
		}
		else {
			System.out.println("Invalid move, not moving");
		}
		System.out.println(this);
		String collision = everyThing.whatCollided(fishy);
		System.out.println("Checking collisions");
		System.out.println("Collisions: " + collision);
		if (collision.equals("trash")) {
			// do nothing 
			// TODO: anything?
		}
	}
	
	public boolean miniGameOver() {
		return (everyThing.getTrash().size() == 0);
	}
}
