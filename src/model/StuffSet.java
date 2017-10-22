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

//	public TrashIterator iterator(Class<StuffInOcean> c) {
//		return new TrashIterator(c);
//	}

//	// https://gist.github.com/jnwhiteh/68d095c630dfcaddffd1
//	public class TrashIterator implements Iterator<StuffInOcean> {
//		Class<StuffInOcean> c;
//		ArrayList<Trash> trash;
//
//		public TrashIterator() {
//			this.c = c;
//			
//		}
//
//		int curr = 0; // must initalize at -1 since next will increment then
//						// reutrn
//
//		@Override
//		public boolean hasNext() {
//			if (curr < ArrayList.this.size()) {
//				
//				StuffSet 
//				return true;
//			} else {
//				return false;
//			}
//		}
//
//		@Override
//		public Integer next() {
//			return arr[curr++ % 3];
//		}
//
//	}
//
//	public static void main(String[] args) {
//		Triple t = new Triple();
//		Iterator ti = t.iterator();
//		for (int i = 0; i < 10; i++)
//			if (ti.hasNext())
//				System.out.print(ti.next() + " ");
//		System.out.println();
//	}// prints 1 2 3 1 2 3 1 2 3 1

}
