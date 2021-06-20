package com.leetcode.Tree.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author manoji on 3/24/20.
 */
public class KDistNodes {

  class Data {

    TreeNode node;
    int dist;

    public Data(TreeNode node, int dist) {
      this.node = node;
      this.dist = dist;
    }
  }

  public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
    HashMap<TreeNode, TreeNode> map = new HashMap();
    dfs(root, null, map);

    LinkedList<Data> queue = new LinkedList<>();
    HashSet<TreeNode> visited = new HashSet<>();
    queue.add(new Data(target, 0));
    List<Integer> list = new ArrayList<>();
    while (!queue.isEmpty()) {
      Data current = queue.poll();
      if (current.dist == K) {
        list.add(current.node.val);
      }
      if (!visited.contains(current.node) && current.dist < K) {
        visited.add(current.node);
        TreeNode parent = map.get(current.node);
        if (parent != null && !visited.contains(parent)) {
          queue.add(new Data(parent, current.dist + 1));
        }
        if (current.node.left != null && !visited.contains(current.node.left)) {
          queue.add(new Data(current.node.left, current.dist + 1));
        }
        if (current.node.right != null && !visited.contains(current.node.right)) {
          queue.add(new Data(current.node.right, current.dist + 1));
        }
      }
    }
    return list;
  }

  private void dfs(TreeNode root, TreeNode parent, HashMap<TreeNode, TreeNode> map) {
    if (root == null) {
      return;
    }
    map.put(root, parent);
    dfs(root.left, root, map);
    dfs(root.right, root, map);
  }

  public static void main(String args[]) {
    KDistNodes kDistNodes = new KDistNodes();

    TreeNode treeNode = new Codec().deserialize("3,5,1,6,2,0,8,null,null,7,4");

    TreeNode target = treeNode.left;

    System.out.println(kDistNodes.distanceK(treeNode, target, 2));
  }

}
