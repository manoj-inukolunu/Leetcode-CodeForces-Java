package com.leetcode.random10.sixmonths.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.HashMap;
import java.util.List;

public class BstToGreaterSumTree {

  int sum=0;

  public TreeNode bstToGst(TreeNode root) {
    dfs(root);
    return root;
  }


  private void dfs(TreeNode root ) {
    if (root == null) {
      return;
    }
    dfs(root.right);
    sum = root.val = root.val + sum;
    dfs(root.left);
  }

  private void dfs(TreeNode root, HashMap<Integer, TreeNode> map, List<Integer> list) {
    if (root == null) {
      return;
    }
    map.put(root.val, root);
    dfs(root.left, map, list);
    list.add(root.val);
    dfs(root.right, map, list);
  }

  public static void main(String args[]) {
    TreeNode root = new Codec().deserialize("4,1,6,0,2,5,7,null,null,null,3,null,null,null,8");
    BstToGreaterSumTree b = new BstToGreaterSumTree();
    root = b.bstToGst(root);

  }

}
