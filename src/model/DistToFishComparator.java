package model;

import java.util.Comparator;

public class DistToFishComparator implements Comparator<StuffInOcean> {
	
	/*
	 * Comparator class to compare objects based on distance form the fishCharacter
	 */
	
	// attributes
	FishCharacter f; 		// the actual fish character that will be used for comparisons
	
	/*
	 * Constructor
	 * Assigns the fish character for comparisons to the input value
	 */
	public DistToFishComparator(FishCharacter f){
		this.f=f;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 * Required compare method
	 * Compares 2 StuffInOceans 
	 */
	public int compare(StuffInOcean s1, StuffInOcean s2){
		// original code returns 1, -1, or 0
		/*
		if (f.getPosition().distFrom(s1.getPosition()) > f.getPosition().distFrom(s2.getPosition())){
			return 1;
		} else if (f.getPosition().distFrom(s1.getPosition()) < f.getPosition().distFrom(s2.getPosition())){
			return -1;
		} else {
			return 0;
		*/
		
		// converts the distance from the fish to the first object to an Integer
		Integer firstDist = f.getPosition().distFrom(s1.getPosition());
		int secondDist = f.getPosition().distFrom(s2.getPosition()); 	// get the other distance
		return firstDist.compareTo(secondDist);							// compare via the Integers compareTo method		
	}
}