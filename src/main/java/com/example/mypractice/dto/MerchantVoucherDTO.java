package com.example.mypractice.dto;


import java.util.Date;

import lombok.Data;

/**
 * @author rishi - created on 14/06/20
 **/
@Data
public class MerchantVoucherDTO {
  private String voucherName;
  private String voucherCode;
  private VoucherStatus voucherStatus;
  private String redemptionType;
  private String combinationType;
  private String rewardType;
  private boolean displayOnProductDetail;
  private RedemptionLimit redemptionLimit = new RedemptionLimit();
  private String changeLog;
  private boolean allStore;
  private String storeName;
  private String merchantName;
  private String merchantCode;
  private long itemSkuCount;
  private IncludeExcludeRule productRule = new IncludeExcludeRule();
  private TimeRangeRule timeRangeRule = new TimeRangeRule();
  private boolean markForStop;
  private String itemSku;
}
