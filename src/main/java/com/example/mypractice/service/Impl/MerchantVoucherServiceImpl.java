package com.example.mypractice.service.Impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.example.mypractice.document.MerchantVoucher;
import com.example.mypractice.dto.MerchantVoucherDTO;
import com.example.mypractice.dto.TimeRangeRule;
import com.example.mypractice.dto.VoucherStatus;
import com.example.mypractice.dto.VoucherType;
import com.example.mypractice.repository.MerchantVoucherRepository;
import com.example.mypractice.service.MerchantVoucherService;

/**
 * @author rishi - created on 14/06/20
 **/
@Service
public class MerchantVoucherServiceImpl implements MerchantVoucherService {

  @Autowired
  MerchantVoucherRepository merchantVoucherRepository;

  @Override
  public MerchantVoucher addMerchantVoucher(MerchantVoucherDTO merchantVoucherDTO) {
    MerchantVoucher merchantVoucher = new MerchantVoucher();

    merchantVoucher.setAllStore(merchantVoucherDTO.isAllStore());
    merchantVoucher.setChangeLog(merchantVoucherDTO.getChangeLog());
    merchantVoucher.setCombinationType(merchantVoucherDTO.getCombinationType());
    merchantVoucher.setDisplayOnProductDetail(merchantVoucherDTO.isDisplayOnProductDetail());
    merchantVoucher.setItemSkuCount(merchantVoucherDTO.getItemSkuCount());
    merchantVoucher.setMarkForStop(merchantVoucherDTO.isMarkForStop());
    merchantVoucher.setMerchantCode(merchantVoucherDTO.getMerchantCode());
    merchantVoucher.setMerchantName(merchantVoucherDTO.getMerchantName());
    merchantVoucher.setProductRule(merchantVoucherDTO.getProductRule());
    merchantVoucher.setRedemptionLimit(merchantVoucherDTO.getRedemptionLimit());
    merchantVoucher.setRewardType(merchantVoucherDTO.getRewardType());
    merchantVoucher.setRedemptionType(merchantVoucherDTO.getRedemptionType());
    merchantVoucher.setStoreName(merchantVoucherDTO.getStoreName());
    TimeRangeRule timeRangeRule = new TimeRangeRule();
    timeRangeRule.setStartDate(new Date());
    timeRangeRule.setEndDate(new Date());
    merchantVoucher.setTimeRangeRule(timeRangeRule);
    merchantVoucher.setVoucherCode(merchantVoucherDTO.getVoucherCode());
    merchantVoucher.setVoucherName(merchantVoucherDTO.getVoucherName());
    merchantVoucher.setVoucherStatus(merchantVoucherDTO.getVoucherStatus());
    merchantVoucher.setTestDate(new Date());
    merchantVoucher.setDoubleTest(220D);
    merchantVoucher.setMyInteger(2203);
    merchantVoucher.setExecuteStartDate(new Date());
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    calendar.add(Calendar.DATE, 10);
    merchantVoucher.setEndDate(calendar.getTime());
    MerchantVoucher merchantVoucher1= merchantVoucherRepository.save(merchantVoucher);
    return merchantVoucher1;
  }

  @Override
  public void getMerchantVoucher() {
    List<MerchantVoucher> merchantVoucherList =
        merchantVoucherRepository.getSelectedFields("PENDING", "MV-100012");

    merchantVoucherList.stream().forEach(merchantVoucher -> {
      System.out.println(merchantVoucher.getChangeLog());
      System.out.println(merchantVoucher.getVoucherCode());
      System.out.println(merchantVoucher.getVoucherName());
      System.out.println(merchantVoucher.getTimeRangeRule());
    });

  }

  @Override
  public void updateMerchantVoucher() {
    MerchantVoucher merchantVoucher = merchantVoucherRepository.updateSelectedVouchers("MV-000010");
    System.out.println(merchantVoucher.getVoucherCode());
    System.out.println(merchantVoucher.isMarkForStop());
    System.out.println(merchantVoucher.getMerchantName());
  }

  @Override
  public void query() {
    merchantVoucherRepository.query();
  }

  @Override
  public void upsertCheck(MerchantVoucherDTO merchantVoucherDTO) {
    merchantVoucherRepository.upsertCheck(merchantVoucherDTO);
  }

  @Override
  public void postEnum() {
//    MerchantVoucher merchantVoucher = new MerchantVoucher();
//    merchantVoucher.setVoucherName("Enum check");
//    merchantVoucher.setVoucherCode("MV-21300");
//    merchantVoucher.setStoreName("Special enum store");
//    merchantVoucher.setVoucherStatus(VoucherStatus.PENDING);
//    merchantVoucher.setVoucherType(VoucherType.SPECIAL);
    Pageable pageable = PageRequest.of(0, 3);

    Page<MerchantVoucher> merchantVoucherSet =
          merchantVoucherRepository.findByVoucherStatus(VoucherStatus.PENDING, pageable);
    System.out.println(merchantVoucherSet.getContent().size());
    System.out.println(merchantVoucherSet.getContent());

    pageable.next();

    Page<MerchantVoucher> merchantVoucherSet1 =
        merchantVoucherRepository.findByVoucherStatus(VoucherStatus.PENDING, pageable);
    System.out.println(merchantVoucherSet1.getContent().size());
    System.out.println(merchantVoucherSet1.getContent());
  }

  @Override
  public MerchantVoucher saveOneVoucher() {
    MerchantVoucher merchantVoucher = new MerchantVoucher();
    merchantVoucher.setVoucherType(VoucherType.SPECIAL);
    merchantVoucher.setStoreName("Voucher store5: Voucher");
    merchantVoucher.setVoucherStatus(VoucherStatus.PENDING);
    merchantVoucher.setVoucherCode("MV-1000015");
    merchantVoucher.setMerchantCode("VOC-101015");
    merchantVoucher.setVoucherName("My special merchant's voucher 5");

     return merchantVoucherRepository.save(merchantVoucher);
  }

  @Override
  public void getInclude() {
   List<MerchantVoucher> merchantVouchers = merchantVoucherRepository.getIncludeTest();
    System.out.println(merchantVouchers.size());
    System.out.println(merchantVouchers.get(0));
  }

}
