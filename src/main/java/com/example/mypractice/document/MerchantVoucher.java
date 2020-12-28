package com.example.mypractice.document;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.mypractice.dto.IncludeExcludeRule;
import com.example.mypractice.dto.RedemptionLimit;
import com.example.mypractice.dto.TimeRangeRule;
import com.example.mypractice.dto.VoucherStatus;
import com.example.mypractice.dto.VoucherType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rishi - created on 14/06/20
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "MerchantVoucher")
public class MerchantVoucher {

  @org.springframework.data.annotation.Id
  private String id;

  @Field
  private String voucherName;
  @Field
  private String voucherCode;
  @Field
  private VoucherStatus voucherStatus;
  @Field
  private String redemptionType;
  @Field
  private String combinationType;
  @Field
  private String rewardType;
  @Field
  private boolean displayOnProductDetail;
  @Field
  private RedemptionLimit redemptionLimit = new RedemptionLimit();
  @Field
  private String changeLog;
  @Field
  private boolean allStore;
  @Field
  private String storeName;
  @Field
  private String merchantName;
  @Field
  private String merchantCode;
  @Field
  private long itemSkuCount;
  @Field
  private IncludeExcludeRule productRule = new IncludeExcludeRule();
  @Field
  private TimeRangeRule timeRangeRule = new TimeRangeRule();
  @Field
  private boolean markForStop;
  @Field
  private Date testDate;
  @Field
  private Double doubleTest;
  @Field
  private  Integer myInteger;
  @Field
  private VoucherType voucherType;
  @Field
  private Date executeStartDate;
  @Field
  private Date endDate;

  @Field
//  @JsonIgnore
  @DBRef(lazy = true)
  private SpecialOccasion specialOccasion;

}

