package com.leetcode.linkedlist;

public class InsertionSortList {

  public ListNode insertionSortList(ListNode head) {
    ListNode sortedHead = head;
    ListNode sortedTail = head;
    ListNode unsorted = head.next;
    ListNode ans = head;
    while (unsorted != null) {
      ListNode next = unsorted.next;
      ListNode curr = unsorted;
      sortedTail.next = next;
      curr.next = null;
      insert(sortedHead, curr);
      unsorted = sortedTail.next;
    }
    return ans;
  }

  private void insert(ListNode head, ListNode toInsert) {
    ListNode prev = null, curr = head;
    while (curr != null) {
      if (prev == null && toInsert.val <= curr.val) {
        toInsert.next = curr;
        head = toInsert;
        return;
      } else if (prev != null && toInsert.val <= curr.val && toInsert.val >= prev.val) {
        prev.next = toInsert;
        toInsert.next = curr;
        return;
      }
      curr = curr.next;
      prev = curr;
    }
  }

  public static void main(String args[]) {
    ListNode head = new ListNode(4);
    head.next = new ListNode(2);
    head.next.next = new ListNode(1);
    head.next.next = new ListNode(3);
    InsertionSortList i = new InsertionSortList();
    ListNode sorted = i.insertionSortList(head);
    while (sorted != null) {
      System.out.print(sorted.val + " ");
      sorted = sorted.next;
    }

  }
}
