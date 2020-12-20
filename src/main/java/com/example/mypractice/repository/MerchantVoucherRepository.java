package com.example.mypractice.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mypractice.document.MerchantVoucher;
import com.example.mypractice.dto.VoucherStatus;
import com.example.mypractice.dto.VoucherType;

/**
 * @author rishi - created on 14/06/20
 **/
@Repository
public interface MerchantVoucherRepository extends MongoRepository<MerchantVoucher, String>, MerchantVoucherRepositoryCustom {
  Set<MerchantVoucher> findByVoucherStatus(VoucherStatus pending);

  Page<MerchantVoucher> findByVoucherStatus(VoucherStatus active, Pageable pageable);

  @Query(value = "{allStore : ?0, voucherType : ?1}", exists = true)
  boolean queryAnnotationTest(Boolean value, VoucherType type);

  List<MerchantVoucher> findByVoucherCode(String voucherCode);
}
