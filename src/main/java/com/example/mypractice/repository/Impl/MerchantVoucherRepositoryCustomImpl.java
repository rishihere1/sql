package com.example.mypractice.repository.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.example.mypractice.document.MerchantVoucher;
import com.example.mypractice.dto.MerchantVoucherDTO;
import com.example.mypractice.repository.MerchantVoucherRepositoryCustom;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;

/**
 * @author rishi - created on 14/06/20
 **/
public class MerchantVoucherRepositoryCustomImpl implements MerchantVoucherRepositoryCustom {

  @Autowired
  MongoTemplate mongoTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private MongoOperations mongoOperations;







  @Override
  public List<MerchantVoucher> getSelectedFields(String voucherStatus, String voucherCode) {
    Query query = new Query(where("voucherStatus").is(voucherStatus).and("voucherCode").in(voucherCode));
    query.fields().include("voucherName");
    query.fields().include("voucherCode");
    query.fields().include("rewardType");
    query.fields().include("timeRangeRule");
    List<MerchantVoucher> merchantVoucher = mongoTemplate.find(query, MerchantVoucher.class);
    return merchantVoucher;
  }

  @Override
  public MerchantVoucher updateSelectedVouchers(String voucherCode) {
    Query query = new Query(where("voucherCode").is(voucherCode).and("markForStop").is(false));
    Update update = new Update();
    update.set("merchantName", "RRRRRR");
    FindAndModifyOptions findAndModifyOptions = new FindAndModifyOptions();
    findAndModifyOptions.returnNew(true);
    return mongoTemplate.findAndModify(query, update, findAndModifyOptions, MerchantVoucher.class);
  }

  @Override
  public void query() {



  }

  @Override
  public void upsertCheck(MerchantVoucherDTO merchantVoucherDTO) {
    Query query = new Query(Criteria.where("voucherStatus").is("ACTIVE"));
    Update update = new Update();
    update.set("allStore", false);
    update.set("itemSkuCount", 1800);
    update.set("merchantName", "Now  very very Special and very important merchant");
    mongoTemplate.findAndModify(query, update, MerchantVoucher.class);
  }

  @Override
  public List<MerchantVoucher> getIncludeTest() {
    Query query = new Query(where("voucherCode").is("MV-1000015"));
    query.fields().include("voucherName");
    return mongoTemplate.find(query, MerchantVoucher.class);
  }

  private MerchantVoucher makeMerchantVoucher() {
    return MerchantVoucher.builder()
        .merchantCode("hahaha here")
        .voucherCode("hahaha code here")
        .storeName("hahaha name here")
        .voucherName("hahaha name here")
        .displayOnProductDetail(false)
        .build();
  }

}
