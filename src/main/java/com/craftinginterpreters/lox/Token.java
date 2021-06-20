package com.craftinginterpreters.lox;

/**
 * @author manoji on 2019-10-01.
 */
public class Token {

  final TokenType type;
  final String lexeme;
  final Object literal;
  final int line;

  public Token(TokenType type, String lexeme, Object literal, int line) {
    this.type = type;
    this.lexeme = lexeme;
    this.literal = literal;
    this.line = line;
  }

  public String toString() {
    return type + " " + lexeme + " " + literal;
  }

}
