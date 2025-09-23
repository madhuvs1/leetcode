package com.mvs.leetcode_problems;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.mvs.leetcode_problems.RemoveNodeFromEndOfList.ListNode;

/*
	You are given the head of a singly linked-list. The list can be represented as:
	L0 → L1 → … → Ln - 1 → Ln Reorder the list to be on the following form: L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
	Example1:
    Input: head = [1,2,3,4] Output: [1,4,2,3]
    Input: head = [1,2,3,4,5] Output: [1,5,2,4,3]
    l-1,r-4, l-2,r-3
    
    Find MiddleNode
    1,2,3,4,5 -> (1,1) -> (2,3) -> (3,5) -> (4,7-NULL) MiddleNode is 3
    1,2,3,4,5,6 -> (1,1) -> (2,3) -> (3,5) -> (4,7-NULL) MiddleNode is 3   
    
    Technique: 
    Split the LinkedList by half by slow and fast pointer,
    Reverse the second half
    Merge First and Second Half
    
 * */

class ReorderList {
	//LinkedList  1,2,3,4,5,6,7,8,9
	public void reorderList(ListNode head) {
		// Find middle node
		ListNode slowNode = head;
		ListNode fastNode = head;
		while (fastNode.next != null && fastNode.next.next != null) {
			slowNode = slowNode.next;
			fastNode = fastNode.next.next;
		}
		ListNode secondHalfNode = slowNode.next; // Second half of the nodes --> 4,5,6,7,8,9 -> 9,8,7,6,5,4 -> 6,5,4 ->
													// 7,8,9
		slowNode.next = null; // tail node of first half --> 1,2,3

		// Reverse Second Half
		ListNode current = secondHalfNode;
		ListNode previous = null;
		ListNode next = null;
		while (current != null) {
			next = current.next;
			current.next = previous;
			previous = current;
			current = next;
			
		}
		//firstHalf: 1,2,3,4,5 |secondHalf: 9,8,7,6   
		//Merged firstHalf and secondHalf:	1-9->2->8->3->7->4->6->5->null
		 ListNode left = head;
		 ListNode right = previous;
		 while(right != null) {
			 var leftNext = left.next; // 2 -> 8 -> 3 -> 7 -> 4 -> 6 -> 5->null 
			 left.next = right; //  1-9 -> 9-2 -> 2-8 -> 8-3 -> 3-7 -> 7-4 -> 4-6 -> 6-5
			 left = right; //  9->2->8->3->7->4->6->5
			 right = leftNext; // 2->8->3->7->4->6->5->null   
		 }
		
		//System.out.println(current);
	}

	static class ListNode {
		int val;
		ListNode next;

		ListNode(int val) {
			this.val = val;
		}

	}

}

public class ReorderListRunner {
	public static int[] listNodeToArray(ReorderList.ListNode head) {
	    // First, find length
	    int length = 0;
	    ReorderList.ListNode curr = head;
	    while (curr != null) {
	        length++;
	        curr = curr.next;
	    }

	    // Fill array
	    int[] arr = new int[length];
	    curr = head;
	    for (int i = 0; i < length; i++) {
	        arr[i] = curr.val;
	        curr = curr.next;
	    }
	    return arr;
	}
	
	public static String getStringFromArray(int[] array) {
		return Arrays.stream(array).mapToObj(String::valueOf).collect(Collectors.joining(","));
	}
	
	public static void main(String[] args) {
		int[] nodes = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		ReorderList.ListNode linkedList = new ReorderList.ListNode(nodes[0]);
		ReorderList.ListNode currentNode  = linkedList;
		for(int i=1; i < nodes.length; i++) {
			ReorderList.ListNode nextNode = new ReorderList.ListNode(nodes[i]);
			currentNode.next = nextNode;
			currentNode = nextNode;
		}
		ReorderList  reorderList = new ReorderList();
		reorderList.reorderList(linkedList);
		var reorderedListArray = listNodeToArray(linkedList);
		System.out.printf("Reorder of nodes(from order L1,L2...Ln to L1,Ln,L2,Ln-1,L3,Ln-2....) array:%s is %s %n ", getStringFromArray(nodes), getStringFromArray(reorderedListArray));
		
	}
}
