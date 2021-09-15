package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class LC1948 {

    HashMap<String, Set<Trie>> dup = new HashMap<>();
    List<List<String>> cleanedPaths = new ArrayList<>();

    class Trie {

        public TreeMap<String, Trie> map = new TreeMap<>();
        public boolean leaf = false;
        public String name;
        public Trie parent;
        public boolean delete = false;

        @Override
        public String toString() {
            return name;
        }

        public Trie(String name, Trie parent) {
            this.name = name;
            this.parent = parent;
        }

        void insert(List<String> path, int idx) {
            if (idx >= path.size()) {
                this.leaf = true;
                return;
            }
            if (!map.containsKey(path.get(idx))) {
                map.put(path.get(idx), new Trie(path.get(idx), this));
            }
            map.get(path.get(idx)).insert(path, idx + 1);
        }
    }

    String traverse(Trie root) {
        StringBuilder hash = new StringBuilder();
        for (String key : root.map.keySet()) {
            String curr = traverse(root.map.get(key));
            hash.append(curr).append("#");
        }
        if (root.map.keySet().size() != 0) {
            if (!dup.containsKey(hash.toString())) {
                dup.put(hash.toString(), new HashSet<>());
            }
            dup.get(hash.toString()).add(root);
        }
        return "/" + root.name + hash;
    }

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        Trie t = new Trie("", null);
        for (List<String> folder : paths) {
            t.insert(folder, 0);
        }
        traverse(t);
        for (String key : dup.keySet()) {
            if (dup.get(key).size() > 1) {
                Set<Trie> set = dup.get(key);
                for (Trie trie : set) {
                    if (trie.parent != null) {
                        trie.parent.map.remove(trie.name);
                    }
                }
            }
        }
        allPaths(t, new ArrayList<>());
        return cleanedPaths;
    }

    private void allPaths(Trie node, List<String> path) {
        if (!node.name.equals("")) {
            path.add(node.name);
        }
        if (!path.isEmpty()) {
            cleanedPaths.add(new ArrayList<>(path));
        }
        for (String child : node.map.keySet()) {
            allPaths(node.map.get(child), path);
        }
        if (path.size() > 0) {
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<List<String>> folders = Utils.convertTo2DList("[\"a\"],[\"a\",\"x\"],[\"a\",\"x\",\"y\"],[\"a\",\"z\"]," +
                "[\"b\"],[\"b\",\"x\"],[\"b\",\"x\",\"y\"],[\"b\",\"z\"],[\"b\",\"w\"]");
        LC1948 l = new LC1948();
        System.out.println(l.deleteDuplicateFolder(folders));
    }
}
