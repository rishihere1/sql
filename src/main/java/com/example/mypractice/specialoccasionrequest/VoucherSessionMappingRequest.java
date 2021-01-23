package com.example.mypractice.specialoccasionrequest;

/**
 * @author rishi - created on 17/12/20
 **/
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoucherSessionMappingRequest implements Serializable {
  private static final long serialVersionUID = -5629057770590786956L;

  private String sessionId;
  private String sessionName;
  private Date sessionStartDate;
  private Date sessionEndDate;
  private List<VoucherInfoRequest> voucherInfoList;
}
