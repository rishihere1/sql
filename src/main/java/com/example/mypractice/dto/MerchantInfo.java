package com.example.mypractice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MerchantInfo {
  private String merchantId;
  private long merchantPrice;
  private String promotionId;
}
