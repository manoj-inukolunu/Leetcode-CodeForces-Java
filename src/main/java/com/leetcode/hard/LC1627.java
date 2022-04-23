package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC1627 {

    class Dsu {
        int[] parent;

        public Dsu(int n) {
            parent = new int[n + 1];
            Arrays.fill(parent, -1);
        }

        public int find(int num) {
            if (parent[num] == -1) {
                return num;
            }
            parent[num] = find(parent[num]);
            return parent[num];
        }

        public void union(int a, int b) {
            int p1 = find(a);
            int p2 = find(b);
            if (p1 != p2) {
                parent[p2] = p1;
            }
        }
    }

    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        Dsu d = new Dsu(n);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    int divisor2 = i / j;
                    if (j > threshold) {
                        d.union(j, i);
                    }
                    if (divisor2 > threshold) {
                        d.union(divisor2, i);
                    }
                }
            }
        }
        List<Boolean> ans = new ArrayList<>();
        for (int[] query : queries) {
            int p1 = d.find(query[0]);
            int p2 = d.find(query[1]);
            if (p1 == p2) {
                ans.add(true);
            } else {
                ans.add(false);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LC1627 l = new LC1627();
        System.out.println(l.areConnected(5, 1, Utils.convertToTwoDIntArray("[[4,5],[4,5],[3,2],[2,3],[3,4]]")));
    }
}






