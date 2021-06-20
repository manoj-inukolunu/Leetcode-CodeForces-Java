package com.leetcode.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class RankTeam {

  public String rankTeams(String[] votes) {
    HashMap<Character, Integer[]> map = new HashMap<>();
    for (int i = 0; i < votes.length; i++) {
      String str = votes[i];
      for (int j = 0; j < str.length(); j++) {
        Integer[] curr = map.get(str.charAt(j));
        if (curr == null) {
          curr = new Integer[votes[i].length() + 1];
          Arrays.fill(curr, 0);
        }
        curr[j + 1]++;
        map.put(str.charAt(j), curr);
      }
    }
    List<Entry<Character, Integer[]>> list = new ArrayList<>(map.entrySet());
    Collections.sort(list, (Comparator<Entry<Character, Integer[]>>) (o1, o2) -> {
      Integer[] o11 = o1.getValue();
      Integer[] o22 = o2.getValue();
      for (int i = 0; i < o11.length; i++) {
        if (o11[i] > o22[i]) {
          return 1;
        } else if (o11[i] < o22[i]) {
          return -1;
        }
      }
      return -o1.getKey().compareTo(o2.getKey());
    });

    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < list.size(); i++) {
      buffer.append(list.get(i).getKey());
    }

    return buffer.reverse().toString();
  }

  public static void main(String args[]) {
    RankTeam r = new RankTeam();
    System.out.println(r.rankTeams(new String[]{"M", "M", "M", "M"}));
  }

}
