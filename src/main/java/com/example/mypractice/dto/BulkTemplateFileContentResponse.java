package com.example.mypractice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rishi - created on 16/12/20
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BulkTemplateFileContentResponse {

  private String requestId;
  private byte[] fileContent;
}
