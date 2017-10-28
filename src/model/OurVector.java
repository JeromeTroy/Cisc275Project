package model;

public class OurVector implements Comparable{

	// Vector for collision detection
	private int x; 		// x position
	private int y;		// y position

	// Constructors
	
	/*
	 * Constructor
	 * Input:
	 * 		xval 	int 	x value for position
	 *  	yval 	int 	y value for position
	 */
	public OurVector(int xval, int yval){
		x = xval;
		y = yval;
	}
	
	/* 
	 * default constructor (zero vector)
	 * Input:
	 * 		None
	 */
	public OurVector(){
		x = 0;
		y = 0;
	}

	/*
	 * Calculates the length^2 of the vector
	 * uses pythagorean theorem c^2 = a^2 + b^2
	 * Input:
	 * 		None
	 * Output:
	 * 		int 	length^2
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
	
	/*
	 *  printing(non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Output:
	 * 		String <x, y>
	 */
	public String toString(){
		return "<" + x  + ", " + y + ">";
	}

	/*
	 * vector dot product
	 * Input:
	 * 		v 		Vector 		vector to calculate dot product with
	 * Output:	
	 * 		int 	dot product 	x1*x2 + y1*y2
	 */
	public int dotWith(OurVector v){
		return v.getX()*x + v.getY()*y;
	}

	/*
	 * determine the distance between 2 vectors
	 * |v-u|^2 = |v|^2 + |u|^2 - 2(v.u)
	 * Input:
	 * 		v 		Vector 		vector to calculate distance from
	 * Output:
	 * 		int 	distance from the vector 
	 */
	public int distFrom(OurVector v){
		return v.norm2() + norm2() - 2*dotWith(v);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * 
	 * For comparing vectors
	 * 		sorts first by x values (distance along map)
	 * 		sorts then by y values (height)
	 * Input:
	 * 		o 		Object 		object to be compared to
	 * Output:
	 * 		int 	value of comparison
	 */
	public int compareTo(Object o){
		if (o instanceof OurVector){ // only compare other vectors
			OurVector v = (OurVector) o;
			Integer yPos = new Integer(v.getY());
			Integer xPos = new Integer(v.getX());
			if (yPos.equals(y)){
				return xPos.compareTo(x);
			}
			else{
				return yPos.compareTo(y);
			}
		}
		else{	// handling non-vector object
			return 0;
		}
	}
}
