package model;

public class Vector implements Comparable{

	// Vector for collision detection
	private int x; 		// x position
	private int y;		// y position

	// Constructors
	public Vector(int xval, int yval){
		x = xval;
		y = yval;
	}
	
	// default constructor (zero vector)
	public Vector(){
		x = 0;
		y = 0;
	}

	/*
	 *  pythagorean theorem c^2 = a^2 + b^2
	 *  returns the SQUARE of the norm
	 *  Sqrt's are not stable...
	 */
	public int norm2(){
		return x*x + y*y;
	}

	

	// vector dot product
	public int dotWith(Vector v){
		return v.getX()*x + v.getY()*y;
	}

	/*
	 *  determine the distance between 2 vectors
	 *  returns the SQUARE of the distance
	 *  sqrt's are not stable...
	 */
	public int distFrom(Vector v){
		return v.norm2() + norm2() - 2*dotWith(v);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * 
	 * For comparing vectors
	 * 		sorts first by x values (distance along map)
	 * 		sorts then by y values (height)
	 */
	public int compareTo(Object o){
		if (o instanceof Vector){ // only compare other vectors
			Vector v = (Vector) o;
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
	
	// getters
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	// printing
	public String toString(){
		return "<" + x  + ", " + y + ">";
	}
	
	// setters
	public void setX(int xVal){
		x = xVal;
	}
	
	public void setY(int yVal){
		y = yVal;
	}
	
		
}
