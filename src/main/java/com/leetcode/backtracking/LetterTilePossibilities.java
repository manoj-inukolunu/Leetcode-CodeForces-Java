package com.leetcode.backtracking;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author manoji on 7/7/20.
 */
public class LetterTilePossibilities {

  public int numTilePossibilities(String tiles) {
    Set<String> counter = new HashSet<>();
    recur(tiles, 0, counter);
//		System.out.println(counter);
    return counter.size();

  }

  private void recur(String tiles, int index, Set<String> total) {
    if (index >= tiles.length()) {
      return;
    }
    Character ch = tiles.charAt(index);
    Set<String> set = new HashSet<>();
    for (String str : total) {
      StringBuffer buff = new StringBuffer(str);
      for (int i = 0; i <= buff.length(); i++) {
        buff.insert(i, ch);
        set.add(buff.toString());
        buff = new StringBuffer(str);
      }
    }
    set.add(ch + "");
    total.addAll(set);
    recur(tiles, index + 1, total);
  }

  public static void main(String args[]) {
    LetterTilePossibilities l = new LetterTilePossibilities();

    System.out.println(l.numTilePossibilities("abcdefgh"));

    Set<ArrayList<Integer>> set = new HashSet<>();

    set.add(Lists.newArrayList(1, 2, 3));
    set.add(Lists.newArrayList(1, 2, 3));

    System.out.println(set);
  }

}
