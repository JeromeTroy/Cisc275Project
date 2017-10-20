package model;

public class Trash extends StuffInOcean{

	public Trash(){
		position = new Vector();
	}

	public Trash(int r){
		radius = r;
		position = new Vector();
	}

	public Trash(int xpos, int ypos){
		position = new Vector(xpos, ypos);
	}

	public Trash(int r, int x, int y){
		position = new Vector(x,y);
		radius = r;
	}
	
	public Trash(int r, Vector pos){
		radius = r;
		position = pos;
	}
	
	public boolean isTrash(){
		return true;
	}
	
	public String getName(){
		return "Trash ";
	}
	

}
