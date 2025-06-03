package com.org.test.validatenametest.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@Component
public class ExcelParser {

    public Workbook createHeader() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Example");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Person");
        row.createCell(1).setCellValue("Verified Name");
        row.createCell(2).setCellValue("Levenshtein");
        row.createCell(3).setCellValue("Max Length");
        row.createCell(4).setCellValue("Ratio");
        return workbook;
    }

    public Workbook createData(Workbook workbook, String person, String verifiedName, Integer levenshtein, Integer maxLength, Double ratio) {
        Sheet sheet = workbook.getSheet("Example");
        var index = sheet.getLastRowNum();
        log.debug("Person {} | Verified Name {} | Levenshtein {} | Max Length {} | Ratio {} |", person, verifiedName, levenshtein, maxLength, ratio);
        Row row = sheet.createRow(index + 1);
        row.createCell(0).setCellValue(person);
        row.createCell(1).setCellValue(verifiedName);
        row.createCell(2).setCellValue(levenshtein);
        row.createCell(3).setCellValue(maxLength);
        row.createCell(4).setCellValue(ratio);
        return workbook;
    }

    public void createFile(Workbook workbook, String fileName) {
        try (workbook; FileOutputStream out = new FileOutputStream(fileName + ".xlsx")) {
            workbook.write(out);
        } catch (IOException e) {
            log.error("Не удалось создать файл для request {}", fileName);
        }
    }

}
