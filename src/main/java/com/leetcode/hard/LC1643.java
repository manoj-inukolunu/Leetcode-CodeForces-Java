package com.leetcode.hard;

public class LC1643 {

    private long[][] pascalsTriangle() {
        long[][] pascal = new long[35][35];
        pascal[0][0] = 1;
        for (int i = 1; i < pascal.length; i++) {
            pascal[i][0] = 1;
            for (int j = 1; j < pascal[i].length; j++) {
                pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j];
            }
        }
        return pascal;
    }

    public String kthSmallestPath(int[] destination, int k) {
        int totalV = destination[0];
        int totalH = destination[1];
        long[][] pascal = pascalsTriangle();
        int len = totalH + totalV;
        int currH = 0, currV = 0;
        int currLen = 0;
        StringBuilder b = new StringBuilder();
        while (currLen < len) {
            if (totalH - currH <= 0) {
                b.append("V");
                currV++;
            } else {
                long startWithH = pascal[len - (currH + currV) - 1][totalH - currH - 1];
                if (k > startWithH) {
                    b.append("V");
                    k -= startWithH;
                    currV++;
                } else {
                    b.append("H");
                    currH++;
                }
            }
            currLen++;
        }
        return b.toString();
    }

    public static void main(String[] args) {
        LC1643 l = new LC1643();
        System.out.println(l.kthSmallestPath(new int[]{2, 3}, 1));
    }
}






