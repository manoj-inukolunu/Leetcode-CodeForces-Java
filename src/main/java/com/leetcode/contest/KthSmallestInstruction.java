package com.leetcode.contest;

import java.util.TreeSet;

public class KthSmallestInstruction {

  public String kthSmallestPath(int[] destination, int k) {
    TreeSet<String>[][] arr = new TreeSet[15][15];
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        arr[i][j] = new TreeSet<>();
      }
    }
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        TreeSet<String> set = new TreeSet<>();
        if (j - 1 >= 0 && i - 1 >= 0) {
          for (String str : arr[i][j - 1]) {
            set.add(str + 'H');
          }
          for (String str : arr[i - 1][j]) {
            set.add(str + 'V');
          }
        } else if (j - 1 >= 0 && i - 1 < 0) {
          for (String str : arr[i][j - 1]) {
            set.add(str + 'H');
          }
        } else if (j - 1 < 0 && i - 1 >= 0) {
          for (String str : arr[i - 1][j]) {
            set.add(str + 'V');
          }
        }
        TreeSet<String> setA = new TreeSet<>();
        int count = 1;
        for (String str : set) {
          if (count > k) {
            break;
          }
          count++;
          setA.add(str.trim());
        }
        if (setA.isEmpty()) {
          setA.add(" ");
        }
        arr[i][j] = setA;
        if (i == destination[0] && j == destination[1]) {
          break;
        }
      }
    }
    int count = 1;
    for (String str : arr[destination[0]][destination[1]]) {
      if (count == k) {
        return str;
      }
      count++;
    }
    return "";
  }

  public static void main(String args[]) {
    KthSmallestInstruction k = new KthSmallestInstruction();
    System.out.println(k.kthSmallestPath(new int[]{15, 15}, 155117520));
  }

}
