package model;

public class Trash extends StuffInOcean{

	public Trash(){
		isTrash = true;
		isFish = false;
		isFodd = false;
		position = new Vector();
	}

	public Trash(int r){
		isTrash = true;
		isFish = false;
		isFodd = false;
		radius = r;
		position = new Vector();
	}

	public Trash(int xpos, int ypos){
		position = new Vector(xpos, ypos);
		isTrash = true;
		isFish = false;
	}

	public Trash(int r, int x, int y){
		position = new Vector(x,y);
		isTrash = true;
		isFish = false;
		isFood = false;
		radius = r;
	}

}
