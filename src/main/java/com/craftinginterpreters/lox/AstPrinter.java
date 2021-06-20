package com.craftinginterpreters.lox;

import com.craftinginterpreters.lox.Expr.Assign;
import com.craftinginterpreters.lox.Expr.Binary;
import com.craftinginterpreters.lox.Expr.Grouping;
import com.craftinginterpreters.lox.Expr.Literal;
import com.craftinginterpreters.lox.Expr.Unary;
import com.craftinginterpreters.lox.Expr.Variable;

/**
 * @author manoji on 2019-10-06.
 */
public class AstPrinter implements Expr.Visitor<String> {

  public static void main(String[] args) {
    Expr expression = new Expr.Binary(
        new Expr.Unary(
            new Token(TokenType.MINUS, "-", null, 1),
            new Expr.Literal(123)),
        new Token(TokenType.STAR, "*", null, 1),
        new Expr.Grouping(
            new Expr.Literal(45.67)));

    System.out.println(new AstPrinter().print(expression));
  }


  public String print(Expr expr) {
    return expr.accept(this);
  }

  @Override
  public String visitAssignExpr(Assign expr) {
    return null;
  }

  @Override
  public String visitBinaryExpr(Binary expr) {
    return parenthesize(expr.operator.lexeme, expr.left, expr.right);
  }

  @Override
  public String visitGroupingExpr(Grouping expr) {
    return parenthesize("group", expr.expression);
  }

  @Override
  public String visitLiteralExpr(Literal expr) {
    if (expr.value == null) {
      return "nil";
    }
    return expr.value.toString();
  }

  @Override
  public String visitUnaryExpr(Unary expr) {
    return parenthesize(expr.operator.lexeme, expr.right);
  }

  @Override
  public String visitVariableExpr(Variable expr) {
    return null;
  }

  private String parenthesize(String name, Expr... exprs) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("(").append(name);

    for (Expr expr : exprs) {
      stringBuilder.append(" ");
      stringBuilder.append(expr.accept(this));
    }
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
}
