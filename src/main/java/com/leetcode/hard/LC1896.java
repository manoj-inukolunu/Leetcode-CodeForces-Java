package com.leetcode.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class LC1896 {

    class Token {
        boolean expression;
        int val;
        int minValToChange;
        char ch;

        public Token(boolean isExpression, int val, int minValToChange, char ch) {
            this.expression = isExpression;
            this.val = val;
            this.minValToChange = minValToChange;
            this.ch = ch;
        }

        boolean isChar() {
            return ch == '&' || ch == '|';
        }
    }

    int unChanged = -1;

    public int minOperationsToFlip(String expression) {
        Stack<Token> stack = new Stack<>();
        int i = 0;
        while (i < expression.length()) {
            char ch = expression.charAt(i);
            if (ch == ')') {
                List<Token> list = new ArrayList<>();
                while (stack.peek().ch != '(') {
                    list.add(stack.pop());
                }
                Collections.reverse(list);
                stack.pop();
                unChanged = -1;
                Integer[][][] dp = new Integer[list.size()][2][2];
                int val = Math.min(solve(list, 1, list.get(0).val, list.get(0).val,
                        list.get(0).ch, list.get(0).val, dp), list.get(0).minValToChange + solve(list, 1,
                        rev(list.get(0).val),
                        rev(list.get(0).val), list.get(0).ch,
                        list.get(0).val, dp));
                stack.push(new Token(true, unChanged, val, 'e'));
            } else if (ch == '1' || ch == '0') {
                stack.push(new Token(false, Character.getNumericValue(ch), 1, 's'));
            } else if (ch == '(') {
                stack.push(new Token(false, 0, 0, ch));
            } else {
                stack.push(new Token(false, 0, 1, ch));
            }
            i++;
        }
        List<Token> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        Collections.reverse(list);
        Integer[][][] dp = new Integer[list.size()][2][2];
        return Math.min(solve(list, 1, list.get(0).val, list.get(0).val, list.get(0).ch,
                list.get(0).val, dp), list.get(0).minValToChange + solve(list, 1, rev(list.get(0).val),
                rev(list.get(0).val), list.get(0).ch,
                list.get(0).val, dp));
    }

    private int solve(List<Token> list, int idx, int exprVal, int prev, char prevUnchanged, int unchanged,
                      Integer[][][] dp) {
        if (idx >= list.size()) {
            unChanged = unchanged;
            return exprVal == unchanged ? Integer.MAX_VALUE / 2 : 0;
        }
        if (dp[idx][exprVal][prev] != null) {
            return dp[idx][exprVal][prev];
        }
        int best = Integer.MAX_VALUE / 2;
        Token curr = list.get(idx);
        if (curr.isChar()) {
            if (curr.ch == '&') {
                //no change
                best = Math.min(best, solve(list, idx + 1, exprVal, 0, curr.ch, unchanged, dp));
                best = Math.min(best, 1 + solve(list, idx + 1, exprVal, 1, curr.ch, unchanged, dp));
            } else {
                best = Math.min(best, solve(list, idx + 1, exprVal, 1, curr.ch, unchanged, dp));
                best = Math.min(best, 1 + solve(list, idx + 1, exprVal, 0, curr.ch, unchanged, dp));
            }
        } else if (curr.expression) {
            if (prev == 0) {
                //&
                //no change curr
                best = Math.min(best, solve(list, idx + 1, exprVal & curr.val, curr.val, curr.ch,
                        prevUnchanged == '&' ? unchanged & curr.val : unchanged | curr.val, dp));
                best = Math.min(best, curr.minValToChange + solve(list, idx + 1, exprVal & rev(curr.val),
                        rev(curr.val), curr.ch,
                        prevUnchanged == '&' ? unchanged & curr.val : unchanged | curr.val, dp));
            } else {
                //|
                best = Math.min(best, solve(list, idx + 1, exprVal | curr.val, curr.val,
                        curr.ch,
                        prevUnchanged == '&' ? unchanged & curr.val : unchanged | curr.val, dp));
                best = Math.min(best, curr.minValToChange + solve(list, idx + 1, exprVal | rev(curr.val),
                        rev(curr.val),
                        curr.ch,
                        prevUnchanged == '&' ? unchanged & curr.val : unchanged | curr.val, dp));
            }
        } else {
            if (prev == 0) {
                //&
                //no change curr
                best = Math.min(best, (solve(list, idx + 1, exprVal & curr.val, curr.val,
                        curr.ch,
                        prevUnchanged == '&' ? unchanged & curr.val : unchanged | curr.val, dp)));
                best = Math.min(best, 1 + solve(list, idx + 1, exprVal & rev(curr.val), rev(curr.val),
                        curr.ch,
                        prevUnchanged == '&' ? unchanged & curr.val : unchanged | curr.val, dp));
            } else {
                //|
                best = Math.min(best, (solve(list, idx + 1, exprVal | curr.val, curr.val,
                        curr.ch,
                        prevUnchanged == '&' ? unchanged & curr.val : unchanged | curr.val, dp)));
                best = Math.min(best, 1 + solve(list, idx + 1, exprVal | rev(curr.val), rev(curr.val),
                        curr.ch,
                        prevUnchanged == '&' ? unchanged & curr.val : unchanged | curr.val, dp));
            }
        }
        return dp[idx][exprVal][prev] = best;
    }

    int rev(int val) {
        return val == 0 ? 1 : 0;
    }

    public static void main(String[] args) {
        String expression = "(0&0)&(0&0&0)";
        String expr2 = "(0|(1|0&1))";
        String expr3 = "((0&(0&0)&(0|(0)&1&0)))";
        String expr4 = "(0&0)&(0|(0)&1&0)";
        LC1896 l = new LC1896();
        System.out.println(l.minOperationsToFlip(expr4));
    }

}

