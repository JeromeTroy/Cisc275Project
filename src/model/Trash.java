package model;

public class Trash extends StuffInOcean{

	/**
	 * Trash class for trash objects in ocean
	 */
	
	// constructors
	
	/**
	 * Constructor
	 */
	public Trash(){
		position = new OurVector();
	}
	
	/**
	 * Constructor
	 * @param xval 		x location
	 * @param yval 		y location
	 */
	public Trash(int xval, int yval){
		position = new OurVector(xval,yval);
	}
	
	/**
	 * Constructor 
	 * @param v 		vector location
	 */
	public Trash(OurVector v){
		position = v;
	}
	
	/**
	 * Constructor
	 * @param xval 		x location
	 * @param yval 		y location
	 * @param rad 		size
	 */
	public Trash(int xval, int yval, int rad){
		position = new OurVector(xval,yval);
		radius = rad;
	}
	
	/**
	 * Constructor
	 * @param v 		vector location
	 * @param rad 		size
	 */
	public Trash(OurVector v, int rad){
		position = v;
		radius = rad;
	}
	
	/**
	 * Constructor
	 * @param rad 		size
	 */
	public Trash(int rad){
		radius = rad;
	}

	/**
	 * Show if trash
	 * @return boolean indicating if is Trash
	 */
	public boolean isTrash(){
		return true;
	}
	
	/**
	 * Show that it is trash
	 * @see model.StuffInOcean#getName()
	 */
	public String getName(){
		return "Trash ";
	}
	
}
