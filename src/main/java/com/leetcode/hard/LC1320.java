package com.leetcode.hard;

import java.util.HashMap;

public class LC1320 {

    HashMap<Character, int[]> map = new HashMap<>();

    public int minimumDistance(String word) {

        int row = 0, col = 0;
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            map.put(ch, new int[]{row, col});
            col++;
            if (col > 5) {
                col = 0;
                row++;
            }
        }
        Integer[][][][][] dp = new Integer[word.length()][5][6][5][6];
        return solve(word, 0, 4, 2, 4, 2, dp);
    }

    boolean inside(int currRow, int currCol, int row, int col) {
        return currRow < row && currCol < col;
    }

    int getDist(int r1, int c1, int r2, int c2) {
        if (r1 == 4 && c1 == 2) {
            return 0;
        }
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    private int solve(String word, int idx, int f1Row, int f1Col, int f2Row, int f2Col, Integer[][][][][] dp) {
        if (idx >= word.length()) {
            return 0;
        }
        if (dp[idx][f1Row][f1Col][f2Row][f2Col] != null) {
            return dp[idx][f1Row][f1Col][f2Row][f2Col];
        }
        char curr = word.charAt(idx);
        int[] currCoord = map.get(curr);
        //move finger 1
        int best = getDist(f1Row, f1Col, currCoord[0], currCoord[1]) + solve(word, idx + 1, currCoord[0],
                currCoord[1], f2Row, f2Col, dp);
        //move finger 2
        best = Math.min(best, getDist(f2Row, f2Col, currCoord[0], currCoord[1]) + solve(word, idx + 1, f1Row, f1Col,
                currCoord[0], currCoord[1], dp));
        return dp[idx][f1Row][f1Col][f2Row][f2Col] = best;
    }

    public static void main(String[] args) {
        LC1320 l = new LC1320();
        System.out.println(l.minimumDistance("YEAR"));
    }
}
