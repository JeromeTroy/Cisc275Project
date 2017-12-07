package model;

/**
 * @author Group 4
 * The minigame's StuffSet
 * extention of StuffSet for minigame behavior
 */
public class MiniStuffSet extends StuffSet{

	// mover
	/** 
	 * @see model.StuffSet#move(model.MainCharacter)
	 * This overrides its predecessor and prevents anything in the stuff set from moving
	 */
	@Override
	public void move(MainCharacter fishy) {
		// Do nothing: nothing moves in the minigame except for the fish
	}
	
	
	
	
}
