package com.leetcode.dfs.medium;

import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode right; TreeNode(int x) { val = x; } }
 */
public class KDistance {

  public class Result {

    boolean found;
    int depth;

    public Result(boolean found, int depth) {
      this.found = found;
      this.depth = depth;
    }
  }

  List<Integer> nodes = new ArrayList();

  private void buildMap(TreeNode root, HashMap<TreeNode, TreeNode> nodeHashMap) {
    if (root == null) {
      return;
    }
    if (root.left != null) {
      nodeHashMap.put(root.left, root);
    }
    if (root.right != null) {
      nodeHashMap.put(root.right, root);
    }
    buildMap(root.left, nodeHashMap);
    buildMap(root.right, nodeHashMap);
  }


  public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {

    HashMap<TreeNode, TreeNode> hashMap = new HashMap<>();
    buildMap(root, hashMap);

    Queue<TreeNode> queue = new LinkedList<TreeNode>();

    Set<TreeNode> visited = new HashSet<>();

    queue.add(target);
    int level = 0;

    while (!queue.isEmpty()) {
      System.out.println(queue);
      TreeNode node = queue.poll();
      if (!visited.contains(node)) {
        visited.add(node);
        if (node.left != null) {
          queue.add(node.left);
        }
        if (node.right != null) {
          queue.add(node.right);
        }
        if (hashMap.get(node) != null) {
          queue.add(hashMap.get(node));
        }

      }
      level++;
    }

    return nodes;
  }

  private Result findDepth(TreeNode root, TreeNode target, int depth, int k) {
    if (root == null) {
      return new Result(false, depth);
    }
    if (root.val == target.val) {
      return new Result(true, depth);
    }
    Result resultLeft = findDepth(root.left, target, depth + 1, k);
    if (resultLeft.found) {
      if (resultLeft.depth > k) {
        //add all nodes at depth depth-k to the list
        dfs(root.left, nodes, 1, resultLeft.depth - k);
      } else {
        dfs(root.right, nodes, 1, k - resultLeft.depth);
      }
      return resultLeft;
    } else {
      Result resultRight = findDepth(root.right, target, depth + 1, k);
      if (resultRight.found) {
        if (resultLeft.depth - depth >= k) {
          dfs(root.right, nodes, 1, resultRight.depth - depth - k);
        } else {
          dfs(root.left, nodes, 1, k - resultRight.depth);
        }
      }
      return resultRight;
    }
  }


  private void dfs(TreeNode root, List<Integer> nodes, int depth, int k) {
    if (root == null || depth > k) {
      return;
    }
    if (depth == k) {
      nodes.add(root.val);
    }

    dfs(root.left, nodes, depth + 1, k);
    dfs(root.right, nodes, depth + 1, k);
  }


  public int[] twoSum(int[] nums, int target) {
    boolean found = false;
    int[] res = new int[2];
    for (int i = 0; i < nums.length; i++) {
      if (found) {
        break;
      }
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
          found = true;
          res[0] = i;
          res[1] = j;
          break;
        }
      }
    }
    return res;
  }


  public static void main(String args[]) {
    KDistance kDistance = new KDistance();

    TreeNode root = new Codec().deserialize("3,5,1,6,2,0,8,null,null,7,4");

    System.out.println(kDistance.distanceK(root, root.left, 2));
  }


}