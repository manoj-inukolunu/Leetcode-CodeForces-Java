package com.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author manoji on 4/21/20.
 */
public class ReorderList {

  public void reorderListStack(ListNode head) {
    Stack<ListNode> stack = new Stack<>();
    ListNode temp = head;

    while (temp != null) {
      stack.push(temp);
      temp = temp.next;
    }

    ListNode newNode = head;

    int count = stack.size();

    while (count > 0) {
      ListNode nextNode = newNode.next;
      newNode.next = stack.pop();
      newNode = newNode.next;
      newNode.next = nextNode;
      newNode = newNode.next;
      count -= 2;
    }
    newNode.next = null;
  }

  public void reorderList(ListNode head) {
    HashMap<Integer, ListNode> map = new HashMap();

    ListNode temp = head;
    int index = 0;
    while (temp != null) {
      map.put(index++, temp);
      temp = temp.next;
    }
    temp = head;
    int i = 0;
    while (index > i) {
      if (i % 2 != 0) {
        temp.next = map.get(i++);
      } else {
        temp.next = map.get((index - 1) - i);
        i += 2;
      }
      temp = temp.next;
    }

    temp.next.next = null;

    System.out.println(head);
  }

  public static void main(String args[]) {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next = new ListNode(5);

    ReorderList reorderList = new ReorderList();

    reorderList.reorderListStack(head);
  }
}
