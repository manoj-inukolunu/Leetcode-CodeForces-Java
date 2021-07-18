package com.leetcode.medium;

import com.leetcode.linkedlist.ListNode;

public class LC1836 {

    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        int[] arr = new int[100001];
        ListNode temp = head;
        while (temp != null) {
            arr[temp.val]++;
            temp = temp.next;
        }
        temp = head;
        ListNode fakeHead = new ListNode(-1);
        ListNode fakeTemp = fakeHead;
        while (temp != null) {
            if (arr[temp.val] == 1) {
                fakeHead.next = new ListNode(temp.val);
                fakeHead = fakeHead.next;
                temp = temp.next;
            } else {
                temp = temp.next;
            }
        }
        return fakeTemp.next;
    }
}
