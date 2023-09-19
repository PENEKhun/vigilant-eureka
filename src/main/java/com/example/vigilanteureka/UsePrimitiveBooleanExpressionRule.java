package com.example.vigilanteureka;

public class UsePrimitiveBooleanExpressionRule {

  // https://rules.sonarsource.com/java/RSPEC-5411/

  public boolean detectedCase1(Boolean value) {
    if (value){
      return true;
    } else {
      return false;
    }
  }

  public boolean detectedCase2(Boolean hi) {
    if (!hi) {
      return false;
    } else {
      return true;
    }
  }

}
