package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC2076 {

    class Dsu {
        int[] parent;
        boolean[] exists;

        public Dsu(int num) {
            parent = new int[num];
            Arrays.fill(parent, -1);
            exists = new boolean[num];
        }

        public int find(int num) {
            while (parent[num] != -1) {
                num = parent[num];
            }
            return num;
        }

        public void union(int num1, int num2) {
            int parent1 = find(num1);
            int parent2 = find(num2);
            parent[parent1] = parent2;
            exists[num1] = true;
            exists[num2] = true;
        }
    }

    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        Dsu d = new Dsu(n);
        List<Boolean> ans = new ArrayList<>();
        for (int[] request : requests) {
            d.union(request[0], request[1]);
            boolean valid = true;
            for (int[] restriction : restrictions) {
                int p1 = d.find(restriction[0]);
                int p2 = d.find(restriction[1]);
                if (p1 == p2 && p1 != -1) {
                    valid = false;
                    break;
                }
            }
            ans.add(valid);
        }
        boolean[] ret = new boolean[ans.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = ans.get(i);
        }
        return ret;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] restrictions = Utils.convertToTwoDIntArray("[[0,1],[1,2],[2,3]]");
        int[][] requests = Utils.convertToTwoDIntArray("[[0,4],[1,2],[3,1],[3,4]]");
        LC2076 l = new LC2076();
        System.out.println(Arrays.toString(l.friendRequests(n, restrictions, requests)));
    }
}






