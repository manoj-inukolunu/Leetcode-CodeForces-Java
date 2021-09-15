package com.leetcode.hard;

import com.google.common.io.Files;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

public class LC1172 {

    static class DinnerPlates {

        TreeMap<Integer, Stack<Integer>> free = new TreeMap<>();
        TreeMap<Integer, Stack<Integer>> full = new TreeMap<>();
        TreeSet<Integer> empty = new TreeSet<>();

        int capacity;

        public DinnerPlates(int capacity) {
            this.capacity = capacity;
        }

        public void push(int val) {
            if (free.isEmpty() && full.isEmpty()) {
                if (empty.isEmpty()) {
                    free.put(0, new Stack<>());
                    push(val);
                } else {
                    Integer first = empty.first();
                    empty.remove(first);
                    free.put(first, new Stack<>());
                    push(val);
                }
                return;
            }
            if (!free.isEmpty()) {
                Integer first = free.firstKey();
                if (empty.isEmpty()) {
                    free.get(first).push(val);
                    if (free.get(first).size() == capacity) {
                        full.put(first, free.remove(first));
                    }
                } else {
                    Integer emptyFirst = empty.first();
                    if (emptyFirst < first) {
                        empty.remove(emptyFirst);
                        free.get(first).push(val);
                        if (free.get(first).size() == capacity) {
                            full.put(first, free.remove(first));
                        }
                    } else {
                        free.get(first).push(val);
                        if (free.get(first).size() == capacity) {
                            full.put(first, free.remove(first));
                        }
                    }
                }

            } else {
                Integer last = full.lastKey();
                if (empty.isEmpty()) {
                    free.put(last + 1, new Stack<>());
                    push(val);
                } else {
                    Integer emptyFirst = empty.first();
                    empty.remove(emptyFirst);
                    free.put(emptyFirst, new Stack<>());
                    push(val);
                }
            }
        }

        public int pop() {
            Integer freeHigh = free.isEmpty() ? null : free.lastKey();
            Integer fullHigh = full.isEmpty() ? null : full.lastKey();
            if (freeHigh != null && fullHigh != null) {
                if (freeHigh > fullHigh) {
                    return popAtStack(freeHigh);
                } else {
                    return popAtStack(fullHigh);
                }
            } else if (fullHigh != null) {
                return popAtStack(fullHigh);
            } else if (freeHigh != null) {
                return popAtStack(freeHigh);
            }
            return -1;
        }

        public int popAtStack(int index) {
            if (free.containsKey(index)) {
                int val = free.get(index).pop();
                if (free.get(index).isEmpty()) {
                    free.remove(index);
                    empty.add(index);
                }
                return val;
            } else if (full.containsKey(index)) {
                int ret = full.get(index).pop();
                Stack<Integer> removed = full.remove(index);
                if (removed.isEmpty()) {
                    empty.add(index);
                } else {
                    free.put(index, removed);
                }
                return ret;
            } else {
                return -1;
            }
        }
    }


    public static void main(String[] args) throws Exception {
        DinnerPlates stack = new DinnerPlates(1);
        List<String> lines = Files.readLines(new File("/Users/manoj/instructions"), Charset.defaultCharset());
        List<String> data = Files.readLines(new File("/Users/manoj/instruction_data"), Charset.defaultCharset());
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals("push")) {
                stack.push(Integer.parseInt(data.get(i)));
            } else if (lines.get(i).equals("popAtStack")) {
                int idx = Integer.parseInt(data.get(i));
                System.out.println(idx + " " + stack.popAtStack(idx));
            }
        }
    }
}
