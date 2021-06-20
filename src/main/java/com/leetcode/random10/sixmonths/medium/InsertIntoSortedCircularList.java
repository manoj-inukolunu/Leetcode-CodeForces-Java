package com.leetcode.random10.sixmonths.medium;

/**
 * @author manoji on 7/30/20.
 */
public class InsertIntoSortedCircularList {

  public static class Node {

    public int val;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, Node _next) {
      val = _val;
      next = _next;
    }
  }

  private int countNodes(Node head) {
    Node temp = head;
    int result = 0;
    if (head != null) {
      do {
        temp = temp.next;
        result++;
      } while (temp != head);
    }

    return result;
  }

  public Node insert(Node head, int insertVal) {
    if (head == null) {
      Node node = new Node(insertVal);
      node.next = node;
      return node;
    }

    Node smallest = head;
    Node largest = head;
    Node temp = head;
    do {
      if (temp.val < smallest.val) {
        smallest = temp;
      }
      if (temp.val > largest.val) {
        largest = temp;
      }
      if (insertVal >= temp.val && insertVal <= temp.next.val) {
        Node next = temp.next;
        temp.next = new Node(insertVal);
        temp.next.next = next;
        return head;
      }
      temp = temp.next;
    } while (temp != head);

    if (insertVal <= smallest.val) {
      Node node = new Node(insertVal);
      largest.next = node;
      node.next = smallest;
    } else {
      Node node = new Node(insertVal);
      largest.next = node;
      node.next = smallest;
    }

    return head;

  }

  public static void main(String args[]) {
    InsertIntoSortedCircularList i = new InsertIntoSortedCircularList();

    Node head = new Node(1);
    head.next = new Node(1);
    head.next.next = head;

    System.out.println(i.countNodes(head));
  }


}
