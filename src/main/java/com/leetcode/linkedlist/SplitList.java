/*
package com.leetcode.linkedlist;

import com.leetcode.linkedlist.Random.Pair;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.IntStream;

public class SplitList {


  private int length(ListNode root) {
    int len = 0;
    while (root != null) {
      len++;
      root = root.next;

    }

    Collections.sort(list,new Comparator<Pair>(){
      @Over
      public void compare(Pair o1,Pair o2){
        if(o1.word.length()==o2.word.length()){
          return Integer.compare(o1.index,o2.index);
        }
        return Integer.compare(o1.word.length(),o2.word.length());
      }
    });
    return len;
  }

  public ListNode[] splitListToParts(ListNode root, int k) {
    int len = length(root);
    ListNode[] ans = new ListNode[k];
    int val = (len % k);
    ListNode nxt = getList(root, ans, 0, val - 1, (len / k) + 1);
    if (nxt != null) {
      getList(nxt, ans, val, val + (k - val - 1), len / k);
    }
    return ans;
  }

  private ListNode getList(ListNode root, ListNode[] ans, int start, int end, int count) {
    int temp = count;
    ListNode prev = null;
    for (int i = start; i <= end; i++) {
      ans[i] = root;
      while (temp > 0 && root != null) {
        temp--;
        prev = root;
        root = root.next;
      }
      temp = count;
      if (prev != null) {
        ListNode nxt = prev.next;
        prev.next = null;
        root = nxt;
      }
    }
    return root;
  }

  public static void main(String args[]) {
    int[] arr = IntStream.range(1, 11).toArray();
    ListNode fake = new ListNode(-1);
    ListNode temp = fake;
    for (int num : arr) {
      fake.next = new ListNode(num);
      fake = fake.next;
    }
    ListNode head = temp.next;

    SplitList s = new SplitList();
    ListNode[] ans = s.splitListToParts(head, 11);
    for (ListNode node : ans) {
      printList(node);
    }
  }

  private static void printList(ListNode node) {
    while (node != null) {
      System.out.println(node.val + " ");
      node = node.next;
    }
    System.out.println();
  }

}
*/
