package com.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author manoji on 3/9/20.
 */
public class FindDuplicateInFiles {

  public List<List<String>> findDuplicate(String[] paths) {
    HashMap<String, List<String>> map = new HashMap<>();

    for (int i = 0; i < paths.length; i++) {
      String[] splits = paths[i].split(" ");
      String directory = splits[0];
      for (int j = 1; j < splits.length; j++) {
        String fileName = splits[j].substring(0, splits[j].indexOf('('));
        String content = splits[j].substring(splits[j].indexOf('(') + 1, splits[j].indexOf(')'));
        if (map.containsKey(content)) {
          List<String> list = map.get(content);
          list.add(directory + "/" + fileName);
          map.put(content, list);
        } else {
          List<String> list = new ArrayList();
          list.add(directory + "/" + fileName);
          map.put(content, list);
        }
      }
    }
    List<List<String>> lists = new ArrayList<>();
    for (String key : map.keySet()) {
      lists.add(map.get(key));
    }
    return lists;
  }

  public static void main(String args[]) {
    FindDuplicateInFiles findDuplicateInFiles = new FindDuplicateInFiles();

    String[] paths = new String[]{"root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"};

    System.out.println(findDuplicateInFiles.findDuplicate(paths));

  }

}
