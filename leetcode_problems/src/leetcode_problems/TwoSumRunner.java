package leetcode_problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/*
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * Example 1:
	Input: nums = [2,7,11,15], target = 9
	Output: [0,1]
	Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:
	Input: nums = [3,2,4], target = 6
	Output: [1,2]
*/

class TwoSum {
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> hashMap = new HashMap<>();
		int[] indices = new int[2];

		for (int i = 0; i < nums.length; i++) {
			if (hashMap.containsKey(target - nums[i])) {
				indices[0] = hashMap.get(target - nums[i]);
				indices[1] = i;
				return indices;
			} else {
				hashMap.put(nums[i], i);
			}
		}
		return indices;
	}
}

public class TwoSumRunner {
	public static void main(String[] args) {
		Map<List<Integer>, Integer> hashMap = new HashMap<>();
		hashMap.put(List.of(2, 7, 11, 15), 9);
		hashMap.put(List.of(3, 2, 4), 6);

		for (Map.Entry<List<Integer>, Integer> entry : hashMap.entrySet()) {
			var intArray = entry.getKey().stream().mapToInt(Integer::intValue).toArray();
			var indices = new TwoSum().twoSum(intArray, entry.getValue().intValue());

			System.out.printf("TwoSum of array:%s with target:%d is %s%n", Arrays.toString(intArray), entry.getValue(),
					Arrays.toString(indices));

		}
	}

}
