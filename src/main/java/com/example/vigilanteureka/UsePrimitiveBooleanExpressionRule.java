package com.example.vigilanteureka;

public class UsePrimitiveBooleanExpressionRule {

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
