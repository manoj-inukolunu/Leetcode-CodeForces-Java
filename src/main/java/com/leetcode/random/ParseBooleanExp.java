package com.leetcode.random;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParseBooleanExp {

  public class Expression {

    Boolean isEvaluated = false;
    Boolean val;
    char ch;

    public Expression(Boolean val) {
      this.val = val;
    }

    public Expression(char ch) {
      this.ch = ch;
    }

    public Expression(char ch, Boolean val) {
      this.ch = ch;
      this.val = val;
    }

    public Expression(boolean val, boolean isEvaluated) {
      this.val = val;
      this.isEvaluated = isEvaluated;
    }

    public boolean isOpenParen() {
      return ch == '(';
    }
  }

  public boolean parseBoolExpr(String expression) {
    Stack<Expression> stack = new Stack<>();
    int i = 0;
    while (i < expression.length()) {
      char ch = expression.charAt(i);
      if (stack.isEmpty()) {
        stack.push(new Expression(ch, ch == 't'));
      } else if (ch == ')') {
        List<Expression> list = new ArrayList<>();
        while (!stack.isEmpty()) {
          Expression top = stack.pop();
          if (top.isOpenParen()) {
            stack.push(evaluate(stack.pop(), list));
            break;
          } else {
            list.add(top);
          }
        }
      } else {
        stack.push(new Expression(ch, ch == 't'));
      }
      i++;
    }
    return stack.peek().val;
  }

  private Expression evaluate(Expression exp, List<Expression> list) {
    if (exp.ch == '!') {
      Expression ex = list.get(0);
      ex.isEvaluated = true;
      ex.val = !ex.val;
      return ex;
    } else if (exp.ch == '&') {
      boolean and = true;
      for (int i = 0; i < list.size(); i++) {
        if (list.get(i).ch != ',' && list.get(i).ch != '(' && list.get(i).ch != ')') {
          and = and && list.get(i).val;
        }
      }
      return new Expression(and, true);
    } else {
      boolean or = false;
      for (int i = 0; i < list.size(); i++) {
        or = or || list.get(i).val;
      }
      return new Expression(or, true);
    }
  }

  public static void main(String args[]) {
    String expr = "&(t,t,t)";
    ParseBooleanExp p = new ParseBooleanExp();
    System.out.println(p.parseBoolExpr(expr));
  }


}
