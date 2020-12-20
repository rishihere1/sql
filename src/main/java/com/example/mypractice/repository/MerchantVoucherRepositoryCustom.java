package com.example.mypractice.repository;

import java.util.List;

import com.example.mypractice.document.MerchantVoucher;
import com.example.mypractice.dto.MerchantVoucherDTO;

/**
 * @author rishi - created on 14/06/20
 **/
public interface MerchantVoucherRepositoryCustom {

  List<MerchantVoucher> getSelectedFields(String voucherStatus, String voucherCode);

  MerchantVoucher updateSelectedVouchers(String voucherCode);

  void query();

  void upsertCheck(MerchantVoucherDTO merchantVoucherDTO);

  List<MerchantVoucher> getIncludeTest();

  void getItemsAggregation();

  List<MerchantVoucher> getByVoucherCode();
}
