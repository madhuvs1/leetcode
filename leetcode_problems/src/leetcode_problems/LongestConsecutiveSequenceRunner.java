package leetcode_problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 *  Exampe1:
	Input: nums = [100,4,200,1,3,2]
	Output: 4
	Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
	
	Example2:
	Input: nums = [1,0,1,2]
	Output: 3
 */
class LongestConsecutiveSequence {
	public int longestConsecutive(int[] nums) {
		if(nums == null || nums.length ==0) return 0;
		Set<Integer> hashSet = new HashSet<Integer>();
		for(int num: nums) hashSet.add(num);
		
		int maxLen=0;
		for(int num: hashSet) {
			if(!hashSet.contains(num-1)) {
				var anchorNum = num;
				int len=1;
				while(hashSet.contains(anchorNum+1)) {
					anchorNum++;
					len++;
				}
				if(len > maxLen) maxLen = len;
			}
		}
		return maxLen;
	}  
}

public class LongestConsecutiveSequenceRunner{
	public static void main(String[] args) {
		List<Integer[]> intArrayList = List.of(new Integer[] {100,4,200,1,3,2}, new Integer[] {1,0,1,2});
		for(Integer[] nums: intArrayList) {
			var intArray = Arrays.stream(nums).mapToInt(Integer::intValue).toArray();
			System.out.printf("MaxLength for array:%s is %d%n", Arrays.toString(nums), new LongestConsecutiveSequence().longestConsecutive(intArray));
		}
	}
}
