package com.mvs.leetcode_problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//Remove Nth Node from End of list

/*
[1,2,3,4,5], n = 2 
 
 * 
Example1:
	Input: head = [1,2,3,4,5], n = 2
	Output: [1,2,3,5]
Example2: 
	Input: head = [1], n = 1
	Output: []
Example3:
	Input: head = [1,2], n = 1
	Output: [1]
	Technique: Create a DummyNode(anchor) if the input is 1,2,3,4,5, create dummyNode and make input as 0,1,2,3,4,5
	From here use 2 pointers. FastPointer and SlowPointer is pointed to dummy, now move FastPointer forward by N+1 steps.
	Next move forward both Fast and SlowPointer until FastPointer is not null.
	Rule is when fast pointer is null, SlowPointer.next is the one that needs to be removed.
	SlowPointer.next = SlowPointer.next.next (link jumped to next)
	now return dummy.next; 
*/

class RemoveNodeFromEndOfList {

	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode dummyNode = new ListNode(0);
		dummyNode.next = head;
		ListNode fastNode = dummyNode;
		ListNode slowNode = dummyNode;
		int i = 0;
		while (i <= n) {
			fastNode = fastNode.next;
			i++;
		}
		while (fastNode != null) {
			fastNode = fastNode.next;
			slowNode = slowNode.next;
		}
		slowNode.next = slowNode.next.next; 
		return dummyNode.next;
	}

	static class ListNode {
		int val;
		ListNode next;

		ListNode(int val) {
			this.val = val;
		}
	}

	public static int[] listNodeToArray(ListNode head) {
		// First, find length
		int length = 0;
		ListNode curr = head;
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
}

public class RemoveNodeFromEndOfListRunner {

	public static String getStringFromArray(int[] array) {
		return Arrays.stream(array).mapToObj(String::valueOf).collect(Collectors.joining(","));
	}

	public static void main(String[] args) {
		HashMap<int[], Integer> hashMap = new HashMap<>();
		hashMap.put(new int[] { 1, 2, 3, 4, 5 }, 5);
		hashMap.put(new int[] {1,2,3,4,5}, 3);
		hashMap.put(new int[] {1}, 1);
		hashMap.put(new int[] {1,2}, 1);

		for (Map.Entry<int[], Integer> hashmapEntry : hashMap.entrySet()) {
			var nodes = hashmapEntry.getKey();
			var nodeN = hashmapEntry.getValue();
			RemoveNodeFromEndOfList.ListNode nodeList = new RemoveNodeFromEndOfList.ListNode(nodes[0]);
			RemoveNodeFromEndOfList.ListNode head = nodeList;
			for (int i = 1; i < nodes.length; i++) {
				var nextNode = new RemoveNodeFromEndOfList.ListNode(nodes[i]);
				head.next = nextNode;
				head = nextNode;
			}
			RemoveNodeFromEndOfList removeNodeFromEndOfList = new RemoveNodeFromEndOfList();
			var updatedLinkedList = removeNodeFromEndOfList.removeNthFromEnd(nodeList, nodeN);
			var listNodeToArray = RemoveNodeFromEndOfList.listNodeToArray(updatedLinkedList);
			System.out.printf("Array:%s with Nth Node:%s to removed and finalList %s%n", getStringFromArray(nodes),
					nodeN, listNodeToArray == null ? "Null" : getStringFromArray(listNodeToArray));
		}

	}

}
