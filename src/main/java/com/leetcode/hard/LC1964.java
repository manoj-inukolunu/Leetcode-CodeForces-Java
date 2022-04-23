package com.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LC1964 {
    public int[] longestObstacleCourseAtEachPosition(int[] nums) {
        int[] ans = new int[nums.length];
        ArrayList<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        ans[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int value;
            int val = Collections.binarySearch(list, nums[i]);
            if (val < 0) {
                if (-(val + 1) >= list.size()) {
                    list.add(nums[i]);
                    value = list.size();
                } else {
                    list.set(-(val + 1), nums[i]);
                    value = (-(val + 1)) + 1;
                }
            } else {
//                val = last(list, val);
                int retVal = last(list, val);
                System.out.println(list + " " + val + " " + last(list, val) + " " + lastIdx(list, val));
                if (retVal + 1 >= list.size()) {
                    list.add(nums[i]);
                    value = list.size();
                } else {
                    list.set(retVal + 1, nums[i]);
                    value = retVal + 1 + 1;
                }
            }
            ans[i] = value;
        }
        return ans;
    }

    int lastIdx(List<Integer> list, int idx) {
        int val = list.get(idx);
        while (idx < list.size() && list.get(idx) == val) {
            idx++;
        }
        return idx - 1;
    }

    int last(List<Integer> list, int idx) {
        int val = list.get(idx);
        int low = 0, high = list.size() - 1;
        int ret = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (val > list.get(mid)) {
                low = mid + 1;
            } else if (val < list.get(mid)) {
                high = mid - 1;
            } else {
                ret = mid;
                low = mid + 1;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        LC1964 l = new LC1964();
        System.out.println(Arrays.toString(l.longestObstacleCourseAtEachPosition(new int[]{1, 2, 3, 2})));
        List<Integer> list = new ArrayList<>();
        int[] arr = new int[]{1, 2, 2, 2, 2, 3, 4, 7, 8, 8};
        for (int num : arr) {
            list.add(num);
        }
        System.out.println(l.last(list, 1));
        System.out.println(l.lastIdx(list, 1));
    }
}






