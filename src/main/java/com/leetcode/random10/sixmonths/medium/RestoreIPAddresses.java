package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RestoreIPAddresses {


  List<String> ans = new ArrayList<>();
  String chunkIPv4 = "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
  Pattern pattenIPv4 = Pattern.compile("^(" + chunkIPv4 + "\\.){3}" + chunkIPv4 + "$");


  public boolean validIPAddress(String IP) {
    return pattenIPv4.matcher(IP).matches();
  }


  public List<String> restoreIpAddresses(String s) {
    if (s.length() > 16) {
      return new ArrayList<>();
    }
    StringBuffer buffer = new StringBuffer(s);
    buildList(buffer, 1, 0);
    return ans;
  }

  private void buildList(StringBuffer buffer, int index, int dotCount) {
    if (index >= buffer.length()) {
      if (validIPAddress(buffer.toString())) {
        ans.add(buffer.toString());
      }
      return;
    }

    if (dotCount <= 4) {
      buffer = buffer.insert(index, '.');
      buildList(buffer, index + 1, dotCount + 1);
      buffer = buffer.deleteCharAt(index);
      buildList(buffer, index + 1, dotCount);
    }
  }

  public static void main(String args[]) {
    RestoreIPAddresses r = new RestoreIPAddresses();
    System.out.println(r.restoreIpAddresses("010010"));
  }

}
