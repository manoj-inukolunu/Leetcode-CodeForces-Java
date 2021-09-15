package com.leetcode.hard;

public class LC493 {


    int count = 0;

    public int reversePairs(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return count;
    }

    private long[] mergeSort(int[] arr, int start, int end) {
        if (start == end) {
            return new long[]{arr[start]};
        }
        int mid = start + (end - start) / 2;
        long[] left = mergeSort(arr, start, mid);
        long[] right = mergeSort(arr, mid + 1, end);

        long[] ret = new long[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                ret[k++] = left[i++];
            } else {
                int idx = first(left, 2 * right[j] + 1);
                if (idx != arr.length) {
                    count += (left.length - idx);
                }
                ret[k++] = right[j++];
            }
        }

        while (i < left.length) {
            ret[k++] = left[i++];
        }
        while (j < right.length) {
            int idx = first(left, 2 * right[j] + 1);
            if (idx != arr.length) {
                count += (left.length - idx);
            }
            ret[k++] = right[j++];
        }
        return ret;
    }

    private int first(long[] arr, long num) {
        int low = 0, high = arr.length - 1, res = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (num < arr[mid]) {
                high = mid - 1;
            } else if (num > arr[mid]) {
                low = mid + 1;
            } else {
                res = mid;
                high = mid - 1;
            }
        }
        return res == -1 ? low : res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 4, 3, 5, 1};
        LC493 l = new LC493();
        System.out.println(l.reversePairs(arr));
    }


}
