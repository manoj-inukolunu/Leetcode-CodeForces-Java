package com.leetcode.random10.sixmonths.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;

public class LongestConsecutiveSequence {

  int ans = 1;

  public int longestConsecutive(TreeNode root) {
    dfs(root);
    return ans;
  }

  private int dfs(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = dfs(root.left);
    int right = dfs(root.right);

    if (root.left != null && root.right != null) {
      if (root.val == root.left.val - 1 && root.val == root.right.val - 1) {
        ans = Math.max(ans, Math.max(left, right) + 1);
        return Math.max(left, right) + 1;
      } else if (root.val == root.left.val - 1) {
        ans = Math.max(ans, left + 1);
        return left + 1;
      } else if (root.val == root.right.val - 1) {
        ans = Math.max(ans, right + 1);
        return right + 1;
      }
      return 1;
    } else if (root.left == null && root.right != null) {
      if (root.val == root.right.val - 1) {
        ans = Math.max(ans, right + 1);
        return right + 1;
      }
      return right;
    } else if (root.right == null && root.left != null) {
      if (root.val == root.left.val - 1) {
        ans = Math.max(ans, left + 1);
        return left + 1;
      }
      return left;
    } else if (root.left == null && root.right == null) {
      ans = Math.max(ans, 1);
      return 1;
    }
    return ans;
  }

  public static void main(String args[]) {
    LongestConsecutiveSequence l = new LongestConsecutiveSequence();
    TreeNode node = new Codec().deserialize("1,6,2,7,5,null,null,8,null,6,4,7,9,null,null,3,5,null,null,10,8,4,2,null,null,9,11,null,null,1");
    System.out.println(l.longestConsecutive(node));
  }
}
