package com.example.mypractice.dto;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

/**
 * @author rishi - created on 14/06/20
 **/
@Data
public class TimeRangeRule {
  @Field
  private Date startDate;

  @Field
  private Date endDate;
}
