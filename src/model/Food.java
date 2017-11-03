package model;

public class Food extends StuffInOcean{
	/*
	 * The food the fish can eat
	 */

	// Constructors
	
	// default
	public Food(){
		position = new OurVector();
	}
	
	// location at x and y
	public Food(int xval, int yval){
		position = new OurVector(xval,yval);
	}
	
	// location at vector
	public Food(OurVector v){
		position = v;
	}
	
	// location at x, y; with size rad
	public Food(int xval, int yval, int rad){
		position = new OurVector(xval,yval);
		radius = rad;
	}
	
	// location at vector, size rad
	public Food(OurVector v, int rad){
		position = v;
		radius = rad;
	}
	
	// no location specified, size rad
	public Food(int rad){
		radius = rad;
	}
	
	// is it food
	public boolean isFood(){
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see model.StuffInOcean#getName()
	 * Show the name of the fish, for printing
	 */
	public String getName(){
		return "Food ";
	}
	
	// get the size
	public int getRadius(){
		return radius;
	}
}