package com.leetcode.hard;

import java.util.HashMap;
import java.util.Objects;

public class LC631 {
    static class Excel {

        class Node {
            int row;
            char column;

            public Node(int row, char column) {
                this.row = row;
                this.column = column;
            }

            @Override
            public boolean equals(Object other) {
                Node node = (Node) other;
                return this.row == node.row && this.column == node.column;
            }


            @Override
            public int hashCode() {
                return Objects.hash(row, column);
            }
        }

        int[][] matrix;

        HashMap<Node, String[]> map = new HashMap<>();


        public Excel(int height, char width) {
            matrix = new int[height + 1][width - 'A' + 1];
        }

        public void set(int row, char column, int val) {
            Node node = new Node(row, column);
            map.remove(node);
            matrix[row][column - 'A'] = val;
        }

        public int get(int row, char column) {
            Node node = new Node(row, column);
            if (map.containsKey(node)) {
                return sum(row, column, map.get(node));
            }
            return matrix[row][column - 'A'];
        }

        public int sum(int row, char column, String[] numbers) {
            Node node = new Node(row, column);
            int val = 0;
            for (String str : numbers) {
                if (str.contains(":")) {
                    String[] split = str.split(":");
                    int startRow = Integer.parseInt(split[0].substring(1));
                    int endRow = Integer.parseInt(split[1].substring(1));
                    char startColumn = split[0].charAt(0);
                    char endColumn = split[1].charAt(0);
                    for (int i = startRow; i <= endRow; i++) {
                        for (char j = startColumn; j <= endColumn; j++) {
                            val += get(i, j);
                        }
                    }
                } else {
                    int r = Integer.parseInt(str.substring(1));
                    char c = str.charAt(0);
                    val += get(r, c);
                }
            }
            matrix[row][column - 'A'] = val;
            map.put(node, numbers);
            return val;
        }
    }


    public static void main(String[] args) {
        Excel e = new Excel(3, 'C');
        e.set(1, 'A', 2);
        System.out.println(e.sum(3, 'C', new String[]{"A1", "A1:B2"}));
        e.set(2, 'B', 2);
        System.out.println(e.get(3, 'C'));
    }

}
