package com.leetcode.stack;

import com.leetcode.ListNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import org.checkerframework.checker.units.qual.C;

/**
 * @author manoji on 4/9/20.
 */
public class CustomStack {

  public int[] nextLargerNodes(ListNode head) {
    List<Integer> list = new ArrayList();
    while (head != null) {
      list.add(head.val);
      head = head.next;
    }

    Collections.sort(list);
    System.out.println(list);
    int[] arr = new int[list.size()];
    Arrays.fill(arr, 0);
    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] > arr[i]) {
          arr[i] = arr[j];
          break;
        }
      }
    }
    return arr;
  }


  Stack<Integer> stack = new Stack();
  int maxSize, size = 0;

  public CustomStack(int maxSize) {
    this.maxSize = maxSize;
  }

  public void push(int x) {
    if (stack.size() < maxSize) {
      stack.push(x);
    }
  }

  public int pop() {
    if (!stack.isEmpty()) {
      return stack.pop();
    }
    return -1;
  }

  public void increment(int k, int val) {
    if (stack.size() < k) {
      for (int i = 0; i < stack.size(); i++) {
        stack.setElementAt(stack.elementAt(i) + val, i);
      }
    } else {
      for (int i = 0; i < k; i++) {
        stack.setElementAt(stack.elementAt(i) + val, i);
      }
    }
  }

  public static void main(String args[]) {
    CustomStack stack = new CustomStack(3);

    ListNode node = new ListNode(2);
    node.next = new ListNode(1);
    node.next.next = new ListNode(5);
    stack.nextLargerNodes(node);

  }
}

/**
 * Your CustomStack object will be instantiated and called as such: CustomStack obj = new CustomStack(maxSize); obj.push(x); int param_2 = obj.pop();
 * obj.increment(k,val);
 */
