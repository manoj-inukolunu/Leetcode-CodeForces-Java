package com.leetcode.advancedds;

import java.util.Arrays;

public class SuffixArray {

  public class Suffix implements Comparable<Suffix> {

    int index;
    int rank;
    int next;

    public Suffix(int index, int rank, int next) {
      this.index = index;
      this.rank = rank;
      this.next = next;
    }

    @Override
    public int compareTo(Suffix o) {
      if (rank != o.rank) {
        return Integer.compare(rank, o.rank);
      }
      return Integer.compare(next, o.next);
    }
  }

  public int[] suffixArray(String str) {
    int n = str.length();
    Suffix[] su = new Suffix[n];
    for (int i = 0; i < n; i++) {
      su[i] = new Suffix(i, str.charAt(i) - '$', 0);
    }
    for (int i = 0; i < n; i++) {
      su[i].next = (i + 1 < n ? su[i + 1].rank : -1);
    }
    Arrays.sort(su);
    int[] ind = new int[n];
    for (int length = 4; length < 2 * n; length <<= 1) {
      int rank = 0, prev = su[0].rank;
      su[0].rank = rank;
      ind[su[0].index] = 0;
      for (int i = 1; i < n; i++) {
        if (su[i].rank == prev && su[i].next == su[i - 1].next) {
          prev = su[i].rank;
          su[i].rank = rank;
        } else {
          prev = su[i].rank;
          su[i].rank = ++rank;
        }
        ind[su[i].index] = i;
      }
      for (int i = 0; i < n; i++) {
        int nextP = su[i].index + length / 2;
        su[i].next = nextP < n ? su[ind[nextP]].rank : -1;
      }
      Arrays.sort(su);
    }
    int[] suf = new int[n];
    for (int i = 0; i < n; i++) {
      suf[i] = su[i].index;
    }
    return suf;
  }

  public static void main(String args[]) {
    SuffixArray arr = new SuffixArray();
    System.out.println(Arrays.toString(arr.suffixArray("leetcode")));
  }

}
