package com.leetcode.string;

import java.util.HashMap;

/**
 * @author manoji on 6/21/20.
 */
public class UniqueEmails {

  public int numUniqueEmails(String[] emails) {
    HashMap<String, Integer> map = new HashMap<>();
    for (String str : emails) {
      StringBuffer buffer = new StringBuffer(str);
      int index = buffer.indexOf("@");
      while (buffer.indexOf("+") < index && buffer.indexOf("+") >= 0) {
        buffer.delete(buffer.indexOf("+"), index);
        index = buffer.indexOf("@");
      }
      while (buffer.indexOf(".") < index && buffer.indexOf(".") >= 0) {
        buffer.deleteCharAt(buffer.indexOf("."));
        index = buffer.indexOf("@");
      }
      map.put(buffer.toString(), map.getOrDefault(buffer.toString(), 0) + 1);
    }
    int sum = 0;
    for (int key : map.values()) {
      sum += key;
    }
    return sum;
  }

  public static void main(String args[]) {
    UniqueEmails e = new UniqueEmails();

    System.out.println(e.numUniqueEmails(
        new String[]{"fg.r.u.uzj+o.pw@kziczvh.com", "r.cyo.g+d.h+b.ja@tgsg.z.com", "fg.r.u.uzj+o.f.d@kziczvh.com", "r.cyo.g+ng.r.iq@tgsg.z.com",
            "fg.r.u.uzj+lp.k@kziczvh.com", "r.cyo.g+n.h.e+n.g@tgsg.z.com", "fg.r.u.uzj+k+p.j@kziczvh.com", "fg.r.u.uzj+w.y+b@kziczvh.com",
            "r.cyo.g+x+d.c+f.t@tgsg.z.com", "r.cyo.g+x+t.y.l.i@tgsg.z.com", "r.cyo.g+brxxi@tgsg.z.com", "r.cyo.g+z+dr.k.u@tgsg.z.com",
            "r.cyo.g+d+l.c.n+g@tgsg.z.com", "fg.r.u.uzj+vq.o@kziczvh.com", "fg.r.u.uzj+uzq@kziczvh.com", "fg.r.u.uzj+mvz@kziczvh.com",
            "fg.r.u.uzj+taj@kziczvh.com", "fg.r.u.uzj+fek@kziczvh.com"}));
  }

}
