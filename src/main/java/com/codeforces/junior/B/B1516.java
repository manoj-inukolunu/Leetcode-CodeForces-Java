package com.codeforces.junior.B;

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
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class B1516 {

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

    int[] readArray(int n) {
      int[] a = new int[n];
      for (int i = 0; i < n; i++) {
        a[i] = nextInt();
      }
      return a;
    }

    long[] readLongArray(int n) {
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


  public static void main(String args[]) {
    parseAndGenerate();
    try (PrintWriter out = new PrintWriter(System.out)) {
      FastScanner scanner = new FastScanner();
      int t = scanner.nextInt();
      top:
      while (t-- > 0) {
        int n = scanner.nextInt();
        long[] arr = scanner.readLongArray(n);
        long xor = 0;
        for (int i = 0; i < arr.length; i++) {
          xor ^= arr[i];
        }
        if (xor == 0) {
          out.println("YES");
          continue;
        }
        long[] prefXor = new long[n];
        for (int i = 0; i < arr.length; i++) {
          if (i != 0) {
            prefXor[i] = prefXor[i - 1] ^ arr[i];
          } else {
            prefXor[i] = arr[i];
          }
        }
        for (int i = 0; i < prefXor.length; i++) {
          for (int j = i + 1; j < prefXor.length; j++) {
            if (prefXor[i] == (prefXor[j] ^ prefXor[i]) && (prefXor[prefXor.length - 1] ^ prefXor[j]) == prefXor[i]) {
              out.println("YES");
              continue top;
            }
          }
        }
        out.println("NO");

      }
    }

  }
}
