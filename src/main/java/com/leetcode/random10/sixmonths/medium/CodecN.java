package com.leetcode.random10.sixmonths.medium;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.leetcode.dfs.Node;

public class CodecN {

  private String dfs(Node root, Node parent, int id, int parentId) {
    if (root == null) {
      return "";
    }
    StringBuffer buffer = new StringBuffer();
    buffer.append(Character.toString((char) id));
    buffer.append(Character.toString((char) root.val));
    if (parent != null) {
      buffer.append(Character.toString((char) parentId));
    } else {
      buffer.append("R");
    }
    if (root.children != null) {
      for (int i = 0; i < root.children.size(); i++) {
        buffer.append(dfs(root.children.get(i), root, id + 1, id));
      }
    }
    return buffer.toString();
  }

  // Encodes a tree to a single string.
  public String serialize(Node root) {
    return dfs(root, null, 1, -1);
  }

  // Decodes your encoded data to tree.
  public Node deserialize(String data) {
    HashMap<Integer, Node> map = new HashMap<>();
    int id = data.charAt(0);
    int rootId = id;
    int val = data.charAt(1);
    map.put(id, new Node(val, new ArrayList<>()));
    int i = 3;
    while (i + 2 < data.length()) {
      id = data.charAt(i);
      val = data.charAt(i + 1);
      int parentId = data.charAt(i + 2);
      Node curr = new Node(val, new ArrayList<>());
      Node parent = map.get(parentId);
      if (parent != null) {
        parent.children.add(curr);
      } else {
        System.out.println("Fail");
      }
      map.put(id, curr);
      i += 3;
    }
    return map.get(rootId);
  }

  public static void main(String args[]) {
    CodecN c = new CodecN();
    Node node = new Node(1);
    List<Node> list = Lists.newArrayList(new Node(2), new Node(3), new Node(4), new Node(5));
    list.get(1).children = Lists.newArrayList(new Node(6), new Node(7));
    list.get(2).children = Lists.newArrayList(new Node(8));
    list.get(3).children = Lists.newArrayList(new Node(9), new Node(10));

    list.get(1).children.get(1).children = Lists.newArrayList(new Node(11));
    list.get(1).children.get(1).children.get(0).children = Lists.newArrayList(new Node(14));

    list.get(2).children.get(0).children = Lists.newArrayList(new Node(12));
    list.get(3).children.get(0).children = Lists.newArrayList(new Node(13));
    node.children = list;

    String str = c.serialize(node);

    System.out.println(str);
    Node n = c.deserialize(str);

    System.out.println(n);

    System.out.println(c.serialize(n));
  }

}
