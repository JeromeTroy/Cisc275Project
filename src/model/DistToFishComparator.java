package model;

import java.util.Comparator;

public class DistToFishComparator implements Comparator<StuffInOcean> {
	
	public int compare(StuffInOcean f, StuffInOcean s){
		return f.getPosition().distFrom(s.getPosition());
	}
}
