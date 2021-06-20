package com.leetcode.random10.sixmonths.medium;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

/**
 * @author manoji on 7/29/20.
 */
public class AccountsMerge {

  private HashMap<String, TreeSet<String>> map = new HashMap<>();

  public List<List<String>> accountsMerge(List<List<String>> accounts) {
    HashMap<String, String> emailToNameMap = new HashMap<>();
    List<List<String>> ans = new ArrayList<>();
    for (int i = 0; i < accounts.size(); i++) {
      List<String> account = accounts.get(i);
      if (account.size() == 2) {
        emailToNameMap.put(account.get(1), account.get(0));
        map.put(account.get(1), new TreeSet());
      } else {
        for (int j = 1; j < account.size(); j++) {
          for (int k = 1; k < account.size(); k++) {
            if (k != j) {
              emailToNameMap.put(account.get(k), account.get(0));
              emailToNameMap.put(account.get(j), account.get(0));
              TreeSet<String> set = map.getOrDefault(account.get(j), new TreeSet());
              set.add(account.get(k));
              map.put(account.get(j), set);
              set = map.getOrDefault(account.get(k), new TreeSet());
              set.add(account.get(j));
              map.put(account.get(k), set);
            }
          }
        }
      }
    }

    HashSet<String> visited = new HashSet<>();
    for (String email : map.keySet()) {
      if (!visited.contains(email)) {
        TreeSet<String> collector = new TreeSet<String>();
        dfs(email, map, visited, collector);
        List<String> list = new ArrayList<>(collector);
        list.add(0, emailToNameMap.get(email));
        ans.add(list);
      }
    }

    return ans;
  }

  private void dfs(String email, HashMap<String, TreeSet<String>> map, HashSet<String> visited, TreeSet<String> collector) {
    if (!visited.contains(email)) {
      visited.add(email);
      collector.add(email);
      for (String child : map.get(email)) {
        if (!visited.contains(child)) {
          dfs(child, map, visited, collector);
        }
      }
    }
  }


  public static void main(String args[]) {
    List<List<String>> accounts = Lists
        .newArrayList(Lists.newArrayList("John", "johnsmith@mail.com", "john00@mail.com"), Lists.newArrayList("John", "johnnybravo@mail.com"),
            Lists.newArrayList("John", "johnsmith@mail.com", "john_newyork@mail.com"), Lists.newArrayList("Mary", "mary@mail.com"));

    AccountsMerge a = new AccountsMerge();

    System.out.println(a.accountsMerge(accounts));
  }


}
