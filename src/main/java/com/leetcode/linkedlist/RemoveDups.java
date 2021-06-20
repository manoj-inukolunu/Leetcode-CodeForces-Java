package com.leetcode.linkedlist;

public class RemoveDups {

  public ListNode deleteDuplicates(ListNode head) {

    ListNode temp = head;
    while (temp != null) {
      if (temp.next != null && temp.val == temp.next.val) {
        temp.next = getDiffNode(temp, temp.val);
      }
      temp = temp.next;
    }
    return head;
  }

  private ListNode getDiffNode(ListNode head, int val) {
    while (true) {
      if (head == null) {
        return null;
      }
      if (head.val != val) {
        return head;
      }
      head = head.next;
    }
  }

}
