package com.leetcode.hard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class LC818 {

    class Data {
        int position;
        int speed;
        int length;

        public Data(int position, int speed, int length) {
            this.position = position;
            this.speed = speed;
            this.length = length;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Data data = (Data) o;
            return position == data.position && speed == data.speed;
        }

        @Override
        public int hashCode() {
            return Objects.hash(position, speed);
        }
    }

    public int racecar(int target) {
        Queue<Data> queue = new LinkedList<>();
        queue.add(new Data(0, 1, 0));
        HashSet<Data> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            Data curr = queue.poll();
            if (curr.position == target) {
                return curr.length;
            }
            if (curr.speed >= 10000 || curr.position < 0 || curr.position > target + 500) {
                continue;
            }
            if (!visited.contains(curr)) {
                visited.add(curr);
                queue.add(new Data(curr.position + curr.speed, curr.speed * 2, curr.length + 1));
                if (curr.speed > 0) {
                    queue.add(new Data(curr.position, -1, curr.length + 1));
                } else {
                    queue.add(new Data(curr.position, 1, curr.length + 1));
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        LC818 r = new LC818();
        System.out.println(r.racecar(10000));
    }
}
