package com.leetcode.contest;

import com.leetcode.linkedlist.ListNode;

public class MergeInBetween {

  public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
    ListNode temp = list1;
    int count = 0;
    ListNode prev = null;
    ListNode next = null;
    while (temp != null) {
      if (count == a - 1) {
        prev = temp;
      }
      if (count == b) {
        next = temp;
        break;
      }
      temp = temp.next;
    }
    ListNode temp2 = list2;
    while (temp2.next != null) {
      temp2 = temp2.next;
    }

    prev.next = list2;
    next.next = temp2;

    return list1;
  }



}
