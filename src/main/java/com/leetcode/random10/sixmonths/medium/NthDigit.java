package com.leetcode.random10.sixmonths.medium;

public class NthDigit {

  public int findNthDigit(int n) {
    int bucket = 1;
    int curr = n;
    while (true) {
      long totalNumbersInBucket = (long) (9 * Math.pow(10, bucket - 1));
      long totalDigitsInBucket = totalNumbersInBucket * (bucket);
      if (curr - totalDigitsInBucket < 0) {
        break;
      } else {
        curr -= totalDigitsInBucket;
      }
      bucket++;
    }

    if (bucket == 1) {
      return n;
    }

    if (curr % bucket == 0) {
      int num = curr / bucket;
      num = (int) (((Math.pow(10, (bucket - 1)) + num) - 1));
      return Character.getNumericValue(String.valueOf(num).charAt(String.valueOf(num).length() - 1));
    } else {
      int num = (curr / bucket) + 1;
      int digit = curr - ((num - 1) * bucket) - 1;
      num = (int) (((Math.pow(10, (bucket - 1)) + num) - 1));
      return Character.getNumericValue(String.valueOf(num).charAt(digit));
    }
  }

  public static void main(String args[]) {
    NthDigit n = new NthDigit();
    System.out.println(n.findNthDigit(14));
  }

}
