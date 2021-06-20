package com.leetcode.array;

/**
 * @author manoji on 3/1/20.
 */
public class MagicGrid {

  public int numMagicSquaresInside(int[][] grid) {
    int count = 0;
    for (int i = 0; i < grid.length && i + 2 < grid.length; i++) {
      for (int j = 0; j < grid[i].length && j + 2 < grid[i].length; j++) {
        count += getNumGrid(i, i + 2, j, j + 2, grid) ? 1 : 0;
      }
    }
    return count;
  }

  private boolean getNumGrid(int rowBegin, int rowEnd, int columnBegin, int columnEnd, int[][] grid) {
    int count = 0, prevSum = 0;
    for (int j = rowBegin; j <= rowEnd; j++) {
      int currentSum = 0;
      for (int i = columnBegin; i <= columnEnd; i++) {
        currentSum += grid[j][i];
      }
      if (currentSum != prevSum && count != 0) {
        return false;
      }
      count++;
      prevSum = currentSum;
    }

    count = 0;
    prevSum = 0;
    for (int j = columnBegin; j <= columnEnd; j++) {
      int currentSum = 0;
      for (int i = rowBegin; i <= rowEnd; i++) {
        currentSum += grid[j][i];
      }
      if (currentSum != prevSum && count != 0) {
        return false;
      }
      count++;
      prevSum = currentSum;
    }

    if (grid[rowBegin][columnBegin] + grid[rowBegin + 1][columnBegin + 1] + grid[rowBegin + 2][columnBegin + 2] != grid[rowBegin][columnEnd] + grid[
        rowBegin + 1][columnEnd - 1] + grid[rowBegin + 2][columnEnd - 2]) {
      return false;
    }
    return true;
  }


  public int dominantIndex(int[] nums) {
    int max = Integer.MIN_VALUE;
    int maxIndex = -1;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > max) {
        max = nums[i];
        maxIndex = i;
      }
    }

//		System.out.println(maxIndex);
    for (int i = 0; i < nums.length; i++) {
      if (i != maxIndex) {
        if (max >= 2 * nums[i]) {
          continue;
        } else {
          return -1;
        }
      }
    }
    return maxIndex;
  }

  public static void main(String args[]) {
    int grid[][] = new int[][]{
        {4, 3, 8, 4},
        {9, 5, 1, 9},
        {2, 7, 6, 2}
    };
    MagicGrid magicGrid = new MagicGrid();

//		System.out.println(magicGrid.numMagicSquaresInside(grid));

    System.out.println(magicGrid.dominantIndex(new int[]{0, 0, 0, 1}));
  }
}
