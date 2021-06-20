package com.leetcode.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SegmentedSieve {

  public void segSieve() {
    List<Integer> primes = sieve();
  }


  public List<Integer> sieve() {
    boolean[] prime = new boolean[(int) Math.pow(10, 5)];
    Arrays.fill(prime, true);
    List<Integer> primes = new ArrayList<>();
    for (int p = 2; p * p <= prime.length; p++) {
      if (prime[p]) {
        primes.add(p);
        for (int i = p * p; i < prime.length; i += p) {
          prime[i] = false;
        }
      }
    }

    return primes;
  }

  private boolean isPalin(int num) {
    int copyNumber, currentDigit, reversedNumber = 0;
    copyNumber = num;
    do {
      currentDigit = copyNumber % 10;
      reversedNumber = (reversedNumber * 10) + currentDigit;
      copyNumber = copyNumber / 10;
    } while (copyNumber != 0);
    return num == reversedNumber;
  }

  public static void main(String args[]) {
    SegmentedSieve s = new SegmentedSieve();
  }
}
