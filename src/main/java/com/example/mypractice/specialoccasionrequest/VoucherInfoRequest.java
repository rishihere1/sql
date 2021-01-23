package com.example.mypractice.specialoccasionrequest;

/**
 * @author rishi - created on 17/12/20
 **/
import java.io.Serializable;
import java.util.Set;

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
public class VoucherInfoRequest implements Serializable {
  private static final long serialVersionUID = -2233118831447869025L;

  private String voucherCode;
  private String group;
  private Set<String> productImageUrls;
  private int priority;
}
