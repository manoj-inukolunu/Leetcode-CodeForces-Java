package com.leetcode.hard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class LC854 {

    class Pair {
        String str;
        int count;

        @Override
        public String toString() {
            return "Pair{" +
                    "str='" + str + '\'' +
                    ", count=" + count +
                    '}';
        }

        public Pair(String str, int count) {
            this.str = str;
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return Objects.equals(str, pair.str);
        }

        @Override
        public int hashCode() {
            return Objects.hash(str);
        }
    }

    public int kSimilarity(String s1, String s2) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(s1, 0));
        HashSet<Pair> visited = new HashSet<>();
        HashSet<Pair> onQueue = new HashSet<>();
        while (!queue.isEmpty()) {
//            System.out.println(queue.size());
            Pair pair = queue.poll();
            onQueue.remove(pair);
            if (pair.count >= 20) {
                continue;
            }
            if (pair.str.equals(s2)) {
                return pair.count;
            }
            if (!visited.contains(pair)) {
                visited.add(pair);
                StringBuilder buffer = new StringBuilder(pair.str);
                Set<Pair> nextSet = new HashSet<>();
                int idx = 0;
                for (int i = 0; i < pair.str.length(); i++) {
                    if (pair.str.charAt(i) != s2.charAt(i)) {
                        idx = i;
                        break;
                    }
                }
                char[] T = pair.str.toCharArray();
                for (int j = idx; j < pair.str.length(); j++) {
                    if (pair.str.charAt(j) == s2.charAt(idx)) {
                        swap(T, idx, j);
                        queue.add(new Pair(new String(T), pair.count + 1));
                        swap(T, j, idx);
                    }
                }
            }
        }
        return -1;
    }

    private void swap(char[] t, int idx, int j) {
        char temp = t[idx];
        t[idx] = t[j];
        t[j] = temp;
    }

    public static void main(String[] args) {
        LC854 l = new LC854();
        /*System.out.println(l.kSimilarity("abccaacceecdeea",
                "bcaacceeccdeaae"));*/
        int one = 10;
        for (int i = 1; i < 1e6; i++) {
            one++;
        }
        System.out.println(one);
    }
}






