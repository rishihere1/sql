package com.example.mypractice.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;

import com.example.mypractice.document.MerchantVoucher;
import com.example.mypractice.dto.BulkTemplateFileContentResponse;
import com.example.mypractice.dto.MerchantVoucherDTO;
import com.example.mypractice.service.MerchantVoucherService;
import com.mongodb.ErrorCategory;

/**
 * @author rishi - created on 14/06/20
 **/
@RestController
public class MerchantVoucherController {

  private static String baseDir = "/Users/rishiraj/Desktop/test/";

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

  @GetMapping("/aggregate")
  public String aggregate() {
    merchantVoucherService.getAggregate();
    return "Done";
  }

  @GetMapping("/queryAnnotaion")
  public String queryAnnotaion() {
    merchantVoucherService.queryAnnotaion();
    return "Done";
  }

  @PostMapping("/nullCheck")
  public String nullCheck() {
    merchantVoucherService.nullCheck();
    return "Done";
  }

  @PostMapping("/dbref")
  public String dbRefCheck(@RequestBody MerchantVoucherDTO merchantVoucherDTO) {
    merchantVoucherService.dbRefCheck(merchantVoucherDTO);
    return "Done";
  }

  @PostMapping("/assSpecial")
  public String addSpecial() {
    merchantVoucherService.saveMerchant();
    return "Done";
  }

  @GetMapping("/getMapping")
  public void getVoucher() {
   List<MerchantVoucher> merchantVouchers =  merchantVoucherService.getVoucher();
    System.out.println(merchantVouchers.get(0).getVoucherCode());
  }

  @GetMapping("/getMappinga")
  public List<MerchantVoucher> getdVoucher() {
    List<MerchantVoucher> m = merchantVoucherService.getVoucher();
    System.out.println(m);
    return m;
  }

  @GetMapping("/testapi")
  public String download(HttpServletResponse httpServletResponse) throws IOException {
//    BulkTemplateFileContentResponse response = getMerchantVoucherBulkTemplateContent("/Users/rishiraj/Desktop/", "ExcelSheet.xlsx");
    httpServletResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
    httpServletResponse
        .setHeader("Content-Disposition", "attachment; filename=" + "excel.xlsx");
//    httpServletResponse.getOutputStream().write(response.getFileContent());
    httpServletResponse.getOutputStream().close();
    System.out.println("File downloaded successfully");
    return "File downloaded successfully";
  }

  public BulkTemplateFileContentResponse getMerchantVoucherBulkTemplateContent(String pathname, String filename)
      throws IOException {
    BulkTemplateFileContentResponse response = BulkTemplateFileContentResponse.builder().requestId(pathname).build();
    File file = new File(pathname + filename);
    try (FileInputStream fileInputStream = new FileInputStream(file)) {
      response.setFileContent(IOUtils.toByteArray(fileInputStream));
    } catch (IOException ex) {
      throw ex;
    }
    return response;
  }

  @PostMapping("/uploadexcel")
  public String upload(HttpServletRequest httpServletRequest,
      @RequestParam(name = "multipartFile") MultipartFile multipartFile) throws IOException, InvalidFormatException {
    String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
    ExcelUtils.createDirectories(baseDir);
    String fileName = "name." + extension;
    String finalPath = baseDir + fileName;
    System.out.println("File saved in " + finalPath);
    File file = new File(finalPath);
    multipartFile.transferTo(file);
    if (extension.equals("xlsx")) {
      ExcelUtils.readExcel(finalPath);
      ExcelUtils.alterExcel(finalPath);
    }
    return "File uploaded successfully";
  }

  @Bean(name = MultipartFilter.DEFAULT_MULTIPART_RESOLVER_BEAN_NAME)
  protected MultipartResolver getMultipartResolver() {
    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    multipartResolver.setMaxUploadSize(9999999999999999L);
    multipartResolver.setMaxInMemorySize(999999999);
    return multipartResolver;
  }
}
