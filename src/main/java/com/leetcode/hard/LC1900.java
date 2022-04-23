package com.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class LC1900 {

    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        int min = solveMin(list, firstPlayer, secondPlayer);
        map.clear();
        int max = solveMax(list, firstPlayer, secondPlayer);
        return new int[]{min + 1, max + 1};
    }

    HashMap<String, Integer> map = new HashMap<>();

    private int solveMin(List<Integer> list, int firstPlayer, int secondPlayer) {
        if (isGameOver(list, firstPlayer, secondPlayer)) {
            return 0;
        }
        if (map.containsKey(list.toString())) {
            return map.get(list.toString());
        }
        int minRound = Integer.MAX_VALUE / 2;
        AllStatesGenerator generator = new AllStatesGenerator();
        generator.allStates(list, 0, list.size() - 1, firstPlayer, secondPlayer, new ArrayList<>());
        List<List<Integer>> states = generator.total;
        if (generator.gameOver) {
            return 0;
        }
        for (List<Integer> state : states) {
            List<Integer> l = new ArrayList<>(state);
            Collections.sort(l);
            minRound = Math.min(minRound, 1 + solveMin(l, firstPlayer, secondPlayer));
        }
        map.put(list.toString(), minRound);
        return minRound;
    }

    private int solveMax(List<Integer> list, int firstPlayer, int secondPlayer) {
        if (isGameOver(list, firstPlayer, secondPlayer)) {
            return 0;
        }
        if (map.containsKey(list.toString())) {
            return map.get(list.toString());
        }
        int maxRound = Integer.MIN_VALUE / 2;
        AllStatesGenerator generator = new AllStatesGenerator();
        generator.allStates(list, 0, list.size() - 1, firstPlayer, secondPlayer, new ArrayList<>());
        List<List<Integer>> states = generator.total;
        if (generator.gameOver) {
            return 0;
        }
        for (List<Integer> state : states) {
            List<Integer> l = new ArrayList<>(state);
            Collections.sort(l);
            maxRound = Math.max(maxRound, 1 + solveMax(l, firstPlayer, secondPlayer));
        }
        map.put(list.toString(), maxRound);
        return maxRound;
    }

    private boolean isGameOver(List<Integer> list, int firstPlayer, int secondPlayer) {
        int start = 0, end = list.size() - 1;
        while (start <= end) {
            if (list.get(start) == firstPlayer && list.get(end) == secondPlayer) {
                return true;
            }
            start++;
            end--;
        }
        return false;
    }

    static class AllStatesGenerator {
        List<List<Integer>> total = new ArrayList<>();
        boolean gameOver = false;

        boolean isFirstOrSecond(int p1, int p2, int first, int second) {
            return p1 == first || p1 == second || p2 == first || p2 == second;
        }

        private int winner(int p1, int p2, int first, int second) {
            if (p1 == first || p1 == second) {
                return p1;
            }
            return p2;
        }

        private void allStates(List<Integer> list, int start, int end, int first, int second,
                               List<Integer> curr) {
            if (list.size() % 2 == 0 && start > end) {
                total.add(new ArrayList<>(curr));
                return;
            }
            if (list.size() % 2 != 0 && start == end) {
                curr.add(list.get(start));
                total.add(new ArrayList<>(curr));
                return;
            }
            if (start > end) {
                return;
            }
            int player1 = list.get(start);
            int player2 = list.get(end);
            if ((player1 == first && player2 == second) || (player1 == second && player2 == first)) {
                gameOver = true;
                return;
            }
            if (isFirstOrSecond(player1, player2, first, second)) {
                List<Integer> next = new ArrayList<>(curr);
                next.add(winner(player1, player2, first, second));
                allStates(list, start + 1, end - 1, first, second, next);
            } else {
                List<Integer> startList = new ArrayList<>(curr);
                startList.add(list.get(start));
                allStates(list, start + 1, end - 1, first, second, startList);
                List<Integer> endList = new ArrayList<>(curr);
                endList.add(list.get(end));
                allStates(list, start + 1, end - 1, first, second, endList);
            }
        }
    }


    public static void main(String[] args) {
        LC1900 l = new LC1900();
        System.out.println(Arrays.toString(l.earliestAndLatest(11, 2, 4)));
    }
}
