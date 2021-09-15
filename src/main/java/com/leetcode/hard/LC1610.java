package com.leetcode.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LC1610 {
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        List<Double> angles = new ArrayList<>();
        int count = 0;
        for (List<Integer> point : points) {
            int x = point.get(0) - location.get(0);
            int y = point.get(1) - location.get(1);
            if (x == 0 && y == 0) {
                count++;
                continue;
            }
            angles.add(Math.atan2(y, x) * 180 / Math.PI);
        }
        Collections.sort(angles);
        int ans = count;
        List<Double> temp = new ArrayList<>(angles);
        for (double d : angles) {
            temp.add(d + 360);
        }
        for (int i = 0, j = 0; i < temp.size(); i++) {
            while (temp.get(j) - temp.get(i) >= angle) {
                j++;
            }
            ans = Math.max(ans, count + (j - i + 1));
        }
        return ans;
    }
}
