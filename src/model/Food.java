package model;

public class Food extends StuffInOcean{
	/**
	 * The food the fish can eat
	 */

	// Constructors
	
	/**
	 * Constructor
	 */
	public Food(){
		position = new OurVector();
	}
	
	/**
	 * Constructor 
	 * @param xval 		x location
	 * @param yval 		y location
	 */
	public Food(int xval, int yval){
		position = new OurVector(xval,yval);
	}
	
	/**
	 * Constructor
	 * @param v 		Vector location
	 */
	public Food(OurVector v){
		position = v;
	}
	
	/**
	 * Constructor
	 * @param xval		x location
	 * @param yval 		y location
	 * @param rad 		size
	 */
	public Food(int xval, int yval, int rad){
		position = new OurVector(xval,yval);
		radius = rad;
	}
	
	/**
	 * Constructor
	 * @param v 		vector location
	 * @param rad 		size
	 */
	public Food(OurVector v, int rad){
		position = v;
		radius = rad;
	}
	
	/**
	 * Constructor
	 * @param rad 		size
	 */
	public Food(int rad){
		radius = rad;
	}
	
	/**
	 * is it food
	 * @return boolean if object is Food
	 */
	public boolean isFood(){
		return true;
	}

	/**
	 * (non-Javadoc)
	 * @see model.StuffInOcean#getName()
	 * Show the name of the fish, for printing
	 * @return String: "Food"
	 */
	public String getName(){
		return "Food ";
	}
	
}