package com.leetcode.math;

import java.util.ArrayList;
import java.util.List;

public class FracAddAndSub {

  class Token {

    int nume;
    int deno;
    boolean fraction;
    int type;

    private Token reduce() {
      int gc = gcd(Math.abs(nume), Math.abs(deno));
      return new Token(nume / gc, deno / gc, true, -1);
    }

    int gcd(int a, int b) {
      if (a == 0) {
        return b;
      }
      if (b == 0) {
        return a;
      }
      if (a == b) {
        return a;
      }
      if (a > b) {
        return gcd(a - b, b);
      }
      return gcd(a, b - a);
    }

    @Override
    public String toString() {
      return "Token{" +
          "nume=" + nume +
          ", deno=" + deno +
          ", fraction=" + fraction +
          ", type=" + type +
          '}';
    }

    public Token(int nume, int deno, boolean fraction, int type) {
      this.nume = nume;
      this.deno = deno;
      this.fraction = fraction;
      this.type = type;
    }

    public Token add(Token next) {
      if (next.deno == deno) {
        return new Token(nume + next.nume, deno, true, -1);
      } else {
        return new Token(nume * next.deno + deno * next.nume, next.deno * deno, true, -1);
      }
    }

    public Token sub(Token next) {
      if (next.deno == deno) {
        return new Token(nume - next.nume, deno, true, -1);
      } else {
        return new Token(nume * next.deno - deno * next.nume, next.deno * deno, true, -1);
      }
    }
  }

  public String fractionAddition(String expression) {
    List<Token> list = new ArrayList<>();
    int i = 0;
    while (i < expression.length()) {
      int idx = expression.indexOf("/", i);
      String nume = expression.substring(i, idx);
      i = idx;
      idx = getOpIdx(expression, idx);
      if (idx < 0) {
        idx = expression.length();
      }
      String den = expression.substring(i + 1, idx);
      i = idx;
      list.add(new Token(Integer.parseInt(nume), Integer.parseInt(den), true, -1));
      if (i < expression.length() && isOperator(expression.charAt(i))) {
        list.add(new Token(-1, -1, false, expression.charAt(i) == '-' ? 2 : 1));
      }
      i++;
    }
    //System.out.println(list);
    return evaluate(list);
  }

  private int getOpIdx(String expression, int idx) {
    int nIdx = expression.indexOf("-", idx);
    int pIdx = expression.indexOf("+", idx);
    if (nIdx > 0 && pIdx > 0) {
      return Math.min(nIdx, pIdx);
    }
    idx = (nIdx < 0) ? expression.indexOf("+", idx) : nIdx;
    return idx;
  }

  private boolean isOperator(char charAt) {
    return charAt == '-' || charAt == '+';
  }

  private String evaluate(List<Token> list) {
    Token prev = null;
    if (list.size() == 1) {
      prev = list.get(0);
      if (prev.nume == 0) {
        return "0/1";
      }
      prev = prev.reduce();
      return "" + prev.nume + "/" + prev.deno;
    }

    for (int i = 0; i < list.size(); i++) {
      Token token = list.get(i);
      if (!token.fraction) {
        if (prev != null) {
          prev = apply(prev, list.get(i + 1), token.type);
        } else {
          prev = apply(list.get(i - 1), list.get(i + 1), token.type);
        }
      }
    }
    if (prev.nume == 0) {
      return "0/1";
    }
    prev = prev.reduce();
    return "" + prev.nume + "/" + prev.deno;
  }

  private Token apply(Token first, Token second, int op) {
    if (op == 1) {
      return first.add(second);
    } else {
      return first.sub(second);
    }
  }

  public static void main(String args[]) {
    FracAddAndSub f = new FracAddAndSub();
    System.out.println(f.fractionAddition("-7/3"));
  }


}
