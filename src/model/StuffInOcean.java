package model;

public class StuffInOcean implements Comparable{

	protected Vector position; 	// position vector
	protected int radius;		// size of stuff
	
	/*
	 * printing(non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Input:
	 * 		None
	 * Output:
	 * 		<Type> located at <x, y>
	 */
	public String toString(){
		String objectString = getName();
		objectString += "located at " + position.toString();
		return objectString;
	}
	
	public String getName(){
		return "Stuff ";
	}
	
	// getters
	public Vector getPosition(){
		return position;
	}
	
	public boolean isFood(){
		return false;
	}
	
	public boolean isFish(){
		return false;
	}
	
	public boolean isTrash(){
		return false;
	}
	
	public int getRadius(){
		return radius;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * 
	 *  For comparing
	 *  compares based on position vector
	 *  Input:
	 *  	o 		Object 		object to be compared to
	 *  Output:
	 *  	int 	value of comparison
	 */
	public int compareTo(Object o){
		if (o instanceof StuffInOcean){	// only compare StuffInOcean
			StuffInOcean s = (StuffInOcean) o;
			return position.compareTo(s.getPosition());
		}
		else{	// non StuffInOcean
			return 0;
		}
	}
	
	/*
	 * Collision detection
	 * Uses radii compared to separation distances
	 * Input:
	 * 		s 		StuffInOcean 		stuff to see if collided
	 * Output:
	 * 		boolean 	whether the 2 objects are collided
	 */
	public boolean isCollided(StuffInOcean s){
		int separation = position.distFrom(s.getPosition());
		int radiiSum = radius + s.getRadius();
		return (separation <= radiiSum*radiiSum);
	}
}
