package com.binarysearch;

import java.util.HashMap;

public class RecurVotes {

  public int solve(int[] votes) {
    HashMap<Integer, Integer> visited = new HashMap();
    int aVotes = 0;
    for (int i = 0; i < votes.length; i++) {
      if (votes[i] < 0) {
        visited.put(i, 1);
        aVotes++;
      } else if (votes[i] >= votes.length) {
        visited.put(i, 2);
      } else if (visited.containsKey(i)) {
        int val = visited.get(i);
        if (val == 1) {
          aVotes++;
        }
      } else {
        int val = dfs(votes, i, visited);
        if (val == 1) {
          aVotes++;
        }
        visited.put(i, val);
      }
    }
    return aVotes;
  }

  private int dfs(int[] votes, int idx, HashMap<Integer, Integer> map) {
    if (votes[idx] < 0) {
      return 1;
    }
    if (votes[idx] >= votes.length) {
      return 2;
    }
    if (map.containsKey(idx)) {
      return map.get(idx);
    }
    int val = dfs(votes, votes[idx], map);
    map.put(idx, val);
    return val;
  }

  public static void main(String args[]) {
    RecurVotes r = new RecurVotes();
    System.out.println(r.solve(new int[]{2, -1, 5, 1, 3}));
  }

}
