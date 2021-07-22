package com.leetcode.DP.hard;

public class LC828 {

    public int uniqueLetterString(String s) {
        int count = 0;
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            char[] arr = s.toCharArray();
            int prevIdx = -1;
            int currLen = 0;
            for (int i = 0; i < arr.length; i++) {
                char curr = arr[i];
                if (curr == ch && prevIdx != -1) {
                    int totalLength = i - prevIdx;
                    currLen += totalLength;
                } else if (curr == ch) {
                    prevIdx = i;
                }
            }
            if (currLen == 0 && prevIdx != -1) {
                currLen = arr.length;
            }
            count += currLen;
        }
        return count;
    }

    public static void main(String[] args) {
        String s = "ABC";
        LC828 l = new LC828();
        System.out.println(l.uniqueLetterString(s));
    }
}