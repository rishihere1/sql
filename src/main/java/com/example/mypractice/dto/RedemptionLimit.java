package com.example.mypractice.dto;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Builder;
import lombok.Data;

/**
 * @author rishi - created on 14/06/20
 **/
@Data
//@Builder
public class RedemptionLimit {
  @Field
  private int redemptionLimitPerCustomer;

  @Field
  private int redemptionLimitPerOrder;

  @Field
  private int redemptionLimitPerDevice;
}
