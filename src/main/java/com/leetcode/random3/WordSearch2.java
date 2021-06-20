package com.leetcode.random3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearch2 {

  Set<String> ans = new HashSet<>();

  class Trie {

    boolean leaf;
    HashMap<Character, Trie> map = new HashMap<>();
    Set<String> word = new HashSet<>();

    void insert(String str, int idx) {
      if (idx == str.length() - 1) {
        leaf = true;
        word.add(str);
        if (!map.containsKey(str.charAt(idx))) {
          map.put(str.charAt(idx), new Trie());
        }
        return;
      }
      char ch = str.charAt(idx);
      Trie t = map.get(ch);
      if (t == null) {
        t = new Trie();
        map.put(ch, t);
      }
      map.get(ch).insert(str, idx + 1);
    }

    private boolean inside(int row, int col, char[][] board) {
      return row >= 0 && col >= 0 && row < board.length && col < board[row].length;
    }

    private void search(int row, int col, char[][] board, boolean[][] visited, StringBuffer buffer) {
      buffer.append(board[row][col]);
      if (visited[row][col]) {
        buffer.deleteCharAt(buffer.length() - 1);
        return;
      }

      if (leaf && map.containsKey(board[row][col]) && word.contains(buffer.toString())) {
        ans.add(buffer.toString());
      }
      visited[row][col] = true;
      if (inside(row, col, board)) {
        char ch = board[row][col];
        if (map.containsKey(ch)) {
          int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
          for (int[] dir : dirs) {
            int nextR = row + dir[0];
            int nextC = col + dir[1];
            if (inside(nextR, nextC, board)) {
              map.get(ch).search(nextR, nextC, board, visited, buffer);
            }
          }
        }
      }
      visited[row][col] = false;
      buffer.deleteCharAt(buffer.length() - 1);
    }
  }

  public List<String> findWords(char[][] board, String[] words) {
    Trie trie = new Trie();
    for (String str : words) {
      trie.insert(str, 0);
    }
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        trie.search(i, j, board, new boolean[board.length][board[i].length], new StringBuffer());
      }
    }
    return new ArrayList<>(ans);
  }

  public static void main(String args[]) {
    char[][] board = new char[][]{
        {'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}
    };
    char[][] board2 = new char[][]{{'a'}};
    WordSearch2 w = new WordSearch2();
    System.out.println(w.findWords(board, new String[]{"oath", "pea", "eat", "rain", "hklf", "hf"}));
  }
}
