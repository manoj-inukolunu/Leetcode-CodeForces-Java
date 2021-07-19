package com.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class LC1902 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    //5,3,4,1,2,8,7,9,6
    //1,2,3,4,5,6,7,8,9

    //2,1,3,4
    //1,2,3,4
    public int maxDepthBST(int[] order) {
        List<Integer> list = new ArrayList<>();
        for (int num : order) {
            list.add(num);
        }
        return go(list);
    }

    private int go(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return 0;
        }
        if (isMono(list)) {
            return list.size();
        }
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int root = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < root) {
                left.add(list.get(i));
            } else {
                right.add(list.get(i));
            }
        }
        return Math.max(go(left), go(right)) + 1;
    }

    private boolean isMono(List<Integer> list) {
        int i = 0;
        while (i < list.size() - 1 && list.get(i) < list.get(i + 1)) i++;
        if (i == list.size() - 1) return true;

        i = 0;
        while (i < list.size() - 1 && list.get(i) > list.get(i + 1)) i++;
        if (i == list.size() - 1) return true;
        return false;
    }

}
