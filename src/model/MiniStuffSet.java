package model;

/**
 * @author jerome
 * The minigame's StuffSet
 */
public class MiniStuffSet extends StuffSet{

	// mover
	/** (non-Javadoc)
	 * @see model.StuffSet#move(model.MainCharacter)
	 * This overrides its predecessor and prevents anything in the stuff set from moving
	 */
	@Override
	public void move(MainCharacter fishy) {
		// Do nothing: nothing moves in the minigame except for the fish
	}
	
	
	
	
}
