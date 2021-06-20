package com.leetcode.stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author manoji on 5/13/20.
 */
public class RemoveKDigits {

  class Pair {

    Character ch;
    Integer index;

    public Pair(Character ch, Integer index) {
      this.ch = ch;
      this.index = index;
    }
  }


  public String removeKdigits(String num, int k) {
    Stack<Pair> stack = new Stack<>();
    List<Integer> list = new ArrayList();
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < num.length(); i++) {
      if (k == 0) {
        break;
      }
      if (!stack.isEmpty()) {
        Integer onStack = Character.getNumericValue(stack.peek().ch);
        Integer curr = Character.getNumericValue(num.charAt(i));
        if (curr >= onStack) {
          stack.push(new Pair(num.charAt(i), i));
        } else {
          while (!stack.isEmpty() && Character.getNumericValue(stack.peek().ch) > curr) {
            if (k == 0) {
              break;
            }
            k--;
            stack.pop();
          }
          stack.push(new Pair(num.charAt(i), i));
        }
      } else {
        stack.push(new Pair(num.charAt(i), i));
      }
    }
    if (k > 0) {
      while (k-- > 0) {
        stack.pop();
      }
      while (!stack.isEmpty()) {
        Pair pair = stack.pop();
        buffer.insert(0, pair.ch);
      }
      return removeZeroes(buffer.toString());
    } else {
      int index = stack.peek().index;
      while (!stack.isEmpty()) {
        Pair pair = stack.pop();
        buffer.insert(0, pair.ch);
      }
      return removeZeroes(buffer.append(num.substring(index + 1)).toString());
    }
  }


  private String removeZeroes(String str) {
    StringBuffer buff = new StringBuffer(str);
    for (int i = 0; i < buff.length(); i++) {
      if (buff.charAt(i) == '0') {
        buff.deleteCharAt(i);
      } else {
        break;
      }
    }
    return buff.toString();
  }

  private String removeKRecur(StringBuffer buff, int i, int k, HashMap<String, String> map) {
    if (k <= 0 || i >= buff.length()) {
      if (buff.length() == 0) {
        return "0";
      }
      return buff.toString();
    }

    if (map.containsKey(buff.toString() + "|" + i + "|" + k)) {
      return map.get(buff.toString() + "|" + i + "|" + k);
    }

    //remove ith char
    char c = buff.charAt(i);
    buff.deleteCharAt(i);
    int remove = Integer.parseInt(removeKRecur(buff, i, k - 1, new HashMap<String, String>()));
    buff.insert(i, c);
    int noRemove = Integer.parseInt(removeKRecur(buff, i + 1, k, new HashMap<String, String>()));

    map.put(buff.toString() + "|" + i + "|" + k, Math.min(remove, noRemove) + "");
    return Math.min(remove, noRemove) + "";
  }


  public static void main(String args[]) {
    RemoveKDigits r = new RemoveKDigits();

    System.out.println(r.removeKdigits("1432219", 3));
  }

}
