package com.example.mypractice.dto;

import lombok.Data;

@Data
public class CreateProductRequest {
  private String productId;
  private String productName;
  private long originalPrice;
  private String description;
  private String brand;
  private String imageUrl1;
  private String imageUrl2;
  private String imageUrl3;
}
