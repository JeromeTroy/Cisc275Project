package model;

public class OurVector {

	/**
	 *  Vector for collision detection and location tracking
	 */
	
	// positions
	private int x; 		// x position
	private int y;		// y position

	
	// methods
	
	
	// Constructors
	
	
	/**
	 * Constructor
	 * @param xval 	int 	x value for position
	 * @param yval 	int 	y value for position
	 */
	private OurVector(int xval, int yval){
		setX(xval);
		setY(yval);
	}
	
	
	/**
	 * Default constructor
	 */
	public OurVector() {
		setX(0);
		setY(0);
	}

	
	// calculations
	
	
	// length
	/**
	 * Calculates the length^2 of the vector
	 * uses pythagorean theorem c^2 = a^2 + b^2
	 * @return int 	length^2
	 */
	public int norm2(){
		return getX()*getX() + getY()*getY();
	}

	
	// dot products
	/**
	 * vector dot product
	 * @param v 		Vector 		vector to calculate dot product with
	 * @return int 		dot product 	x1*x2 + y1*y2
	 */
	private int dotWith(OurVector v){
		return v.getX()*getX() + v.getY()*getY();
	}
	
	
	// distances
	
	
	// vector
	/**
	 * determine the distance between 2 vectors
	 * |v-u|^2 = |v|^2 + |u|^2 - 2(v.u)
	 * @param v 		Vector 		vector to calculate distance from
	 * @return int 		distance^2  from the vector 
	 */
	private int distFrom(OurVector v){
		return v.norm2() + norm2() - 2*dotWith(v);
	}
	
	
	// point
	/**
	 * determine the distance from this vector and a point
	 * @param x 		x position
	 * @param y 		y position
	 * @return 			distance^2
	 */
	public int distFrom(int x, int y) {
		OurVector v = new OurVector(x,y);
		return distFrom(v);
	}

	
	// 2 points
	/**
	 * Calculate the distance between 2 points
	 * @param x1 			x position 1st point
	 * @param y1 			y position 1st point
	 * @param x2 			x position 2nd point
	 * @param y2			y position 2nd point
	 * @return 				distance^2 from p1 to p2
	 */
	public static int distBetween(int x1, int y1, int x2, int y2) {
		OurVector v1 = new OurVector(x1,y1);
		return v1.distFrom(x2, y2);
	}
	
	
	public static int angleBetween(int x1, int y1, int x2, int y2) {
		OurVector v1 = new OurVector(x1,y1);
		return v1.angleBetween(x2,y2);
	}
	
	public int angleBetween(int x, int y) {
		int newX = x - getX();
		int newY = y - getY();
		if (newX == 0) {
			if (newY > 0) {
				return 90;
			}else if (newY < 0) {
				return -90;
			}else {
				return 0;
			}
		}else {
			double phi = Math.atan(newY/newX);
			int theta = (int) (180/Math.PI * phi);
			if (newX < 0) {
				theta += 180;
			}
			return theta;
		}
	}
	
	// printing
	/**
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * @return String <x, y>
	 */
	public String toString(){
		return "<" + getX()  + "," + getY() + ">";
	}
	
	
	// getters
	
	
	// x
	public int getX(){
		return x;
	}
	
	
	// y
	public int getY(){
		return y;
	}
		
	
	// setters
	
	
	// x
	public void setX(int xval){
		x = xval;
	}
	
	
	// y
	public void setY(int yval){
		y = yval;
	}
		
	
	
	
	
	
	
}
