package com.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FilterRestraunts {

  class Restraunt {

    int id;
    int rating;
    int price;
    int dist;

    public Restraunt(int id, int rating, int price, int dist) {
      this.id = id;
      this.rating = rating;
      this.price = price;
      this.dist = dist;
    }
  }

  public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
    List<Restraunt> list = new ArrayList<>();
    if (veganFriendly == 1) {
      for (int i = 0; i < restaurants.length; i++) {
        int[] restraunt = restaurants[i];
        if (restraunt[2] == 1) {
          list.add(new Restraunt(restraunt[0], restraunt[1], restraunt[3], restraunt[4]));
        }
      }
      return list.stream().map(restraunt -> restraunt.id).collect(Collectors.toList());
    } else {
      for (int i = 0; i < restaurants.length; i++) {
        int[] restaurant = restaurants[i];
        list.add(new Restraunt(restaurant[0], restaurant[1], restaurant[3], restaurant[4]));
      }
    }
    Collections.sort(list, (o1, o2) -> {
      if (o1.rating > o2.rating) {
        return 1;
      } else if (o1.rating < o2.rating) {
        return -1;
      } else {
        return -Integer.compare(o1.id, o2.id);
      }
    });
    return list.stream().map(restraunt -> restraunt.id).collect(Collectors.toList());
  }

  public boolean isPalin(StringBuffer str) {
    for (int i = 0; i < str.length() / 2; i++) {
      if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
        return false;
      }
    }
    return true;
  }

  public static void main(String args[]) {
    FilterRestraunts f = new FilterRestraunts();
    System.out.println(f.isPalin(new StringBuffer("abca")));
  }

}
