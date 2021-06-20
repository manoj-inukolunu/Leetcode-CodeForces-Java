package com.leetcode.random1;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {


  List<String> ans = new ArrayList<>();

  class Pair {

    int len;
    List<String> words;

    public Pair(int len, List<String> words) {
      this.len = len;
      this.words = words;
    }
  }

  public List<String> fullJustify(String[] words, int maxWidth) {

    int currLen = 0;
    List<Pair> list = new ArrayList<>();
    Pair pair = new Pair(0, new ArrayList<>());
    for (int i = 0; i < words.length; i++) {
      if (canAdd(currLen, words[i].length() + 1, maxWidth)) {
        pair.len += (words[i].length());
        currLen += (words[i].length() + 1);
        pair.words.add(words[i]);
      } else if (canAdd(currLen, words[i].length(), maxWidth)) {
        pair.len += (words[i].length());
        currLen += (words[i].length());
        pair.words.add(words[i]);
      } else {
        list.add(pair);
        pair = new Pair(words[i].length(), new ArrayList<>());
        pair.words.add(words[i]);
        currLen = words[i].length() + 1;
      }
    }

    for (Pair p : list) {
      int numSpaces = p.words.size() - 1;
      if (numSpaces > 0) {
        int spaceLength = (maxWidth - p.len) / numSpaces;
        StringBuffer spaces = new StringBuffer();
        for (int i = 0; i < spaceLength; i++) {
          spaces.append(' ');
        }
        int leftOver = maxWidth - (p.len + spaceLength * (numSpaces));
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < p.words.size(); i++) {
          buffer.append(p.words.get(i));
          if (i != p.words.size() - 1) {
            buffer.append(spaces);
            if (leftOver > 0) {
              buffer.append(' ');
              leftOver--;
            }
          }
        }
        ans.add(buffer.toString());
      } else {
        addSpacesLast(maxWidth, p);
      }
    }
    addSpacesLast(maxWidth, pair);
    return ans;
  }

  private void addSpacesLast(int maxWidth, Pair p) {
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < p.words.size(); i++) {
      buffer.append(p.words.get(i));
      if (i != p.words.size() - 1) {
        buffer.append(' ');
        p.len++;
      }
    }
    int len = maxWidth - p.len;
    for (int i = 0; i < len; i++) {
      buffer.append(' ');
    }
    ans.add(buffer.toString());
  }

  private boolean canAdd(int currLen, int len, int maxWidth) {
    return currLen + len <= maxWidth;
  }

  public static void main(String args[]) {
    String[] str = new String[]{"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is",
        "everything", "else", "we", "do"};
    TextJustification t = new TextJustification();
    List<String> list = t.fullJustify(str, 20);
    for (String s : list) {
      System.out.println(s.length());
    }
    System.out.println(list);
  }

}
