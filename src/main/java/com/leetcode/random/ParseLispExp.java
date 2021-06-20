package com.leetcode.random;


import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import org.jetbrains.annotations.NotNull;

public class ParseLispExp {

  enum Type {
    ADD, MULT, LET, LPAREN, RPAREN, VAR, NUM, TOP
  }

  class Token {

    Type type;
    Integer val;
    String var;
    Token parent;
    List<Token> children = new ArrayList<>();

    HashMap<String, Integer> currMap = new HashMap<>();

    public int evaluate(HashMap<String, Integer> varMap) {
      if (type == Type.NUM) {
        return val;
      } else if (type == Type.VAR) {
        if (val != null) {
          return val;
        }
        return varMap.get(var);
      } else if (type == Type.ADD) {
        currMap.putAll(varMap);
        return children.get(0).evaluate(currMap) + children.get(1).evaluate(currMap);
      } else if (type == Type.MULT) {
        currMap.putAll(varMap);
        return children.get(0).evaluate(currMap) * children.get(1).evaluate(currMap);
      } else {
        currMap.putAll(varMap);
        for (int i = 0; i < children.size() - 1; i++) {
          Type t = children.get(i).type;
          if (t == Type.VAR) {
            currMap.put(children.get(i).var, children.get(i + 1).evaluate(currMap));
          }
        }
        return children.get(children.size() - 1).evaluate(currMap);
      }
    }

    @Override
    public String toString() {
      return "Token{" +
          "type=" + type +
          ", val=" + val +
          ", var='" + var + '\'' +
          '}';
    }

    public Token(Type type, Token parent) {
      this.type = type;
      this.parent = parent;
    }

    public Token(Type type, int num, Token parent) {
      this.val = num;
      this.type = type;
      this.parent = parent;
    }

    public Token(Type type, String var, Token parent) {
      this.var = var;
      this.parent = parent;
      this.type = type;
    }
  }

  public int process(String str) {
    int i = 0;
    Stack<Token> stack = new Stack<>();
    Token first = new Token(Type.TOP, null);
    first.parent = null;
    stack.push(first);
    char prev = '*';
    while (i < str.length()) {
      char ch = str.charAt(i);
      Token parent = stack.peek();
      if (ch == ')') {
        List<Token> list = new ArrayList<>();
        while (true) {
          Token top = stack.pop();
          if (top.type == Type.LPAREN) {
            Token token = gen(list);
            stack.push(token);
            break;
          } else {
            list.add(0, top);
          }
        }
        i++;
      } else if (ch == '(') {
        stack.push(new Token(Type.LPAREN, parent));
        prev = ch;
        i++;
      } else if (ch == 'm' && prev == '(') {
        stack.push(new Token(Type.MULT, parent));
        i += 4;
        prev = str.charAt(i);
      } else if (ch == 'a' && prev == '(') {
        stack.push(new Token(Type.ADD, parent));
        i += 3;
        prev = str.charAt(i);
      } else if (ch == 'l' && prev == '(') {
        stack.push(new Token(Type.LET, parent));
        i += 3;
        prev = str.charAt(i);
      } else if (ch == ' ') {
        i++;
      } else if (Character.isDigit(ch) || ch == '-') {
        String number = getString(str, i);
        stack.push(new Token(Type.NUM, Integer.parseInt(number), parent));
        i += number.length();
      } else {
        String var = getString(str, i);
        stack.push(new Token(Type.VAR, var, parent));
        i += var.length();
      }
    }
    Token top = stack.pop();
    return top.evaluate(new HashMap<>());
  }

  @NotNull
  private String getString(String str, int i) {
    int idx1 = str.indexOf(" ", i);
    int idx2 = str.indexOf(")", i);
    if (idx1 > 0 && idx2 > 0) {
      return str.substring(i, Math.min(idx1, idx2));
    }
    return str.substring(i, idx1 > 0 ? idx1 : idx2);
  }

  private Token gen(List<Token> tokens) {
    Type tokenType = tokens.get(0).type;
    if (tokenType == Type.ADD || tokenType == Type.MULT) {
      Token ret = new Token(tokenType, null);
      ret.children.add(tokens.get(1));
      ret.children.add(tokens.get(2));
      return ret;
    } else {
      Token ret = new Token(tokenType, null);
      for (int i = 1; i < tokens.size(); i++) {
        ret.children.add(tokens.get(i));
      }
      return ret;
    }
  }


  public static void main(String args[]) {
    ParseLispExp p = new ParseLispExp();
    List<String> expressions = Lists.newArrayList(
        "(let x 7 -12)",
        "(add 1 2)",
        "(mult 3 (add 2 3))",
        "(let x 2 (mult x 5))",
        "(let x 2 (mult x (let x 3 y 4 (add x y))))",
        "(let x 3 x 2 x)",
        "(let x 1 y 2 x (add x y) (add x y))",
        "(let x 2 (add (let x 3 (let x 4 x)) x))",
        "(let a1 3 b2 (add a1 1) b2)"
    );
    expressions.forEach(s -> System.out.println(p.process(s)));
  }

}
