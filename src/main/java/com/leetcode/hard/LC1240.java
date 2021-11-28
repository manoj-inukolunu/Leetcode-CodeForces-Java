package com.leetcode.hard;

public class LC1240 {
    int ans = Integer.MAX_VALUE;

    public int tilingRectangle(int n, int m) {
        boolean[][] arr = new boolean[n][m];
        solve(arr, 0, 0, 0);
        return ans;
    }

    private void solve(boolean[][] arr, int currRow, int currCol, int totalSquares) {
        int n = arr.length, m = arr[0].length;
        if (totalSquares >= ans) return;
        if (currRow >= n) {
            ans = totalSquares;
            return;
        }
        if (currCol >= m) {
            solve(arr, currRow + 1, 0, totalSquares);
            return;
        }
        if (arr[currRow][currCol]) {
            solve(arr, currRow, currCol + 1, totalSquares);
            return;
        }
        for (int k = Math.min(n - currRow, m - currCol); k >= 1 && isAvailable(arr, currRow, currCol, k); k--) {
            cover(arr, currRow, currCol, k);
            solve(arr, currRow, currCol + 1, totalSquares + 1);
            uncover(arr, currRow, currCol, k);
        }
    }

    private boolean isAvailable(boolean[][] rect, int r, int c, int k) {
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (rect[r + i][c + j]) return false;
            }
        }
        return true;
    }

    private void cover(boolean[][] rect, int r, int c, int k) {
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                rect[r + i][c + j] = true;
            }
        }
    }

    private void uncover(boolean[][] rect, int r, int c, int k) {
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                rect[r + i][c + j] = false;
            }
        }
    }

    public static void main(String[] args) {
        LC1240 l = new LC1240();
        System.out.println(l.tilingRectangle(2, 3));
    }

}
