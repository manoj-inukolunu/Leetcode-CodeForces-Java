package com.algorithms.graph;

public class DSU {

    int[] parent;

    public int find(int num) {
        while (parent[num] != -1) {
            num = parent[num];
        }
        return num;
    }

    public int union(int num1, int num2) {
        int p1 = find(num1);
        int p2 = find(num2);
        parent[p1] = p2;
        return p1;
    }
}






