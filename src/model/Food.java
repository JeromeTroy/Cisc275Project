package model;

public class Food extends StuffInOcean{
	
	public Food(){
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
