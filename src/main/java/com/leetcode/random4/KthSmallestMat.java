package com.leetcode.random4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

public class KthSmallestMat {

  class Data implements Comparable<Data> {

    int sum;
    int[] arr;
    String state;

    public Data(int sum, int[] arr, String state) {
      this.sum = sum;
      this.arr = arr;
      this.state = state;
    }

    @Override
    public int compareTo(Data o) {
      return Integer.compare(sum, o.sum);
    }
  }

  public int kthSmallest(int[][] mat, int k) {
    PriorityQueue<Data> p = new PriorityQueue<>();
    int sum = 0;
    int[] arr = new int[mat.length];
    Arrays.fill(arr, 0);
    for (int row = 0; row < mat.length; row++) {
      sum += mat[row][0];
    }
    HashSet<String> set = new HashSet<>();
    p.add(new Data(sum, arr, Arrays.toString(arr)));
    int ans = 0;
    while (k-- > 0 && !p.isEmpty()) {
      Data curr = p.poll();
      set.add(curr.state);
      ans = curr.sum;
      for (int i = 0; i < curr.arr.length; i++) {
        int prev = mat[i][curr.arr[i]];
        if (curr.arr[i] + 1 < mat[i].length) {
          int next = mat[i][curr.arr[i] + 1];
          int nextSum = curr.sum - prev + next;
          curr.arr[i]++;
          int[] nextArr = Arrays.copyOf(curr.arr, curr.arr.length);
          String nextState = Arrays.toString(nextArr);
          if (!set.contains(nextState)) {
            set.add(nextState);
            p.add(new Data(nextSum, Arrays.copyOf(curr.arr, curr.arr.length), nextState));
          }
          curr.arr[i]--;
        }
      }
    }
    return ans;
  }

  public static void main(String args[]) {
    KthSmallestMat k = new KthSmallestMat();
    int[][] arr = new int[][]{
        {1, 1, 10}, {2, 2, 9}
    };
    System.out.println(k.kthSmallest(arr, 7));
  }


}
