package com.leetcode.linkedlist;

import java.util.Arrays;

/**
 * @author manoji on 5/16/20.
 */
public class OddEvenList {

  public ListNode oddEvenList(ListNode head) {
    ListNode even = head;
    ListNode odd = head.next;
    ListNode temp = odd;
    ListNode ans = head;
    while (even != null || odd != null) {
      if (even != null && even.next != null) {
        even.next = even.next.next;
      }
      if (odd != null && odd.next != null) {
        odd.next = odd.next.next;
      }
      if (even != null) {
        even = even.next;
      }

      if (odd != null) {
        odd = odd.next;
      }
    }

    ListNode temp1 = ans;
    while (temp1 != null) {
      if (temp1.next == null) {
        temp1.next = temp;
        break;
      }
      temp1 = temp1.next;
    }
    return ans;
  }

  public static void main(String args[]) {
    OddEvenList o = new OddEvenList();

    ListNode head = new ListNode(-1);
    ListNode temp = head;
    int[] arr = new int[]{1, 2, 3};
    for (int i = 0; i < arr.length; i++) {
      head.next = new ListNode(arr[i]);
      head = head.next;
    }

    temp = o.oddEvenList(temp.next);

    System.out.println(temp);
  }

}
