package com.mvs.leetcode_problems;

import java.util.ArrayList;
import java.util.List;

/*
 * Example 1:

	Input: s = "abc"
	Output: 3
	Explanation: Three palindromic strings: "a", "b", "c".
	Example 2:
	
	Input: s = "aaa"
	Output: 6
	Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
	 
 * 
 */

class PalindromeSubstring{

	public int countSubstrings(String s) {
		List<String> palindromes = new ArrayList<String>();
		char[] chars = s.toCharArray();
		
		for (int i = 0; i < chars.length; i++) {
			Expando(i, i, chars, s, palindromes);
			Expando(i, i+1, chars, s, palindromes);
		}
		for(String palindromeString: palindromes) {
			System.out.println("Palindrome string:"+palindromeString);
		}
		return palindromes.size();
	}
	
	public void Expando(int l, int r, char[] chars, String s, List<String> palindromes) {
		while(l >=0 && r < chars.length && chars[l]==chars[r]) {
			var subString = s.substring(l,r+1);
			palindromes.add(subString);
			l--;
			r++;
		}
	}
}

public class PalindromeSubstringRunner {
	public static void main(String[] args) {
		List<String> palindromeStrings = List.of("abc","aaa","aba","abba","abcd","racecar","aaaab"); 
		for(String palindromeString: palindromeStrings) {
			System.out.printf("Palindromes for string %s and its count %s %n", palindromeString,  new PalindromeSubstring().countSubstrings(palindromeString));
		}
		
	}
}
