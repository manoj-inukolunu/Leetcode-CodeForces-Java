package com.codeforces.div2;

import java.util.Scanner;

public class A75 {

  public void solve() {

    Scanner scanner = new Scanner(System.in);

    String str1 = scanner.nextLine();
    String str2 = scanner.nextLine();

    StringBuffer buff1 = new StringBuffer();
    for (int i = 0; i < str1.length(); i++) {
      if (str1.charAt(i) != '0') {
        buff1.append(str1.charAt(i));
      }
    }

    StringBuffer buff2 = new StringBuffer();
    for (int i = 0; i < str2.length(); i++) {
      if (str2.charAt(i) != '0') {
        buff2.append(str2.charAt(i));
      }
    }

    Long val = Long.parseLong(str1) + Long.parseLong(str2);

    Long val2 = Long.parseLong(buff1.toString()) + Long.parseLong(buff2.toString());

    String str3 = String.valueOf(val);
    StringBuffer buff3 = new StringBuffer();
    for (int i = 0; i < str3.length(); i++) {
      if (str3.charAt(i) != '0') {
        buff3.append(str3.charAt(i));
      }
    }

    if (val2 == Long.parseLong(buff3.toString())) {
      System.out.println("YES");
    } else {
      System.out.println("NO");
    }
  }

}
