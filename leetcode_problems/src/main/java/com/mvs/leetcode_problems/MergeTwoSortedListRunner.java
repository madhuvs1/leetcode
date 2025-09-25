package com.mvs.leetcode_problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/*
You are given the heads of two sorted linked lists list1 and list2. Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
Return the head of the merged linked list.

Input: list1 = [1,2,4], list2 = [1,3,4] Output: [1,1,2,3,4,4]
Input: list1 = [], list2 = []  Output: []
Input: list1 = [], list2 = [0] Output: [0]

Technique: 
Use 2 fingers one on each list, both fingers point at the first node, when they are equal remove one of them from original list, if they are not equal take the lesser value and remove from the list and continue until the node is empty. 
  
 * */

class MergeTwoSortedList {

	public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		ListNode anchorNode = new ListNode(0);
		ListNode anchorNodeCurrentNode = anchorNode;
		while (list1 != null && list2 != null) {
			if (list1.val <= list2.val) {
				anchorNodeCurrentNode.next = list1;
				anchorNodeCurrentNode = anchorNodeCurrentNode.next;
				list1 = list1.next;
			} else {
				anchorNodeCurrentNode.next = list2;
				anchorNodeCurrentNode = anchorNodeCurrentNode.next;
				list2 = list2.next;
			}
		}
		if (list1 != null)
			anchorNodeCurrentNode.next = list1;
		if (list2 != null)
			anchorNodeCurrentNode.next = list2;
		return anchorNode.next;
	}

	public static class ListNode {
		int val;
		ListNode next;

		public ListNode(int val) {
			this.val = val;
		}
	}

	public static ListNode buildListNode(int[] nums) {
		if (nums.length == 0)
			return null;
		ListNode head = new ListNode(nums[0]);
		ListNode headReference = head;
		for (int i = 1; i < nums.length; i++) {
			var newNode = new ListNode(nums[i]);
			headReference.next = newNode;
			headReference = newNode;
		}
		return head;
	}

	public static int[] ListNodeToArray(MergeTwoSortedList.ListNode listNode) {
		if (listNode == null)
			return null;
		if (listNode.next == null)
			return new int[] { listNode.val };
		int i = 0;
		MergeTwoSortedList.ListNode listNodeReference = listNode;
		while (listNodeReference != null) {
			listNodeReference = listNodeReference.next;
			i++;
		}
		int[] nums = new int[i];
		listNodeReference = listNode;
		nums[0] = listNodeReference.val;
		i = 1;
		while (listNodeReference.next != null) {
			nums[i] = listNodeReference.next.val;
			listNodeReference = listNodeReference.next;
			i++;
		}
		return nums;
	}

	public static String ArrayToString(int[] nums) {
		if (nums == null || nums.length == 0)
			return "";
		return Arrays.stream(nums).mapToObj(String::valueOf).collect(Collectors.joining(","));
	}
}

public class MergeTwoSortedListRunner {
	public static void main(String[] args) {
		Map<int[], int[]> map = new HashMap<>();
		map.put(new int[] { 1, 4, 5 }, new int[] { 1, 1, 2, 3, 6, 7 });
		map.put(new int[] {}, new int[] {});
		map.put(new int[] {}, new int[] { 0 });
		for (Map.Entry<int[], int[]> entry : map.entrySet()) {
			int[] nums1 = entry.getKey();
			int[] nums2 = entry.getValue();
			MergeTwoSortedList.ListNode listNode1 = MergeTwoSortedList.buildListNode(nums1);
			MergeTwoSortedList.ListNode listNode2 = MergeTwoSortedList.buildListNode(nums2);
			MergeTwoSortedList mergeTwoSortedList = new MergeTwoSortedList();
			MergeTwoSortedList.ListNode listNode3 = mergeTwoSortedList.mergeTwoLists(listNode1, listNode2);

			int[] nums3 = MergeTwoSortedList.ListNodeToArray(listNode3);
			System.out.printf("For Input1 int[] %s and Input2 int[] %s merged Output int[] of ListNode %s %n",
					MergeTwoSortedList.ArrayToString(nums1), MergeTwoSortedList.ArrayToString(nums2),
					MergeTwoSortedList.ArrayToString(nums3));

		}
	}
}
