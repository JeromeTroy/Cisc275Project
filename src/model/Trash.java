package model;

public class Trash extends StuffInOcean{
	protected int radius = 1;	
	
	public Trash(){
		position = new Vector();
	}

	public Trash(int xpos, int ypos){
		position = new Vector(xpos, ypos);
	}

//	public Trash(int r, int x, int y){
//		position = new Vector(x,y);
//		radius = r;
//	}
	
	public Trash(Vector pos){
		position = pos;
	}
	
	public boolean isTrash(){
		return true;
	}
	
	public String getName(){
		return "Trash ";
	}
	
	public int getRadius(){
		return radius;
	}

}
