package model;

import java.util.*;

public class StuffSet extends ArrayList<StuffInOcean> {
	// sort method update to sort by distance
	// compareTo for adding
	// add elements off screen 45 degree angle arc
	// -- pull screen size from controller
	// --fix to center of corner

	int partitionDist;
	FishCharacter f;

	public StuffSet() {
	}

	public StuffSet(FishCharacter f) {
		this.f = f;
		//super.add(f);
	}
	
	//May not need this constuctor
	public StuffSet(FishCharacter f, int partitionDist) {
		this.f = f;
		this.partitionDist = partitionDist;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ArrayList#add(java.lang.Object) 
	 * Addition Tests if the
	 * addition of the object would cause a collision If so then does not add
	 * the object Input: o Object object to be added Output: boolean if the
	 * addition was successful
	 */
	public boolean add(StuffInOcean s) {
//		if (f == null) {				// trying to add but fish not initialized
//			if (s.isFish()) {			// if it's a fish
//				// super.add(s);
//				this.f = (FishCharacter) s;		// assign to the fish position
//				return true;
//			}
//			else{						// why are we adding something with no fish yet?
//				System.out.println("Warning, no fish character assigned yet");
//				// TODO: better handling here
//				return false;
//			}
//			
//		}else{ 							// there is a fish
			for (StuffInOcean item : this) {		// verify there is not a collision
				if (s.isCollided(item)) {
					return false;					// if collision, do not add
				}
			}
			// supposing there has not been a collision:
			super.add(s);
			
			// if (f != null) { // redundant
			Collections.sort(this, new DistToFishComparator(f));
			//}
			return true;
//		}

		// super.add(s);
		// Collections.sort(this, new DistToFishComparator(f));
		// int currIndex = indexOf(s);
		// boolean goodAdd = true;
		//
		// try {
		// StuffInOcean ahead = (StuffInOcean) get(currIndex - 1);
		// goodAdd = !s.isCollided(ahead);
		// } catch (IndexOutOfBoundsException e) {
		// goodAdd = true;
		// }
		//
		// try {
		// StuffInOcean behind = (StuffInOcean) get(currIndex + 1);
		// goodAdd = !s.isCollided(behind);
		// } catch (IndexOutOfBoundsException e) {
		// }
		//
		// if (!goodAdd) {
		// remove(s);
		// }
		// return goodAdd;
	}

}
