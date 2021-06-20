package com.geeksforgeeks;

public class NQueenSolution {

  public void solveBoard(char[][] board, int row, int rowmask, int ldmask, int rdmask) {
    int n = board.length;
    int all_rows_filled = (1 << n) - 1;
    if (rowmask == all_rows_filled) {

    }
    int safe = all_rows_filled & (~(rowmask | ldmask | rdmask));
    while (safe > 0) {
      int p = safe & (-safe);
      int col = (int) (Math.log(p) / Math.log(2));
      board[row][col] = 'Q';
      solveBoard(board, row + 1, rowmask | p, (ldmask | p) << 1, (rdmask | p) >> 1);
      safe = safe & (safe - 1);
      board[row][col] = ' ';
    }
  }


  // Driver Code
  public static void main(String args[]) {

    // Board size
    int n = 9;

    char board[][] = new char[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        board[i][j] = ' ';
      }
    }

    int rowmask = 0, ldmask = 0, rdmask = 0;
    int row = 0;

    NQueenSolution solution = new
        NQueenSolution();

    // Function Call
    solution.solveBoard(board, row, rowmask,
        ldmask, rdmask);
    System.out.println();
  }
}
 