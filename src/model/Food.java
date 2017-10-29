package model;

public class Food extends StuffInOcean{
	protected int radius = 1;
	
	public Food(){
<<<<<<< HEAD
		isTrash = false;
		isFood = true;
		isFish = false;
		position = new OurVector();
	}

	public Food(int r){
		isTrash = false;
		isFood = true;
		isFish = false;
		radius = r;
		position = new OurVector();
	}

	public Food(int xpos, int ypos){
		isTrash = false;
		position = new OurVector(xpos, ypos);
		isFood = true;
		isFish = false;
	}

	public Food(int r, int x, int y){
		isTrash = false;
		position = new OurVector(x,y);
		isFood = true;
		isFish = false;
		radius = r;
	}

}
=======
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
>>>>>>> master
