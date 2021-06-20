package com.leetcode.random4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinNumPeopleTeach {

  public int minimumTeachings(int n, int[][] languages, int[][] friendships) {

    HashMap<Integer, Set<Integer>> map = new HashMap<>();
    for (int i = 0; i < languages.length; i++) {
      Set<Integer> set = map.getOrDefault(i + 1, new HashSet<>());
      for (int j = 0; j < languages[i].length; j++) {
        set.add(languages[i][j]);
      }
      map.put(i + 1, set);
    }
    boolean[][] talk = new boolean[5][5];
    HashMap<Integer, List<Integer>> fMap = new HashMap<>();
    top:
    for (int i = 0; i < friendships.length; i++) {
      int first = friendships[i][0];
      int second = friendships[i][1];
      List<Integer> list = fMap.getOrDefault(first, new ArrayList<>());
      list.add(second);
      fMap.put(first, list);
      int[] aLang = languages[first - 1];
      for (int j = 0; j < aLang.length; j++) {
        if (map.get(second).contains(aLang[j])) {
          talk[first][second] = true;
          continue top;
        }
      }
    }
    int ans = Integer.MAX_VALUE;
    for (int language = 1; language <= n; language++) {
      HashSet<Integer> taught = new HashSet<>();
      for (int key : fMap.keySet()) {
        List<Integer> friends = fMap.get(key);
        for (int j = 0; j < friends.size(); j++) {
          if (!talk[key][friends.get(j)]) {
            if (map.get(key).contains(language) && !map.get(friends.get(j)).contains(language)) {
              taught.add(friends.get(j));
            } else if (map.get(friends.get(j)).contains(language) && !map.get(key).contains(language)) {
              taught.add(key);
            } else {
              taught.add(key);
              taught.add(friends.get(j));
            }
          }
        }
      }
      ans = Math.min(ans, taught.size());
    }
    return ans == Integer.MAX_VALUE ? 0 : ans;
  }

  public static void main(String args[]) {
    MinNumPeopleTeach m = new MinNumPeopleTeach();
    int n = 3;
    int[][] languages = new int[][]{
        {2}, {1, 3}, {1, 2}, {3}
    };
    int[][] friendships = new int[][]{
        {1, 4}, {1, 2}, {3, 4}, {2, 3}
    };
    System.out.println(m.minimumTeachings(n, languages, friendships));
  }

}
