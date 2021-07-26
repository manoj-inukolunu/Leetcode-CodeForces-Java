package com.leetcode.hard;

public class LC1255 {
    public int maxScoreWords(String[] words, char[] letters, int[] score) {

        int[][] wordsMap = new int[words.length][26];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                wordsMap[i][words[i].charAt(j) - 'a']++;
            }
        }
        int[] lettersMap = new int[26];
        for (int i = 0; i < letters.length; i++) {
            lettersMap[letters[i] - 'a']++;
        }

        return solve(wordsMap, 0, lettersMap, score);
    }

    private int solve(int[][] wordsMap, int idx, int[] lettersMap, int[] score) {
        if (idx >= wordsMap.length) {
            return 0;
        }
        int max = Integer.MIN_VALUE / 2;
        if (possible(wordsMap[idx], lettersMap)) {
            int scoreAdd = 0;
            int[] word = wordsMap[idx];
            for (int i = 0; i < word.length; i++) {
                if (word[i] > 0) {
                    lettersMap[i] -= word[i];
                    scoreAdd += (word[i] * score[i]);
                }
            }
            max = Math.max(max, scoreAdd + solve(wordsMap, idx + 1, lettersMap, score));
            for (int i = 0; i < word.length; i++) {
                if (word[i] > 0) {
                    lettersMap[i] += word[i];
                }
            }
        }
        max = Math.max(max, solve(wordsMap, idx + 1, lettersMap, score));
        return max;
    }

    boolean possible(int[] word, int[] letters) {
        for (int i = 0; i < word.length; i++) {
            if (word[i] > 0 && letters[i] < word[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"dog", "cat", "dad", "good"};
        char[] letters = new char[]{'a', 'a', 'c', 'd', 'd', 'd', 'g', 'o', 'o'};
        int[] score = new int[]{1, 0, 9, 5, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        LC1255 l = new LC1255();
        System.out.println(l.maxScoreWords(words, letters, score));
    }
}
