package com.codeforces.div2;

import java.util.HashMap;
import java.util.Scanner;

public class KeyBoardLayOut {

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    String first = scanner.nextLine();
    String second = scanner.nextLine();
    String str = scanner.nextLine();

    HashMap<Character, Character> map = new HashMap<>();
    for (int i = 0; i < first.length(); i++) {
      map.put(first.charAt(i), second.charAt(i));
    }

    StringBuffer buffer = new StringBuffer();

    for (int i = 0; i < str.length(); i++) {
      Character ch = str.charAt(i);
      if (Character.isUpperCase(ch)) {
        buffer.append(Character.toUpperCase(map.get(Character.toLowerCase(ch))));
      } else if (Character.isDigit(ch)) {
        buffer.append(ch);
      } else {
        buffer.append(map.get(ch));
      }
    }
    System.out.println(buffer.toString());
  }

}
