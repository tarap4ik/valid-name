package com.org.test.validatenametest.utils;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class LevenshteinDistanceCounter {

    @Setter
    private UUID requestId;

    private final ExcelParser excelParser;

    public LevenshteinDistanceCounter(ExcelParser excelParser) {
        this.excelParser = excelParser;
    }

    public double levenshteinDistanceCount(List<String> array1, List<String> array2){
        LevenshteinDistance distance = new LevenshteinDistance();
        double max = 0.0;
        var workbook = excelParser.createHeader();

        for (String a : array1) {
            for (String b : array2) {
                int dist = distance.apply(a, b);
                int maxLength = Math.max(a.length(), b.length());
                double ratio = 1.0 - ((double) dist / maxLength);
                workbook = excelParser.createData(workbook, a,b, dist, maxLength, ratio);
                max = Math.max(max, ratio);
            }
        }
        excelParser.createFile(workbook, String.valueOf(requestId));
        return max;
    }

}
