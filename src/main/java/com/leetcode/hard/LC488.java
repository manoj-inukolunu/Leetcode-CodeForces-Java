package com.leetcode.hard;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LC488 {

    Set<String> vis = new HashSet<>();
    Set<String> hVis = new HashSet<>();

    public int findMinStep(String board, String hand) {
        StringBuffer b = new StringBuffer(board);
        int val = dfs(b, new StringBuffer(hand), 0);
        return val == Integer.MAX_VALUE ? -1 : val;
    }

    private int dfs(StringBuffer board, StringBuffer hand, int insert) {
        if (board.length() == 0) {
            return insert;
        }
        if (hand.length() == 0) {
            return Integer.MAX_VALUE;
        }
        if (vis.contains(board.toString()) && hVis.contains(hand.toString())) {
            return Integer.MAX_VALUE;
        }
        vis.add(board.toString());
        hVis.add(hand.toString());
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < hand.length(); i++) {
            char ch = hand.charAt(i);
            hand.deleteCharAt(i);
            for (int j = 0; j < board.length(); j++) {
                board.insert(j, ch);
                StringBuffer buffer = collapse(board);
                int val = dfs(buffer, hand, insert + 1);
                if (val != Integer.MAX_VALUE) {
                    min = Math.min(min, val);
                }
                board.deleteCharAt(j);
            }
            hand.insert(i, ch);
        }
        return min;
    }

    StringBuffer collapse(StringBuffer buffer) {
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < buffer.length(); i++) {
            char ch = buffer.charAt(i);
            if (stack.isEmpty()) {
                stack.push(new int[]{ch - 'A', 1});
            } else if (stack.peek()[0] != ch - 'A' && stack.peek()[1] >= 3) {
                int k = stack.peek()[1];
                while (k > 0) {
                    stack.pop();
                    k--;
                }
                if (!stack.isEmpty()) {
                    int[] top = stack.peek();
                    if (top[0] == ch - 'A') {
                        stack.push(new int[]{top[0], 1 + top[1]});
                    } else {
                        stack.push(new int[]{ch - 'A', 1});
                    }
                } else {
                    stack.push(new int[]{ch - 'A', 1});
                }
            } else if (stack.peek()[0] == ch - 'A') {
                int[] top = stack.peek();
                stack.push(new int[]{top[0], 1 + top[1]});
            } else {
                stack.push(new int[]{ch - 'A', 1});
            }
        }
        StringBuffer ret = new StringBuffer();
        if (!stack.isEmpty() && stack.peek()[1] >= 3) {
            int k = stack.peek()[1];
            while (k-- > 0) {
                stack.pop();
            }
        }
        while (!stack.isEmpty()) {
            ret.insert(0, (char) ('A' + stack.pop()[0]));
        }
        return ret;
    }

    public static void main(String[] args) {
        LC488 l = new LC488();
        System.out.println(l.findMinStep("RBYYBBRRB", "YRBGB"));
    }
}
