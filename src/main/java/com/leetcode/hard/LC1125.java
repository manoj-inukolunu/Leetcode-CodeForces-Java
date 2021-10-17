package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class LC1125 {

    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        Arrays.sort(req_skills);
        for (List<String> list : people) {
            Collections.sort(list);
        }
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < req_skills.length; i++) {
            map.put(req_skills[i], i);
        }
        long[][] dp = new long[people.size() + 1][70000];
        for (long[] row : dp) {
            Arrays.fill(row, -1);
        }
        long peopleList = solve(map, people, 0, 0, dp);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            if (((1L << i) & peopleList) > 0) {
                list.add(i);
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    private long solve(HashMap<String, Integer> map, List<List<String>> people, int idx, int currSkills, long[][] dp) {
        if (currSkills == ((1 << map.size()) - 1)) {
            return 0;
        }
        if (idx >= people.size()) {
            return (currSkills == (1 << map.size()) - 1) ? 0 : Long.MAX_VALUE;
        }
        if (dp[idx][currSkills] != -1) {
            return dp[idx][currSkills];
        }
        //incl people[idx]
        long incl = solve(map, people, idx + 1, getSkills(map, people, idx, currSkills), dp);
        if (incl != Long.MAX_VALUE) {
            incl = incl | (1L << idx);
        }
        long excl = solve(map, people, idx + 1, currSkills, dp);
        if (incl != Long.MAX_VALUE && excl != Long.MAX_VALUE) {
            return dp[idx][currSkills] = (getCount(incl) <= getCount(excl) ? incl : excl);
        } else if (incl != Long.MAX_VALUE) {
            return dp[idx][currSkills] = incl;
        } else if (excl != Long.MAX_VALUE) {
            return dp[idx][currSkills] = excl;
        }
        return Long.MAX_VALUE;
    }

    private long getCount(long incl) {
        return Long.bitCount(incl);
    }

    private int getSkills(HashMap<String, Integer> map, List<List<String>> people, int idx, int skills) {
        for (String str : people.get(idx)) {
            if (map.containsKey(str)) {
                skills = skills | (1 << map.get(str));
            }
        }
        return skills;
    }

    public static void main(String[] args) {
        String[] reqSkills = new String[]{"cpp", "python", "javascript", "kotlin", "ruby", "r", "c", "rust", "vb"};
        List<List<String>> people = Utils.convertTo2DList("[[\"cpp\", \"c\", \"rust\"], [\"cpp\", \"python\", " +
                "\"javascript\", \"c\", \"r\", \"rust\"], [\"cpp\", \"python\", \"javascript\", \"c\", \"r\", " +
                "\"vb\"], [\"cpp\", \"python\", \"javascript\", \"ruby\", \"kotlin\", \"r\", \"c\", \"rust\", " +
                "\"vb\"], [\"cpp\", \"python\", \"javascript\", \"kotlin\", \"ruby\", \"r\", \"c\", \"rust\", " +
                "\"vb\"], [\"python\", \"r\"], [\"cpp\", \"python\", \"javascript\", \"ruby\", \"kotlin\", \"r\", " +
                "\"c\", \"rust\", \"vb\"], [\"cpp\", \"python\", \"javascript\", \"r\", \"c\", \"rust\"], [\"cpp\", " +
                "\"javascript\", \"rust\", \"vb\"], [\"kotlin\", \"ruby\", \"c\", \"vb\"], [\"cpp\", \"python\", " +
                "\"kotlin\", \"ruby\", \"rust\", \"vb\"], [\"ruby\", \"c\", \"r\", \"rust\"], [\"python\", " +
                "\"javascript\"], [\"javascript\", \"ruby\", \"rust\"], [\"python\", \"javascript\", \"ruby\", \"c\"," +
                " \"r\", \"rust\", \"vb\"], [\"cpp\", \"javascript\", \"kotlin\", \"r\", \"c\", \"vb\"], [\"c\", " +
                "\"rust\"], [\"cpp\", \"kotlin\", \"ruby\", \"c\", \"r\", \"rust\", \"vb\"], [\"cpp\", \"python\", " +
                "\"javascript\", \"ruby\", \"kotlin\", \"rust\"], [\"cpp\", \"javascript\", \"ruby\"], [\"vb\"]]");
        if (people.get(people.size() - 1).size() == 0) {
            people.remove(people.size() - 1);
        }
        System.out.println(people);
        LC1125 l = new LC1125();
        int[] arr = l.smallestSufficientTeam(reqSkills, people);
        System.out.println(Arrays.toString(arr));
    }

}
