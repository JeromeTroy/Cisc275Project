package model;

/**
 * @author jerome
 * MiniGame model
 */
public class MiniGame extends MainModel{

	private int startingTrash = 10;
	
	/**
	 * Constructor
	 */
	public MiniGame() {
		super();
		fishy = new MiniMainCharacter(map);
		accumulateAll();
	}
	
	public MiniGame(int num) {
		super();
		startingTrash = num;
		accumulateAll();
	}
	
	/** (non-Javadoc)
	 * @see model.MainModel#accumulate()
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
}
