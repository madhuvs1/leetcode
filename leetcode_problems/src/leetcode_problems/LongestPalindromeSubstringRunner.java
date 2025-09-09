package leetcode_problems;

import java.util.List;

/*
 * Example 1:
	Input: s = "babad"
	Output: "bab"
	Explanation: "aba" is also a valid answer.

	Example 2:
	Input: s = "cbbd"
	Output: "bb"
	
	abba
	l=a r=b
	l=b, r=b
	
 */

class LongestPalindromeSubstring {
	public String longestPalindrome(String s) {
		if(s.equals("") || s == null || s.length() < 2)return s;
		char[] chars = s.toCharArray();
		int[] maxSubStringIndices = new int[2];
		for (int i = 0; i < chars.length; i++) {
			var indices = expandO(i, i + 1, chars);
			if (indices[1] - indices[0] > maxSubStringIndices[1] - maxSubStringIndices[0]) {
				maxSubStringIndices[0] = indices[0];
				maxSubStringIndices[1] = indices[1];
			}
			indices = expandO(i, i, chars);
			if (indices[1] - indices[0] > maxSubStringIndices[1] - maxSubStringIndices[0]) {
				maxSubStringIndices[0] = indices[0];
				maxSubStringIndices[1] = indices[1];
			}
		}
		return s.substring(maxSubStringIndices[0], maxSubStringIndices[1] + 1);
	}

	private int[] expandO(int l, int r, char[] chars) {
		while (l >= 0 && r < chars.length && chars[l] == chars[r]) {
			l--;
			r++;
		}
		return new int[] { l + 1, r - 1 };
	}
}

public class LongestPalindromeSubstringRunner {
	public static void main(String[] args) {
		List<String> stringList = List.of("abb");
		for (String s : stringList) {
			System.out.printf("Longest Palindrome substring of %s is %s %n", s,
					new LongestPalindromeSubstring().longestPalindrome(s));
		}
	}

}
