package com.leetcode.hard;

import org.jetbrains.annotations.NotNull;

import java.util.PriorityQueue;

public class LC2102 {

    public static class SORTracker {

        class Pair implements Comparable<Pair> {
            int score;
            String name;

            public Pair(String name, int score) {
                this.score = score;
                this.name = name;
            }

            @Override
            public int compareTo(@NotNull Pair o) {
                if (o.score == this.score) {
                    return o.name.compareTo(this.name);
                }
                return Integer.compare(this.score, o.score);
            }
        }

        PriorityQueue<Pair> smaller = new PriorityQueue<>((o1, o2) -> {
            if (o1.score == o2.score) {
                return -o1.name.compareTo(o2.name);
            }
            return Integer.compare(o1.score, o2.score);
        });
        PriorityQueue<Pair> larger = new PriorityQueue<>((o1, o2) -> {
            if (o1.score == o2.score) {
                return o1.name.compareTo(o2.name);
            }
            return -Integer.compare(o1.score, o2.score);
        });
        int count = 0;

        public SORTracker() {

        }

        public void add(String name, int score) {
            Pair curr = new Pair(name, score);
            if (smaller.isEmpty()) {
                smaller.add(curr);
            } else {
                Pair s = smaller.peek();
                int comp = curr.compareTo(s);
                if (comp > 0) {
                    //curr is larger
                    smaller.add(curr);
                } else {
                    larger.add(curr);
                }
                while (smaller.size() < count + 1) {
                    smaller.add(larger.poll());
                }
                while (smaller.size() > count + 1) {
                    larger.add(smaller.poll());
                }
            }
        }

        public String get() {
            count++;
            String val = smaller.peek().name;
            while (smaller.size() < count + 1 && !larger.isEmpty()) {
                smaller.add(larger.poll());
            }
            while (smaller.size() > count + 1 && !smaller.isEmpty()) {
                larger.add(smaller.poll());
            }
            return val;
        }
    }

    public static void main(String[] args) {
        SORTracker tracker = new SORTracker();
        tracker.add("bradford", 2); // Add location with name="bradford" and score=2 to the system.
        tracker.add("branford", 3); // Add location with name="branford" and score=3 to the system.
        System.out.println(tracker.get());
        tracker.add("alps", 2);
        System.out.println(tracker.get());
        tracker.add("orland", 2);
        System.out.println(tracker.get());
        tracker.add("orlando", 3);
        System.out.println(tracker.get());
        tracker.add("alpine", 2);
        System.out.println(tracker.get());
        System.out.println(tracker.get());
    }
}






