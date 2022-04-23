package com.leetcode.contest;

public class BitSet {

  int[] arr;
  boolean flipped = false;
  int ones = 0;
  int zeroes;

  public BitSet(int size) {
    arr = new int[size];
    zeroes = size;
  }

  private int val(int idx) {
    if (flipped) {
      return arr[idx] == 0 ? 1 : 0;
    }
    return arr[idx];
  }

  public void fix(int idx) {
    if (val(idx) == 0) {
      arr[idx] = 1;
      ones++;
      zeroes--;
    }
    System.out.println(this);
  }

  public void unfix(int idx) {
    if (val(idx) == 1) {
      arr[idx] = 0;
      ones--;
      zeroes++;
    }
    System.out.println(this);
  }

  public void flip() {
    flipped = !flipped;
    int temp = ones;
    ones = zeroes;
    zeroes = temp;
    System.out.println(this);
  }

  public boolean all() {
    return ones == arr.length;
  }

  public boolean one() {
    return ones > 0;
  }

  public int count() {
    return ones;
  }

  public String toString() {
    StringBuffer b = new StringBuffer();
    for (int i = 0; i < arr.length; i++) {
      if (flipped) {
        b.append(arr[i] == 0 ? 1 : 0);
      } else {
        b.append(arr[i]);
      }
    }
    return b.toString();
  }

  public static void main(String[] args) {
    BitSet bs = new BitSet(5);
    bs.fix(3);     // the value at idx = 3 is updated to 1, so bitset = "00010".
    bs.fix(1);     // the value at idx = 1 is updated to 1, so bitset = "01010".
    bs.flip();     // the value of each bit is flipped, so bitset = "10101".
    bs.all();      // return False, as not all values of the bitset are 1.
    bs.unfix(0);   // the value at idx = 0 is updated to 0, so bitset = "00101".
    bs.flip();     // the value of each bit is flipped, so bitset = "11010".
    bs.one();      // return True, as there is at least 1 index with value 1.
    bs.unfix(0);   // the value at idx = 0 is updated to 0, so bitset = "01010".
    System.out.println(bs.count());    // return 2, as there are 2 bits with value 1.
    bs.toString(); // return "01010", which is the composition of bitset.


  }
}

