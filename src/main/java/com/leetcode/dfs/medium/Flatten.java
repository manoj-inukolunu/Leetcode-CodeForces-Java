package com.leetcode.dfs.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author manoji on 2020-01-12.
 */
public class Flatten {


  public void flattenRecursive(TreeNode root) {
    if (root == null) {
      return;
    }
    flattenRecursive(root.left);
    flattenRecursive(root.right);
    if (root.left != null) {
      TreeNode temp = root.right;
      root.right = null;
      TreeNode current = root;
      while (current.left != null) {
        current = current.left;
      }
      current.right = temp;
      root.left.right = current;
      root.right = root.left;
      root.left = null;
    }
  }

  public void flatten(TreeNode root) {
/*

    if (root == null) {
      return;
    }

    if (root.left != null) {
      TreeNode temp = root.right;
      root.right = null;
      root.left.right = temp;
      root.right = root.left;
      root.left=null;

    }
    flatten(root.left);
    flatten(root.right);
*/

    List<TreeNode> list = new ArrayList();
    flattenToList(root, list);
    for (int i = 0; i < list.size(); i++) {
      if (i + 1 < list.size()) {
        list.get(i).right = list.get(i + 1);
      }
    }
    if (list.size() >= 2) {
      root.left = null;
      root.right = list.get(1);
    }
  }

  public void flattenToList(TreeNode root, List<TreeNode> treeNodeList) {
    if (root == null) {
      return;
    }
    treeNodeList.add(new TreeNode(root.val));
    flattenToList(root.left, treeNodeList);
    flattenToList(root.right, treeNodeList);
  }


  public static void main(String args[]) {
    TreeNode root = new Codec().deserialize("1,2,5,3,4,null,6");

    Flatten flatten = new Flatten();

    flatten.flattenRecursive(root);

    System.out.println(new Codec().serialize(root));
  }

}
