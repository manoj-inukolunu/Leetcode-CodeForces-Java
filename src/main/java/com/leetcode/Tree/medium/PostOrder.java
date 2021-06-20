package com.leetcode.Tree.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * @author manoji on 3/21/20.
 */
public class PostOrder {

  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> integers = new ArrayList();
    Stack<TreeNode> stack = new Stack<>();
    HashSet<TreeNode> visited = new HashSet<>();
    if (root != null) {
      stack.push(root);
    }
    while (!stack.isEmpty()) {
      TreeNode current = stack.peek();
      if (isVisited(visited, current)) {
        integers.add(current.val);
        visited.add(current);
        stack.pop();
        if (stack.isEmpty()) {
          break;
        }
      }
      if (current.left != null && !visited.contains(current.left)) {
        stack.push(current.left);
      } else if (current.right != null && !visited.contains(current.right)) {
        stack.push(current.right);
      }

      if (current.left == null && current.right == null) {
        integers.add(current.val);
        visited.add(current);
        stack.pop();
      }
    }
    return integers;
  }

  private boolean isVisited(HashSet<TreeNode> visited, TreeNode current) {
    if (current.left == null && current.right != null) {
      return visited.contains(current.right);
    }
    if (current.right == null && current.left != null) {
      return visited.contains(current.left);
    }
    return visited.contains(current.left) && visited.contains(current.right);
  }

  public static void main(String args[]) {
    TreeNode node = new Codec().deserialize("3,1,2");
    PostOrder postOrder = new PostOrder();
    System.out.println(postOrder.postorderTraversal(node));
  }


}
