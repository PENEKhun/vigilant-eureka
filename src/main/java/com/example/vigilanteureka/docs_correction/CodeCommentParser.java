package com.example.vigilanteureka.docs_correction;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to parse comments from source code.
 */
public class CodeCommentParser {

  // The source code to parse.
  String sourceCode;

  public CodeCommentParser(String sourceCode) {
    this.sourceCode = sourceCode;
  }

  /**
   * Parse comments from source code.
   * @return : The list of comments.
   */
  public List<CodeComment> parse() {
    List<CodeComment> codeComments = new ArrayList<>();

    String[] lines = this.sourceCode.split("\n");
    int startLine = 0;
    int endLine = 0;
    StringBuilder comment = new StringBuilder();

    boolean inBlockComment = false;
    boolean inJavaDocComment = false;

    for (int i = 0; i < lines.length; i++) {
      String line = lines[i];

      if (inBlockComment) {
        if (line.contains("*/")) {
          inBlockComment = false;
          endLine = i + 1;
          comment.append(line).append("\n");
          codeComments.add(
              new CodeComment("/* */ Comment", "", startLine, endLine, comment.toString()));
          comment = new StringBuilder();
        } else {
          comment.append(line).append("\n");
        }
      } else if (inJavaDocComment) {
        if (line.contains("*/")) {
          inJavaDocComment = false;
          endLine = i + 1;
          comment.append(line).append("\n");
          codeComments.add(
              new CodeComment("JavaDoc Comment", "", startLine, endLine, comment.toString()));
          comment = new StringBuilder();
        } else {
          comment.append(line).append("\n");
        }
      } else if (line.trim().startsWith("//")) {
        if (startLine == 0) {
          startLine = i + 1;
        }
        comment.append(line).append("\n");
        endLine = i + 1;
      } else if (line.trim().startsWith("/*")) {
        inBlockComment = true;
        startLine = i + 1;
        comment.append(line).append("\n");
        if (line.contains("*/")) {
          inBlockComment = false;
          endLine = i + 1;
          comment = new StringBuilder();
        }
      } else if (line.trim().startsWith("/**")) {
        inJavaDocComment = true;
        startLine = i + 1;
        comment.append(line).append("\n");
        if (line.contains("*/")) {
          inJavaDocComment = false;
          endLine = i + 1;
          comment = new StringBuilder();
        }
      } else {
        if (startLine != 0) {
          codeComments.add(
              new CodeComment("Single-line Comment", "", startLine, endLine, comment.toString()));
          startLine = 0;
          endLine = 0;
          comment = new StringBuilder();
        }
      }
    }

    return codeComments;
  }

}
