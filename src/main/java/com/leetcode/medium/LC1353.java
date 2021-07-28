package com.leetcode.medium;

import java.util.Arrays;
import java.util.TreeSet;

public class LC1353 {

    public int maxEvents(int[][] events) {
        final int[] maxDay = new int[]{events[0][1]};
        Arrays.sort(events, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return Integer.compare(o1[0], o2[0]);
            }
            maxDay[0] = Math.max(maxDay[0], Math.max(o1[1], o2[1]));
            return Integer.compare(o1[1], o2[1]);
        });
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 1; i <= maxDay[0]; i++) {
            set.add(i);
        }
        int count = 0;
        for (int[] event : events) {
            int start = event[0], end = event[1];
            Integer ceiling = set.ceiling(start);
            if (ceiling != null && ceiling <= end) {
                set.remove(ceiling);
                count++;
            }
        }
        return count;
    }
}
