package com.leetcode.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class LC1713 {
    public int minOperations(int[] target, int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            map.put(target[i], i);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                list.add(map.get(arr[i]));
            }
        }
        return target.length - lis(list);

    }

    private int lis(List<Integer> list) {
        if (list.size() == 0) {
            return 0;
        }
        ArrayList<Integer> li = new ArrayList<>();
        li.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            int num = list.get(i);
            if (li.get(li.size() - 1) < num) {
                li.add(num);
            } else {
                int idx = Collections.binarySearch(li, num);
                if (idx < 0) {
                    li.set(-(idx + 1), num);
                } else {
                    li.set(idx, num);
                }
            }
        }
        return li.size();
    }
}
