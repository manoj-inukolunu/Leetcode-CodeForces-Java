package com.leetcode.Tree.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author manoji on 5/31/20.
 */
public class DuplicateSubTree {

  List<TreeNode> ans = new ArrayList<>();
  HashSet<String> hashSet = new HashSet<>();
  HashSet<String> added = new HashSet<>();

  public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
    dfs(root);
    return ans;
  }

  private String dfs(TreeNode root) {
    if (root == null) {
      return "#";

    }
    String str = root.val + "," + dfs(root.left) + "," + dfs(root.right);
    if (hashSet.contains(str) && !added.contains(str)) {
      ans.add(root);
      added.add(str);
    } else {
      hashSet.add(str);
    }
    return str;
  }


  public static void main(String args[]) {
    DuplicateSubTree d = new DuplicateSubTree();

    System.out.println(d.findDuplicateSubtrees(new Codec().deserialize("2,1,11,11,null,1")));
  }
}
