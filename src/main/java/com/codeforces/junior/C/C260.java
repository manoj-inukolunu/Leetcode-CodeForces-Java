package com.codeforces.junior.C;

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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class C260 {

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
                .resolve("src/main/java/" + MethodHandles.lookup().lookupClass().getPackage().getName().replaceAll(
                        "\\.", "/")));
        CompilationUnit unit = r.parse("", MethodHandles.lookup().lookupClass().getSimpleName() + ".java");
        Optional<ClassOrInterfaceDeclaration> classOrInterfaceDeclaration =
                unit.getClassByName(MethodHandles.lookup().lookupClass().getSimpleName());
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
            Files.deleteIfExists(Paths.get("/Users/manoj/Desktop/Main.java"));
            Files.write(Paths.get("/Users/manoj/Desktop/Main.java"), unit.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String args[]) {
        parseAndGenerate();
        try (PrintWriter out = new PrintWriter(System.out)) {
            FastScanner scanner = new FastScanner();
            int n = scanner.nextInt();
            Integer[] arr = scanner.readArrayInt(n);
            Arrays.sort(arr);
            List<Pair> list = new ArrayList<>();
            list.add(new Pair(arr[0], 1));
            for (int i = 1; i < arr.length; i++) {
                Pair prev = list.get(list.size() - 1);
                if (prev.first == arr[i]) {
                    list.set(list.size() - 1, new Pair(prev.first, prev.second + 1));
                } else {
                    list.add(new Pair(arr[i], 1));
                }
            }

            Long[] dp = new Long[list.size() + 1];
            long val = solve(0, list, dp);
            out.println(val);
        }

    }

    private static long solve(int idx, List<Pair> list, Long[] dp) {
        if (idx >= list.size()) {
            return 0;
        }
        if (dp[idx] != null) {
            return dp[idx];
        }
        long best = solve(idx + 1, list, dp);
        Pair curr = list.get(idx);
        if (idx + 1 < list.size()) {
            Pair next = list.get(idx + 1);
            if (next.first != curr.first + 1) {
                best = Math.max((long) curr.first * curr.second + solve(idx + 1, list, dp), best);
            } else {
                best = Math.max((long) curr.first * curr.second + solve(idx + 2, list, dp), best);
            }
        } else {
            best = Math.max((long) curr.first * curr.second + solve(idx + 1, list, dp), best);
        }
        return dp[idx] = best;
    }
}