package com.leetcode.hard;

import java.util.Arrays;

public class LC927 {

    public int[] threeEqualParts(int[] arr) {
        try {
            StringBuilder last = new StringBuilder();
            int count = 0;
            StringBuilder total = new StringBuilder();
            for (int num : arr) {
                count += (num == 1 ? 1 : 0);
                total.append(num);
            }
            if (count % 3 != 0) {
                return new int[]{-1, -1};
            }
            int curr = 0;
            for (int i = arr.length - 1; i >= 0; i--) {
                if (arr[i] == 1) {
                    curr++;
                }
                last.insert(0, arr[i]);
                if (curr == count / 3) {
                    break;
                }
            }
            int lastBegin = total.lastIndexOf(last.toString());
            int firstBegin = total.indexOf(last.toString());
            if (firstBegin == -1 || firstBegin == lastBegin) {
                return new int[]{-1, -1};
            }
            String mid = total.substring(firstBegin + last.length(), lastBegin);
            int midIdx = mid.indexOf(last.toString());
            if (midIdx == -1) {
                return new int[]{-1, -1};
            }
            String leftOver = mid.substring(midIdx + last.length());
            if (leftOver.contains("1")) {
                return new int[]{-1, -1};
            }
            return new int[]{firstBegin + last.length() - 1, total.length() - last.length() - leftOver.length()};
        } catch (StringIndexOutOfBoundsException e) {
            return new int[]{-1, -1};
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1};
        LC927 l = new LC927();
        System.out.println(Arrays.toString(l.threeEqualParts(arr)));
    }
}
