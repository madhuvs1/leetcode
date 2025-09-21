package com.mvs.leetcode_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * Notice that the solution set must not contain duplicate triplets.
 * 
	Example1
		Input: nums = [-1,0,1,2,-1,-4]
		Output: [[-1,-1,2],[-1,0,1]]
		Explanation: 
		nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
		nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
		nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
		The distinct triplets are [-1,0,1] and [-1,-1,2].
		Notice that the order of the output and the order of the triplets does not matter.
	
	Example 2:
		Input: nums = [0,1,1]
		Output: []
		Explanation: The only possible triplet does not sum up to 0.

	Example 3:
		Input: nums = [0,0,0]
		Output: [[0,0,0]]
		Explanation: The only possible triplet sums up to 0.
		
		Have 2 pointers
		Left = n+1, right = n-1;

 * */

//-2,0,1,2,4,
//0+4 -> -1+4 -> 3
//0,2 ->-1+2 -> 1
//0,1 -> -1+1 -> 0

class ThreeSum { // [-4, -1, -1, 0, 1, 2]
	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> threeSumList = new ArrayList<>();
		int arrayLength = nums.length - 2; //because we need enough room for leftPointer and rightPointer, as leftPointer(++) moves one step ahead to find triplet, rightPointer retracts(--)  
		for (int i = 0; i < arrayLength; i++) {
			if (i > 0 && nums[i] == nums[i - 1]) // to avoid duplicates, in a sorted array duplicates show up next to each other
				continue;
			int leftPointer = i + 1;
			int rightPointer = nums.length - 1;  //note here though outer loop is length-2, rightPointer always starts from the end

			while (leftPointer < rightPointer) {
				var result = nums[i] + nums[leftPointer] + nums[rightPointer];
				if (result == 0) {
					threeSumList.add(List.of(nums[i], nums[leftPointer], nums[rightPointer]));
					leftPointer++;
					rightPointer--;
					while (leftPointer < rightPointer && nums[leftPointer] == nums[leftPointer - 1])  //i value from outerloop remains same as you continue finding next triplet, if the next number is a duplicate of previously found leftPointer then we skip it, as the duplicate left pointer with const combination can become zero only when the last number is same as previously what was part of triplet  
						leftPointer++;
					while (leftPointer < rightPointer && nums[rightPointer] == nums[rightPointer + 1])
						rightPointer--;
					continue;
				} else if (result < 0) {
					leftPointer++;
				} else {
					rightPointer--;
				}
			}
		}
		return threeSumList;
	}

	// Array sort custom implementation (just tried)
	public int[] sortArray(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			int indexToSwap = 0;
			int j = i;
			int min = nums[j];
			while (j < nums.length) {
				if (nums[j] < min) {
					min = nums[j];
					indexToSwap = j;
				}
				j++;
			}
			if (indexToSwap > 0 && i != indexToSwap) {
				nums[indexToSwap] = nums[i];
				nums[i] = min;
			}
		}
		System.out.printf("sortedNums array: %s %n", Arrays.toString(nums));
		return nums;

	}
}

public class ThreeSumRunner {
	public static void main(String[] args) {
		int[] nums = { -1, 0, 1, 2, -1, -4 };

		System.out.println(new ThreeSum().threeSum(nums));
	}
}
