package com.leetcode.hard;

public class LC1444 {


    public int ways(String[] pizza, int k) {
        int[][] pre = new int[pizza.length + 1][pizza[0].length() + 1];
        for (int r = 0; r < pizza.length; r++) {
            char[] arr = pizza[r].toCharArray();
            for (int c = 0; c < arr.length; c++) {
                int toAdd = (pizza[r].charAt(c) == 'A' ? 1 : 0);
                pre[r + 1][c + 1] = pre[r + 1][c] + pre[r][c + 1] + toAdd - pre[r][c];
            }
        }
        Integer[][][][] dp = new Integer[pizza.length + 1][pizza[0].length() + 1][k][k];
        return count(pre, 0, 0, 0, 0, k, dp, pizza.length, pizza[0].length());
    }

    private int count(int[][] pre, int startRow, int startCol, int pieces, int cuts, int k, Integer[][][][] dp,
                      int rows, int cols) {
        if (cuts < 0 || pieces > k) {
            return 0;
        }
        if (cuts == k - 1) {
            return (pieces == k - 1 && (hasApple(pre, startRow, startCol, rows - 1, cols - 1))) ? 1 : 0;
        }
        if (dp[startRow][startCol][pieces][cuts] != null) {
            return dp[startRow][startCol][pieces][cuts];
        }
        int ret = 0;
        for (int i = startRow + 1; i < pre.length; i++) {
            if (hasApple(pre, startRow, startCol, i - 1, cols - 1)) {
                ret += count(pre, i, startCol, pieces + 1, cuts + 1, k, dp, rows, cols);
            }
        }
        for (int i = startCol + 1; i < pre[startRow].length; i++) {
            if (hasApple(pre, startRow, startCol, rows - 1, i - 1)) {
                ret += count(pre, startRow, i, pieces + 1, cuts + 1, k, dp, rows, cols);
            }
        }
        return dp[startRow][startCol][pieces][cuts] = ret;
    }


    private boolean hasApple(int[][] pre, int row1, int col1, int row2, int col2) {
        return pre[row2 + 1][col2 + 1] - pre[row1][col2 + 1] - pre[row2 + 1][col1] + pre[row1][col1] >= 1;
    }

    public static void main(String[] args) {
        String[] pizza = new String[]{".A", "AA", "A."};
        int k = 3;
        LC1444 l = new LC1444();
        System.out.println(l.ways(pizza, k));
    }

}
