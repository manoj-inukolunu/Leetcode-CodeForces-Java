package com.leetcode.Tree;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author manoji on 4/8/20.
 */
public class Diameter {


  private int max = Integer.MIN_VALUE;

  class Data {

    TreeNode node;
    int level;

    public Data(TreeNode root, int level) {
      this.node = root;
      this.level = level;
    }
  }

  public int diameterOfBinaryTree(TreeNode root) {
    //diameterOld(root);

    dfs(root);

    return max;
  }

  private int dfs(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int left = 1 + dfs(root.left);

    int right = 1 + dfs(root.right);

    if (left + right - 2 > max) {
      max = left + right - 2;
    }

    return Math.max(left, right);
  }

  private void diameterOld(TreeNode root) {
    HashMap<TreeNode, TreeNode> parentMap = new HashMap();
    dfs(root, null, parentMap);

    dfs(root, parentMap);
  }

  private void dfs(TreeNode root, HashMap<TreeNode, TreeNode> map) {
    if (root == null) {
      return;
    }

    Queue<Data> queue = new LinkedList<>();
    queue.add(new Data(root, 0));

    HashSet<TreeNode> visited = new HashSet();

    while (!queue.isEmpty()) {
      Data current = queue.poll();
      int level = current.level;
      if (level > max) {
        max = level;
      }
      if (!visited.contains(current.node)) {

        TreeNode parent = map.get(current.node);
        visited.add(current.node);

        if (parent != null && !visited.contains(parent)) {
          queue.add(new Data(parent, level + 1));
        }

        if (current.node.left != null && !visited.contains(current.node.left)) {
          queue.add(new Data(current.node.left, level + 1));
        }

        if (current.node.right != null && !visited.contains(current.node.right)) {
          queue.add(new Data(current.node.right, level + 1));
        }
      }
    }

    dfs(root.left, map);
    dfs(root.right, map);
  }


  private void dfs(TreeNode root, TreeNode parent, HashMap<TreeNode, TreeNode> parentMap) {
    if (root == null) {
      return;
    }
    if (parent != null) {
      parentMap.put(root, parent);
    }
    dfs(root.left, root, parentMap);
    dfs(root.right, root, parentMap);
  }

  public static void main(String args[]) {
    Diameter diameter = new Diameter();
    TreeNode root = new Codec().deserialize("1,2,3,4,5");
    System.out.println(diameter.diameterOfBinaryTree(root));
  }

}
