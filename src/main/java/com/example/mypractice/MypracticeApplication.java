package com.example.mypractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MypracticeApplication {

  public static void main(String[] args) {
    SpringApplication.run(MypracticeApplication.class, args);
  }

}
