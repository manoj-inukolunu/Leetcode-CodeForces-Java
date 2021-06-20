package com.leetcode.linkedlist;

import java.util.HashSet;

/**
 * @author manoji on 5/2/20.
 */
public class NumComponents {


  public int numComponents(ListNode head, int[] G) {

		/*
		[1,2,0,4,3]
		[4,3,2,0,1]
		 */
    int count = 0;
    HashSet<Integer> set = new HashSet<>();
    for (int num : G) {
      set.add(num);
    }
    ListNode temp = head;

    while (temp != null) {
      if (set.contains(temp.val)) {
        count++;
        temp = temp.next;
        while (set.contains(temp.val)) {
          temp = temp.next;
        }
      }
    }
    return count;
  }

}
