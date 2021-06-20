package com.leetcode.bits;


import com.leetcode.ListNode;
import org.checkerframework.checker.units.qual.A;

/**
 * @author manoji on 3/30/20.
 */
public class Add {

  public static void main(String args[]) {
    char[] arr = "708".toCharArray();
    ListNode head = new ListNode(Character.getNumericValue(arr[0]));
    ListNode temp = head;
    for (int i = 1; i < arr.length; i++) {
      head.next = new ListNode(Character.getNumericValue(arr[i]));
      head = head.next;
    }

    while (temp != null) {
      System.out.println(temp);
      temp = temp.next;
    }

  }


  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    StringBuffer buffer1 = new StringBuffer();
    while (l1 != null) {
      buffer1.append(l1.val);
      l1 = l1.next;
    }
    StringBuffer buffer2 = new StringBuffer();
    while (l2 != null) {
      buffer2.append(l2.val);
      l2 = l2.next;
    }

    String val = String.valueOf(Integer.parseInt(buffer1.reverse().toString()) + Integer.parseInt(buffer2.reverse().toString()));

    StringBuffer buffer3 = new StringBuffer(val);

    char arr[] = buffer3.reverse().toString().toCharArray();

    ListNode head = new ListNode(Character.getNumericValue(arr[0]));
    for (int i = 1; i < arr.length; i++) {
      head.next = new ListNode(Character.getNumericValue(arr[i]));
    }

    return head;

  }

}
