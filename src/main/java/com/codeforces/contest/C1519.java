package com.codeforces.contest;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.ModifierVisitor;
import com.github.javaparser.ast.visitor.Visitable;
import com.github.javaparser.utils.CodeGenerationUtils;
import com.github.javaparser.utils.SourceRoot;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class C1519 {

  public static void main(String args[]) {
    parseAndGenerate();
    try (PrintWriter out = new PrintWriter(System.out)) {
      FastScanner scanner = new FastScanner();
      int t = scanner.nextInt();
      while (t-- > 0) {
        int n = scanner.nextInt();
        long[] uni = scanner.readArray(n);
        long[] skill = scanner.readArray(n);
        HashMap<Long, List<Long>> map = new HashMap<>();
        for (int i = 0; i < skill.length; i++) {
          List<Long> list = map.getOrDefault(uni[i], new ArrayList<>());
          list.add(skill[i]);
          map.put(uni[i], list);
        }
        for (long key : map.keySet()) {
          map.get(key).sort(Collections.reverseOrder());
        }
        for (long key : map.keySet()) {
          List<Long> values = map.get(key);
          for (int i = 1; i < values.size(); i++) {
            values.set(i, values.get(i - 1) + values.get(i));
          }
        }
        List<Long> ans = new ArrayList<>();
        List<Entry<Long, List<Long>>> entries = new ArrayList<>(map.entrySet());
        entries.sort((o1, o2) -> -Integer.compare(o1.getValue().size(), o2.getValue().size()));
        for (int k = 1; k <= n; k++) {

          long curr = 0;
          for (int i = 0; i < entries.size(); i++) {
            int size = entries.get(i).getValue().size();
            int lastIdx = (int) (Math.floor(((double) size / k)) * k);
            long sum = getSum(entries.get(i).getValue(), 0, lastIdx - 1);
            if (sum == 0) {
              break;
            }
            curr += sum;
          }
          ans.add(curr);
        }
        for (long num : ans) {
          out.print(num + " ");
        }
        out.println();
      }
    }
  }

  static long getSum(List<Long> list, int start, int end) {
    if (end >= list.size() || end < 0) {
      return 0;
    }
    if (start > 0) {
      return list.get(end) - list.get(start - 1);
    }
    return list.get(end);
  }

  static final Random random = new Random();

  static void ruffleSort(long[] a) {
    int n = a.length;//shuffle, then sort
    for (int i = 0; i < n; i++) {
      int oi = random.nextInt(n);
      long temp = a[oi];
      a[oi] = a[i];
      a[i] = temp;
    }
    Arrays.sort(a);
  }

  static class FastScanner {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer("");

    String next() {
      while (!st.hasMoreTokens()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    long[] readArray(int n) {
      long[] a = new long[n];
      for (int i = 0; i < n; i++) {
        a[i] = nextLong();
      }
      return a;
    }

    Integer[] readArrayInt(int n) {
      Integer[] a = new Integer[n];
      for (int i = 0; i < n; i++) {
        a[i] = nextInt();
      }
      return a;
    }

    long nextLong() {
      return Long.parseLong(next());
    }
  }

  static class Pair {

    int first;
    int second;

    public Pair(int first, int second) {
      this.first = first;
      this.second = second;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Pair pair = (Pair) o;
      return first == pair.first && second == pair.second;
    }

    @Override
    public int hashCode() {
      return Objects.hash(first, second);
    }
  }

  static class Equation {

    //ax+by=c;
    int a, b, c;

    public Equation(int x1, int y1, int x2, int y2) {
      int a = y2 - y1;
      int b = x1 - x2;
      int c = a * x1 + b * y1;
    }

    public Equation(int a, int b, int c) {
      this.a = a;
      this.b = b;
      this.c = c;
    }

    public boolean isPointOnLine(int x, int y) {
      return a * x + b * y == c;
    }
  }

  private static void buildGraph(HashMap<Integer, Set<Integer>> map, int first, int second) {
    Set<Integer> set = map.getOrDefault(first, new HashSet<>());
    set.add(second);
    map.put(first, set);
    set = map.getOrDefault(second, new HashSet<>());
    set.add(first);
  }

  public static void parseAndGenerate() {
    SourceRoot r = new SourceRoot(CodeGenerationUtils.mavenModuleRoot((MethodHandles.lookup().lookupClass()))
        .resolve("src/main/java/" + MethodHandles.lookup().lookupClass().getPackage().getName().replaceAll("\\.", "/")));
    CompilationUnit unit = r.parse("", MethodHandles.lookup().lookupClass().getSimpleName() + ".java");
    Optional<ClassOrInterfaceDeclaration> classOrInterfaceDeclaration = unit.getClassByName(MethodHandles.lookup().lookupClass().getSimpleName());
    ClassOrInterfaceDeclaration declaration = classOrInterfaceDeclaration.get();
    declaration.setName("Main");
    List<ImportDeclaration> validImports =
        unit.getImports().stream().filter(importDeclaration -> !importDeclaration.getName().toString().contains("com.")).collect(Collectors.toList());
    unit.setImports(new NodeList<>(validImports));
    unit.removePackageDeclaration();
    unit.accept(new ModifierVisitor<Void>() {
      @Override
      public Visitable visit(MethodDeclaration md, Void arg) {
        if (md.getName().toString().equals("parseAndGenerate")) {
          md.setBody(new BlockStmt());
        }
        return super.visit(md, arg);
      }
    }, null);
    try {
      Files.deleteIfExists(Paths.get("/Users/manoji/Desktop/Main.java"));
      Files.write(Paths.get("/Users/manoji/Desktop/Main.java"), unit.toString().getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private static void print2dArr(PrintWriter out, int[][] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        out.print(arr[i][j] + " ");
      }
      out.println();
    }
  }

  private static boolean[] sieve(int n) {
    boolean[] isPrime = new boolean[n + 1];
    Arrays.fill(isPrime, true);
    isPrime[0] = isPrime[1] = false;
    for (int i = 2; i <= n; i++) {
      if (isPrime[i] && ((long) i * i) <= n) {
        for (int j = i * i; j <= n; j += i) {
          isPrime[j] = false;
        }
      }
    }
    return isPrime;
  }


}

  

