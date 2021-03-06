package com.leetcode.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Utils {

  public static int[][] convertToTwoDIntArray(String data) {
    List<List<Integer>> list = convertToTwoDIntList(data);
    int[][] ret = new int[list.size()][];
    for (int i = 0; i < list.size(); i++) {
      ret[i] = list.get(i).stream().mapToInt(j -> j).toArray();
    }
    return ret;
  }

  public static List<List<Integer>> convertToTwoDIntList(String data) {
    List<List<Integer>> list = new ArrayList<>();
    List<Integer> tempList = null;
    StringBuilder buffer = new StringBuilder();
    for (char ch : data.toCharArray()) {
      if (ch == '[') {
        tempList = new ArrayList<>();
        buffer.setLength(0);
      } else if (ch == ']') {
        if (buffer.length() != 0 && tempList != null) {
          tempList.add(Integer.parseInt(buffer.toString()));
        }
        if (tempList != null && !tempList.isEmpty()) {
          list.add(new ArrayList<>(tempList));
        }
        buffer.setLength(0);
        Objects.requireNonNull(tempList).clear();
      } else if (Character.isDigit(ch) || ch == '-') {
        buffer.append(ch);
      } else {
        if (buffer.length() != 0 && tempList != null) {
          tempList.add(Integer.parseInt(buffer.toString()));
        }
        buffer.setLength(0);
      }
    }
    return list;
  }


  public static List<List<String>> convertTo2DList(String data) {
    data = data.replaceAll(" ", "");
    List<List<String>> list = new ArrayList<>();
    List<String> tempList = new ArrayList<>();
    StringBuilder buffer = new StringBuilder();
    for (char ch : data.toCharArray()) {
      if (ch == '"' && buffer.length() == 0) {
        buffer.append(ch);
      } else if (ch == '"') {
        buffer.deleteCharAt(0);
        tempList.add(buffer.toString());
        buffer.setLength(0);
      } else if (ch == '[') {
        tempList.clear();
        buffer.setLength(0);
      } else if (ch == ']') {
        list.add(new ArrayList<>(tempList));
        tempList.clear();
      } else if (ch != ',') {
        buffer.append(ch);
      }
    }
    return list;
  }

  public static void main(String args[]) {
        /*int[][] data = Utils.convertToTwoDIntArray("[[1,10],[10,1],[10,1],[1,10],[5,1]]");
        for (int[] row : data) {
            System.out.println(Arrays.toString(row));
        }*/
/*
    List<List<String>> list = Utils.convertTo2DList("[\"abc\"],[\"a\",\"x\"],[\"a\",\"x\",\"y\"],[\"a\",\"z\"]," +
        "[\"b\"],[\"b\",\"x\"],[\"b\",\"x\",\"y\"],[\"b\",\"z\"]");
    for (List<String> l : list) {
      System.out.println(l);
    }*/

    /*System.out.println(Arrays.deepToString(
        Utils.convertToTwoDIntArray("[[1,1,1,0,0],[1,1,1,0,0],[1,1,1,0,0],[0,0,0,0,1],[0,0,0,1,1]]")));*/
    System.out.println("100100");
    int val = Integer.parseInt("100100", 2);
    val &= ~(1 << 2);
    System.out.println(Integer.toBinaryString(val));
  }

}

