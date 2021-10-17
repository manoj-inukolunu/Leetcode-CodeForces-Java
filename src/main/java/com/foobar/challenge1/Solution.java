package com.foobar.challenge1;

import java.util.HashMap;

public class Solution {
    public static String solution(String x) {
        HashMap<Character, Character> map = new HashMap<>();
        for (char ch = 'z', mapCh = 'a'; ch >= 'a'; ch--, mapCh++) {
            map.put(ch, mapCh);
        }
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < x.length(); i++) {
            char ch = x.charAt(i);
            if (Character.isLowerCase(ch)) {
                ret.append(map.get(ch));
            } else {
                ret.append(ch);
            }
        }
        return ret.toString();
    }

    public static String solution1(String s) {
        int l = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < l; i++) {
            char c = chars[i];
            if (c >= 'a' && c <= 'z') {
                int pos = c - 'a';
                chars[i] = (char) ('a' + 25 - pos);
            }
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        String data = Solution.solution("Yvzs! I xzm'g yvorvev Lzmxv olhg srh qly zg gsv xlolmb!!");
        String data2 = Solution.solution1("Yvzs! I xzm'g yvorvev Lzmxv olhg srh qly zg gsv xlolmb!!");
        System.out.println(data.equals(data2));
    }
}