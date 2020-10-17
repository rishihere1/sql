package com.example.mypractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.mypractice.document.MerchantVoucher;
import com.example.mypractice.dto.MerchantVoucherDTO;
import com.example.mypractice.repository.MerchantVoucherRepositoryCustom;
import com.example.mypractice.service.MerchantVoucherService;

/**
 * @author rishi - created on 14/06/20
 **/
@RestController
public class MerchantVoucherController {

  @Autowired
  MerchantVoucherService merchantVoucherService;

  @RequestMapping(method = RequestMethod.POST, value = "/createMerchantVoucher", produces = "application/json", consumes =
      "application/json")
  public MerchantVoucher addMerchantVoucher(@RequestBody MerchantVoucherDTO merchantVoucherDTO) {
    return merchantVoucherService.addMerchantVoucher(merchantVoucherDTO);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/getMerchantVoucher", produces = "application/json", consumes =
      "application/json")
  public void getMerchantVoucher() {
    merchantVoucherService.getMerchantVoucher();
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/updateMerchantVoucher", produces = "application/json", consumes =
      "application/json")
  public void updateMerchantVoucher () {
    merchantVoucherService.updateMerchantVoucher();
  }

  @RequestMapping(method = RequestMethod.POST, value = "/query")
  public String query() {
    merchantVoucherService.query();
    return "Executed successfully";
  }

  @RequestMapping(method = RequestMethod.POST, value = "/query1")
  public String upsertCheck(MerchantVoucherDTO merchantVoucherDTO) {
    merchantVoucherService.upsertCheck(merchantVoucherDTO);
    return "Executed successfully";
  }

  @PostMapping("/enumPost")
  public String  enumPost() {
    merchantVoucherService.postEnum();

    return "DONE";
  }

  @GetMapping("/testInclude")
  public String t() {
    merchantVoucherService.getInclude();
    return "Done";
  }



}
