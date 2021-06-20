package com.binarysearch;

public class Review {

  public int solve(int[][] reviews, int threshold) {
    int total5Star = 0, total = 0;
    for (int[] review : reviews) {
      total5Star += review[0];
      total += review[1];
    }
    int ret = (int) Math.ceil((double) (total * threshold - 100 * total5Star) / (100 - threshold));
    return ret;
  }

  public static void main(String args[]) {
    int[][] reviews = new int[][]{
        {1, 1}
    };

    Review r = new Review();
    System.out.println(r.solve(reviews, 100));
  }


}
