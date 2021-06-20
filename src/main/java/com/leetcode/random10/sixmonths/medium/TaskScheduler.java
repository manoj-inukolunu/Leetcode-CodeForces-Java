package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author manoji on 7/27/20.
 */
public class TaskScheduler {

  class Pair {

    int count;
    Character task;

    public Pair(int count, Character task) {
      this.count = count;
      this.task = task;
    }
  }

  public int leastInterval(char[] tasks, int n) {
    if (n == 0) {
      return tasks.length;
    }
    HashMap<Character, Integer> map = new HashMap<>();
    HashMap<Character, Integer> timeMap = new HashMap<>();

    int taskCount = 0;
    for (int i = 0; i < tasks.length; i++) {
      map.put(tasks[i], map.getOrDefault(tasks[i], 0) + 1);
      taskCount++;
    }
    int time = 0;
    List<Pair> sortedList = new ArrayList<>();
    for (Character task : map.keySet()) {
      sortedList.add(new Pair(map.get(task), task));
    }

    List<Character> list = new ArrayList<>();

    while (taskCount > 0) {
      Character curr = getNext(sortedList, timeMap, time, n);
      if (curr != null) {
        taskCount--;
      }
      list.add(curr);
      time++;
    }
    System.out.println(list);
    return list.size();

  }

  private Character getNext(List<Pair> sortedList, HashMap<Character, Integer> timeMap, int time, int n) {
    Collections.sort(sortedList, (o1, o2) -> {
      if (o1.count > o2.count) {
        return -1;
      } else if (o1.count < o2.count) {
        return 1;
      } else {
        return 0;
      }
    });

    for (int i = 0; i < sortedList.size(); i++) {
      Pair curr = sortedList.get(i);
      if (curr.count > 0) {
        if (timeMap.containsKey(curr.task) && timeMap.get(curr.task) + n < time) {
          timeMap.put(curr.task, time);
          sortedList.get(i).count = sortedList.get(i).count - 1;
          return curr.task;
        } else if (!timeMap.containsKey(curr.task)) {
          timeMap.put(curr.task, time);
          sortedList.set(i, new Pair(curr.count - 1, curr.task));
          return curr.task;
        }
      }
    }
    return null;
  }


  public static void main(String args[]) {
    TaskScheduler t = new TaskScheduler();

    System.out.println(t.leastInterval(new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}, 2));
  }

}
