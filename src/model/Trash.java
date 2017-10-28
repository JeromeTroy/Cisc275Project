package model;

public class Trash extends StuffInOcean{

	public Trash(){
		isTrash = true;
		isFish = false;
		isFood = false;
		position = new OurVector();
	}

	public Trash(int r){
		isTrash = true;
		isFish = false;
		isFood = false;
		radius = r;
		position = new OurVector();
	}

	public Trash(int xpos, int ypos){
		position = new OurVector(xpos, ypos);
		isTrash = true;
		isFish = false;
	}

	public Trash(int r, int x, int y){
		position = new OurVector(x,y);
		isTrash = true;
		isFish = false;
		isFood = false;
		radius = r;
	}

}
