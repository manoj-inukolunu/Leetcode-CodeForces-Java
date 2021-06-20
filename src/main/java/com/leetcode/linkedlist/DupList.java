package com.leetcode.linkedlist;

import java.util.Stack;

/**
 * @author manoji on 5/17/20.
 */
public class DupList {

  public ListNode removeElements(ListNode head, int val) {
    Stack<ListNode> stack = new Stack();
    while (head != null) {
      if (head.val != val) {
        stack.push(head);
      }
      head = head.next;
    }

    if (!stack.isEmpty()) {
      ListNode node = stack.pop();
      ListNode curr = null;
      while (!stack.isEmpty()) {
        curr = stack.pop();
        curr.next = node;
      }
      return curr == null ? node : curr;
    }
    return null;
  }


  public static void main(String args[]) {
		/*
		[6,2,6,3,4,5,6]
````6
		 */
    ListNode head = new ListNode(6);
    head.next = new ListNode(2);
    head.next.next = new ListNode(6);
    head.next.next.next = new ListNode(3);
    head.next.next.next.next = new ListNode(4);
    head.next.next.next.next.next = new ListNode(5);
    head.next.next.next.next.next.next = new ListNode(6);

    DupList d = new DupList();

    d.removeElements(head, 6);

  }

}
