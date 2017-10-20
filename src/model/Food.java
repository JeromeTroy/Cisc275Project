package model;

public class Food extends StuffInOcean{
	
	public Food(){
		position = new Vector();
	}

	public Food(int r){
		radius = r;
		position = new Vector();
	}

	public Food(int xpos, int ypos){
		position = new Vector(xpos, ypos);
	}

	public Food(int r, int x, int y){
		position = new Vector(x,y);
		radius = r;
	}
	
	public Food(int r, Vector pos){
		radius = r;
		position = pos;
	}
	
	public boolean isFood(){
		return true;
	}

	public String getName(){
		return "Food ";
	}
}
