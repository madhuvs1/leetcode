package leetcode_problems;

import java.util.Arrays;
import java.util.List;

/*
 * Given a string s, find the length of the longest substring without duplicate characters.

	Example 1:
	
	Input: s = "abcabcbb"
	Output: 3
	Explanation: The answer is "abc", with the length of 3.
	Example 2:
	
	Input: s = "bbbbb"
	Output: 1
	Explanation: The answer is "b", with the length of 1.
 
 * */

class LongestSubstring {
	public int lengthOfLongestSubstring(String s) {
		int[] charSeen = new int[128];
		Arrays.fill(charSeen, -1);
		char[] strCharacters = s.toCharArray();
		int maxLength = 0;
		int leftPointer = 0;
		int rightPointer = 0;
		for (int i = 0; i < strCharacters.length; i++) {
			var lastSeen = charSeen[strCharacters[i]];
			if (lastSeen >= leftPointer) {
				if (rightPointer - leftPointer > maxLength)
					maxLength = rightPointer - leftPointer;
				leftPointer = lastSeen + 1;
				rightPointer += 1;
				charSeen[strCharacters[i]] = i;
			} else {
				rightPointer += 1;
				charSeen[strCharacters[i]] = i;
			}
		}
		if (rightPointer - leftPointer > maxLength)
			maxLength = rightPointer - leftPointer;
		return maxLength;
	}
}

public class LongestSubstringRunner {

	public static void main(String[] args) {
		List<String> stringList = List.of("abcabcbb", "bbbbb", "tmmzuxt", "aab", "anviaj");
		for (String str : stringList) {
			System.out.printf("Length of longest Substring for string %s is %d%n", str,
					new LongestSubstring().lengthOfLongestSubstring(str));
		}

	}

}
