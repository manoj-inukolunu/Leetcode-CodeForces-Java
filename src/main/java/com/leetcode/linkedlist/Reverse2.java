package com.leetcode.linkedlist;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author manoji on 6/20/20.
 */
public class Reverse2 {

  public ListNode reverseBetween(ListNode head, int m, int n) {
    int count = 1;
    Stack<ListNode> stack = new Stack();
    ListNode temp = head;
    ListNode start = null;
    while (count <= n) {
      if (count == m - 1) {
        start = temp;
      }
      if (count >= m) {
        stack.push(temp);
      }
      temp = temp.next;
      count++;
    }
    ListNode last = temp;
    ListNode curr = stack.pop();
    ListNode first = curr;
    while (!stack.isEmpty()) {
      curr.next = stack.pop();
      curr = curr.next;
    }
    curr.next = last;
    if (start != null) {
      start.next = first;
    } else {
      return first;
    }
    return head;
  }

  public static void main(String args[]) {
    final ListNode[] head = {new ListNode(Integer.MAX_VALUE)};
    ListNode temp = head[0];
    //[-1,-2,2,-1,0]
    Arrays.stream("1,2,3,4,5".split(",")).map(s -> Integer.parseInt(s)).forEach(s -> {
      head[0].next = new ListNode(s);
      head[0] = head[0].next;
    });

    Reverse2 r = new Reverse2();

    ListNode ans = r.reverseBetween(temp.next, 2, 5);

    System.out.println(ans);
  }

}
