package model;

public class StuffInOcean implements Comparable{

	protected Vector position; 	// position vector
	protected boolean isTrash; 	// is it garbage
	protected boolean isFish; 	// is it a fish
	protected boolean isFood; 	// is it food
	protected int radius;		// size of stuff
	
	// printing
	public String toString(){
		// printing
		String objectString = "";
		if (isTrash){
			objectString += "Trash ";
		}else if (isFish){
			objectString += "Fish ";
		}else if (isFood){
			objectString += "Food ";
		}
		objectString += "located at " + position.toString();
		return objectString;
	}
	
	// getters
	public Vector getPosition(){
		return position;
	}
	
	public boolean isTrash(){
		return isTrash;
	}
	
	public boolean isFish(){
		return isFish;
	}
	
	public boolean isFood(){
		return isFood;
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
}
