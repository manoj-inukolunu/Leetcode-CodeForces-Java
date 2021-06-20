package com.leetcode.random10.sixmonths.medium;

/**
 * @author manoji on 7/31/20.
 */
public class MyCircularQueue {

  int arr[];
  int total = 0;

  int front = -1, rear = -1;

  /**
   * Initialize your data structure here. Set the size of the queue to be k.
   */
  public MyCircularQueue(int k) {
    arr = new int[k];
  }

  /**
   * Insert an element into the circular queue. Return true if the operation is successful.
   */
  public boolean enQueue(int value) {
    if ((front == 0 && rear == arr.length - 1) || (rear == (front - 1))) {
      return false;
    } else if (front == -1) {
      front = 0;
      rear = 0;
      arr[rear] = value;
      total++;
      return true;
    } else if (rear == arr.length - 1 && front != 0) {
      rear = 0;
      total++;
      arr[rear] = value;
      return true;
    } else {
      rear++;
      total++;
      arr[rear] = value;
      return true;
    }
  }

  /**
   * Delete an element from the circular queue. Return true if the operation is successful.
   */
  public boolean deQueue() {
    if (front == -1) {
      return false;
    }
    total--;
    int val = arr[front];
    arr[front] = -1;
    if (front == rear) {
      front = -1;
      rear = -1;
    } else if (front == arr.length - 1) {
      front = 0;
    } else {
      front++;
    }
    return true;
  }

  /**
   * Get the front item from the queue.
   */
  public int Front() {
    if (front < 0) {
      return -1;
    }
    return arr[front];
  }

  /**
   * Get the last item from the queue.
   */
  public int Rear() {
    if (rear < 0) {
      return -1;
    }
    return arr[rear];
  }

  /**
   * Checks whether the circular queue is empty or not.
   */
  public boolean isEmpty() {
    return total == 0;
  }

  /**
   * Checks whether the circular queue is full or not.
   */
  public boolean isFull() {
    return total == arr.length;
  }


  public static void main(String args[]) {
    MyCircularQueue circularQueue = new MyCircularQueue(2);
    System.out.println(circularQueue.enQueue(4));  // return true
    System.out.println(circularQueue.Rear());  // return true
    System.out.println(circularQueue.enQueue(9));  // return true
    System.out.println(circularQueue.deQueue());  // return false, the queue is full
    System.out.println(circularQueue.Front());  // return 3
    System.out.println(circularQueue.deQueue());  // return true
    System.out.println(circularQueue.deQueue());  // return true
    System.out.println(circularQueue.isEmpty());  // return true
    System.out.println(circularQueue.enQueue(6));  // return 4
    System.out.println(circularQueue.enQueue(4));  // return 4
  }
}
