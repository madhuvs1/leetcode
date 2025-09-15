package com.mvs.leetcode_problems;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Input: head = [3,2,0,-4] Output: true
 * Input: head = [1,2], Output: true
 * Input: head = [1], Output: false
 * 
 * 
 *  */

class ListNode {
	int val;
	ListNode next;

	ListNode(int val) {
		this.val = val;
		next = null;
	}
}

class Solution {
	//Has Time Complexity of O(n) and Space Complexity of O(n) 
	public boolean hasCycle1(ListNode head) {
		Set<ListNode> set = new HashSet<>();
		while (head != null && head.next != null) {
			if (set.contains(head))
				return true;
			set.add(head);
			head = head.next;
		}
		return false;
	}
	
	//using Floyd's cycle detection algorithm
	public boolean hasCycle(ListNode head) {
		ListNode slowNode = head;
		ListNode fastNode = head;  
		while(fastNode != null && fastNode.next != null) {
			slowNode = slowNode.next; 
			fastNode = fastNode.next.next; 
			if(slowNode == fastNode) return true;
		}
		return false;
	}
}

public class LinkedListCycleRunner {
	public static void main(String[] args) {
		Map<Integer, int[]> map = new HashMap<>();
		map.put(1, new int[] { 3, 2, 0, -4 });
		map.put(0, new int[] { 1, 2 });
		map.put(0, new int[] { 1 });
		map.put(null, new int[] { 1 });
		
		for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
			var pos = entry.getKey();
			var nodes = entry.getValue();
			var headNode = new ListNode(nodes[0]);
			var currentNode = headNode;
			ListNode loopNode = null;
			for (Integer i = 1; i < nodes.length; i++) {
				currentNode.next = new ListNode(nodes[i]);
				currentNode = currentNode.next;
				if (i == pos)
					loopNode = currentNode;
			}
			if (pos != null && pos == 0)
				currentNode.next = headNode;
			else if (loopNode != null) {
				currentNode.next = loopNode;
			} else
				currentNode.next = null;
			System.out.printf("Array %s with pos (connects with position(index) pos:%d, has loop cycle: %s %n",
					Arrays.toString(nodes), pos, new Solution().hasCycle1(headNode) == true);
			
		}
		
	}
}
