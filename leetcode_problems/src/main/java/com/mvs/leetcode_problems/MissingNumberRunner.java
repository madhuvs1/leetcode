package com.mvs.leetcode_problems;

import java.util.Arrays;
import java.util.List;

/* 
 * Arrays start with [0,n] -> array length of n, find missing number
 * Input: nums = [3,0,1] Output: 2
 * Input: nums = [0,1] Output: 2
 * Input: nums = [9,6,4,2,3,5,7,0,1] Output: 8 
 * 
 * 
 * Technique use XOR bitwise operator XOR will return 1 only when both x ^ y are different meaning 1 ^ 2 (01 ^ 10) -> (11) -> 3 
 * Do (0 ^ 1 ^ 2 ^ 3) ^ (3 ^ 0 ^ 1).
 * Notice: 0 appears twice → cancels to 0.
   1 appears twice → cancels to 0.
   3 appears twice → cancels to 0.
   Only 2 has no partner → it survives.
 */

class MissingNumber {
	public int missingNumber(int[] nums) {
		int xorAll = nums.length; 
		for (int i = 0; i < nums.length; i++) {
			xorAll = xorAll ^ i;
			xorAll = xorAll ^ nums[i];
		}
		return xorAll;
	}
}

public class MissingNumberRunner {
	public static void main(String[] args) {
		
		  List<int[]> numsList = List.of(new int[] {3,0,1}, new int[] {0,1}, new int[] {9,6,4,2,3,5,7,0,1});
		  for(int[] nums: numsList) {
			  var missingNumber = new MissingNumber().missingNumber(nums);
			  System.out.printf("Missing Number of Array:%s is %d %n",Arrays.toString(nums), missingNumber);  
		  }
	}

}
