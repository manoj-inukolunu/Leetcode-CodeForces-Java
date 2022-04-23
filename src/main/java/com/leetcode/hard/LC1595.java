package com.leetcode.hard;

import com.leetcode.common.Utils;
import java.util.List;

public class LC1595 {

    public int connectTwoGroups(List<List<Integer>> cost) {
        return solve(0, 0, 0, 0, cost);
    }

    private int solve(int point1, int included, int point2, int state2, List<List<Integer>> cost) {
        if (point1 >= cost.size()) {
            return state2 == (1 << cost.get(0).size()) - 1 ? 0 : Integer.MAX_VALUE / 2;
        }
        if (point2 >= cost.get(0).size()) {
            if (included == 1) {
                return solve(point1 + 1, 0, 0, state2, cost);
            }
            return Integer.MAX_VALUE / 2;
        }
        int best = Integer.MAX_VALUE / 2;
        if (included == 1) {
            best = Math.min(best, solve(point1 + 1, 0, 0, state2, cost));
        }
        best = Math.min(best, solve(point1, included, point2 + 1, state2, cost));
        best = Math.min(best, cost.get(point1).get(point2) + solve(point1, 1, point2 + 1, state2 | (1 << point2),
                cost));
        return best;
    }

    public static void main(String[] args) {
        LC1595 l = new LC1595();
        List<List<Integer>> data = Utils.convertToTwoDIntList("[[1, 3, 5], [4, 1, 1], [1, 5, 3]]");
        System.out.println(data);

        System.out.println(l.connectTwoGroups(data));
    }
}






