package com.example.mypractice.dto;

import lombok.Data;

@Data
public class BaseRestResponse {
  private boolean success;
  private String error;
}
