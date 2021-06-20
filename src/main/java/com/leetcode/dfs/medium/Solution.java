package com.leetcode.dfs.medium;

import com.google.common.collect.Sets;
import com.leetcode.dfs.TreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * @author manoji on 2020-01-07.
 */
public class Solution {

  private int numNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return 1 + numNodes(root.left) + numNodes(root.right);
  }

  private int numCoins(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return root.val + numCoins(root.left) + numCoins(root.right);
  }


  private int depth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    if (root.left == null && root.right == null) {
      return 1;
    }
    return Math.max(1 + depth(root.left), 1 + depth(root.right));
  }

  public TreeNode lcaDeepestLeaves(TreeNode root) {
    int depth = depth(root);
    ArrayList<TreeNode> nodes = new ArrayList<>();
    getDepthNodes(root, depth, 1, nodes);
    int minDepth = Integer.MIN_VALUE;
    TreeNode minDepthNode = nodes.get(0);
    for (int i = 0; i < nodes.size(); i++) {
      for (int j = i + 1; j < nodes.size(); j++) {
        TreeNode node = findLca(root, nodes.get(i), nodes.get(j));
        int currentDepth = depth(node);
        if (currentDepth > minDepth) {
          minDepth = currentDepth;
          minDepthNode = node;
        }
      }
    }
    return minDepthNode;
  }


  private TreeNode findLca(TreeNode root, TreeNode node1, TreeNode node2) {
    if (root == null) {
      return null;
    }

    if (node1.val == root.val || node2.val == root.val) {
      return root;
    }

    TreeNode left = findLca(root.left, node1, node2);
    TreeNode right = findLca(root.right, node1, node2);

    if (left != null && right != null) {
      return root;
    }

    return (left != null) ? left : right;

  }

  private void getDepthNodes(TreeNode root, int depth, int currentDpeth, ArrayList<TreeNode> nodes) {
    if (root == null) {
      return;
    }
    if (root.left == null && root.right == null && currentDpeth == depth) {
      nodes.add(root);
      return;
    }
    getDepthNodes(root.left, depth, currentDpeth + 1, nodes);
    getDepthNodes(root.right, depth, currentDpeth + 1, nodes);
  }

  public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>> paths = new ArrayList();
    //psr(root, sum, paths, new Stack<Integer>());
    //psri(root, sum);
    return paths;
  }

  private void psri(TreeNode root, int sum) {
    Stack<TreeNode> stack = new Stack();
    Set<TreeNode> visited = Sets.newHashSet();

    stack.push(root);

    while (!stack.isEmpty()) {

    }


  }

  public static List<List<Integer>> lists = new ArrayList<>();

  void printPaths(TreeNode node, int sum) {
    int path[] = new int[1000];
    dfs(node, path, 0, sum);
  }

  void printPathsList(TreeNode node, int sum) {
    addPath(node, new ArrayList<>(), sum);
  }

  void dfs(TreeNode node, int path[], int len, int sum) {
    if (node == null) {
      return;
    }
    path[len] = node.val;
    len++;
    if (node.left == null && node.right == null && sum - node.val == 0) {
      List<Integer> list = new ArrayList<>();
      for (int i = 0; i < len; i++) {
        list.add(path[i]);
      }
      lists.add(list);
    } else {
      dfs(node.left, path, len, sum - node.val);
      dfs(node.right, path, len, sum - node.val);
    }
  }


  public void addPath(TreeNode node, List<Integer> path, int sum) {
    if (node == null) {
      return;
    }
    path.add(node.val);
    if (node.left == null && node.right == null && sum == node.val) {
      lists.add(path);
    } else {
      addPath(node.left, path, sum - node.val);
      addPath(node.right, path, sum - node.val);
    }
  }

  public static TreeNode buildTree1() {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(2);
    return root;
  }


  public static TreeNode buildTree() {

    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(4);
    root.right = new TreeNode(8);

    root.left.left = new TreeNode(11);

    root.right.left = new TreeNode(13);

    root.right.right = new TreeNode(4);

    root.left.left.left = new TreeNode(7);
    root.left.left.right = new TreeNode(2);

    root.right.right.left = new TreeNode(5);
    root.right.right.right = new TreeNode(1);

    return root;

  }


  public boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }

    if (root.left == null && root.right == null) {
      return true;
    }
    return (root.val > root.left.val) && (root.val < root.right.val) && isValidBST(root.left) && isValidBST(root.right);
  }

  public int connectedComponents(int[][] arr) {
    int rows = arr.length;
    int columns = arr[0].length;
    boolean visited[] = new boolean[rows * columns];
    int connectedComponents = 0;
    for (int i = 0; i < arr.length; i++) {
      if (!visited[i]) {
        dfs(i, arr, visited);
        connectedComponents++;
      }
    }
    return connectedComponents;

  }


  private void dfs(int currentNode, int[][] arr, boolean[] visited) {
    if (!visited[currentNode]) {
      visited[currentNode] = true;
      System.out.print(currentNode + 1 + " ");
      for (int j = 0; j < arr[currentNode].length; j++) {
        if (arr[currentNode][j] == 1 && !visited[j]) {
          dfs(j, arr, visited); // Visit node
        }
      }
    }
  }


  public static void main(String args[]) {
    Solution solution = new Solution();

    int[][] arr = new int[][]{
        {1, 1, 0, 0, 0},
        {1, 1, 0, 0, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 0, 1, 1}
    };
    System.out.println(solution.connectedComponents(arr));
  }


}
