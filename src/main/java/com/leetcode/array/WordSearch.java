package com.leetcode.array;

import com.leetcode.array.WordSearch.MinStack.Pair;
import java.util.HashSet;
import java.util.Stack;

/**
 * @author manoji on 4/17/20.
 */
public class WordSearch {

  public boolean exist(char[][] board, String word) {
    HashSet<Pair> visited = new HashSet<>();
    for (int j = 0; j < board.length; j++) {
      for (int k = 0; k < board[j].length; k++) {
        visited.clear();
        if (dfs(board, j, k, 0, word, visited)) {
          return true;
        }
      }
    }
    return false;

  }

  private boolean dfs(char[][] board, int row, int col, int index, String word, HashSet<Pair> visited) {
    if (row >= board.length || col >= board[row].length || row < 0 || col < 0) {
      return false;
    }
    if (index == word.length() - 1) {
      return true;
    }
    if (index >= word.length()) {
      return true;
    }
    Pair pair = new Pair(row, col);
    boolean found = false;
    if (!visited.contains(pair)) {
      visited.add(pair);
      if (word.charAt(index) == board[row][col]) {
        if (index == word.length() - 1) {
          return true;
        }
        if (row + 1 < board.length && !visited.contains(new Pair(row + 1, col))) {
          found = dfs(board, row + 1, col, index + 1, word, visited);
          //visited.remove(pair);
        }
        if (row - 1 >= 0 && !visited.contains(new Pair(row - 1, col)) && !found) {
          found = dfs(board, row - 1, col, index + 1, word, visited);
          //visited.remove(pair);
        }
        if (col + 1 < board[row].length && !visited.contains(new Pair(row, col + 1)) && !found) {
          found = dfs(board, row, col + 1, index + 1, word, visited);
          //visited.remove(pair);
        }
        if (col - 1 >= 0 && !visited.contains(new Pair(row, col - 1)) && !found) {
          found = dfs(board, row, col - 1, index + 1, word, visited);
          //visited.remove(pair);
        }
      }
    }
    visited.remove(pair);
    return found;
  }


  static class MinStack {

    static class Pair {

      int elem;
      int min;

      public Pair(int elem, int min) {
        this.elem = elem;
        this.min = min;
      }
    }


    /**
     * initialize your data structure here.
     */

    Stack<Pair> stack = new Stack();

    public MinStack() {

    }

    public void push(int x) {
      Pair p = stack.peek();
      if (x < p.min) {
        Pair pair = new Pair(x, x);
        stack.push(pair);
      } else {
        Pair pair = new Pair(x, p.min);
        stack.push(pair);
      }
    }

    public void pop() {
      stack.pop();
    }

    public int top() {
      return stack.peek().elem;
    }

    public int getMin() {
      return stack.peek().min;
    }
  }

  /**
   * Your MinStack object will be instantiated and called as such: MinStack obj = new MinStack(); obj.push(x); obj.pop(); int param_3 = obj.top(); int
   * param_4 = obj.getMin();
   */

  public static void main(String args[]) {
    WordSearch wordSearch = new WordSearch();
    MinStack minStack = new MinStack();
    minStack.push(-2);
    minStack.push(0);
    minStack.push(-3);
    minStack.getMin();
    minStack.pop();
    minStack.top();
    minStack.getMin();

  }
}
