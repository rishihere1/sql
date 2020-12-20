package com.example.mypractice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mypractice.document.MerchantVoucher;
import com.example.mypractice.document.SpecialOccasion;

/**
 * @author rishi - created on 30/11/20
 **/
public interface SpecialRepo extends MongoRepository<SpecialOccasion, String>{

}
