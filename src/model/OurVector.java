package model;

public class OurVector implements Comparable<OurVector>{

	/**
	 *  Vector for collision detection and location tracking
	 */
	private int x; 		// x position
	private int y;		// y position

	// Constructors
	
	/**
	 * Constructor
	 * @param xval 	int 	x value for position
	 * @param yval 	int 	y value for position
	 */
	private OurVector(int xval, int yval){
		x = xval;
		y = yval;
	}
	
	
	/**
	 * Default constructor
	 */
	public OurVector() {
		x = 0;
		y = 0;
	}

	/**
	 * Calculates the length^2 of the vector
	 * uses pythagorean theorem c^2 = a^2 + b^2
	 * @return int 	length^2
	 */
	public int norm2(){
		return x*x + y*y;
	}

	// getters
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	// setters
	public void setX(int xval){
		x = xval;
	}
	public void setY(int yval){
		y = yval;
	}
	
	/**
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * @return String <x, y>
	 */
	public String toString(){
		return "<" + x  + "," + y + ">";
	}

	/**
	 * vector dot product
	 * @param v 		Vector 		vector to calculate dot product with
	 * @return int 		dot product 	x1*x2 + y1*y2
	 */
	private int dotWith(OurVector v){
		return v.getX()*x + v.getY()*y;
	}
	
	private int dotWith(int xval, int yval) {
		return x*xval + y*yval;
	}

	/**
	 * determine the distance between 2 vectors
	 * |v-u|^2 = |v|^2 + |u|^2 - 2(v.u)
	 * @param v 		Vector 		vector to calculate distance from
	 * @return int 		distance^2  from the vector 
	 */
	private int distFrom(OurVector v){
		return v.norm2() + norm2() - 2*dotWith(v);
	}
	
	private int distFrom(int x, int y) {
		OurVector v = new OurVector(x,y);
		return distFrom(v);
	}

	public static int distBetween(int x1, int y1, int x2, int y2) {
		OurVector v1 = new OurVector(x1,y1);
		return v1.distFrom(x2, y2);
	}
	/**
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * For comparing vectors
	 * 		sorts first by x values (distance along map)
	 * 		sorts then by y values (height)
	 * @param v 	OurVector 		vector to be compared to
	 * Output:
	 * 		int 	value of comparison
	 */
	public int compareTo(OurVector v){
		Integer yPos = new Integer(v.getY());
		Integer xPos = new Integer(v.getX());
		if (yPos.equals(y)){
			return xPos.compareTo(x);
		}
		else{
			return yPos.compareTo(y);
		}
	}
}
