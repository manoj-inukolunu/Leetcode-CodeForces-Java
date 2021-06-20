package com.leetcode.random10.sixmonths.medium;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author manoji on 7/22/20.
 */
public class NestedIterator implements Iterator<Integer> {

  private List<NestedInteger> nestedIntegers;
  int curr = 0;
  List<Integer> ans = new ArrayList<>();

  public NestedIterator(List<NestedInteger> nestedList) {
    this.nestedIntegers = nestedList;
    explore();
  }

  private void explore() {
    for (int i = 0; i < nestedIntegers.size(); i++) {
      explore(nestedIntegers.get(i));
    }
  }

  private void explore(NestedInteger nestedInteger) {
    if (nestedInteger.isInteger()) {
      ans.add(nestedInteger.getInteger());
      return;
    } else {
      List<NestedInteger> list = nestedInteger.getList();
      for (int i = 0; i < list.size(); i++) {
        explore(list.get(i));
      }
    }
  }

  @Override
  public Integer next() {
    return ans.get(curr++);
  }

  @Override
  public boolean hasNext() {
    return curr < ans.size();
  }


  public static void main(String args[]) {
    NestedInteger nestedInteger = new NestedInteger(Integer.MAX_VALUE, false,
        Lists.newArrayList(new NestedInteger(1, true, new ArrayList<>()), new NestedInteger(1, true, new ArrayList<>())));
    NestedInteger nestedInteger1 = new NestedInteger(2, true, new ArrayList<>());
    NestedInteger nestedInteger2 = new NestedInteger(Integer.MAX_VALUE, false,
        Lists.newArrayList(new NestedInteger(1, true, new ArrayList<>()), new NestedInteger(1, true, new ArrayList<>())));

    NestedIterator n = new NestedIterator(Lists.newArrayList(nestedInteger, nestedInteger1, nestedInteger2));

    while (n.hasNext()) {
      System.out.println(n.next());
    }

  }

}
