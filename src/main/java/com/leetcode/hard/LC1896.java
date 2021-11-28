/*
package com.leetcode.hard;

import java.util.Arrays;

public class LC1896 {



    public int minOperationsToFlip(String expression) {

    }

    private int solve(char[] arr, int start, int end, int idx, int curr, int prevSymbol, int unChanged) {
        if (idx >= end) {
            return curr != unChanged ? 0 : Integer.MAX_VALUE / 2;
        }
        char currChar = arr[idx];
        int best = Integer.MAX_VALUE / 2;
        if (currChar == '0' || currChar == '1') {
            if (prevSymbol == 1) {
                int changedVal = curr & (currChar == '0' ? 1 : 0);
                int unchanged = curr & (currChar == '0' ? 0 : 1);
                best = Math.min(best, 1 + solve(arr, start, end, idx + 1, changedVal, 2,
                        unChanged & (currChar == '0' ? 0 : 1)));
                best = Math.min(best, solve(arr, start, end, idx + 1, unchanged, 2,
                        unChanged & (currChar == '0' ? 0 : 1)));
            } else {
                int changedVal = curr | (currChar == '0' ? 1 : 0);
                int unchanged = curr | (currChar == '0' ? 0 : 1);
                best = Math.min(best, 1 + solve(arr, start, end, idx + 1, changedVal, 2,
                        unChanged | (currChar == '0' ? 0 : 1)));
                best = Math.min(best, solve(arr, start, end, idx + 1, unchanged, 2,
                        unChanged | (currChar == '0' ? 0 : 1)));
            }
            //don'change
        } else {
            best = Math.min(best, solve(arr, start, end, idx + 1, curr, currChar == '&' ? 1 : 0, unChanged));
            best = Math.min(best, solve(arr, start, end, idx + 1, curr, currChar == '&' ? 0 : 1, unChanged));
        }
        return best;
    }
}
*/
