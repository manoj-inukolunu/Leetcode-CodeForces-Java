package com.leetcode.hard;

import java.util.List;

public class LC546 {

    class Pair {
        int num;
        int count;

        public Pair(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public String toString() {
            return "(" + num + "," + count + ")";
        }
    }

    public int removeBoxes(int[] boxes) {
        return solve(boxes, 0, boxes.length - 1, 1);
    }



    private int solve(int[] arr, int start, int end, int len) {
        if (start > end) {
            return 0;
        }
        if (start == end) {
            return 1;
        }
        int best = (len * len) + (solve(arr, start + 1, end, len));
        for (int i = start + 1; i <= end; i++) {
            if (arr[start] == arr[i]) {
                best = Math.max(best, solve(arr, start + 1, i - 1, 0) + solve(arr, start, end, len + 1));
            }
        }
        return best;
    }

    private int solve(List<Pair> list, int start, int end) {
        if (start > end || start < 0 || start > list.size()) {
            return 0;
        }
        if (start == end) {
            return list.get(start).count * list.get(start).count;
        }
        int best = Integer.MIN_VALUE;
        for (int num = 1; num <= 100; num++) {
            //merge all num
            int prev = -1;
            int merge = 0, count = 0;
            for (int j = start; j <= end; j++) {
                if (list.get(j).num == num && prev != -1) {
                    merge += solve(list, prev + 1, j - 1);
                    prev = j;
                    count += list.get(j).count;
                } else if (prev == -1 && list.get(j).num == num) {
                    prev = j;
                    count += list.get(j).count;
                    merge += solve(list, start, j - 1);
                }
            }
            if (prev != -1) {
                merge += solve(list, prev + 1, end);
            }
            best = Math.max(best, merge + (count * count));
        }
        return best;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 1, 3, 10, 1, 3};
        int[] arr = new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1};
        int[] arr2 = new int[]{6, 10, 1, 7, 1, 3, 10, 2, 1, 3};
        LC546 l = new LC546();
        System.out.println(l.removeBoxes(arr2));
    }
}
