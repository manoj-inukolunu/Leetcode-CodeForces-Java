package com.leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LC1178 {

    private long getMask(String str) {
        long mask = 0;
        for (char ch : str.toCharArray()) {
            mask |= (1L << (ch - 'a'));
        }
        return mask;
    }

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        HashMap<Integer, List<Long>> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            map.put(i, new ArrayList<>());
        }
        for (String word : words) {
            long mask = getMask(word);
            for (int i = 0; i < 26; i++) {
                if ((mask & (1 << i)) != 0) {
                    map.get(i).add(mask);
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (String puzzle : puzzles) {
            long mask = getMask(puzzle);
            int c = puzzle.charAt(0) - 'a';
            int count = 0;
            for (long key : map.get(c)) {
                if ((key & mask) == key) {
                    count++;
                }
            }
            ans.add(count);
        }
        return ans;
    }

    public static void main(String args[]) {
        String[] words = new String[]{"apple", "pleas", "please"};
        String[] puzzles = new String[]{"aelwxyz", "aelpxyz", "aelpsxy", "saelpxy", "xaelpsy"};
        LC1178 l = new LC1178();
        System.out.println(l.findNumOfValidWords(words, puzzles));
    }
}
