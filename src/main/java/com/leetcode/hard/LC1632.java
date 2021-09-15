package com.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LC1632 {

    class Data {
        int row;
        int col;
        int num;

        public Data(int row, int col, int num) {
            this.row = row;
            this.col = col;
            this.num = num;
        }
    }

    public int[][] matrixRankTransform(int[][] matrix) {
        int[] maxR = new int[matrix.length];
        int[] maxC = new int[matrix[0].length];
        List<Data> list = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                list.add(new Data(i, j, matrix[i][j]));
            }
        }
        list.sort(Comparator.comparingInt(o -> o.num));
        int[][] rank = new int[matrix.length][matrix[0].length];
        for (int[] row : rank) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        for (int i = 0; i < list.size(); i++) {
            Data curr = list.get(i);
            if (rank[curr.row][curr.col] == Integer.MIN_VALUE) {

            }
        }
        return null;
    }
}
