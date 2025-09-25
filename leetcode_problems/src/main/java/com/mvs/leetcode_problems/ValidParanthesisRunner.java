package com.mvs.leetcode_problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
	Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
	Input: s = "()" Output: true
	Input: s = "()[]{}" Output: true
	Input: s = "(]" Output: false
	Input: s = "([])" Output: true
	Input: s = "([)]" Output: false

	Technique: Use Stack (LIFO Last In First Out, think of plates stacked, last stacked is the one first goes out) 
	1) Add matching pairs of parenthesis in hashmap
	2) Now enumerate the parenthesis array:
	a) if you encounter a closing parenthesis ')' or ']' or '}' and the queue is empty then its invalid
	b) if you encounter a closing parenthesis ')' and peek the Stack and if it doesn't have matching pair then its invalid
	c) otherwise keep adding items 
	

*/
class ValidParanthesis {
	public boolean isValid(String s) {
		char[] chars = s.toCharArray();
		if (chars.length % 2 != 0)
			return false;

		Map<Character, Character> parenthesisMap = new HashMap<>();
		parenthesisMap.put('(', ')');
		parenthesisMap.put('[', ']');
		parenthesisMap.put('{', '}');
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < chars.length; i++) {
			if(stack.isEmpty() && !parenthesisMap.containsKey(chars[i])) return false;
			if (!stack.isEmpty() && !parenthesisMap.containsKey(chars[i])
					&& parenthesisMap.get(stack.peek()) != chars[i])
				return false;
			if (!stack.isEmpty() && parenthesisMap.get(stack.peek()) == chars[i]) {
				stack.pop();
			} else {
				stack.push(chars[i]);
			}
		}
		if (!stack.isEmpty())
			return false;
		return true;
	}

	public boolean isValidRough(String s) {
		char[] chars = s.toCharArray();
		if (chars.length % 2 != 0)
			return false;
		Map<Integer, Character> map = new HashMap<>();
		Map<Character, Character> pairs = new HashMap<>();
		pairs.put('(', ')');
		pairs.put('[', ']');
		pairs.put('{', '}');
		for (int i = 0; i < chars.length; i++) {
			map.put(i, chars[i]);
		}
		boolean[] pairfoundIndex = new boolean[chars.length];
		for (int i = 0; i < chars.length && map.size() != 0; i++) {
			if (pairfoundIndex[i] == true)
				continue;
			int j = i + 1;
			char leftPair = chars[i];
			boolean pairFound = false;
			while (j < chars.length) {
				char rightPair = chars[j];
				if (pairs.get(leftPair) == rightPair && (i % 2 != j % 2)) {
					map.remove(i);
					map.remove(j);
					pairfoundIndex[j] = true;
					pairFound = true;
					break;
				}
				j++;
			}
			if (!pairFound)
				return false;
		}
		if (map.size() > 0)
			return false;
		return true;
	}
}

public class ValidParanthesisRunner {
	public static void main(String[] args) {
		ValidParanthesis validParanthesis = new ValidParanthesis();
		String[] paranthesisArray = new String[] { "){", "()", "()[]{}", "(]", "([])", "([)]", "([)()]", "(()())" };
		for (String s : paranthesisArray) {
			boolean isValid = validParanthesis.isValid(s);
			System.out.printf("String: %s has valid paranthesis:%s%n", s, isValid);
		}

	}
}
