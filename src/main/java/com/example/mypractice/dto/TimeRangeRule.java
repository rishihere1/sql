package com.example.mypractice.dto;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Builder;
import lombok.Data;

/**
 * @author rishi - created on 14/06/20
 **/
@Data
//@Builder
public class TimeRangeRule {
  @Field
  private Date startDate;

  @Field
  private Date endDate;
}
