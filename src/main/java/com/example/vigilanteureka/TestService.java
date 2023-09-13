package com.example.vigilanteureka;

import org.springframework.stereotype.Service;

@Service
public class TestService {

  public boolean isTrue(boolean value) {
    // bad smell code
    if (value == true) {
      return true;
    } else {
      return false;
    }
  }

}
