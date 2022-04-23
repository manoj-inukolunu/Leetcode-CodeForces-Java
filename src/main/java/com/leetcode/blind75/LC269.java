package com.leetcode.blind75;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LC269 {
    public String alienOrder(String[] words) {
        HashMap<Character, Set<Character>> graph = new HashMap<>();
        for (String str : words) {
            for (char ch : str.toCharArray()) {
                graph.put(ch, new HashSet<>());
            }
        }
        for (int i = 0; i + 1 < words.length; i++) {
            String curr = words[i];
            String next = words[i + 1];
            for (int j = 0; j < curr.length(); j++) {
                if (j < next.length()) {
                    if (curr.charAt(j) != next.charAt(j)) {
                        Set<Character> set = graph.get(curr.charAt(j));
                        set.add(next.charAt(j));
                        graph.put(curr.charAt(j), set);
                        break;
                    }
                }
            }

        }
        int[] indegree = new int[26];
        for (char ch : graph.keySet()) {
            for (char c : graph.get(ch)) {
                indegree[c - 'a']++;
            }
        }
        StringBuilder b = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0 && graph.containsKey((char) (i + (int) 'a'))) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            Integer curr = queue.poll();
            b.append((char) (curr + (int) 'a'));
            for (char next : graph.get((char) (curr + (int) 'a'))) {
                indegree[next - 'a']--;
                if (indegree[next - 'a'] == 0) {
                    queue.add(next - 'a');
                }
            }
        }
        if (b.length() != graph.size()) {
            return "";
        }
        return b.toString();
    }

    public static void main(String[] args) {
        LC269 l = new LC269();
        System.out.println(l.alienOrder(new String[]{"abc", "ab"}));
    }
}






