package com.example.mypractice.specialoccasionrequest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;

/**
 * @author rishi - created on 17/12/20
 **/
public class VoucherSessionMappingExcelParser {


  static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
  static SimpleDateFormat simpleDateTime = new SimpleDateFormat("HH:mm");
  static SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");

  public static void main(String[] args) throws Exception {
    simpleDateTimeFormat.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the file path...");
    String filePath = scanner.next();
    String specialOccasionId = scanner.next();
    FileReader fileReader = new FileReader(filePath);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    bufferedReader.readLine();
    String line;
    Map<String, VoucherSessionMappingRequest> map = new HashMap<>();
    SpecialOccasionVoucherSessionsSaveRequest specialOccasionVoucherSessionsSaveRequest =
        createSOVSSR(specialOccasionId);
    while ((line = bufferedReader.readLine()) != null) {
      String chunks[] = line.split(",");
      String sessionName = chunks[4];
      if (map.containsKey(sessionName)) {
        addVIR(map, sessionName, chunks);
      } else {
        addVSMR(specialOccasionVoucherSessionsSaveRequest, chunks, map);
        addVIR(map, sessionName, chunks);
      }
    }

    System.out.println(new ObjectMapper().writeValueAsString(specialOccasionVoucherSessionsSaveRequest));
    fileReader.close();
    bufferedReader.close();
  }

  private static void addVIR(Map<String, VoucherSessionMappingRequest> map, String sessionName, String[] chunks)
      throws Exception {
    String voucherCode = chunks[0];
    int priority = Integer.parseInt(chunks[1]);
    Set<String> urls = Collections.emptySet();
    if(!chunks[5].isEmpty()) {
      String[] urlArr = chunks[5].split(";;;;");
      urls = getUrlSet(urlArr);
    }
    String group = chunks[6];
    VoucherInfoRequest voucherInfoRequest = VoucherInfoRequest.builder()
        .voucherCode(voucherCode)
        .group(group)
        .priority(priority)
        .productImageUrls(urls)
        .build();
    map.get(sessionName).getVoucherInfoList().add(voucherInfoRequest);
  }

  private static Set<String> getUrlSet(String[] urlArr) {
    return Arrays.stream(urlArr).collect(Collectors.toSet());
  }

  private static void addVSMR(SpecialOccasionVoucherSessionsSaveRequest specialOccasionVoucherSessionsSaveRequest,
      String[] chunks, Map<String, VoucherSessionMappingRequest> map) throws ParseException {
    Pair<Date, Date> sessionPeriod = getSessionPeriod(chunks);
    Date sessionStartDate = sessionPeriod.getKey();
    Date sessionEndDate = sessionPeriod.getValue();
    String sessionName = chunks[4];
    VoucherSessionMappingRequest voucherSessionMappingRequest = VoucherSessionMappingRequest.builder()
        .sessionName(sessionName)
        .sessionStartDate(sessionStartDate)
        .sessionEndDate(sessionEndDate)
        .voucherInfoList(new ArrayList<>())
        .build();
    specialOccasionVoucherSessionsSaveRequest.getVoucherSessionMappingList().add(voucherSessionMappingRequest);
    map.put(sessionName, voucherSessionMappingRequest);
  }

  private static Pair<Date, Date> getSessionPeriod(String[] chunks) throws ParseException {
    Date sessionStartDate = getSessionDate(chunks, 2);
    Date sessionEndDate = getSessionDate(chunks, 3);
    return new Pair<>(sessionStartDate,sessionEndDate);
  }

  private static Date getSessionDate(String[] chunks, int index) throws ParseException {
    int tStart = chunks[index].indexOf("(");
    int tEnd = chunks[index].indexOf(")");
    String date = chunks[index].substring(0, tStart - 1);
    String time = chunks[index].substring(tStart+1, tEnd);
    String dateTime = date + " " + time;
    Date returnDate = simpleDateTimeFormat.parse(dateTime);
    if (index == 3){
      return new Date(returnDate.getTime()-1000);
    }
    return returnDate;
  }

  private static SpecialOccasionVoucherSessionsSaveRequest createSOVSSR(String specialOccasionId) {
    return SpecialOccasionVoucherSessionsSaveRequest.builder()
        .specialOccasionId(specialOccasionId)
        .voucherSessionMappingList(new ArrayList<>())
        .build();
  }
}
