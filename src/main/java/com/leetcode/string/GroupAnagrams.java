package com.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author manoji on 3/6/20.
 */
public class GroupAnagrams {

  public List<List<String>> groupAnagrams(String[] strs) {
    List<List<String>> strsList = new ArrayList();
    for (String str : strs) {
      boolean isAdded = false;
      for (List<String> curr : strsList) {
        if (isAnagram(curr, str)) {
          curr.add(str);
          isAdded = true;
        }
      }
      if (!isAdded) {
        List<String> lis1 = new ArrayList();
        lis1.add(str);
        strsList.add(lis1);
      }
    }
    return strsList;
  }

  private boolean isAnagram(List<String> strs, String str) {
    char[] arr1 = strs.get(0).toCharArray();
    char[] arr2 = str.toCharArray();
    Arrays.sort(arr1);
    Arrays.sort(arr2);
    return arr1.equals(arr2);
  }

  public static void main(String args[]) {
    GroupAnagrams groupAnagrams = new GroupAnagrams();

    System.out.println(groupAnagrams.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
  }

}
