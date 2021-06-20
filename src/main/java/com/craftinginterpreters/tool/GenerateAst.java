package com.craftinginterpreters.tool;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * @author manoji on 2019-10-05.
 */
public class GenerateAst {

  public static void main(String args[]) throws Exception {
    /*if (args.length != 1) {
      System.err.println("Usage : generage_ast < output directory >");
      System.exit(1);
    }*/

    String outputDir = "";//args[0];

   /* defineAst(outputDir, "Expr", Arrays.asList(
        "Assign    :  Token name, Expr value",
        "Binary    :  Expr left, Token operator, Expr right",
        "Grouping  :  Expr expression",
        "Literal   :  Object value",
        "Unary     :  Token Operator, Expr right",
        "Variable  :  Token name"
    ));*/

    defineAst(outputDir, "Statement", Arrays.asList(
        "Block      : List<Statement> statements",
        "Expression : Expr expression",
        "Print      : Expr expression",
        "Var        : Token name, Expr initializer"
    ));
  }

  private static void defineAst(String outputDir, String baseName, List<String> types) throws Exception {

    String path = outputDir + "/" + baseName + ".java";

    PrintWriter outWriter = new PrintWriter(System.out);
    outWriter.println("package com.craftinginterpreters.lox;");
    outWriter.println();
    outWriter.println("import java.util.List;");
    outWriter.println();
    outWriter.println("abstract class " + baseName + " {");

    defineVisitor(outWriter, baseName, types);

    for (String type : types) {
      String className = type.split(":")[0].trim();
      String fields = type.split(":")[1].trim();
      defineType(outWriter, baseName, className, fields);
    }
    outWriter.println();

    outWriter.println(" abstract <R> R accept(Visitor<R> visitor);");
    outWriter.println("}");
    outWriter.close();
  }

  private static void defineVisitor(PrintWriter writer, String baseName, List<String> types) {
    writer.println(" interface Visitor<R> {");

    for (String type : types) {
      String typeName = type.split(":")[0].trim();
      writer.println(" R visit" + typeName + baseName + "(" + typeName + " " + baseName.toLowerCase() + ");");
    }
    writer.println(" }");
  }

  private static void defineType(PrintWriter writer, String baseName, String className, String fieldList) {
    writer.println(" static class " + className + " extends " + baseName + " {");

    //constructor
    writer.println("   " + className + "(" + fieldList + ") {");

    //Store parameters in fields
    String[] fields = fieldList.split(", ");
    for (String field : fields) {
      String name = field.split(" ")[1];
      writer.println("    this." + name + " = " + name + ";");
    }

    writer.println("  }");

    writer.println();
    writer.println(" <R> R accept(Visitor<R> visitor) {");
    writer.println(" return visitor.visit" + className + baseName + "(this);");
    writer.println("}");

    //Fields
    writer.println();
    for (String field : fields) {
      writer.println(" final " + field + ";");
    }

    writer.println(" }");

  }

}
