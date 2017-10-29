package model;

public class Food extends StuffInOcean{

	public Food(){
		position = new OurVector();
	}
	
	public Food(int xval, int yval){
		position = new OurVector(xval,yval);
	}
	
	public Food(OurVector v){
		position = v;
	}
	
	public Food(int xval, int yval, int rad){
		position = new OurVector(xval,yval);
		radius = rad;
	}
	
	public Food(OurVector v, int rad){
		position = v;
		radius = rad;
	}
	
	public Food(int rad){
		radius = rad;
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