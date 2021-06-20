package com.leetcode.Tree.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author manoji on 2020-01-16.
 */
public class SumRootToLeaf {

  public int sumNumbers(TreeNode root) {
    int sum = 0;
    List<String> paths = binaryTreePaths(root);
    for (String path : paths) {
      sum += Integer.parseInt(path);
    }
    return sum;
  }

  private List<List<TreeNode>> dfs(TreeNode root) {
    if (root == null) {
      return null;
    }

    if (root.left == null && root.right == null) {
      List<List<TreeNode>> list = new ArrayList();
      List<TreeNode> current = new ArrayList();
      current.add(root);
      list.add(current);
      return list;
    }

    List<List<TreeNode>> left = dfs(root.left);
    if (left != null) {
      for (List<TreeNode> list : left) {
        list.add(root);
      }
    }
    List<List<TreeNode>> right = dfs(root.right);
    if (right != null) {
      for (List<TreeNode> list : right) {
        list.add(root);
      }
    }

    if (left != null && right != null) {
      left.addAll(right);
      return left;
    }

    if (left == null && right != null) {
      return right;
    }

    if (left != null && right == null) {
      return left;
    }
    return null;
  }


  public List<String> binaryTreePaths(TreeNode root) {
    return binaryTreePathsRecursive(root, new ArrayList(), "");
  }

  private List<String> binaryTreePathsRecursive(TreeNode root, ArrayList<String> strList, String str) {
    if (root == null) {
      return strList;
    }
    if (root.left == null && root.right == null) {
      str += "" + root.val;
      strList.add(str);
      return strList;
    }
    str += root.val;
    binaryTreePathsRecursive(root.left, strList, str);
    binaryTreePathsRecursive(root.right, strList, str);
    return strList;
  }


  public static void main(String args[]) {
    SumRootToLeaf sumRootToLeaf = new SumRootToLeaf();

    System.out.println(sumRootToLeaf.binaryTreePaths(new Codec().deserialize("4,9,0,5,1")));
  }


}
