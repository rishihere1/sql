//package com.example.mypractice.util;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.example.mypractice.document.Product;
//import com.example.mypractice.dto.MerchantInfo;
//
///**
// * @author rishi - created on 26/07/20
// **/
//public class ProjectUtil {
//
//  public static Product setProduct() {
//    Product product = Product.builder()
//        .productName("Iphone")
//        .brand("Apple")
//        .merchantInfoList(setMerchantInfo())
//        .description("Apple product")
//        .originalPrice(150000)
//        .build();
//    return product;
//  }
//
//  private static List<MerchantInfo> setMerchantInfo() {
//    List<MerchantInfo> merchantInfos = new ArrayList<>();
//    merchantInfos.add(MerchantInfo.builder()
//        .merchantPrice(100000)
//        .promotionId("promoted")
//        .build());
//
//    merchantInfos.add(MerchantInfo.builder()
//        .merchantPrice(2000000)
//        .promotionId("advanced promoted")
//        .build());
//    return merchantInfos;
//  }
//}
