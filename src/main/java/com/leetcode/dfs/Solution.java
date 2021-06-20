package com.leetcode.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @author manoji on 2019-12-28.
 */
public class Solution {

  public int maxDepth(Node root) {
    HashSet<Node> visited = new HashSet<>();
    visited.add(root);
    return dfs(root, visited);
  }

  public int dfs(Node root, HashSet<Node> visited) {
    if (root == null) {
      return 0;
    }
    if (root.children == null || root.children.size() == 0) {
      return 1;
    }
    List<Node> children = root.children;

    Iterator<Node> nodeIterator = children.iterator();
    int maxLength = 0;
    while (nodeIterator.hasNext()) {
      Node n = nodeIterator.next();
      if (!visited.contains(n)) {
        int length = 1 + dfs(n, visited);
        if (length > maxLength) {
          maxLength = length;
        }
      }
    }

    return maxLength;
  }

  public TreeNode increasingBST(TreeNode root) {
    Stack<TreeNode> stack = new Stack();
    List<Integer> nodes = new ArrayList<>();
    TreeNode curr = root;
    while (curr != null || stack.size() > 0) {
      while (curr != null) {
        stack.push(curr);
        curr = curr.left;
      }
      curr = stack.pop();
      // System.out.println(curr.val);
      nodes.add(curr.val);
      curr = curr.right;
    }

    TreeNode node = new TreeNode(nodes.get(0));
    TreeNode rootNode = node;
    int i = 0;
    while (true) {
      if (i + 1 == nodes.size()) {
        break;
      }
      node.right = new TreeNode(nodes.get(i + 1));
      node.left = null;
      node = node.right;
      i++;
    }
    return rootNode;
  }

  public int numDecodings(String s) {
    HashMap<Integer, Integer> hashMap = new HashMap();
    return numDecodingsRecursive(s, 0, hashMap);
  }

  public int numDecodingsRecursive(String s, int index, HashMap<Integer, Integer> map) {
    if (index >= s.length()) {
      return 1;
    }

    if (map.containsKey(index)) {
      return map.get(index);
    }

    int totalDecodings;
    if (canDecode(s.charAt(index))) {
      if ((index + 1 < s.length()) && Integer.parseInt(s.substring(index, index + 2)) <= 26) {
        totalDecodings = numDecodingsRecursive(s, index + 2, map) + numDecodingsRecursive(s, index + 1, map);
        map.put(index, totalDecodings);
      } else {
        totalDecodings = numDecodingsRecursive(s, index + 1, map);
        map.put(index, totalDecodings);
      }
      return totalDecodings;
    } else {
      return 0;
    }
  }

  private boolean canDecode(char charAt) {
    return Character.getNumericValue(charAt) >= 1;
  }

  public static void main(String args[]) {
    Solution solution = new Solution();
//    Codec codec = new Codec();

//    System.out.println(codec.serialize(solution.increasingBST(codec.deserialize("5,3,6,2,4,null,8,1,null,null,null,7,9"))));

    System.out.println(solution.numDecodings("226"));
  }
}


