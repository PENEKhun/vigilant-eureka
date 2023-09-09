package com.example.vigilanteureka.docs_correction;

import lombok.Getter;

@Getter
public class CodeComment {

  String filePath;
  String fileName;
  int startLine;
  int endLine;
  String comment;

  public CodeComment(String filePath, String fileName, int startLine, int endLine, String comment) {
    this.filePath = filePath;
    this.fileName = fileName;
    this.startLine = startLine;
    this.endLine = endLine;
    this.comment = comment;
  }

  @Override
  public String toString() {
    return "CodeComment{" +
        "filePath='" + filePath + '\'' +
        ", fileName='" + fileName + '\'' +
        ", startLine=" + startLine +
        ", endLine=" + endLine +
        ", comment='" + comment + '\'' +
        '}';
  }
}
