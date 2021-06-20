package com.leetcode.random11;

public class RLEIterator {


  int curr = 0;
  int[] arr;
  int count = 0;

  //3,8,0,9,2,5
  /*
  ["RLEIterator","next","next","next","next","next","next","next","next","next","next"]
[[[811,903,310,730,899,684,472,100,434,611]],[358],[345],[154],[265],[73],[220],[138],[4],[170],[88]]
   */
  public RLEIterator(int[] A) {
    arr = A;
  }

  public int next(int n) {
    int last = -1;
    while (n > 0) {
      if (arr[curr] - n >= 0) {
        last = arr[curr + 1];
        arr[curr] = arr[curr] - n;
        n -= n;
      } else if (curr + 2 < arr.length) {
        n -= arr[curr];
        arr[curr] = 0;
        curr = curr + 2;
      } else {
        return -1;
      }
    }
    return last;
  }

  public static void main(String args[]) {
    RLEIterator r = new RLEIterator(new int[]{811, 903, 310, 730, 899, 684, 472, 100, 434, 611});
    System.out.println(r.next(358));
    System.out.println(r.next(345));
    System.out.println(r.next(154));
    System.out.println(r.next(265));
    System.out.println(r.next(73));
    System.out.println(r.next(220));
    System.out.println(r.next(138));
    System.out.println(r.next(4));
    System.out.println(r.next(170));
    System.out.println(r.next(88));
  }

}
