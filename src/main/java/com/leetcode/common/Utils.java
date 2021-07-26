package com.leetcode.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Utils {

    public static int[][] convertToTwoDIntArray(String data) {
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
            } else if (Character.isDigit(ch)) {
                buffer.append(ch);
            } else {
                if (buffer.length() != 0 && tempList != null) {
                    tempList.add(Integer.parseInt(buffer.toString()));
                }
                buffer.setLength(0);
            }
        }

        int[][] ret = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i).stream().mapToInt(j -> j).toArray();
        }
        return ret;
    }

    public static void main(String args[]) {
        int[][] data = Utils.convertToTwoDIntArray("[[1,10],[10,1],[10,1],[1,10],[5,1]]");
        for (int[] row : data) {
            System.out.println(Arrays.toString(row));
        }
    }

}
