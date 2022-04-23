package com.leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class LC1606 {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        PriorityQueue<Long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[0], o2[0]));
        TreeSet<Integer> free = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            free.add(i);
        }
        HashMap<Integer, Integer> serverTime = new HashMap<>();
        for (int i = 0; i < arrival.length; i++) {
            while (!pq.isEmpty() && pq.peek()[0] <= arrival[i]) {
                Long[] curr = pq.poll();
                free.add(Math.toIntExact(curr[1]));
            }
            if (!free.isEmpty()) {
                if (free.contains(i % k)) {
                    pq.add(new Long[]{(long) (arrival[i] + load[i]), (long) (i % k)});
                    serverTime.put(i % k, serverTime.getOrDefault(i % k, 0) + 1);
                    free.remove(i % k);
                } else {
                    Integer server = free.higher(i % k);
                    if (server == null) {
                        server = free.first();
                    }
                    pq.add(new Long[]{(long) (arrival[i] + load[i]), Long.valueOf(server)});
                    free.remove(server);
                    serverTime.put(server, serverTime.getOrDefault(server, 0) + 1);
                }
            }
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(serverTime.entrySet());
        list.sort((o1, o2) -> -Integer.compare(o1.getValue(), o2.getValue()));
        List<Integer> ans = new ArrayList<>();
        int max = list.get(0).getValue();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getValue() == max) {
                ans.add(list.get(i).getKey());
            } else {
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LC1606 l = new LC1606();
        System.out.println(l.busiestServers(2, new int[]{1, 2, 3}, new int[]{1000000000, 1, 1000000000}));
    }
}






