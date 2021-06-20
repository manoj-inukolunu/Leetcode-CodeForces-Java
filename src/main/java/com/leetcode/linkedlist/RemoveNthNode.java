package com.leetcode.linkedlist;

import java.util.Stack;

/**
 * @author manoji on 4/22/20.
 */
public class RemoveNthNode {


  public int rangeBitwiseAnd(int m, int n) {
    int ans = m;
    for (int i = m + 1; i <= n; i++) {
      if (i > 0) {
        ans = ans & i;
        if (ans == 0) {
          return 0;
        }
      } else {
        break;
      }
    }
    return ans;
  }


  private ListNode remove(ListNode head, int n) {
    int[] val = recur(head, n, 0);
    if (val[1] == n) {
      return head.next;
    }
    return head;
  }

  private int[] recur(ListNode head, int n, int size) {
    if (head == null) {
      return new int[]{0, size};
    }
    int[] val = recur(head.next, n, size + 1);
    int count = 1 + val[0];
    if (count == n + 1) {
      head.next = head.next.next;
    }
    return new int[]{count, val[1]};
  }

  public ListNode removeNthFromEnd(ListNode head, int n) {
    Stack<ListNode> stack = new Stack();
    ListNode temp = head;

    while (temp != null) {
      stack.push(temp);
      temp = temp.next;
    }

    int count = 1;
    ListNode node = null;
    while (true) {
      if (count == n + 2) {
        break;
      }
      if (!stack.isEmpty()) {
        node = stack.pop();
      }
      count++;
    }
    if (node != null) {
      node.next = node.next.next;
    }

    if (node == head) {
      return head.next;
    }
    return head;
  }

  public static void main(String args[]) {

    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);

    RemoveNthNode r = new RemoveNthNode();
    //	ListNode node = r.remove(head, 2);

    System.out.println(r.rangeBitwiseAnd(2147483646, 2147483647));

  }

}
