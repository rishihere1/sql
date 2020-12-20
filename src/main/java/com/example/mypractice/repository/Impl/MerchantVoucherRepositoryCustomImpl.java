package com.example.mypractice.repository.Impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
//import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.example.mypractice.document.MerchantVoucher;
import com.example.mypractice.dto.MerchantVoucherDTO;
import com.example.mypractice.repository.MerchantVoucherRepositoryCustom;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.Block;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.Arrays;
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
  private AggregationResults<MerchantVoucher> output;

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
    update.set("voucherStatus", null);
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

//  @Override
//  public void getItemsAsggregation() {
//    MatchOperation matchStage = Aggregation.match(new Criteria("foo").is("bar"));
//    ProjectionOperation projectStage = Aggregation.project("foo", "bar.baz").andExpression();
//    Aggregation aggregation
//        = Aggregation.newAggregation(matchStage, projectStage);
//    AggregationResults<MerchantVoucher> output =
//        mongoTemplate.aggregate(aggregation, "collection_name", MerchantVoucher.class);
//
//  }

  @Override
  public void getItemsAggregation() {

    Bson project = Aggregates.project(Projections.fields(Projections.excludeId(), Projections.include("voucherCode"),
        Projections.computed("items", Projections.computed("$slice", Arrays.asList("$productRule.items", 2)))));
    AggregateIterable<Document> iterable = mongoTemplate.getCollection("dummy_data")
        .aggregate(Arrays.asList(Aggregates.match(Filters.eq("voucherCode", "MV-00005")), project));
    iterable.forEach((Block<? super Document>) System.out::println);
  }

  @Override
  public List<MerchantVoucher> getByVoucherCode() {
    Query query = new Query(where("voucherCode").is("MV-73648"));
    return mongoTemplate.find(query, MerchantVoucher.class);

  }


  /*
  AggregationResults<Document> result = mongoTemplate.aggregate(agg, Document.class);
  iterable.forEach((Block<? super Document>) System.out::println);


    Bson project = Aggregates.project(Projections.fields(Projections.excludeId(), Projections.include("voucherCode"),
        Projections.computed("xyz", Projections.computed("$slice", Arrays.asList("$productRule.items", 2)))));
    AggregateIterable<Document> iterable = mongoTemplate.getCollection("dummy_data")
        .aggregate(Arrays.asList(Aggregates.match(Filters.eq("voucherCode", "MV-00005")), project));
   */
}
