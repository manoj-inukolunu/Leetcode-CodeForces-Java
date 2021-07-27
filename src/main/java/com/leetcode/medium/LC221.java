package com.leetcode.medium;

public class LC221 {

    public int maximalSquare(char[][] matrix) {


        int ans = 0, rows = matrix.length, cols = matrix[0].length;
        int[][] arr = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                arr[i][j] = Character.getNumericValue(matrix[i][j]);
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (arr[i][j] == 1) {
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        arr[i][j] = 1 + Math.min(arr[i - 1][j], Math.min(arr[i][j - 1], arr[i - 1][j - 1]));
                    }
                    ans = Math.max(ans, arr[i][j]);
                }
            }
        }


        return ans * ans;
    }


}
