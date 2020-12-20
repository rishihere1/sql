package com.example.mypractice.dto;

import java.util.List;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

/**
 * @author rishi - created on 14/06/20
 **/
@Data
public class IncludeExcludeRule {
  @Field
  private String ruleType;

  @Field
  private List<String> items;
}
