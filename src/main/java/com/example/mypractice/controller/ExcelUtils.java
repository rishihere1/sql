package com.example.mypractice.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lombok.SneakyThrows;

/**
 * @author rishi - created on 17/12/20
 **/
public class ExcelUtils {

  public static void createDirectories(String path) {
    File directories = new File(path);
    if (!directories.exists()) {
      directories.mkdirs();
    }
  }

  public static void readExcel(String finalPath) throws IOException, InvalidFormatException {
    System.out.println("Reading excel");
    List<Map<String, String>> itemDataFromExcel = processExcel(finalPath);
    for (Map<String, String> stringStringMap : itemDataFromExcel) {
      System.out.println(stringStringMap);
    }
  }

  @SneakyThrows
  private static List<Map<String, String>> processExcel(String finalPath) {
    File file = new File(finalPath);
    try (Workbook workBook = new XSSFWorkbook(file)) {
      return reading(workBook.getSheetAt(0));
    }
  }

  private static List<Map<String, String>> reading(Sheet sheet) {
    Map<Integer, String> headers = new TreeMap<>();
    List<Map<String, String>> datas = new ArrayList<>();
    Row headerRow = sheet.getRow(0);
    setExcelHeader(headerRow, headers);
    Iterator<Row> rows = sheet.rowIterator();
    int numOfCell = headerRow.getPhysicalNumberOfCells(); // this is number of columns
    int counter = 0;
    while (rows.hasNext()) {
      Row row = rows.next();
      if (counter >= 1) {
        Map<String, String> data = new LinkedHashMap<>();
        extractRowAndUpdateData(numOfCell, row, data, headers);
        if (!data.isEmpty()) {
          datas.add(data);
        }
      }
      counter++;
    }
    return datas;
  }

  private static void extractRowAndUpdateData(int numOfCell, Row row, Map<String, String> data,
      Map<Integer, String> headers) {
    for (int i = 0; i < numOfCell; i++) {
      Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
      data.put(headers.get(i), cell.getRichStringCellValue().getString());
    }
  }

  private static void setExcelHeader(Row row, Map<Integer, String> headers) {
    Iterator<Cell> cells = row.cellIterator();
    int count = 0;
    while (cells.hasNext()) {
      Cell cell = cells.next();
      headers.put(count++, cell.getStringCellValue());
    }
  }

  public static void alterExcel(String finalPath) throws IOException, InvalidFormatException {
    System.out.println("Writing on excel");
    File uploadedFile = new File(finalPath);
    try (Workbook workbook = new XSSFWorkbook(uploadedFile)) {
      Sheet excelSheetData = workbook.getSheetAt(0);
      Iterator<Row> rows = excelSheetData.rowIterator();
      int counter = 0;
      while (rows.hasNext()) {
        Row row = rows.next();
        if (counter >= 1) {
          for (int i = 0; i < 15; i++) {
            String rowValue = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
            if (StringUtils.isNotBlank(rowValue)) {
              if (i == 5) {
                row.createCell(i).setCellValue("");
              } else {
                row.createCell(i).setCellValue("Changed value");
              }
            } else {
              if (i != 12)
                row.createCell(i).setCellValue("New value");
            }
          }
        }
        counter++;
      }
      excelSheetData.autoSizeColumn(4);
      File newFile = new File("/Users/rishiraj/Desktop/test/names.xlsx");
      try (FileOutputStream fileOutputStream = new FileOutputStream(newFile)) {
        workbook.write(fileOutputStream);
      }
    }
    File f1 = new File(finalPath);
    if (f1.delete()) {
      System.out.println("File " + f1.getName() + " is deleted.");
    } else {
      System.out.println("File " + f1.getName() + " not found.");
    }
  }

  private static void writing(Sheet sheet) {
    Iterator<Row> rows = sheet.rowIterator();
    int counter = 0;
    while (rows.hasNext()) {
      Row row = rows.next();
      if (counter == 4) {
        Cell cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Changed value");
        cell.setCellStyle(row.getCell(0).getCellStyle());
        break;
      }
      counter++;
    }
  }
}
