package com.leetcode.hard;

import java.util.Arrays;

public class LC1728 {
    int fR = -1, fC = -1;

    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        char[][] arr = new char[grid.length][];
        int catRow = -1, catCol = -1, mouseRow = -1, mouseCol = -1;
        for (int i = 0; i < grid.length; i++) {
            arr[i] = new char[grid[i].length()];
            for (int j = 0; j < grid[i].length(); j++) {
                arr[i][j] = grid[i].charAt(j);
                if (arr[i][j] == 'C') {
                    catRow = i;
                    catCol = j;
                }
                if (arr[i][j] == 'M') {
                    mouseRow = i;
                    mouseCol = j;
                }
                if (arr[i][j] == 'F') {
                    fR = i;
                    fC = j;
                }
            }
        }
        int[][][][][][] dp = new int[arr.length + 1][arr[0].length + 1][arr.length + 1][arr[0].length + 1][2][1001];
        for (int[][][][][] d : dp) {
            for (int[][][][] d1 : d) {
                for (int[][][] d2 : d1) {
                    for (int[][] d3 : d2) {
                        for (int[] row : d3) {
                            Arrays.fill(row, -1);
                        }
                    }
                }
            }
        }
        return winner(arr, catRow, catCol, mouseRow, mouseCol, 0, 0, catJump, mouseJump, dp) == 0;
    }

    boolean inside(int row, int col, char[][] arr) {
        return row >= 0 && col >= 0 && row < arr.length && col < arr[row].length;
    }

    boolean isWall(int row, int col, char[][] arr) {
        return arr[row][col] == '#';
    }

    //turn==0 mouse
    private int winner(char[][] arr, int cR, int cC, int mR, int mC, int turn, int turns, int catJump,
                       int mouseJump, int[][][][][][] dp) {
        if (cR == mR && cC == mC) {
            return 1;
        }
        if (cR == fR && cC == fC) {
            return 1;
        }
        if (mR == fR && mC == fC) {
            return 0;
        }
        if (turns >= 1000) {
            return 1;
        }
        if (dp[cR][cC][mR][mC][turn][turns] != -1) {
            return dp[cR][cC][mR][mC][turn][turns];
        }
        if (turn == 0) {
            //stay
            int stay = winner(arr, cR, cC, mR, mC, 1, turns + 1, catJump, mouseJump, dp);
            if (stay == 0) {
                return dp[cR][cC][mR][mC][turn][turns] = 0;
            }
            int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            for (int[] dir : dirs) {
                int nextR = mR;
                int nextC = mC;
                for (int jump = 1; jump <= mouseJump; jump++) {
                    nextR += dir[0];
                    nextC += dir[1];
                    if (inside(nextR, nextC, arr) && isWall(nextR, nextC, arr)) {
                        break;
                    }
                    if (inside(nextR, nextC, arr)) {
                        int curr = winner(arr, cR, cC, nextR, nextC, 1, turns + 1, catJump, mouseJump, dp);
                        if (curr == 0) {
                            return dp[cR][cC][mR][mC][turn][turns] = 0;
                        }
                    }
                }
            }
            return dp[cR][cC][mR][mC][turn][turns] = 1;
        } else {
            int stay = winner(arr, cR, cC, mR, mC, 0, turns + 1, catJump, mouseJump, dp);
            if (stay == 1) {
                return dp[cR][cC][mR][mC][turn][turns] = 1;
            }
            int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            for (int[] dir : dirs) {
                int nextR = cR;
                int nextC = cC;
                for (int jump = 1; jump <= catJump; jump++) {
                    nextR += dir[0];
                    nextC += dir[1];
                    if (inside(nextR, nextC, arr) && isWall(nextR, nextC, arr)) {
                        break;
                    }
                    if (inside(nextR, nextC, arr)) {
                        int curr = winner(arr, nextR, nextC, mR, mC, 0, turns + 1, catJump, mouseJump, dp);
                        if (curr == 1) {
                            return dp[cR][cC][mR][mC][turn][turns] = 1;
                        }
                    }
                }
            }
            return dp[cR][cC][mR][mC][turn][turns] = 0;
        }
    }

    public static void main(String[] args) {
        String[] grid = new String[]{
                "####.##",
                ".#C#F#.",
                "######.",
                "##M.###"
        };
        LC1728 l = new LC1728();
        System.out.println(l.canMouseWin(grid, 3, 6));
    }
}
