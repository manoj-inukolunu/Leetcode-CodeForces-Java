package com.leetcode.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PartitionLables {


  public List<Integer> partitionLabels(String S) {

    int start = 0, end;
    HashMap<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < S.length(); i++) {
      map.put(S.charAt(i), i);
    }
    List<Integer> ans = new ArrayList<>();
    while (start < S.length()) {
      char ch = S.charAt(start);
      end = map.get(ch);
      int i = start;
      while (i <= end) {
        if (map.get(S.charAt(i)) > end) {
          end = map.get(S.charAt(i));
        }
        i++;
      }
      ans.add(end - start + 1);
      start = end;
      start++;
    }
    return ans;
  }

  public static void main(String args[]) {
    PartitionLables p = new PartitionLables();
    System.out.println(p.partitionLabels("zababcbacadefegdehijhklij"));
  }


}
