package com.mvs.leetcode_problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Example
		Input: height = [1,8,6,2,5,4,8,3,7]
		max water it contain -> from 8 to 7, now to calculate area -> difference of indices*Height -> (8-1) -> 7 * 7 = 49

	formula, Math.abs(l-r) * Min(height[r],height[l])
 */

class ContainerWithMostWater {
	 public int maxArea(int[] height) {
		 int l = 0;
		 int r = height.length-1;
		 int maxWaterCapacityArea = 0;
		 while(l < r) {
			 var width = r-l;
			 var containerHeight = Math.min(height[r], height[l]);
			 var waterCapacityArea = width*containerHeight;
			 if(waterCapacityArea > maxWaterCapacityArea) maxWaterCapacityArea = waterCapacityArea;
			 if(height[l]< height[r]) l++; else r--;  
		 }
		 return maxWaterCapacityArea;
	 }
}


public class ContainerWithMostWaterRunner {
	public static void main(String[] args) {
		 int[] height=  {1,8,6,2,5,4,8,3,7};
	    System.out.println("Max water container can hold:"+	new ContainerWithMostWater().maxArea(height));
	}
}


