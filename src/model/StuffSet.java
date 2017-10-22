package model;

import java.util.*;

public class StuffSet extends ArrayList<StuffInOcean> {

	public StuffSet() {
	}

	public StuffSet(StuffInOcean s) {
		add(s);
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ArrayList#add(java.lang.Object) Addition Tests if the
	 * addition of the object would cause a collision If so then does not add
	 * the object Input: o Object object to be added Output: boolean if the
	 * addition was successful
	 */
	public boolean add(StuffInOcean s) {
		super.add(s);
		Collections.sort(this);
		int currIndex = indexOf(s);
		boolean goodAdd = true;

		try {
			StuffInOcean ahead = (StuffInOcean) get(currIndex - 1);
			goodAdd = !s.isCollided(ahead);
		} catch (IndexOutOfBoundsException e) {
			goodAdd = true;
		}

		try {
			StuffInOcean behind = (StuffInOcean) get(currIndex + 1);
			goodAdd = !s.isCollided(behind);
		} catch (IndexOutOfBoundsException e) {
		}

		if (!goodAdd) {
			remove(s);
		}
		return goodAdd;

	}

}
