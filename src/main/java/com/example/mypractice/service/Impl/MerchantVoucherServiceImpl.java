package com.example.mypractice.service.Impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.example.mypractice.document.MerchantVoucher;
import com.example.mypractice.document.SpecialOccasion;
import com.example.mypractice.dto.IncludeExcludeRule;
import com.example.mypractice.dto.MerchantVoucherDTO;
import com.example.mypractice.dto.RedemptionLimit;
import com.example.mypractice.dto.TimeRangeRule;
import com.example.mypractice.dto.VoucherStatus;
import com.example.mypractice.dto.VoucherType;
import com.example.mypractice.repository.MerchantVoucherRepository;
import com.example.mypractice.repository.SpecialRepo;
import com.example.mypractice.service.MerchantVoucherService;
import lombok.SneakyThrows;

/**
 * @author rishi - created on 14/06/20
 **/
@Service
public class MerchantVoucherServiceImpl implements MerchantVoucherService {

  @Autowired
  MerchantVoucherRepository merchantVoucherRepository;

  @Autowired
  SpecialRepo specialRepo;

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

  @Override
  public void getAggregate() {
    merchantVoucherRepository.getItemsAggregation();
  }

  @Override
  public void queryAnnotaion() {

    boolean b = merchantVoucherRepository.queryAnnotationTest(true, VoucherType.SPECIAL);
//    merchantVouchers.forEach(System.out::println);
    System.out.println(b);
  }

  @Override
  public void nullCheck() {
    merchantVoucherRepository.updateSelectedVouchers("MV-00005");
  }

  @Override
  public void dbRefCheck(MerchantVoucherDTO merchantVoucherDTO) {
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
    merchantVoucher.setSpecialOccasion(getSpecialOccasion());

    MerchantVoucher merchantVoucher1= merchantVoucherRepository.save(merchantVoucher);
  }

  @Override
  public void saveMerchant() {
    specialRepo.save(getSpecialOccasion());
  }

  @Override
  @SneakyThrows
  public List<MerchantVoucher> getVoucher() {
   List<MerchantVoucher> merchantVouchers = merchantVoucherRepository.getByVoucherCode();
//   Thread.sleep(20000);
//    System.out.println(merchantVouchers);
    return merchantVouchers;
  }

  private SpecialOccasion getSpecialOccasion() {
    TimeRangeRule timeRangeRule = new TimeRangeRule();
    timeRangeRule.setStartDate(new Date());
    timeRangeRule.setEndDate(new Date());
    SpecialOccasion specialOccasion = SpecialOccasion.builder()
        .id("new id")
        .specialOccasionName("special occasion name")
        .claimPeriod(timeRangeRule)
        .executeStartDate(new Date())
        .registrationPeriod(timeRangeRule)
        .build();
    return specialOccasion;
  }


  /**
   *
   * Populate database in bulk
   *
  @Override
  public void populateDataAutomatically() {
    for (int i=1;i<=50;i++) {
      MerchantVoucher merchantVoucher = MerchantVoucher.builder()
          .voucherCode("MV-0000" + i)
          .merchantCode("TOQ-161" + i)
          .merchantName("Chacha ka store" + i)
          .voucherName("Seller voucher" + i)
          .voucherStatus(i%2 == 0 ? VoucherStatus.PENDING : VoucherStatus.ACTIVE)
          .voucherType(i%3 == 0 ? VoucherType.SPECIAL : VoucherType.REGULAR)
          .changeLog("logChanged" + 1)
          .combinationType(String.valueOf(i * 100))
          .allStore(i%2 == 0 ? true : false)
          .displayOnProductDetail(i%2 == 0 ? true : false)
          .timeRangeRule(getTimeRangeRule())
          .doubleTest((double)(i* ((i*3) +i)))
          .redemptionLimit(getRedumption(i))
          .itemSkuCount(i)
          .rewardType(i%2 == 0 ? "COUPON" : "AMOUNT_OFF")
          .productRule(getProductRele())
          .build();

      merchantVoucherRepository.save(merchantVoucher);
    }
  }

  private IncludeExcludeRule getProductRele() {
    IncludeExcludeRule includeExcludeRule = new IncludeExcludeRule();
    includeExcludeRule.setRuleType("INCLUDE");
    includeExcludeRule.setItems(getItemSkus());
    return includeExcludeRule;
  }

  private List<String> getItemSkus() {
    List<String> items = new ArrayList<>();
    for (int i=1;i<= 10; i++) {
      if (i/10 ==0) {
        items.add("TOQ-16110-00001-0000" + i);
      } else {
        items.add("TOQ-16110-00001-000" + i);
      }
    }
    return items;
  }

  private static Date getDateForAfter10Days() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    calendar.add(Calendar.DAY_OF_MONTH, 10);
    return calendar.getTime();
  }

  private static TimeRangeRule getTimeRangeRule() {
    TimeRangeRule timeRangeRule = new TimeRangeRule();
    timeRangeRule.setStartDate(new Date());
    timeRangeRule.setEndDate(getDateForAfter10Days());
    return timeRangeRule;
  }


  private static RedemptionLimit getRedumption(int i) {
    RedemptionLimit redemptionLimit = new RedemptionLimit();
    redemptionLimit.setRedemptionLimitPerOrder(i*4);
    redemptionLimit.setRedemptionLimitPerDevice(i*8);
    redemptionLimit.setRedemptionLimitPerCustomer(i*16);
    return redemptionLimit;
  }
*/
}
