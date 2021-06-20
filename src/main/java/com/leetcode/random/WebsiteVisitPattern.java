package com.leetcode.random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class WebsiteVisitPattern {

  class Hold implements Comparable<Hold> {

    String website1;
    String website2;
    String website3;

    @Override
    public String toString() {
      return "{" +
          "'" + website1 + '\'' +
          ", '" + website2 + '\'' +
          ", '" + website3 + '\'' +
          '}';
    }

    String user;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Hold hold = (Hold) o;
      return Objects.equals(website1, hold.website1) &&
          Objects.equals(website2, hold.website2) &&
          Objects.equals(website3, hold.website3);
    }

    @Override
    public int hashCode() {
      return Objects.hash(website1, website2, website3);
    }

    public Hold(String website1, String website2, String website3, String user) {
      this.website1 = website1;
      this.website2 = website2;
      this.website3 = website3;
      this.user = user;
    }

    public List<String> getList() {
      List<String> list = new ArrayList<>();
      list.add(website1);
      list.add(website2);
      list.add(website3);
      return list;
    }

    @Override
    public int compareTo(Hold o) {
      return (website1 + "-" + website2 + "-" + website3).compareTo(o.website1 + "-" + o.website2 + "-" + o.website3);
    }
  }

  class Triplet {

    String website;
    int timestamp;
    String user;

    public Triplet(String website, int timestamp, String user) {
      this.website = website;
      this.timestamp = timestamp;
      this.user = user;
    }
  }

  public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
    List<Triplet> list = new ArrayList<>();
    for (int i = 0; i < website.length; i++) {
      list.add(new Triplet(website[i], timestamp[i], username[i]));
    }
    Collections.sort(list, Comparator.comparingInt(o -> o.timestamp));
    HashMap<Hold, Set<String>> map = new HashMap<>();
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < list.size(); i++) {
      for (int j = i + 1; j < list.size(); j++) {
        for (int k = j + 1; k < list.size(); k++) {
          if (list.get(i).user.equalsIgnoreCase(list.get(j).user) && list.get(j).user.equalsIgnoreCase(list.get(k).user)) {
            Hold hold = new Hold(list.get(i).website, list.get(j).website, list.get(k).website, list.get(i).user);
            Set<String> curr = map.getOrDefault(hold, new HashSet<>());
            curr.add(list.get(i).user);
            map.put(hold, curr);
            max = Math.max(curr.size(), max);
          }
        }
      }
    }
    TreeSet<Hold> ans = new TreeSet<>();
    for (Hold key : map.keySet()) {
      if (map.get(key).size() == max) {
        ans.add(key);
      }
    }
    return ans.first().getList();
  }

  public static void main(String args[]) {
    WebsiteVisitPattern w = new WebsiteVisitPattern();
    String[] usernames = new String[]{"joe", "joe", "joe", "james", "james", "james", "james", "mary", "mary", "mary"};
    int[] timestamps = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    String[] website = new String[]{"home", "about", "career", "home", "cart", "maps", "home", "home", "about", "career"};
    System.out.println(w.mostVisitedPattern(usernames, timestamps, website));
    System.out.println("mhpzoaiw".compareTo("m"));
  }

}
