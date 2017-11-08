package model;

public class MiniMainCharacter extends MainCharacter{

	public MiniMainCharacter(Map map) {
		super();
		position.setX(map.getLength()/2);
	}
	
	@Override
	public void move() {
		super.move();
		int deltaX = (int) (speed * Math.cos(Math.toRadians(angle)));
		position.setX(position.getX() + deltaX);
	}
}
