package com.leetcode.linkedlist;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PhoneDirectory {

  List<Integer> empty = new ArrayList();
  Set<Integer> occupied = new HashSet();

  /**
   * Initialize your data structure here
   *
   * @param maxNumbers - The maximum numbers that can be stored in the phone directory.
   */
  public PhoneDirectory(int maxNumbers) {
    for (int i = 0; i < maxNumbers; i++) {
      empty.add(i);
    }
  }

  /**
   * Provide a number which is not assigned to anyone.
   *
   * @return - Return an available number. Return -1 if none is available.
   */
  public int get() {
    if (empty.isEmpty()) {
      return -1;
    }
    int ret = empty.get(0);
    occupied.add(ret);
    empty.remove(new Integer(ret));
    return ret;
  }

  /**
   * Check if a number is available or not.
   */
  public boolean check(int number) {
    return !occupied.contains(number);
  }

  /**
   * Recycle or release a number.
   */
  public void release(int number) {
    if (occupied.contains(number)) {
      empty.add(number);
      occupied.remove(number);
    }
  }

  public static void main(String args[]) {
    PhoneDirectory p = new PhoneDirectory(3);
    p.release(2);
    System.out.println(p.get());
    p.release(2);
    p.release(0);
    System.out.println(p.get());
    System.out.println(p.get());
    System.out.println(p.check(1));
    System.out.println(p.get());
    p.release(0);
    System.out.println(p.get());
    p.release(0);
    p.release(0);
    System.out.println(p.get());
    System.out.println(p.check(1));
    p.release(1);
  }
}
