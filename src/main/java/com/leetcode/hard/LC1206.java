package com.leetcode.hard;

import java.util.Random;
import java.util.Stack;

public class LC1206 {
    class Skiplist {

        class Node {
            int val;
            int count = 1;
            Node next;
            Node down;
            Node up;

            public Node(int val) {
                this.val = val;
            }
        }

        Node dummy = new Node(Integer.MAX_VALUE);
        int listLevel = 0;
        Stack<Node> path = new Stack<>();
        Random rand = new Random();

        public Skiplist() {

        }

        private Node findPred(int num) {
            Node curr = dummy;
            while (curr != null) {
                while (curr.next != null && curr.next.val < num) {
                    curr = curr.next;
                }
                path.push(curr);
                if (curr.down != null) {
                    curr = curr.down;
                } else {
                    return curr;
                }
            }
            return curr;
        }

        public boolean search(int target) {
            Node pred = findPred(target);
            return pred.next != null && pred.next.val == target;
        }

        public void add(int num) {
            Node pred = findPred(num);
            if (pred.next != null && pred.next.val == num) {
                pred.next.count++;
                return;
            }
            Node toAdd = new Node(num);
            int level = pickHeight();
            if (level > listLevel) {
                while (listLevel < level) {
                    path.push(dummy);
                    listLevel++;
                }
            }

        }

        public boolean erase(int num) {
            return false;
        }

        private int pickHeight() {
            return Integer.numberOfTrailingZeros(rand.nextInt());
        }
    }
}
