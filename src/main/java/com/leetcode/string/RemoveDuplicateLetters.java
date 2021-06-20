package com.leetcode.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class RemoveDuplicateLetters {

  class Pair {

    int idx;
    char ch;

    public Pair(int idx, char ch) {
      this.idx = idx;
      this.ch = ch;
    }
  }

  public String removeDuplicateLetters(String s) {
    StringBuffer ans = new StringBuffer();
    HashMap<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      int count = map.getOrDefault(s.charAt(i), 0);
      map.put(s.charAt(i), count + 1);
    }
    HashSet<Character> seen = new HashSet<>();
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      char curr = s.charAt(i);
      if (seen.contains(curr)) {
        continue;
      }
      if (stack.isEmpty()) {
        stack.push(curr);
      } else {
        while (true) {
          if (stack.isEmpty()) {
            break;
          }
          if (stack.peek() >= curr) {
            StringBuffer buffer = new StringBuffer();
            if (map.get(stack.peek()) == 1) {
              while (!stack.isEmpty()) {
                char ch = stack.pop();
                buffer.append(ch);
                seen.add(ch);
                map.remove(ch);
              }
              ans.append(buffer.reverse());
              break;
            }
            Character ch = stack.pop();
            int count = map.get(ch);
            if (count - 1 == 0) {
              map.remove(ch);
            } else {
              map.put(ch, count - 1);
            }
          } else {
            break;
          }
        }
        if (!seen.contains(curr)) {
          stack.push(curr);
        }
      }
    }
    StringBuffer buffer = new StringBuffer();
    while (!stack.isEmpty()) {
      buffer.append(stack.pop());
    }
    ans.append(buffer.reverse());
    return ans.toString();
  }

  public String removeDuplicateLetters1(String s) {
    Stack<Character> chars = new Stack<>();
    int[] counter = new int[26];
    boolean[] visited = new boolean[26];

    for (int i = 0; i < s.length(); i++) {
      counter[s.charAt(i) - 'a']++;
    }

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      counter[c - 'a']--;
      if (visited[c - 'a']) {
        continue;
      }
      while (!chars.isEmpty() && chars.peek() > c && counter[chars.peek() - 'a'] > 0) {
        visited[chars.pop() - 'a'] = false;
      }
      chars.push(c);
      visited[c - 'a'] = true;
    }
    StringBuffer buffer = new StringBuffer();
    while (!chars.isEmpty()) {
      buffer.insert(0, chars.pop());
    }
    return buffer.toString();
  }

  public static void main(String args[]) {
    RemoveDuplicateLetters r = new RemoveDuplicateLetters();
    System.out.println(r.removeDuplicateLetters1("abacb"));
  }

}
