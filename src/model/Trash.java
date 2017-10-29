package model;

public class Trash extends StuffInOcean{

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
