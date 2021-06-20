package com.leetcode.revision.fb.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.HashSet;
import java.util.Stack;

public class BSTIterator {

  Stack<TreeNode> stackA = new Stack();
  Stack<TreeNode> stackB = new Stack();
  HashSet<TreeNode> visited = new HashSet<>();

  public BSTIterator(TreeNode root) {
    while (root != null) {
      stackA.push(root);
      root = root.left;
    }
  }

  public boolean hasNext() {
    return !stackA.isEmpty();
  }

  public int next() {
    TreeNode curr = stackA.pop();
    if (!visited.contains(curr) && curr.right != null) {
      pushLeft(curr);
    }
    visited.add(curr);
    stackB.push(curr);
    return curr.val;
  }

  public boolean hasPrev() {
    return !stackB.isEmpty() && stackB.size() > 1;
  }

  public int prev() {
    stackA.push(stackB.pop());
    return stackB.peek().val;
  }

  public void pushLeft(TreeNode node) {
    while (node != null) {
      stackA.push(node);
      node = node.left;
    }
  }

  public static void main(String args[]) {
    TreeNode root = new Codec().deserialize("7,3,15,null,null,9,20");
    BSTIterator bSTIterator = new BSTIterator(root);

    System.out.println(bSTIterator.next()); // state becomes [3, 7, 9, 15, 20], return 3
    System.out.println(bSTIterator.next()); // state becomes [3, 7, 9, 15, 20], return 7
    System.out.println(bSTIterator.prev()); // state becomes [3, 7, 9, 15, 20], return 3
    System.out.println(bSTIterator.next()); // state becomes [3, 7, 9, 15, 20], return 7
    System.out.println(bSTIterator.hasNext()); // return true
    System.out.println(bSTIterator.next()); // state becomes [3, 7, 9, 15, 20], return 9
    System.out.println(bSTIterator.next()); // state becomes [3, 7, 9, 15, 20], return 15
    System.out.println(bSTIterator.next()); // state becomes [3, 7, 9, 15, 20], return 20
    System.out.println(bSTIterator.hasNext()); // return false
    System.out.println(bSTIterator.hasPrev()); // return true
    System.out.println(bSTIterator.prev()); // state becomes [3, 7, 9, 15, 20], return 15
    System.out.println(bSTIterator.prev()); // state becomes [3, 7, 9, 15, 20], return 9
  }
}
