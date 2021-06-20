package com.leetcode.random;

import com.leetcode.linkedlist.ListNode;
import java.util.Stack;
import java.util.stream.IntStream;

public class ReverseKGruoup {

  public ListNode reverseKGroup(ListNode head, int k) {
    ListNode temp;
    ListNode curr = head;
    int len = 0;
    Stack<ListNode> stack = new Stack<>();
    int i = 0;
    ListNode newHead = new ListNode(-1);
    temp = newHead;
    while (curr != null) {
      while (i < k && curr != null) {
        stack.push(curr);
        len++;
        curr = curr.next;
        i++;
      }
      i = 0;
      while (!stack.isEmpty()) {
        newHead.next = stack.pop();
        newHead = newHead.next;
      }
      newHead.next = null;
    }
    if (k != 1) {
      return reverse(temp.next, len % k, len);
    }
    return temp.next;
  }

  private ListNode reverse(ListNode head, int num, int len) {
    int i = 0;
    ListNode temp = head;
    ListNode temp2 = head;
    Stack<ListNode> stack = new Stack<>();
    while (temp != null) {
      stack.push(temp);
      temp = temp.next;
      if (i < len - num - 1) {
        temp2 = temp2.next;
        i++;
      }
    }
    ListNode newHead = new ListNode(-1);
    temp = newHead;
    while (num-- > 0) {
      newHead.next = stack.pop();
      newHead = newHead.next;
    }
    newHead.next = null;
    temp2.next = temp.next;
    return head;
  }

  public static void main(String args[]) {
    int[] arr = IntStream.range(1, 6).toArray();
    ListNode head = new ListNode(-1);
    ListNode temp = head;
    for (int i = 0; i < arr.length; i++) {
      head.next = new ListNode(arr[i]);
      head = head.next;
    }
    ReverseKGruoup r = new ReverseKGruoup();
    head = r.reverseKGroup(temp.next, 1);
    while (head != null) {
      System.out.println(head.val);
      head = head.next;
    }
  }

}
