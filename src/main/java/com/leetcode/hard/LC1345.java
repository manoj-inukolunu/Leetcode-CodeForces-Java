package com.leetcode.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LC1345 {

    public int minJumps(int[] arr) {
        HashMap<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            Set<Integer> set = map.getOrDefault(arr[i], new HashSet<>());
            set.add(i);
            map.put(arr[i], set);
        }

        Queue<int[]> front = new LinkedList<>();
        Queue<int[]> back = new LinkedList<>();
        front.add(new int[]{0, 0});
        back.add(new int[]{arr.length - 1, 0});
        boolean[] vis = new boolean[arr.length];
        Arrays.fill(vis, false);
        boolean[] visL = new boolean[arr.length];
        Arrays.fill(visL, false);
        int min = 0;
        while (!front.isEmpty() && !back.isEmpty()) {
            int[] first = front.poll();
            int[] last = back.poll();
            if (first[0] == arr.length - 1) {
                return first[1];
            }
            if (last[0] == 0) {
                return last[1];
            }
            if (first[0] == last[0]) {
                return Math.min(first[1], last[1]) + 1;
            }

            if (map.get(arr[first[0]]).contains(arr.length - 1)) {
                return first[1] + 1;
            }
            if (map.get(arr[last[0]]).contains(0)) {
                return last[1] + 1;
            }
            if (!vis[first[0]]) {
                vis[first[0]] = true;

                for (int next : map.get(arr[first[0]])) {
                    if (next != first[0]) {
                        front.add(new int[]{next, first[1] + 1});
                    }
                }
                if (first[0] - 1 >= 0) {
                    front.add(new int[]{first[0] - 1, first[1] + 1});
                }
                if (first[0] + 1 < arr.length) {
                    front.add(new int[]{first[0] + 1, first[1] + 1});
                }

            }

            if (!visL[last[0]]) {
                visL[last[0]] = true;
                for (int next : map.get(arr[last[0]])) {
                    if (next != last[0]) {
                        back.add(new int[]{next, last[1] + 1});
                    }
                }
                if (last[0] - 1 >= 0) {
                    back.add(new int[]{last[0] - 1, last[1] + 1});
                }
                if (last[0] + 1 < arr.length) {
                    back.add(new int[]{last[0] + 1, last[1] + 1});
                }
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-12, -86, 27, -61, -4};
        LC1345 l = new LC1345();
        System.out.println(l.minJumps(arr));
    }

}
