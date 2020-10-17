package com.example.mypractice.service;

import com.example.mypractice.document.MerchantVoucher;
import com.example.mypractice.dto.MerchantVoucherDTO;

/**
 * @author rishi - created on 14/06/20
 **/
public interface MerchantVoucherService {

  MerchantVoucher addMerchantVoucher (MerchantVoucherDTO merchantVoucherDTO);

  void getMerchantVoucher();

  void updateMerchantVoucher();

  void query();

  void upsertCheck(MerchantVoucherDTO merchantVoucherDTO);

  void postEnum();

  MerchantVoucher saveOneVoucher();

  void getInclude();

  //  void testDate();
}
