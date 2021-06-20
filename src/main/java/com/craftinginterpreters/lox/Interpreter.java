package com.craftinginterpreters.lox;


import com.craftinginterpreters.lox.Expr.Assign;
import com.craftinginterpreters.lox.Expr.Binary;
import com.craftinginterpreters.lox.Expr.Grouping;
import com.craftinginterpreters.lox.Expr.Literal;
import com.craftinginterpreters.lox.Expr.Unary;
import com.craftinginterpreters.lox.Expr.Variable;
import com.craftinginterpreters.lox.Statement.Block;
import com.craftinginterpreters.lox.Statement.Expression;
import com.craftinginterpreters.lox.Statement.Print;
import com.craftinginterpreters.lox.Statement.Var;
import java.util.List;

/**
 * @author manoji on 2019-10-06.
 */
public class Interpreter implements Expr.Visitor<Object>, Statement.Visitor<Void> {

  private Environment environment = new Environment();

  public void interpret(List<Statement> statements) {
    try {
      for (Statement statement : statements) {
        execute(statement);
      }
    } catch (RuntimeError error) {
      Lox.runtimeError(error);
    }
  }

  private void execute(Statement statement) {
    statement.accept(this);
  }

  public void interpretExpression(Expr expression) {
    try {
      Object value = evaluate(expression);
      System.out.println(stringify(value));
    } catch (RuntimeError error) {
      Lox.runtimeError(error);
    }
  }

  private String stringify(Object object) {
    if (object == null) {
      return "nil";
    }

    // Hack. Work around Java adding ".0" to integer-valued doubles.
    if (object instanceof Double) {
      String text = object.toString();
      if (text.endsWith(".0")) {
        text = text.substring(0, text.length() - 2);
      }
      return text;
    }

    return object.toString();
  }

  @Override
  public Void visitBlockStatement(Block statement) {
    executeBlock(statement.statements, new Environment(environment));
    return null;
  }

  private void executeBlock(List<Statement> statements, Environment environment) {
    Environment previous = this.environment;
    try {
      this.environment = environment;
      for (Statement statement : statements) {
        execute(statement);
      }
    } finally {
      this.environment = previous;
    }
  }

  @Override
  public Void visitExpressionStatement(Expression statement) {
    evaluate(statement.expression);
    return null;
  }

  @Override
  public Void visitPrintStatement(Print statement) {
    Object value = evaluate(statement.expression);
    System.out.println(stringify(value));
    return null;
  }

  @Override
  public Void visitVarStatement(Var statement) {
    Object value = null;
    if (statement.initializer != null) {
      value = evaluate(statement.initializer);
    }
    environment.define(statement.name.lexeme, value);
    return null;
  }


  private void checkNumberOperand(Token operator, Object operand) {
    if (operand instanceof Double) {
      return;
    }
    throw new RuntimeError(operator, "Object must be a Number");
  }


  private void checkNumberOperands(Token operator, Object left, Object right) {
    if (left instanceof Double && right instanceof Double) {
      return;
    }
    throw new RuntimeError(operator, "Operands must be numbers.");
  }

  @Override
  public Object visitAssignExpr(Assign expr) {
    Object value = evaluate(expr.value);
    environment.assign(expr.name, value);
    return value;
  }

  @Override
  public Object visitBinaryExpr(Binary expr) {

    Object left = evaluate(expr.left);
    Object right = evaluate(expr.right);

    switch (expr.operator.type) {
      case GREATER:
        checkNumberOperands(expr.operator, left, right);
        return (double) left > (double) right;
      case GREATER_EQUAL:
        checkNumberOperands(expr.operator, left, right);
        return (double) left >= (double) right;
      case LESS:
        checkNumberOperands(expr.operator, left, right);
        return (double) left < (double) right;
      case LESS_EQUAL:
        checkNumberOperands(expr.operator, left, right);
        return (double) left <= (double) right;
      case MINUS:
        checkNumberOperand(expr.operator, right);
        return (double) left - (double) right;
      case PLUS:
        if (left instanceof Double && right instanceof Double) {
          return (double) left + (double) right;
        }

        if (left instanceof String && right instanceof String) {
          return left + (String) right;
        }
        throw new RuntimeError(expr.operator, "Operands must be two numbers or two strings.");
      case SLASH:
        checkNumberOperands(expr.operator, left, right);
        return (double) left / (double) right;
      case STAR:
        checkNumberOperands(expr.operator, left, right);
        return (double) left * (double) right;
      case BANG_EQUAL:
        return !isEqual(left, right);
      case EQUAL_EQUAL:
        return isEqual(left, right);
    }
    return null;
  }

  private boolean isEqual(Object a, Object b) {
    if (a == null && b == null) {
      return true;
    }
    if (a == null) {
      return false;
    }
    return a.equals(b);
  }

  @Override
  public Object visitGroupingExpr(Grouping expr) {
    return evaluate(expr.expression);
  }

  @Override
  public Object visitLiteralExpr(Literal expr) {
    return expr.value;
  }

  @Override
  public Object visitUnaryExpr(Unary expr) {
    Object right = evaluate(expr.right);

    switch (expr.operator.type) {
      case BANG:
        return !isTruthy(right);
      case MINUS:
        return -(double) right;
    }
    return null;
  }

  @Override
  public Object visitVariableExpr(Variable expr) {
    return environment.get(expr.name);
  }

  private boolean isTruthy(Object object) {
    if (object == null) {
      return false;
    }
    if (object instanceof Boolean) {
      return (boolean) object;
    }
    return true;
  }

  private Object evaluate(Expr expr) {
    return expr.accept(this);
  }
}
