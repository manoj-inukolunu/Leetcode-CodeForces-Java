package com.leetcode.random;

import java.util.ArrayList;
import java.util.List;

public class MyCircularDeque {

  List<Integer> queue = new ArrayList();
  int size;

  /**
   * Initialize your data structure here. Set the size of the deque to be k.
   */
  public MyCircularDeque(int k) {
    size = k;
  }

  /**
   * Adds an item at the front of Deque. Return true if the operation is successful.
   */
  public boolean insertFront(int value) {
    if (queue.size() == size) {
      return false;
    } else {
      queue.add(value);
      return true;
    }
  }

  /**
   * Adds an item at the rear of Deque. Return true if the operation is successful.
   */
  public boolean insertLast(int value) {
    if (queue.size() == size) {
      return false;
    } else {
      queue.add(0, value);
      return true;
    }
  }

  /**
   * Deletes an item from the front of Deque. Return true if the operation is successful.
   */
  public boolean deleteFront() {
    if (queue.size() != 0) {
      queue.remove(queue.size() - 1);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Deletes an item from the rear of Deque. Return true if the operation is successful.
   */
  public boolean deleteLast() {
    if (queue.size() != 0) {
      queue.remove(0);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Get the front item from the deque.
   */
  public int getFront() {
    return queue.get(queue.size() - 1);
  }

  /**
   * Get the last item from the deque.
   */
  public int getRear() {
    return queue.get(0);
  }

  /**
   * Checks whether the circular deque is empty or not.
   */
  public boolean isEmpty() {
    return queue.size() == 0;
  }

  /**
   * Checks whether the circular deque is full or not.
   */
  public boolean isFull() {
    return queue.size() == size;
  }

  public static void main(String args[]) {
    MyCircularDeque circularDeque = new MyCircularDeque(3);
    System.out.println(circularDeque.insertLast(1));      // return true
    System.out.println(circularDeque.insertLast(2));      // return true
    System.out.println(circularDeque.insertFront(3));      // return true
    System.out.println(circularDeque.insertFront(4));      // return false, the queue is full
    System.out.println(circularDeque.getRear());        // return 2
    System.out.println(circularDeque.isFull());        // return true
    System.out.println(circularDeque.deleteLast());      // return true
    System.out.println(circularDeque.insertFront(4));      // return true
    System.out.println(circularDeque.getFront());      // return 4

  }
}
