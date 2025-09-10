package com.mvs.leetcode_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class NodeHelper {
	public List<Node> buildNodes() {
		// [[2,4],[1,3],[2,4],[1,3]] ->
		List<int[]> adjacencyList = List.of(new int[] { 2, 4 }, new int[] { 1, 3 }, new int[] { 2, 4 },
				new int[] { 1, 3 });

		List<Node> nodes = new ArrayList<>();
		for (int i = 1; i <= adjacencyList.size(); i++) {
			var node = new Node(i);
			nodes.add(node);
		}
		for (int i = 0; i < adjacencyList.size(); i++) {
			var node = nodes.get(i);
			for (int neighbor : adjacencyList.get(i)) {
				var neigborNode = nodes.get(neighbor - 1);
				node.addNeighbors(neigborNode);
			}
		}
		return nodes;
	}
}

class Node {
	public int val;
	public List<Node> neighbors;

	public Node(int val) {
		this.val = val;
		this.neighbors = new ArrayList<>();
	}

	public void addNeighbors(Node node) {
		neighbors.add(node);
	}
}

public class CloneGraph {
	public Map<Integer, Node> clones = new HashMap<>();

	public Node cloneGraph(Node originalNode) {
		if (originalNode == null)
			return null;
		if (clones.containsKey(originalNode.val))
			return clones.get(originalNode.val);

		var clonedNode = new Node(originalNode.val);
		clones.put(originalNode.val, clonedNode);

		for (Node originalNeighborNode : originalNode.neighbors) {
			var clonedNeighborNode = cloneGraph(originalNeighborNode);
			clonedNode.neighbors.add(clonedNeighborNode);
		}
		return clonedNode;
	}

	public static void main(String[] args) {
		var nodeHelper = new NodeHelper();
		var nodes = nodeHelper.buildNodes();

		var cloneGraph = new CloneGraph();
		var clonedNode = cloneGraph.cloneGraph(nodes.getFirst());

	}
}
