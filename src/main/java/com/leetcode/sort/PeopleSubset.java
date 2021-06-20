package com.leetcode.sort;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PeopleSubset {

  class Pair {

    int index;
    Set<String> data;

    public Pair(int index, Set<String> data) {
      this.index = index;
      this.data = data;
    }
  }

  public List<Integer> peopleIndexes(List<List<String>> fav) {
    List<Pair> list = new ArrayList<>();
    for (int i = 0; i < fav.size(); i++) {
      list.add(new Pair(i, new HashSet<>(fav.get(i))));
    }

    Collections.sort(list, new Comparator<Pair>() {
      @Override
      public int compare(Pair o1, Pair o2) {
        if (o1.data.size() == o2.data.size()) {
          return Integer.compare(o1.index, o2.index);
        }
        return Integer.compare(o1.data.size(), o2.data.size());
      }
    });
    List<Integer> ans = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      boolean shouldAdd = true;
      for (int j = i + 1; j < list.size(); j++) {
        if (isSubset(list.get(i), list.get(j))) {
          shouldAdd = false;
          break;
        }
      }
      if (shouldAdd) {
        ans.add(list.get(i).index);
      }
    }
    Collections.sort(ans);
    return ans;
  }

  private boolean isSubset(Pair first, Pair second) {
    for (String str : first.data) {
      if (!second.data.contains(str)) {
        return false;
      }
    }
    return true;
  }

  public static void main(String args[]) {
    List<List<String>> fav = Lists.newArrayList(Lists.newArrayList("leetcode", "google", "facebook"),
        Lists.newArrayList("google", "microsoft"), Lists.newArrayList("google", "facebook"),
        Lists.newArrayList("google"), Lists.newArrayList("amazon"));
    List<List<String>> fav1 = Lists.newArrayList(Lists.newArrayList("leetcode", "google", "facebook"),
        Lists.newArrayList("leetcode", "amazon"), Lists.newArrayList("facebook", "google"));
    List<List<String>> fav2 = Lists.newArrayList(Lists.newArrayList("leetcode"),
        Lists.newArrayList("amazon"), Lists.newArrayList("facebook"), Lists.newArrayList("google"));
    PeopleSubset p = new PeopleSubset();
    System.out.println(p.peopleIndexes(fav2));
  }

}
