
package com.leetcode.random;

import java.util.Arrays;
import java.util.HashSet;

public class ValidTicTacToe {

  public boolean validTicTacToe(String[] board) {

    HashSet<String> states = new HashSet<>();
    char[][] st = new char[3][3];
    for (char[] ch : st) {
      Arrays.fill(ch, ' ');
    }
    dfs(st, states, 'X');

    StringBuffer buffer = new StringBuffer();
    for (String str : board) {
      buffer.append(str);
      buffer.append("|");
    }
    buffer.deleteCharAt(buffer.length() - 1);
    return states.contains(buffer.toString());
  }

  private void dfs(char[][] st, HashSet<String> states, char player) {
    String curr = convert(st);
    if (states.contains(curr)) {
      return;
    } else {
      states.add(curr);
    }
    if (isEndState(st)) {
      return;
    }
    char next = player == 'X' ? 'O' : 'X';
    for (int i = 0; i < st.length; i++) {
      for (int j = 0; j < st[i].length; j++) {
        if (st[i][j] == ' ') {
          st[i][j] = player;
          dfs(st, states, next);
          st[i][j] = ' ';
        }
      }
    }
  }

  private boolean isEndState(char[][] st) {
    //horizontal
    for (char[] s : st) {
      if (new String(s).equals("XXX") || new String(s).equals("OOO")) {
        return true;
      }
    }
    //vertical

    for (int i = 0; i < st[0].length; i++) {
      StringBuffer buffer = new StringBuffer();
      for (int j = 0; j < st.length; j++) {
        buffer.append(st[i][j]);
      }
      if (buffer.toString().equals("XXX") || buffer.toString().equals("OOO")) {
        return true;
      }
    }
    return false;
  }

  private String convert(char[][] st) {
    StringBuffer buffer = new StringBuffer();
    for (char[] ch : st) {
      buffer.append(new String(ch));
      buffer.append("|");
    }
    buffer.deleteCharAt(buffer.length() - 1);
    return buffer.toString();
  }

  public static void main(String args[]) {
    ValidTicTacToe v = new ValidTicTacToe();
    System.out.println(v.validTicTacToe(new String[]{"X  ", "O  ", "   "}));
  }


}

