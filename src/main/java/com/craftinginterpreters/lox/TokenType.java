package com.craftinginterpreters.lox;

/**
 * @author manoji on 2019-10-01.
 */
public enum TokenType {

  //Single-character tokens.
  LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE, COMMA, DOT, MINUS, PLUS, SEMICOLON, SLASH, STAR,

  // One of two character tokens
  BANG, BANG_EQUAL, EQUAL, EQUAL_EQUAL, GREATER, GREATER_EQUAL, LESS, LESS_EQUAL,

  //Literals
  IDENTIFIER, STRING, NUMBER,

  //KeyWords.
  AND, CLASS, ELSE, FALSE, FUN, FOR, IF, NIL, OR, PRINT, RETURN, SUPER, THIS, TRUE, VAR, WHILE,

  EOF
}
