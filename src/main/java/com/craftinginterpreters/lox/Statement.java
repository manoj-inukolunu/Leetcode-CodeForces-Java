package com.craftinginterpreters.lox;

import java.util.List;

/**
 * @author manoji on 2019-10-07.
 */
public abstract class Statement {

  interface Visitor<R> {

    R visitBlockStatement(Block statement);


    R visitExpressionStatement(Expression statement);

    R visitPrintStatement(Print statement);

    R visitVarStatement(Var statement);
  }

  static class Block extends Statement {

    Block(List<Statement> statements) {
      this.statements = statements;
    }

    <R> R accept(Visitor<R> visitor) {
      return visitor.visitBlockStatement(this);
    }

    final List<Statement> statements;
  }


  static class Expression extends Statement {

    Expression(Expr expression) {
      this.expression = expression;
    }

    <R> R accept(Visitor<R> visitor) {
      return visitor.visitExpressionStatement(this);
    }

    final Expr expression;
  }

  static class Print extends Statement {

    Print(Expr expression) {
      this.expression = expression;
    }

    <R> R accept(Visitor<R> visitor) {
      return visitor.visitPrintStatement(this);
    }

    final Expr expression;
  }

  static class Var extends Statement {

    Var(Token name, Expr initializer) {
      this.name = name;
      this.initializer = initializer;
    }

    <R> R accept(Visitor<R> visitor) {
      return visitor.visitVarStatement(this);
    }

    final Token name;
    final Expr initializer;
  }

  abstract <R> R accept(Visitor<R> visitor);
}
