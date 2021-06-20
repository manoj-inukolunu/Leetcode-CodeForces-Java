package com.leetcode.random10.sixmonths.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author manoji on 7/26/20.
 */
public class SplitBST {


  HashMap<Integer, TreeNode> map = new HashMap();
  TreeNode node = null;
  int smallest = Integer.MAX_VALUE, largest = Integer.MIN_VALUE;

  public TreeNode[] splitBST(TreeNode root, int V) {
    dfs(root, null, V);
    if (node == null) {
      if (V < smallest) {
        return new TreeNode[]{null, root};
      } else {
        return new TreeNode[]{root, null};
      }
    }
    if (map.get(node.val) == null) {
      TreeNode right = root.right;
      root.right = null;
      return new TreeNode[]{root, right};
    } else {
      TreeNode parent = map.get(node.val);
      if (node.val > parent.val) {
        while (parent != null && node.val > parent.val) {
          parent = map.get(parent.val);
        }
        if (parent == null) {
          TreeNode right = node.right;
          node.right = null;
          return new TreeNode[]{root, right};

        } else {
          TreeNode less = parent.left;
          parent.left = node.right;
          node.right = null;
          return new TreeNode[]{less, root};
        }

      } else {
        while (parent != null && node.val < parent.val) {
          parent = map.get(parent.val);
        }
        if (parent == null) {
          map.get(node.val).left = node.right;
          node.right = null;
          return new TreeNode[]{node, root};
        } else {
          TreeNode more = parent.right;
          parent.right = node;
          map.get(node.val).left = node.right;
          node.right = null;
          return new TreeNode[]{root, more};
        }
      }
    }
  }

  private void dfs(TreeNode root, TreeNode parent, int val) {
    if (root == null) {
      return;
    }
    smallest = Math.min(root.val, smallest);
    largest = Math.max(root.val, largest);
    if (root.val == val) {
      node = root;
    }
    map.put(root.val, parent);
    dfs(root.left, root, val);
    dfs(root.right, root, val);
  }

  public static void main(String args[]) {
    SplitBST s = new SplitBST();

    TreeNode[] res = s.splitBST(new Codec().deserialize("10,5,20,3,9,15,25,null,null,8,null,null,null,null,null,6,null,null,7"), 6);

    Arrays.stream(res).forEach(node1 -> System.out.println(new Codec().serialize(node1)));
  }

}
