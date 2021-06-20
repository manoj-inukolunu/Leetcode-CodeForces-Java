package com.leetcode.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CampusBikes {

  class Point {

    int x;
    int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  class Pair implements Comparable<Pair> {

    Point bike;
    Point worker;
    int workerId;
    int bikeId;

    public Pair(Point bike, Point worker, int workerId, int bikeId) {
      this.bike = bike;
      this.worker = worker;
      this.workerId = workerId;
      this.bikeId = bikeId;
    }

    private int dist() {
      return Math.abs(bike.x - worker.x) + Math.abs(bike.y - worker.y);
    }

    @Override
    public int compareTo(Pair o) {
      return Integer.compare(dist(), o.dist());
    }
  }


  List<Pair> countSort(List<Pair> list) {
    int max = Collections.max(list).dist();
    int[] arr = new int[max + 1];
    HashMap<Integer, List<Pair>> map = new HashMap<>();
    for (int i = 0; i < list.size(); i++) {
      arr[list.get(i).dist()]++;
      List<Pair> set = map.getOrDefault(list.get(i).dist(), new ArrayList<>());
      set.add(list.get(i));
      map.put(list.get(i).dist(), set);
    }
    List<Pair> sorted = new ArrayList<>();
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] != 0) {
        if (arr[i] == 1) {
          sorted.addAll(map.get(i));
        } else {
          List<Pair> set = map.get(i);
          Collections.sort(set, (o1, o2) -> {
                if (o1.workerId == o2.workerId) {
                  return Integer.compare(o1.bikeId, o2.bikeId);
                }
                return Integer.compare(o1.workerId, o2.workerId);
              }
          );
          sorted.addAll(set);
        }
      }
    }
    return sorted;
  }

  public int[] assignBikes(int[][] workers, int[][] bikes) {
    List<Pair> list = new ArrayList<>();
    for (int i = 0; i < bikes.length; i++) {
      Point bike = new Point(bikes[i][0], bikes[i][1]);
      for (int j = 0; j < workers.length; j++) {
        Point worker = new Point(workers[j][0], workers[j][1]);
        list.add(new Pair(bike, worker, j, i));
      }
    }
    int[] ans = new int[bikes.length];
    return ans;
  }


  public static void main(String args[]) {
    int[][] workers = new int[][]{{0, 0}, {1, 1}, {2, 0}};
    int[][] bikes = new int[][]{{1, 0}, {2, 2}, {2, 1}};
    CampusBikes c = new CampusBikes();
    int[] ans = c.assignBikes(workers, bikes);
    System.out.println(Arrays.toString(ans));
  }

}
