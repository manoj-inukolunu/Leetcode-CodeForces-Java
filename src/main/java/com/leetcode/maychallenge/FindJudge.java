package com.leetcode.maychallenge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author manoji on 5/10/20.
 */
public class FindJudge {

  public int findJudge(int N, int[][] trust) {

    HashMap<Integer, Set<Integer>> map = new HashMap<>();

    for (int[] row : trust) {
      if (map.containsKey(row[0])) {
        Set<Integer> trusts = map.get(row[0]);
        trusts.add(row[1]);
        map.put(row[0], trusts);
      } else {
        Set<Integer> trusts = new HashSet<>();
        trusts.add(row[1]);
        map.put(row[0], trusts);
      }
    }

    System.out.println(map);
    List<Integer> list = map.keySet().stream().collect(Collectors.toList());

    for (int i = 0; i < list.size(); i++) {
      int person = list.get(i);
      if (!map.containsKey(person)) {
        //candidate judge
        for (int other : map.keySet()) {
          if (other != person && map.get(other).contains(person)) {
            continue;
          } else {
            return -1;
          }
        }
        return person;
      }
    }

    return -1;

  }


  public static void main(String args[]) {
    FindJudge f = new FindJudge();
    System.out.println(f.findJudge(2, new int[][]{{1, 2}}));
  }
}
