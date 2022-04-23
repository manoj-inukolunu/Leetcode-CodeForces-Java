package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class LC1847 {

    public int[] closestRoom(int[][] rooms, int[][] queries) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        TreeSet<Integer> roomIds = new TreeSet<>();
        for (int[] room : rooms) {
            pq.add(room);
            roomIds.add(room[0]);
        }
        int[][] arr = new int[queries.length][3];
        for (int i = 0; i < queries.length; i++) {
            arr[i] = new int[]{i, queries[i][0], queries[i][1]};
        }
        Arrays.sort(arr, (o1, o2) -> Integer.compare(o1[2], o2[2]));
        int[] ans = new int[queries.length];
        for (int i = 0; i < arr.length; i++) {
            int size = arr[i][2];
            int room = arr[i][1];
            int idx = arr[i][0];
            while (!pq.isEmpty() && pq.peek()[1] < size) {
                int[] val = pq.poll();
                roomIds.remove(val[0]);
            }
            if (roomIds.isEmpty()) {
                ans[idx] = -1;
            } else {
                Integer lower = roomIds.floor(room);
                Integer higher = roomIds.ceiling(room);
                if (lower == room || higher == room) {
                    ans[idx] = room;
                } else if (Math.abs(room - lower) == Math.abs(room - higher)) {
                    ans[idx] = lower;
                } else if (Math.abs(room - lower) < Math.abs(room - higher)) {
                    ans[idx] = lower;
                } else {
                    ans[idx] = higher;
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        LC1847 l = new LC1847();
        int[][] arr = Utils.convertToTwoDIntArray("[[1,4],[2,3],[3,5],[4,1],[5,2]]");
        int[][] queries = Utils.convertToTwoDIntArray("[[2,3],[2,4],[2,5]]");
        System.out.println(Arrays.toString(l.closestRoom(arr, queries)));

        int[] rooms = new int[]{4, 5, 2, 1, 3};
        System.out.println(Arrays.binarySearch(rooms, 2, 5, 2));
    }
}






