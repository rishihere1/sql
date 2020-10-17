package com.example.mypractice.dto;

import lombok.Data;

@Data
public class CreateCustomerRequest {
  private String name;
  private String address;
  private String mobileNumber;
  private String emailId;
}
