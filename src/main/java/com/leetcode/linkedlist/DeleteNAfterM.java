package com.leetcode.linkedlist;

import java.util.stream.IntStream;

public class DeleteNAfterM {

  public ListNode deleteNodes(ListNode head, int m, int n) {
    ListNode fakeHead = new ListNode(-1);
    fakeHead.next = head;
    ListNode temp = fakeHead;
    while (temp != null) {
      int count = 0;
      while (temp != null && count < m) {
        count++;
        temp = temp.next;
      }
      if (temp != null) {
        temp.next = skipN(temp, n);
      }
    }
    return fakeHead.next;
  }

  private ListNode skipN(ListNode head, int val) {
    int count = 0;
    while (head != null && count < val) {
      count++;
      head = head.next;
    }
    return head != null ? head.next : null;
  }

  public static void main(String args[]) {
    ListNode head = new ListNode(-1);
    ListNode temp = head;
    int[] arr = IntStream.range(1, 12).toArray();
    for (int i = 0; i < arr.length; i++) {
      temp.next = new ListNode(arr[i]);
      temp = temp.next;
    }
    DeleteNAfterM d = new DeleteNAfterM();
    ListNode ans = d.deleteNodes(head.next, 1, 3);
    while (ans != null) {
      System.out.println(ans.val);
      ans = ans.next;
    }
  }

}
