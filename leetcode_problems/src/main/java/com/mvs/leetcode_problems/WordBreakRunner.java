package com.mvs.leetcode_problems;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * Example 1:

	Input: s = "leetcode", wordDict = ["leet","code"]
	Output: true
	Explanation: Return true because "leetcode" can be segmented as "leet code".
	Example 2:
	
	Input: s = "applepenapple", wordDict = ["apple","pen"]
	Output: true
	Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
	Note that you are allowed to reuse a dictionary word.
	Example 3:
	
	Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
	Output: false
	
	Technique: lets take word "Hello"  diction: {"He","l","lo"};
	Index of Hello -> H(0)->e(1)->l(2)->l(2)->0(3)
	Substring Of Hello -> s.substring(0,1) -> "H" substring(start,end) are checkpoint indices
	checkpoint indices -> H(0-1), e(1-2), l(2-3), l(3-4), 0(4-5)
	With understanding of checkpoint indices, BFS (Breadth First Search) tries to find a contiguous graph from start to end.
	Here as part of contiguity we use the words in diction(any order) and see if a connected graph is found
	How do we achieve that.
	a) Start with checkpoint indices(0-1) H doesn't exist in dict, Next (0-2) "He" found in diction since we found a part of the graph lets remember that queue(2), Next 0-3 "Hel" (not found), 
	Next (0-4) "Hell" not found, Next (0-5) "Hello" not found. So in the queue we have 2
	b) Next iteration, start with checkpoint indices(2-3) "l" found queue(3), next indices(2-4) "l" found queue(4), next(2-5) -> "llo" not found, final queue 3,4
	c) Next iteration, start with checkpoint 3 or 4, lets say I start 3 -> checkpoint indices(3,4) "l" "l" found but queue(4) already exists so skip it, next (3,5) -> "lo" found queue(5) 
		but 5= s.length we stop here as the word has been constructed with words in dict.
 	
 	Lets implement this, instead of character by character, have end index based on words given in dict
 	start with queue 0 as "H" is from 0-1 	
 	Iteration1: say, s.substring(0, words.length) possible lengths I have "He" -> 2, "l" -> 1, "lo" -> 2 distinct values (1,2) 
 	Iteration1: (0,1) -> "H" doesn't exist -> (0,2) -> "He" exists queue(2), Next (0,3) - not required as there is no such lengths in dict. Queue has 2
 	Iteration2: (2,2+1) (because it has to be i, i+(dict words length) -> (2,3) -> "l"  found Queue(3) -> Next (2,4) "ll" not found. Queue has 3
 	Iteration3: (3,3+1) (3,4) -> "l" Queue(4) -> Next (3,5) -> "lo" found in dict Queue(5) but 5 == "Hello".length we stop here and conclude that word "Hello" can be segmented using Diction words
 	      
 	
	 
 */

class WordBreak {
	// Input: s = "aaaab", wordDict = ["a", "aaa", "ab"]

	public boolean wordBreak(String s, List<String> wordDict) {
		Set<Integer> substringLengths = new HashSet<Integer>();
		for (String word : wordDict) {
			substringLengths.add(word.length());
		}
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		boolean[] visitedCheckpoints = new boolean[s.length()];
		queue.add(0);
		while (!queue.isEmpty()) {
			int i = queue.pop();
			for (int dictWordLength : substringLengths) {
				if (i + dictWordLength > s.length())
					continue;
				if (wordDict.contains(s.substring(i, i + dictWordLength))) {
					if (i + dictWordLength == s.length())
						return true;
					if (visitedCheckpoints[i + dictWordLength] == false) {
						queue.add(i + dictWordLength);
						visitedCheckpoints[i + dictWordLength] = true;
					}
				}
			}
		}
		return false;
	}
}

public class WordBreakRunner {
	public static void main(String[] args) {
		Map<String, List<String>> wordDict = new HashMap<>();
		wordDict.put("leetcode", List.of("leet", "code"));
		wordDict.put("applepenapple", List.of("apple", "pen"));
		wordDict.put("catsandog", List.of("cats", "dog", "sand", "and", "cat"));
		wordDict.put("aaaab", List.of("a", "aaa", "ab")); // tricky one
		wordDict.put("aaab", List.of("aa", "aaa", "ab")); // tricky one

		for (Map.Entry<String, List<String>> map : wordDict.entrySet()) {
			var result = new WordBreak().wordBreak(map.getKey(), map.getValue());
			System.out.printf("WordBreak of word:\"%s\" from wordDict: (%s) result:%s %n", map.getKey(),
					map.getValue().stream().collect(Collectors.joining(",")), result);
		}

	}
}
