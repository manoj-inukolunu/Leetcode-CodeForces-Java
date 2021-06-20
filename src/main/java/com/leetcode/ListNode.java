package com.leetcode;

/**
 * @author manoji on 4/1/20.
 */
public class ListNode {


  public int val;
  public ListNode next;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ListNode{");
    sb.append("val=").append(val);
    sb.append('}');
    return sb.toString();
  }

  public ListNode(int x) {
    val = x;
  }

}
