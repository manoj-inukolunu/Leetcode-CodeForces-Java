package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author manoji on 7/22/20.
 */
public class NestedInteger {

  private Integer integer;
  private Boolean isInteger;

  public NestedInteger(Integer integer, Boolean isInteger, List<NestedInteger> nestedIntegers) {
    this.integer = integer;
    this.isInteger = isInteger;
    this.nestedIntegers = nestedIntegers;
  }

  private List<NestedInteger> nestedIntegers;

  public NestedInteger(int parseInt) {
    this.integer = parseInt;
    this.isInteger = true;
  }

  public NestedInteger() {
    nestedIntegers = new ArrayList<>();
    this.isInteger = false;
  }


  public boolean isInteger() {
    return this.isInteger;
  }

  public Integer getInteger() {
    return this.integer;
  }

  public List<NestedInteger> getList() {
    return this.nestedIntegers;
  }

}
