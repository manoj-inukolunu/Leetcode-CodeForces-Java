package com.leetcode.Tree;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author manoji on 4/10/20.
 */
public class PrintTree {

  private int depth(TreeNode root) {
    if (root == null) {
      return 0;
    }

    return Math.max(depth(root.left), depth(root.right)) + 1;
  }

  public List<List<String>> printTree(TreeNode root) {
    int treeDepth = depth(root);
    List<List<String>> list = new ArrayList();

    for (int i = 0; i < treeDepth; i++) {
      List<String> l = new ArrayList();
      for (int j = 0; j < Math.pow(2, treeDepth) - 1; j++) {
        l.add("");
      }
      list.add(l);
    }

    dfs(root, list, 0, list.get(0).size(), 0);

    return list;
  }

  private void dfs(TreeNode root, List<List<String>> list, int begin, int end, int level) {
    if (root == null) {
      return;
    }
    int mid = begin + (end - begin) / 2;

    list.get(level).set(mid, root.val + "");

    dfs(root.left, list, begin, mid - 1, level + 1);
    dfs(root.right, list, mid + 1, end, level + 1);

  }

  public static void main(String args[]) {

    TreeNode node = new Codec().deserialize("1,2");

    PrintTree printTree = new PrintTree();

    System.out.println(printTree.printTree(node));

  }

}
