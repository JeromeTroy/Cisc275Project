package model;

public class Trash extends StuffInOcean{

	/* 
	 * Trash class for trash objects in ocean
	 */
	
	// constructors
	public Trash(){
		position = new OurVector();
	}
	
	public Trash(int xval, int yval){
		position = new OurVector(xval,yval);
	}
	
	public Trash(OurVector v){
		position = v;
	}
	
	public Trash(int xval, int yval, int rad){
		position = new OurVector(xval,yval);
		radius = rad;
	}
	
	public Trash(OurVector v, int rad){
		position = v;
		radius = rad;
	}
	
	public Trash(int rad){
		radius = rad;
	}
	
	// tell us that it is trash
	public boolean isTrash(){
		return true;
	}
	
	// name of the trash
	public String getName(){
		return "Trash ";
	}
	
	// size
	public int getRadius(){
		return radius;
	}

}
