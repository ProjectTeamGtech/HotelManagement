package com.apm.CoreJava;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
	 public static void main(String[] args) {
	 	System.out.println("Test");
		int[] arr = {9,30,40,50,80};
		ArrayList<Integer> integers = new ArrayList<>();
		for(int i=0;i<arr.length;i++){
			integers.add(arr[i]);
		}
		List<Integer> list = integers.stream().sorted((x,y)->y.compareTo(x)).collect(Collectors.toList());
		int i=0;
		for (Integer integer : list) {
			System.out.println(integer);
		}
	}
}
