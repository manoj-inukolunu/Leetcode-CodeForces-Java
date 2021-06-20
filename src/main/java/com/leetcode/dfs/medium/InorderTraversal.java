package com.leetcode.dfs.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * @author manoji on 2020-01-18.
 */
public class InorderTraversal {

  private void recursive(TreeNode root) {
    if (root == null) {
      return;
    }

    recursive(root.left);
    System.out.println(root.val);
    recursive(root.right);
  }

  private List<Integer> iterative(TreeNode root) {

    List<Integer> list = new ArrayList();
    Stack<TreeNode> stack = new Stack<>();
    HashSet<TreeNode> visited = new HashSet<>();
    if (root != null) {
      stack.push(root);
    }
    while (!stack.isEmpty()) {
      TreeNode current = stack.peek();
      if (current.left != null && !visited.contains(current.left)) {
        stack.push(current.left);
      } else if (current.right != null && !visited.contains(current.right)) {
        list.add(current.val);
        stack.pop();
        visited.add(current);
        stack.push(current.right);
      } else {
        // leaf node
        stack.pop();
        visited.add(current);
        list.add(current.val);
      }
    }
    return list;
  }

  public static void main(String args[]) {
    InorderTraversal traversal = new InorderTraversal();

    System.out.println(traversal.iterative(new Codec().deserialize("")));

    System.out.println();
    System.out.println();

    traversal.recursive(new Codec().deserialize(""));
  }

}
