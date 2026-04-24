package com.apm.Emr.eu.entity;

import java.util.Comparator;

public class RatingComparator implements Comparator<Investigation> {

	public int compare(Investigation obj1, Investigation obj2) {
		// TODO Auto-generated method stub
		 return (obj1.getIndx()<obj2.indx) ? -1 : (obj1.indx>obj2.getIndx()) ? 1 : 0;  
	}

}
