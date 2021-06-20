package com.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class ProductOfNumbers {

  List<Integer> list = new ArrayList();

  public ProductOfNumbers() {

  }

  public void add(int num) {
    if (num == 0) {
      list.clear();
    } else {
      if (list.isEmpty()) {
        list.add(num);
      } else {
        list.add(list.get(list.size() - 1) * num);
      }
    }

  }

  public int getProduct(int k) {
    if (k > list.size()) {
      return 0;
    } else {
      return getProd(list.size() - k, list.size() - 1);
    }
  }

  int getProd(int start, int end) {
    if (start > 0) {
      return list.get(end) / list.get(start - 1);
    }
    return list.get(end);
  }

  public static void main(String args[]) {
    ProductOfNumbers p = new ProductOfNumbers();
    p.add(3);
    p.add(0);
    p.add(2);
    p.add(5);
    p.add(4);
    System.out.println(p.getProduct(2));
    System.out.println(p.getProduct(3));
    System.out.println(p.getProduct(4));
    p.add(8);
    System.out.println(p.getProduct(2));
  }
}
