package com.leetcode.hard;

public class LC1349 {
    int maxCol;
    int maxRow;

    public int maxStudents(char[][] seats) {
        maxCol = seats[0].length - 1;
        maxRow = seats.length - 1;
        StringBuilder state = new StringBuilder();
        for (int i = 0; i <= maxCol + 2; i++) {
            state.append("0");
        }
        return solve(seats, state.toString(), 0, 0);
    }

    private int solve(char[][] seats, String prevState, int row, int col) {
        if (row >= seats.length) {
            return 0;
        }
        int best = 0;
        if (isTopLeftFree(prevState, row, col) && isTopRightFree(prevState, row, col) && isLeftFree(prevState) && seats[row][col] == '.') {
            best = 1 + solve(seats, prevState.substring(1) + "1", col == maxCol ? row + 1 : row,
                    col == maxCol ? 0 : col + 1);
        }
        best = Math.max(best, solve(seats, prevState.substring(1) + "0", col == maxCol ? row + 1 : row,
                col == maxCol ? 0 : col + 1));
        return best;
    }

    boolean isTopLeftFree(String prevState, int currRow, int currCol) {
        if (currRow == 0 || currCol == 0) {
            return false;
        } else {
            return prevState.charAt(currCol - 1) == '0';
        }
    }

    boolean isTopRightFree(String prevState, int currRow, int currCol) {
        if (currRow == 0 || currCol == maxCol) {
            return false;
        } else {
            return prevState.charAt(currCol + 1) == '0';
        }
    }

    boolean isLeftFree(String state) {
        return state.charAt(state.length() - 1) == '0';
    }

    public static void main(String[] args) {

    }
}
