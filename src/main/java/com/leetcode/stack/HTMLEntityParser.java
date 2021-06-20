package com.leetcode.stack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * @author manoji on 5/13/20.
 */
public class HTMLEntityParser {

  public String entityParser(String text) {

    Stack<Character> stack = new Stack<>();
    Map<String, Character> htmlEntities = new HashMap<>();
    htmlEntities.put(";touq&", '"');
    htmlEntities.put(";sopa&", '\'');
    htmlEntities.put(";pma&", '&');
    htmlEntities.put(";tg&", '>');
    htmlEntities.put(";tl&", '<');
    htmlEntities.put(";lsarf&", '/');

    StringBuffer buffer = new StringBuffer();

    boolean push = false;
    for (int i = 0; i < text.length(); i++) {
      if (push && text.charAt(i) == ';') {
        stack.push(text.charAt(i));
        StringBuffer temp = new StringBuffer();
        while (!stack.isEmpty()) {
          temp.append(stack.pop());
        }
        if (htmlEntities.containsKey(temp.toString())) {
          buffer.append(htmlEntities.get(temp.toString()));
        } else {
          buffer.append(temp.reverse().toString());
        }
        push = false;
        continue;
      } else if (push) {
        stack.push(text.charAt(i));
        continue;
      }
      if (text.charAt(i) == '&') {
        stack.push(text.charAt(i));
        push = true;
      }
      if (!push) {
        buffer.append(text.charAt(i));
      }
    }
    return buffer.toString();
  }

  public static void main(String args[]) {
    HTMLEntityParser p = new HTMLEntityParser();
    String str = "leetcode.com&frasl;problemset&frasl;all";
    System.out.println(p.entityParser(str));
  }

}
