package com.leetcode.dfs.easy;

import com.google.common.collect.Lists;
import com.leetcode.dfs.Codec;
import com.leetcode.dfs.TreeNode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author manoji on 2020-01-06.
 */
public class Solution {

  public boolean isBalanced(TreeNode root) {
    if (root == null) {
      return true;
    }
    Stack<TreeNode> stack = new Stack();
    HashSet<TreeNode> visited = new HashSet<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      TreeNode current = stack.pop();
      if (current != null) {
        if (Math.abs(height(current.left) - height(current.right)) > 1) {
          return false;
        }
        if (!visited.contains(current)) {
          visited.add(current);
          stack.push(current.left);
          stack.push(current.right);
        }
      }
    }
    return true;
  }

  private int height(TreeNode root) {
    if (root == null) {
      return 0;
    }
    if (root.left == null && root.right == null) {
      return 1;
    }
    return Math.max(1 + height(root.left), 1 + height(root.right));
  }


  public boolean isSymmetric(TreeNode root) {
    for (int i = 0; i <= height(root); i++) {
      ArrayList<Integer> buffer = new ArrayList();
      traverseLevelOrder(root, buffer, i);
      if (!isPalindrome(buffer)) {
        return false;
      }
    }
    return true;
  }

  private boolean isPalindrome(ArrayList<Integer> buffer) {
    int bufferSize = (int) Math.floor(buffer.size() / 2);
    for (int i = 0; i < bufferSize; i++) {
      if (buffer.get(i) != buffer.get(buffer.size() - 1 - i)) {
        return false;
      }
    }
    return true;
  }

  public void traverseLevelOrder(TreeNode node, ArrayList<Integer> buffer, int level) {
    if (node == null) {
      buffer.add(null);
      return;
    }
    if (level == 1) {
      buffer.add(node.val);
    } else if (level > 1) {
      traverseLevelOrder(node.left, buffer, level - 1);
      traverseLevelOrder(node.right, buffer, level - 1);
    }

  }

  class Pair {

    int row;
    int column;

    Pair(int row, int column) {
      this.row = row;
      this.column = column;
    }
  }


  public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
    int nColumns = image[0].length;
    int nRows = image.length;
    int color = image[sr][sc];
    boolean[][] visited = new boolean[nRows][nColumns];
    Stack<Pair> stack = new Stack<>();
    stack.push(new Pair(sr, sc));

    while (!stack.isEmpty()) {
      Pair current = stack.pop();
      if (!visited[current.row][current.column]) {
        visited[current.row][current.column] = true;
        image[current.row][current.column] = newColor;
        for (Pair pair : reachablePairs(current, nRows, nColumns, color, image)) {
          stack.push(pair);
        }
      }
    }
    return image;
  }

  private List<Pair> reachablePairs(Pair current, int nRows, int nColumns, int color, int[][] image) {
    ArrayList<Pair> pairs = new ArrayList<>();
    int currentRow = current.row;
    int currentColumn = current.column;

    if (currentRow - 1 >= 0 && image[currentRow - 1][currentColumn] == color) {
      pairs.add(new Pair(currentRow - 1, currentColumn));
    }
    if (currentRow + 1 < nRows && image[currentRow + 1][currentColumn] == color) {
      pairs.add(new Pair(currentRow + 1, currentColumn));
    }
    if (currentColumn - 1 >= 0 && image[currentRow][currentColumn - 1] == color) {
      pairs.add(new Pair(currentRow, currentColumn - 1));
    }
    if (currentColumn + 1 < nColumns && image[currentRow][currentColumn + 1] == color) {
      pairs.add(new Pair(currentRow, currentColumn + 1));
    }
    return pairs;
  }


  public static void main(String arg[]) {
    Solution solution = new Solution();
    Codec codec = new Codec();
    TreeNode node = codec.deserialize("1,2");
    int[][] image = new int[][]{
        {1, 1, 1}, {1, 1, 0}, {1, 0, 1}
    };
    int[][] finalImage = solution.floodFill(image, 1, 1, 2);

    for (int[] row : finalImage) {
      System.out.println(Arrays.toString(row));
    }

  }

}
