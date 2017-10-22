package model;

public abstract class StuffInOcean implements Comparable<StuffInOcean>{
	protected Vector position; 	// position vector
	
	/*
	 * printing(non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Input:
	 * 		None
	 * Output:
	 * 		<Type> located at <x, y>
	 */
	public String toString(){
		return getName() + "located at " + position.toString();
	}
	
	public abstract String getName();
	
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
	
	abstract public int getRadius();
	
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
	public int compareTo(StuffInOcean o){
		return position.compareTo(((StuffInOcean)o).getPosition());
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
		int radiiSum = this.getRadius() + s.getRadius();
		return (separation <= radiiSum*radiiSum);
	}
}
