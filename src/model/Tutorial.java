package model;

public class Tutorial extends MiniGame{

	private boolean contactedTrash;
	private boolean contactedFood;
	
	public Tutorial() {
		super();
		getStuffSet().getFood().clear();
		getStuffSet().getTrash().clear();
		setStartingTrash(1);
		setStartingFood(1);
		accumulateAll();
		contactedTrash = false;
		contactedFood = false;
	}
	
	public void accumulateAll() {
		int[] trashLoc = {2*getMap().getLength()/3,2*getMap().getHeight()/3};
		int[] foodLoc = {2*getMap().getLength()/3,getMap().getHeight()/3};
		getStuffSet().add(trashLoc,"trash");
		getStuffSet().add(foodLoc, "food");
	}
	
	public void update(int newSpeed, int deltaTheta) {
		super.update(newSpeed, deltaTheta);
		String collided = getStuffSet().whatCollided(getMainCharacter());
		setContact(collided);
		
	}
	
	public void setContact(String s) {
		if (s.equals("trash")) {
			contactedTrash = true;
		}else if (s.equals("food")) {
			contactedFood = true;
		}
	}
	
	public void setContactedFood(boolean b) {
		contactedFood = b;
	}
	
	public void setContactedTrash(boolean b) {
		contactedTrash = b;
	}
	
	public boolean getContactedTrash() {
		return contactedTrash;
	}
	
	public boolean getContactedFood() {
		return contactedFood;
	}

}