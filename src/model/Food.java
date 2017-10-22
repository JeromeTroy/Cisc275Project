package model;

public class Food extends StuffInOcean{
	protected int radius = 1;
	
	public Food(){
		position = new Vector();
	}

	public Food(int xpos, int ypos){
		position = new Vector(xpos, ypos);
	}
	
	public Food(Vector pos){
		position = pos;
	}
	
	public boolean isFood(){
		return true;
	}

	public String getName(){
		return "Food ";
	}
	
	public int getRadius(){
		return radius;
	}
}
