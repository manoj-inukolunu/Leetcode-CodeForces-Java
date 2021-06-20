package com.craftinginterpreters.lox;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import java.util.stream.Collectors;

/**
 * @author manoji on 2019-09-30.
 */
public class Lox {

  static boolean hadError = false;
  static boolean hadRuntimeError = false;

  private static final Interpreter interpreter = new Interpreter();


  static void runtimeError(RuntimeError error) {
    System.err.println(error.getMessage() +
        "\n[line " + error.token.line + "]");
    hadRuntimeError = true;
  }


  private static void runFile(String path) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(path));
    run(new String(bytes, Charset.defaultCharset()));

    if (hadError) {
      System.exit(65);
    }

    if (hadRuntimeError) {
      System.exit(70);
    }

  }

  public static void main(String[] args) throws IOException {

    if (args.length > 1) {
      System.out.println("Usage : jLox [script]");
      System.exit(64);
    } else if (args.length == 1) {
      runFile(args[0]);
    } else {
      runPrompt();
    }
  }

  static void error(Token token, String message) {
    if (token.type == TokenType.EOF) {
      report(token.line, " at end", message);
    } else {
      report(token.line, " at '" + token.lexeme + "'", message);
    }
  }

  static void error(int line, String message) {
    report(line, "", message);
  }

  private static void report(int line, String where, String message) {
    System.err.println("[line " + line + " ] Error" + where + ": " + message);
    hadError = true;
  }

  private static void run(String source) {
    Scanner scanner = new Scanner(source);
    List<Token> tokens = scanner.scanTokens();

    Parser parser = new Parser(tokens);
    List<Statement> statements = parser.parse();

    if (hadError) {
      return;
    }

    interpreter.interpret(statements);

   /* // For now, just print the tokens.
    for (Token token : tokens) {
      System.out.println(token);
    }*/
  }

  private static void runPrompt() throws IOException {
    InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    for (; ; ) {
      System.out.println("> ");
      run(bufferedReader.readLine());
      hadError = false;
    }
  }

}
