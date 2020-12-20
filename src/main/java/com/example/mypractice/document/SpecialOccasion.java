package com.example.mypractice.document;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.mypractice.dto.TimeRangeRule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rishi - created on 30/11/20
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Merchant")
public class SpecialOccasion {

  @org.springframework.data.annotation.Id
  private String id;

  private String specialOccasionName;

  private TimeRangeRule registrationPeriod;

  private TimeRangeRule claimPeriod;

  private Date useStartDate;

  private int minUsePeriod;

  private int maxUsePeriod;

  private Date executeStartDate;
}
