package com.leetcode.hard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LC1900 {

    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        StringBuffer state = new StringBuffer();
        int tn = n;
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            if (tn > 0) {
                state.append(ch);
                tn--;
            }
        }
        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (tn > 0) {
                state.append(ch);
                tn--;
            }
        }
        int min = solve(state.toString(), state.charAt(firstPlayer - 1), state.charAt(secondPlayer - 1));
        int max = solveM(state.toString(), state.charAt(firstPlayer - 1), state.charAt(secondPlayer - 1));
        return new int[]{min, max};
    }

    private int solveM(String currState, char first, char second) {
        if (compete(currState, first, second)) {
            return 0;
        }
        StringBuffer curr = new StringBuffer(currState);
        Set<String> nextSet = new HashSet<>();
        if (currState.length() % 2 != 0) {
            char mid = curr.charAt(currState.length() / 2);
            curr.deleteCharAt(currState.length() / 2);
            StringBuffer buffer = new StringBuffer();
            buffer.append(mid);
            nextStates(curr, buffer, 0, nextSet);
        } else {
            nextStates(curr, new StringBuffer(), 0, nextSet);
        }
        int best = Integer.MIN_VALUE / 2;
        for (String state : nextSet) {
            best = Math.max(best, 1 + solve(state, first, second));
        }
        return best;
    }

    private int solve(String currState, char first, char second) {
        if (compete(currState, first, second)) {
            return 0;
        }
        StringBuffer curr = new StringBuffer(currState);
        Set<String> nextSet = new HashSet<>();
        if (currState.length() % 2 != 0) {
            char mid = curr.charAt(currState.length() / 2);
            curr.deleteCharAt(currState.length() / 2);
            StringBuffer buffer = new StringBuffer();
            buffer.append(mid);
            nextStates(curr, buffer, 0, nextSet);
        } else {
            nextStates(curr, new StringBuffer(), 0, nextSet);
        }
        int best = Integer.MAX_VALUE / 2;
        for (String state : nextSet) {
            best = Math.min(best, 1 + solve(state, first, second));
        }
        return best;
    }

    private boolean compete(String state, char first, char second) {
        for (int i = 0; i < state.length() / 2; i++) {
            if (state.charAt(i) == first && state.charAt(state.length() - i - 1) == second) {
                return true;
            }
        }
        return false;
    }

    private void nextStates(StringBuffer curr, StringBuffer hold, int idx, Set<String> set) {
        if (hold.length() == 0 || hold.length() == 1) {
            return;
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(hold.toString());
        while (!queue.isEmpty()) {
            String b = queue.poll();
            if (idx > curr.length() / 2) {
                set.add(b);
            } else {
                queue.add(b + curr.charAt(idx));
                queue.add(b + curr.charAt(curr.length() - 1 - idx));
                idx++;
            }
        }
    }

    public static void main(String[] args) {
        LC1900 l = new LC1900();
        System.out.println(Arrays.toString(l.earliestAndLatest(11, 2, 4)));
    }
}
