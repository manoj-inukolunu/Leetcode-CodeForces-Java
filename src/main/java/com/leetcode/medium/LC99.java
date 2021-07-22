package com.leetcode.medium;

import com.leetcode.dfs.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LC99 {
    public void recoverTree(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        dfs(root, list);
        List<TreeNode> prev = new ArrayList<>(list);
        Collections.sort(list, Comparator.comparingInt(value -> value.val));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).val != prev.get(i).val) {
                int val = list.get(i).val;
                list.get(i).val = prev.get(i).val;
                prev.get(i).val = val;
                break;
            }
        }
    }

    private void dfs(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        dfs(root.left, list);
        list.add(root);
        dfs(root.right, list);
    }
}
