package com.leetcode.amazon;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class FindLeaves {


  public List<List<Integer>> findLeaves(TreeNode root) {
    List<List<Integer>> ans = new ArrayList<>();

    while (!(root.left == null && root.right == null)) {
      List<Integer> list = new ArrayList<>();
      removeLeaves(root, null, null, list);
      ans.add(new ArrayList(list));
    }
    List<Integer> list = new ArrayList<>();
    list.add(root.val);
    ans.add(list);
    return ans;
  }

  private void removeLeaves(TreeNode root, TreeNode parent, String dir, List<Integer> ans) {
    if (root == null) {
      return;
    }
    if (root.left == null && root.right == null) {
      ans.add(root.val);
      if (parent != null && dir != null) {
        if (dir.equals("left")) {
          parent.left = null;
        } else if (dir.equals("right")) {
          parent.right = null;
        }
      }
      return;
    }
    removeLeaves(root.left, root, "left", ans);
    removeLeaves(root.right, root, "right", ans);

  }


  public static void main(String args[]) {
    TreeNode root = new Codec().deserialize("1,2,3,4,5");
    FindLeaves f = new FindLeaves();
    System.out.println(f.findLeaves(root));
  }
}
