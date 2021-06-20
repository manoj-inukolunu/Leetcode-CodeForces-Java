package com.leetcode.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author manoji on 6/17/20.
 */
public class ZeroSum {

  class Pair {

    ListNode node;
    Pair prev;
    Pair next;

    public Pair(ListNode node, Pair next, Pair prev) {
      this.next = next;
      this.prev = prev;
      this.node = node;
    }
  }

  public ListNode removeZeroSumSublists(ListNode head) {
    Pair headPair = new Pair(null, null, null);
    ListNode temp = head;
    Pair tempHeadPair = headPair;
    while (temp != null) {
      if (temp.val != 0) {
        tempHeadPair.next = new Pair(temp, null, tempHeadPair);
        tempHeadPair = tempHeadPair.next;
      }
      temp = temp.next;
    }
    Pair curr = headPair.next;
    while (curr != null) {
      int sum = 0;
      boolean found = false;
      Pair track = curr;
      while (track.prev != null) {
        sum += track.node.val;
        if (sum == 0) {
          found = true;
          break;
        }
        track = track.prev;
      }
      if (found) {
        if (track == null || track.prev == null) {
          curr.prev = null;
        } else {
          //1,1,1,0,-1,1,-1
          Pair prev = track.prev;
          track.prev.next = curr.next;
          curr = curr.next;
          if (curr != null) {
            curr.prev = prev;
          }
        }
      } else {
        curr = curr.next;
      }
    }
    ListNode ans = new ListNode(Integer.MAX_VALUE);
    temp = ans;
    headPair = headPair.next;
    while (headPair != null) {
      temp.next = new ListNode(headPair.node.val);
      temp = temp.next;
      headPair = headPair.next;
    }
    return ans.next;
  }

  private void printList(Pair headPair) {
    Pair temp = headPair.next;
    while (temp != null) {
      System.out.print(temp.node.val);
      System.out.print(",");
      temp = temp.next;
    }
    System.out.println();
  }

  public static void main(String args[]) {
    final ListNode[] head = {new ListNode(Integer.MAX_VALUE)};
    ListNode temp = head[0];
    //[-1,-2,2,-1,0]
    Arrays.stream("1,1,1,0,-1,1,-1".split(",")).map(s -> Integer.parseInt(s)).forEach(s -> {
      head[0].next = new ListNode(s);
      head[0] = head[0].next;
    });

    ZeroSum z = new ZeroSum();
    ListNode node = z.removeZeroSumSublists(temp.next);
    System.out.println(node);
  }

}
