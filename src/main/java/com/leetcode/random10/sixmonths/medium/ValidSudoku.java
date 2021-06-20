package com.leetcode.random10.sixmonths.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {

  public boolean check(int key, char ch, HashMap<Integer, Set<Character>> map) {
    Set<Character> set = map.getOrDefault(key, new HashSet<>());
    if (set.contains(ch)) {
      return false;
    } else {
      set.add(ch);
      map.put(key, set);
    }
    return true;
  }

  public boolean isValidSudoku(char[][] board) {

    HashMap<Integer, Set<Character>> rowMap = new HashMap<>();
    HashMap<Integer, Set<Character>> colMap = new HashMap<>();
    HashMap<Integer, Set<Character>> boxMap = new HashMap<>();

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == '.') {
          continue;
        }
        if (!(Character.isDigit(board[i][j]) && check(i, board[i][j], rowMap) && check(j, board[i][j], colMap) && check(getBoxNum(i, j), board[i][j],
            boxMap))) {
          return false;
        }
      }
    }
    return true;
  }

  public int getBoxNum(int row, int col) {
    return (((row) / 3) * 3) + ((col) / 3);
  }

  public static void main(String args[]) {
    ValidSudoku v = new ValidSudoku();
    char[][] board = new char[][]{
        {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    };
    System.out.println(v.getBoxNum(3, 6));
    System.out.println(v.isValidSudoku(board));
  }


}
