package com.leetcode.random3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder2 {

  class Solution {

    int min = Integer.MAX_VALUE;
    Set<List<String>> ans = new HashSet<>();
    HashMap<String, Set<String>> map = new HashMap<>();
    HashMap<String, Integer> dist = new HashMap<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
      Set<String> set = new HashSet<>(wordList);
      if (!set.contains(endWord)) {
        return new ArrayList<>();
      }
      bfs(beginWord, endWord, set);
      List<String> hold = new ArrayList<>();
      hold.add(beginWord);
      HashSet<String> vis = new HashSet<>();
      dfs(beginWord, endWord, set, hold, vis);
      List<List<String>> ret = new ArrayList<>();

      for (List<String> list : ans) {
        if (list.size() == min) {
          ret.add(list);
        }
      }
      return ret;
    }

    private void bfs(String beginWord, String endWord, Set<String> set) {
      Queue<String[]> queue = new LinkedList();
      HashSet<String> visited = new HashSet<>();
      queue.add(new String[]{beginWord, "1"});
      dist.put(beginWord, 1);
      while (!queue.isEmpty()) {
        String[] curr = queue.poll();
        if (curr[0].equals(endWord)) {
          min = Integer.parseInt(curr[1]);
          return;
        }
        if (!visited.contains(curr[0])) {
          visited.add(curr[0]);
          Set<String> next = getNext(curr[0], set);
          map.put(curr[0], next);
          for (String str : next) {
            if (!dist.containsKey(str)) {
              dist.put(str, Integer.parseInt(curr[1]) + 1);
            }
            queue.add(new String[]{str, String.valueOf(Integer.parseInt(curr[1]) + 1)});
          }
        }
      }
    }

    private Set<String> getNext(String s, Set<String> set) {
      Set<String> ret = new HashSet<>();
      for (int i = 0; i < s.length(); i++) {
        StringBuffer b = new StringBuffer(s);
        for (char j = 'a'; j <= 'z'; j++) {
          b.setCharAt(i, j);
          if (set.contains(b.toString())) {
            ret.add(b.toString());
          }
          b.setCharAt(i, s.charAt(i));
        }
      }
      return ret;
    }


    private void dfs(String curr, String end, Set<String> words, List<String> hold, Set<String> vis) {
      if (curr.equals(end)) {
        ans.add(new ArrayList<>(hold));
        return;
      }
      if (map.containsKey(curr)) {
        for (String str : map.get(curr)) {
          if (!vis.contains(str) && dist.get(str) == dist.get(curr) + 1) {
            hold.add(str);
            vis.add(str);
            if (hold.size() <= min) {
              dfs(str, end, words, hold, vis);
            }
            hold.remove(hold.size() - 1);
            vis.remove(str);
          }
        }
      }

    }
  }
/*

  public static void main(String args[]) {
    WordLadder2 w = new WordLadder2();
    List<String> words = Lists.newArrayList("hot", "dot", "dog", "lot", "log", "cog");
    System.out.println(w.findLadders("hit", "cog", words));
  }
*/

}
