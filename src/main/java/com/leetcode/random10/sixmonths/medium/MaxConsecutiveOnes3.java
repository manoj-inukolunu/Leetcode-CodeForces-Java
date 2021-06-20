package com.leetcode.random10.sixmonths.medium;

public class MaxConsecutiveOnes3 {


  public int longestOnes(int[] A, int K) {
    int start = 0, end = 0, curr = 0, max = Integer.MIN_VALUE, i = 0;
    while (i < A.length) {
      if (A[i] == 1) {
        max = Math.max(end - start + 1, max);
        end++;
      } else if (A[i] == 0) {
        curr++;
        if (curr > K) {
          while (curr > K) {
            if (A[start] == 0) {
              curr--;
            }
            start++;
          }
          end++;
        } else {
          end++;
        }
      }
      i++;
    }
    return max;
  }

  public static void main(String args[]) {
    int arr[] = new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
    MaxConsecutiveOnes3 m = new MaxConsecutiveOnes3();
    System.out.println(m.longestOnes(arr, 3));
  }

}
