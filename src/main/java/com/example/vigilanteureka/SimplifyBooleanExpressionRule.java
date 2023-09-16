package com.example.vigilanteureka;

import org.springframework.stereotype.Service;

@Service
public class SimplifyBooleanExpressionRule {

  public boolean detectedCase1(boolean value) {
    // bad smell code
    if (value == true) {
      return true;
    } else {
      return false;
    }
  }

  public boolean detectedCase2(boolean hi) {
    // bad smell code
    if (false == hi) {
      return false;
    } else {
      return true;
    }
  }

  public boolean notDetectedCase1(boolean value) {
    // good code in this rule
    if (value) {
      return true;
    } else {
      return false;
    }
  }

}
