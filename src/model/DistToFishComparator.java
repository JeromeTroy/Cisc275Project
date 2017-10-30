package model;

import java.util.Comparator;

public class DistToFishComparator implements Comparator<StuffInOcean> {
	
		FishCharacter f;
		
		public DistToFishComparator(FishCharacter f){
			this.f=f;
		}
		
		public int compare(StuffInOcean s1, StuffInOcean s2){
			if (f.getPosition().distFrom(s1.getPosition()) > f.getPosition().distFrom(s2.getPosition())){
				return 1;
			} else if (f.getPosition().distFrom(s1.getPosition()) < f.getPosition().distFrom(s2.getPosition())){
				return -1;
			} else {
				return 0;
		}
}
}