package com.example.mypractice.specialoccasionrequest;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rishi - created on 17/12/20
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpecialOccasionVoucherSessionsSaveRequest {
  private String specialOccasionId;
  private List<VoucherSessionMappingRequest> voucherSessionMappingList;

}
