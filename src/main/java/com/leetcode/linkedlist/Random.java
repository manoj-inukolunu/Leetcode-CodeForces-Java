package com.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author manoji on 4/18/20.
 */
public class Random {

  static class Node {

    int val;
    Node next;
    Node random;

    public Node(int val) {
      this.val = val;
      this.next = null;
      this.random = null;
    }
  }

  class Pair {

    int val;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Pair)) {
        return false;
      }
      Pair pair = (Pair) o;
      return val == pair.val &&
          index == pair.index;
    }

    @Override
    public int hashCode() {
      return Objects.hash(val, index);
    }

    public Pair(int val, int index) {
      this.val = val;
      this.index = index;
    }

    int index;
  }

  public Node copyRandomList(Node head) {
    HashMap<Pair, Node> map = new HashMap();
    HashMap<Node, Integer> randomMap = new HashMap();
    Node temp = head;
    int i = 0;
    while (temp != null) {
      Node curr = new Node(temp.val);
      map.put(new Pair(temp.val, i), curr);

      randomMap.put(temp, i);
      temp = temp.next;
      i++;
    }

    temp = head;
    i = 0;
    Node newHead = map.get(new Pair(temp.val, i));
    if (temp.random != null) {
      newHead.random = map.get(new Pair(temp.random.val, randomMap.get(temp.random)));
    }
    Node temp1 = newHead;
    temp = temp.next;
    while (temp != null) {
      temp1.next = map.get(new Pair(temp.val, ++i));
      temp1 = temp1.next;
      if (temp.random != null) {
        temp1.random = map.get(new Pair(temp.random.val, randomMap.get(temp.random)));
      }
      temp = temp.next;
    }
    return newHead;
  }

  public static void main(String args[]) {
    //[[7,null],[13,0],[11,4],[10,2],[1,0]]
    Random r = new Random();
    Node node = new Node(7);
    node.random = null;
    node.next = new Node(13);
    node.next.random = node;
    node.next.next = new Node(11);
    node.next.next.next = new Node(10);
    node.next.next.next.random = node.next.next;
    node.next.next.next.next = new Node(1);
    node.next.next.next.next.random = node;

    node.next.next.random = node.next.next.next.next;
    r.copyRandomList(node);
  }

}
