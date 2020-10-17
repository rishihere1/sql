package com.example.mypractice.dto;

import lombok.Data;

@Data
public class CreateMerchantRequest {
  private String storeName;
  private String storeAddress;
  private String mobileNumber;
  private String emailId;
}
