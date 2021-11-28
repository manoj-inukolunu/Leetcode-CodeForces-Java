package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class LC1687 {

    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int[] changes = new int[boxes.length];
        changes[0] = 0;
        int[] weights = new int[boxes.length];
        weights[0] = boxes[0][1];
        for (int i = 1; i < boxes.length; i++) {
            changes[i] = (boxes[i][0] != boxes[i - 1][0]) ? (1 + changes[i - 1]) : changes[i - 1];
            weights[i] = weights[i - 1] + boxes[i][1];
        }
        int[] dp = new int[boxes.length];
        Arrays.fill(dp, -1);
        dp[0] = 2;
        Deque<int[]> deque = new LinkedList<>();
        deque.add(new int[]{0, -changes[0]});
        for (int i = 1; i < boxes.length; i++) {
            int maxW = boxes[i][1];
            int maxB = 1;
            dp[i] = dp[i - 1] + 2;
            int min = Integer.MAX_VALUE / 2;
            for (int j = i - 1; j >= 0 && maxB <= maxBoxes && maxW <= maxWeight; j--) {
                maxB++;
                maxW += boxes[j][1];
                if (maxB > maxBoxes || maxW > maxWeight) {
                    break;
                }
                if (j - 1 < 0) {
                    min = Math.min(-changes[j], min);
                } else {
                    min = Math.min(dp[j - 1] - changes[j], min);
                }
            }
            dp[i] = Math.min(min + (2 + changes[i]), dp[i - 1] + 2);
        }
        return dp[boxes.length - 1];
    }

    public static void main(String[] args) {
        LC1687 l = new LC1687();
        int[][] boxes = Utils.convertToTwoDIntArray("[[1,1],[2,1],[1,1]]");
        int[][] boxes1 = Utils.convertToTwoDIntArray("[[1,2],[3,3],[3,1],[3,1],[2,4]]");
        int[][] boxes2 = Utils.convertToTwoDIntArray("[[1,4],[1,2],[2,1],[2,1],[3,2],[3,4]]");
        int[][] boxes3 = Utils.convertToTwoDIntArray("[[2,4],[2,5],[3,1],[3,2],[3,7],[3,1],[4,4],[1,3],[5,2]]");
        System.out.println(l.boxDelivering(boxes, 2, 3, 3));
    }
}
