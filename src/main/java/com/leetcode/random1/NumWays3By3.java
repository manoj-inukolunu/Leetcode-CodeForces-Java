package com.leetcode.random1;

public class NumWays3By3 {

  class Tuple {

    int red;
    int green;
    int yellow;

    public Tuple(int red, int green, int yellow) {
      this.red = red;
      this.green = green;
      this.yellow = yellow;
    }

    @Override
    public String toString() {
      return "Tuple{" +
          "red=" + red +
          ", green=" + green +
          ", yellow=" + yellow +
          '}';
    }
  }

  public int numOfWays(int n) {
    long a121 = 6, a123 = 6, b121, b123, mod = (int) (Math.pow(10, 9) + 7);
    for (int i = 1; i < n; i++) {
      b121 = a121 * 3 + a123 * 2;
      b123 = a121 * 2 + a123 * 2;
      a121 = b121 % mod;
      a123 = b123 % mod;
    }
    return (int) ((a121 + a123) % mod);
  }

  public static void main(String args[]) {
    NumWays3By3 n = new NumWays3By3();
    System.out.println(n.numOfWays(2));
  }


}
