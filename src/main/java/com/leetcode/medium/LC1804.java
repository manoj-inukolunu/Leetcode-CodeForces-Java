package com.leetcode.medium;

import java.util.HashMap;

public class LC1804 {
    static class Trie {

        static class TrieNode {
            HashMap<Character, TrieNode> map = new HashMap<>();
            int count = 0;

            void insert(String str, int idx) {
                if (idx >= str.length()) {
                    count++;
                    return;
                }
                char ch = str.charAt(idx);
                TrieNode node = map.get(ch);
                if (node == null) {
                    map.put(ch, new TrieNode());
                }
                map.get(ch).insert(str, idx + 1);
            }

            int countEqualWords(String word, int idx) {
                if (idx >= word.length()) {
                    return count;
                }
                char ch = word.charAt(idx);
                if (map.containsKey(ch)) {
                    return map.get(ch).countEqualWords(word, idx + 1);
                } else {
                    return 0;
                }
            }

            int countWordsStartingWith(String prefix, int idx) {
                if (idx >= prefix.length()) {
                    return dfs();
                }
                char ch = prefix.charAt(idx);
                if (map.containsKey(ch)) {
                    return map.get(ch).countWordsStartingWith(prefix, idx + 1);
                }
                return 0;
            }

            int dfs() {
                int ret = count;
                for (char ch : map.keySet()) {
                    ret += map.get(ch).dfs();
                }
                return ret;
            }

            void erase(String word, int idx) {
                if (idx >= word.length()) {
                    if (count > 0) {
                        count--;
                    }
                    return;
                }
                char ch = word.charAt(idx);
                if (map.containsKey(ch)) {
                    map.get(ch).erase(word, idx + 1);
                }
            }
        }

        TrieNode node = new TrieNode();

        public Trie() {

        }

        public void insert(String word) {
            node.insert(word, 0);
        }

        public int countWordsEqualTo(String word) {
            return node.countEqualWords(word, 0);
        }

        public int countWordsStartingWith(String prefix) {
            return node.countWordsStartingWith(prefix, 0);
        }

        public void erase(String word) {
            node.erase(word, 0);
        }
    }

    public static void main(String args[]) {
        Trie t = new LC1804.Trie();
        t.insert("apple");
        t.insert("apple");
        t.insert("app");
        t.insert("appear");
        t.insert("appear");
        System.out.println(t.countWordsEqualTo("apple"));
        System.out.println(t.countWordsStartingWith("app"));
        t.erase("app");
        System.out.println(t.countWordsEqualTo("apple"));
        System.out.println(t.countWordsStartingWith("app"));
        t.erase("appear");
        System.out.println(t.countWordsStartingWith("app"));
    }

}
