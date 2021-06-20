package com.leetcode.segmenttree;

import com.leetcode.ListNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author manoji on 3/31/20.
 */
public class SegmentTree {

  private void build(int[] tree, int[] arr, int current, int start, int end) {
    if (start > end) {
      return;
    }
    if (start == end) {
      tree[current] = arr[start];
      return;
    }
    int mid = (start + end) / 2;
    build(tree, arr, 2 * current, start, mid);
    build(tree, arr, 2 * current + 1, mid + 1, end);
    tree[current] = tree[2 * current] + tree[2 * current + 1];
  }


  public boolean isPalindrome(ListNode head) {
    List<Integer> list = new ArrayList();
    while (head != null) {
      list.add(head.val);
      head = head.next;
    }
    for (int i = 0; i < list.size() / 2; i++) {
      int listI = list.get(i);
      int listI1 = list.get(list.size() - 1 - i);
      if (listI != listI1) {
        return false;
      }
    }
    return true;
  }

  public static void main(String args[]) {
    SegmentTree segmentTree = new SegmentTree();
    ListNode head = new ListNode(-129);
    head.next = new ListNode(-129);

    System.out.println(segmentTree.isPalindrome(head));
  }

}
