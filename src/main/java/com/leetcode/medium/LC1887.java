package com.leetcode.medium;

import java.util.PriorityQueue;

public class LC1887 {


    public int reductionOperations(int[] nums) {
        int[] arr = new int[50001];
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            min = Math.min(num, min);
            arr[num]++;
        }
        PriorityQueue<int[]> p = new PriorityQueue<>((o1, o2) -> -Integer.compare(o1[0], o2[0]));
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                p.add(new int[]{i, arr[i]});
            }
        }
        int count = 0;
        while (p.size() != 1 && !p.isEmpty()) {
            int[] first = p.poll();
            int[] second = p.poll();
            second[1] += first[1];
            count += first[1];
            p.add(second);
        }
        return count;
    }

    public static void main(String args[]) {
        LC1887 l = new LC1887();
        int[] arr1 = new int[]{5, 1, 3};
        int[] arr2 = new int[]{1, 1, 1};
        int[] arr3 = new int[]{1, 1, 2, 2, 3};
        int[] arr4 = new int[]{1, 1, 2, 2, 3, 123, 3, 2, 1};
        System.out.println(l.reductionOperations(arr1));
        System.out.println(l.reductionOperations(arr2));
        System.out.println(l.reductionOperations(arr3));
        System.out.println(l.reductionOperations(arr4));
    }
}
