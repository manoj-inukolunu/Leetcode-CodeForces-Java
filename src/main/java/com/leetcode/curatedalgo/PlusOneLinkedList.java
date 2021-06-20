package com.leetcode.curatedalgo;

import com.leetcode.linkedlist.ListNode;
import java.util.Stack;

public class PlusOneLinkedList {

  public ListNode plusOne(ListNode head) {
    if (head == null) {
      return null;
    }
    Stack<ListNode> stack = new Stack<>();
    ListNode temp = head;
    while (temp != null) {
      stack.push(temp);
      temp = temp.next;
    }
    if (stack.isEmpty()) {
      return null;
    }
    int carry = 0;
    ListNode top = stack.pop();
    int val = top.val + 1;
    if (val >= 10) {
      top.val = 0;
      carry = 1;
    } else {
      top.val = val;
      carry = 0;
    }
    while (!stack.isEmpty()) {
      top = stack.pop();
      val = top.val + carry;
      if (val >= 10) {
        top.val = 0;
        carry = 1;
      } else {
        top.val = val;
        carry = 0;
      }
    }
    if (carry != 0) {
      ListNode node = new ListNode(carry);
      node.next = head;
      return node;
    }
    return head;
  }


  public static void main(String args[]) {
    ListNode head = new ListNode(9);
    head.next = new ListNode(9);
    head.next.next = new ListNode(9);
    PlusOneLinkedList p = new PlusOneLinkedList();
    head = p.plusOne(head);
    while (head != null) {
      System.out.println(head.val);
      head = head.next;
    }
  }

}
