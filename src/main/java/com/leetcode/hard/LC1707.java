package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LC1707 {

    static class Data {
        int x;
        int m;
        int idx;

        public Data(int x, int m, int idx) {
            this.x = x;
            this.m = m;
            this.idx = idx;
        }
    }

    static class Trie {
        Trie[] t = new Trie[2];

        void insert(String str, int idx) {
            if (idx >= str.length()) {
                return;
            }
            char ch = str.charAt(idx);
            int i = (ch == '0' ? 0 : 1);
            if (t[i] == null) {
                t[i] = new Trie();
            }
            t[i].insert(str, idx + 1);
        }

        int max(String str, int idx, StringBuffer buff) {
            if (idx >= str.length()) {
                return Integer.parseInt(buff.toString(), 2);
            }
            char ch = str.charAt(idx);
            int i = (ch == '0' ? 0 : 1);
            if (i == 0 && t[1] != null) {
                buff.append('1');
                return t[1].max(str, idx + 1, buff);
            } else if (i == 1 && t[0] != null) {
                buff.append('1');
                return t[0].max(str, idx + 1, buff);
            } else {
                buff.append('0');
                return t[i].max(str, idx + 1, buff);
            }
        }

        public boolean isEmpty() {
            return t[0] == null && t[1] == null;
        }
    }

    private String getStr(int input) {
        String result = Integer.toBinaryString(input);
        return String.format("%4s", result).replaceAll(" ", "0");
    }

    public int[] maximizeXor(int[] nums, int[][] queries) {
        Arrays.sort(nums);
        List<Data> list = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            list.add(new Data(queries[i][0], queries[i][1], i));
        }
        Trie trie = new Trie();
        list.sort(Comparator.comparingInt(o -> o.m));
        int j = 0;
        int[] ans = new int[queries.length];
        for (int i = 0; i < list.size(); ) {
            Data data = list.get(i);
            if (data.m >= nums[j]) {
                trie.insert(getStr(nums[j]), 0);
                j++;
            } else if (trie.isEmpty()) {
                ans[data.idx] = -1;
                i++;
            } else {
                int currMax = trie.max(getStr(data.x), 0, new StringBuffer());
                ans[data.idx] = currMax;
                i++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 2, 4, 6, 6, 3};
        int[][] queries = Utils.convertToTwoDIntArray("[[12,4],[8,1],[6,3]]");
        LC1707 l = new LC1707();
        int[] ret = l.maximizeXor(nums, queries);
        System.out.println(Arrays.toString(ret));
    }
}
