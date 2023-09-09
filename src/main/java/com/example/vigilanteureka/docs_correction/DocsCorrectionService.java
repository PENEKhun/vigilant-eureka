package com.example.vigilanteureka.docs_correction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocsCorrectionService {

  /*
    A service code that checks the grammar correction of the contents of the README file,
    Comments (including javaDoc) in the Java file.
    Author : penekhun
   */

  /**
   * Start the service.
   * @param clonedCodeDir The directory where the cloned code is located.
   */
  public void start(String clonedCodeDir) {
    // codeDirectory 경로 내 모든 Java 파일을 탐색하여 내용 가져오기
    File directory = new File(clonedCodeDir);
    List<String> sources = getJavaCodeFromDirectory(directory);

    List<CodeComment> codeComments = new ArrayList<>();
    for (String sourceCode : sources) {
      codeComments.addAll(new CodeCommentParser(sourceCode).parse());
    }

    codeComments.forEach(System.out::println);
  }

  /**
   * Get the contents of all Java files in the codeDirectory path.
   * @param directory : The directory where the cloned code is located.
   * @return : The contents of all Java files in the codeDirectory path.
   */
  private List<String> getJavaCodeFromDirectory(File directory) {
    List<String> sources = new ArrayList<>();

    // 디렉토리 내의 파일 목록 가져오기
    File[] files = directory.listFiles();

    if (files != null) {
      for (File file : files) {
        if (file.isDirectory()) {
          // 하위 디렉토리인 경우 재귀적으로 탐색
          sources.addAll(getJavaCodeFromDirectory(file));
        } else if (isJavaFile(file)) {
          // Java 파일인 경우 내용을 읽어 sources 리스트에 추가
          String code = parseFileContent(file);
          sources.add(code);
        }
      }
    }

    return sources;
  }

  /**
   * Read the contents of the file.
   * @param file : The file to read.
   * @return : The contents of the file.
   */
  private String parseFileContent(File file) {
    StringBuilder result = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = reader.readLine()) != null) {
        result.append(line).append("\n");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result.toString();
  }

  /**
   * Check if the file is a Java file.
   * @param file : The file to check.
   * @return : True if the file is a Java file, false otherwise.
   */
  private boolean isJavaFile(File file) {
    return file.isFile() && file.getName().endsWith(".java");
  }
}
