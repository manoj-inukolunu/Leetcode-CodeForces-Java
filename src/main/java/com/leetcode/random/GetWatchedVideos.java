package com.leetcode.random;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class GetWatchedVideos {


  static class Pair {

    int level;
    int friend;

    public Pair(int friend, int level) {
      this.level = level;
      this.friend = friend;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Pair pair = (Pair) o;
      return friend == pair.friend;
    }

    @Override
    public int hashCode() {
      return Objects.hash(friend);
    }
  }

  public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
    Queue<Pair> queue = new LinkedList<>();
    queue.add(new Pair(id, 0));
    HashSet<Pair> visited = new HashSet<>();
    HashMap<String, Integer> frequencies = new HashMap<>();
    while (!queue.isEmpty()) {
      Pair curr = queue.poll();
      if (curr.level == level) {
        if (!visited.contains(curr)) {
          Set<String> watchedCurr = new HashSet<>(watchedVideos.get(curr.friend));
          for (String vid : watchedCurr) {
            frequencies.put(vid, frequencies.getOrDefault(vid, 0) + 1);
          }
        }
      } else {
        if (!visited.contains(curr)) {
          visited.add(curr);
          for (int friend : friends[curr.friend]) {
            queue.add(new Pair(friend, curr.level + 1));
          }
        }
      }
    }
    TreeMap<Integer, TreeSet<String>> map = new TreeMap<>();
    for (String key : frequencies.keySet()) {
      Integer count = frequencies.get(key);
      TreeSet<String> set = map.getOrDefault(count, new TreeSet<>());
      set.add(key);
      map.put(count, set);
    }

    List<String> ans = new ArrayList<>();
    for (int key : map.navigableKeySet()) {
      ans.addAll(map.get(key));
    }

    return ans;
  }

  public static void main(String args[]) {
    GetWatchedVideos g = new GetWatchedVideos();
    int[][] friends = new int[][]{
        {1, 2}, {0, 3}, {0, 3}, {1, 2}
    };
    List<List<String>> list = Lists.newArrayList(Lists.newArrayList("A", "B"), Lists.newArrayList("C"), Lists.newArrayList("B", "C"),
        Lists.newArrayList("D"));

    System.out.println(g.watchedVideosByFriends(list, friends, 0, 2));

    HashSet<Pair> visited = new HashSet<>();
    visited.add(new Pair(1, 1));
    System.out.println(visited.contains(new Pair(1, 2)));
  }

}
