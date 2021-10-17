package com.leetcode.medium;


import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class LC146 {

    static class DoublyLinkedList {
        Node head;
        Node tail;

        int size = 0;

        void addToFront(Node node) {
            if (size == 0) {
                head = node;
                tail = node;
            } else {
                head.next = node;
                node.prev = head;
                head = node;
            }
            size++;
        }

        void removeNode(Node node) {
            Node prev = node.prev;
            Node next = node.next;
            if (prev != null) {
                prev.next = next;
            }
            if (next != null) {
                next.prev = prev;
            }
            if (node == head) {
                head = prev;
            }
            if (node == tail) {
                tail = next;
            }
        }

        void removeLast() {
            if (size != 0) {
                tail = tail.next;
                tail.prev = null;
                size--;
                if (size == 1) {
                    tail = head;
                }
            }
        }
    }

    static class Node {
        Node prev;
        Node next;
        int val;

        public int maxEvents(int[][] events) {
            final int[] maxDay = {-1};
            Arrays.sort(events, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return 0;
                }

                private int compareTo (int[] o1, int[] o2){
                    if (o1[1] == o2[1]) {
                        return Integer.compare(o1[0], o2[0]);
                    }
                    maxDay[0] = Math.max(o1[1], o2[1]);
                    return Integer.compare(o1[1], o2[1]);
                }
            });
            System.out.println(maxDay[0]);
            return 1;
        }
    }

    static class LRUCache {

        private int size;
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Node> nodeMap = new HashMap<>();
        DoublyLinkedList dll = new DoublyLinkedList();

        public LRUCache(int capacity) {
            this.size = capacity;
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                Node node = nodeMap.get(key);
                dll.removeNode(node);
                dll.addToFront(node);
                return map.get(key);
            }
            return -1;
        }

        public void put(int key, int value) {
            if (map.size() >= size) {

            }
            Node node = new Node();
            node.val = key;
            dll.addToFront(node);
            nodeMap.put(key, node);
            map.put(key, value);
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));

    }
}
