package com.leetcode.Tree.medium;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SmallestCommonRegion {


  public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
    HashSet<String> tree = new HashSet<>();
    HashMap<String, String> parentMap = new HashMap<>();
    for (int i = 0; i < regions.size(); i++) {
      String parent = regions.get(i).get(0);
      tree.add(parent);
      for (int j = 1; j < regions.get(i).size(); j++) {
        parentMap.put(regions.get(i).get(j), parent);
        tree.add(regions.get(i).get(j));
      }
    }
    String root = null;
    for (String key : tree) {
      if (!parentMap.containsKey(key)) {
        root = key;
        break;
      }
    }
    Set<String> path = new HashSet<>();
    while (region1 != null) {
      path.add(region1);
      region1 = parentMap.get(region1);
    }
    while (region2 != null) {
      if (path.contains(region2)) {
        return region2;
      } else {
        region2 = parentMap.get(region2);
      }
    }
    return root;
  }

  public static void main(String args[]) {
    List<List<String>> regions = Lists.newArrayList(Lists.newArrayList("Earth", "North America", "South America"),
        Lists.newArrayList("North America", "United States", "Canada"),
        Lists.newArrayList("United States", "New York", "Boston"),
        Lists.newArrayList("Canada", "Ontario", "Quebec"),
        Lists.newArrayList("South America", "Brazil"));

    SmallestCommonRegion s = new SmallestCommonRegion();
    System.out.println(s.findSmallestRegion(regions, "Canada", "Quebec"));
  }

}
