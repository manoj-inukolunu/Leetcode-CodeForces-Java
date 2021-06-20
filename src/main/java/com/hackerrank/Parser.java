package com.hackerrank;

import com.google.gson.Gson;
import com.hackerrank.Parser.Response.Data;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Parser {

  public List<String> process(String author) {
    try {
      int page = 1, totalPages = 1;
      List<String> titles = new ArrayList<>();
      while (page <= totalPages) {
        String webPage = "https://jsonmock.hackerrank.com/api/articles?author=" + author + "&age=" + page;
        System.out.println(webPage);

        try (InputStream is = new URL(webPage).openStream();
            Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
          Gson gson = new Gson();
          Response td = gson.fromJson(reader, Response.class);
          totalPages = td.total_pages;
          page++;
          for (Data data : td.data) {
            if (data.title != null) {
              titles.add(data.title);
            } else if (data.story_title != null) {
              titles.add(data.story_title);
            }
          }
        }
      }
      return titles;
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  public static void main(String args[]) throws Exception {

    Parser p = new Parser();
    System.out.println(p.process("coloneltcb"));
  }

  public static class Response {

    int page;
    int per_page;
    int total;
    int total_pages;
    List<Data> data;

    @Override
    public String toString() {
      return "Response{" +
          "page=" + page +
          ", per_page=" + per_page +
          ", total=" + total +
          ", total_pages=" + total_pages +
          ", data=" + data +
          '}';
    }

    public class Data {

      String title;
      String url;
      String author;
      int num_comments;
      String story_id;
      String story_title;
      String parent_id;
      long created_at;

      @Override
      public String toString() {
        return "Data{" +
            "title='" + title + '\'' +
            ", url='" + url + '\'' +
            ", author='" + author + '\'' +
            ", num_comments=" + num_comments +
            ", story_id='" + story_id + '\'' +
            ", story_title='" + story_title + '\'' +
            ", parent_id='" + parent_id + '\'' +
            ", created_at=" + created_at +
            '}';
      }

    }
  }

  public int segment(int x, List<Integer> space) {
    // Write your code here
    MonoQueue m = new MonoQueue(2);
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < x; i++) {
      m.add(space.get(i));
    }
    max = Math.max(m.query(), max);
    for (int i = 1; i + x <= space.size(); i++) {
      m.add(space.get(i + x - 1));
      m.remove();
      max = Math.max(m.query(), max);
    }
    return max;
  }


  static class MonoQueue {

    private ArrayDeque<Integer> s1Num;
    private ArrayDeque<Integer> s1M;
    private ArrayDeque<Integer> s2Num;
    private ArrayDeque<Integer> s2M;
    private int size;

    public int NONE;
    public ArrayDeque<Integer> q;

    private int t;

    public MonoQueue(int t) {
      s1Num = new ArrayDeque<Integer>();
      s1M = new ArrayDeque<Integer>();
      s2Num = new ArrayDeque<Integer>();
      s2M = new ArrayDeque<Integer>();
      q = new ArrayDeque<Integer>();
      size = 0;
      NONE = 0;
      this.t = t;
    }

    public int merge(int a, int b) {
      if (t == 1) {
        return Math.max(a, b);
      } else {
        return Math.min(a, b);
      }
    }

    // Get the min/max in the monoqueue
    public int query() {
      if (size == 0) {
        return NONE;
      }
      int m;
      if (s1Num.isEmpty()) {
        m = s2M.peek();
      } else if (s2Num.isEmpty()) {
        m = s1M.peek();
      } else {
        m = merge(s1M.peek(), s2M.peek());
      }
      return m;
    }

    // Add a number to the tail of the queue
    public void add(int n) {
      int m = s1Num.isEmpty() ? n : merge(n, s1M.peek());
      s1Num.push(n);
      s1M.push(m);
      size++;
      q.add(n);
    }

    // Remove a number from the head of the queue
    public void remove() {
      if (size == 0) {
        return;
      }
      q.remove();
      if (s2Num.isEmpty()) {
        while (!s1Num.isEmpty()) {
          int n = s1Num.pop();
          s1M.pop();
          int m = s2Num.isEmpty() ? n : merge(n, s2M.peek());
          s2Num.push(n);
          s2M.push(m);
        }
      }
      s2Num.pop();
      s2M.pop();
      size--;
    }

    public int size() {
      return size;
    }

    public String toString() {
      return q.toString();
    }
  }


}
