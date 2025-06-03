package com.org.test.validatenametest;

import com.org.test.validatenametest.utils.ExcelParser;
import com.org.test.validatenametest.utils.LevenshteinDistanceCounter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

class LevenshteinDistanceTest {

    private final ExcelParser excelParser = mock(ExcelParser.class);

    private final LevenshteinDistanceCounter levenshteinDistanceCounter = new LevenshteinDistanceCounter(excelParser);

    private static List<String> array1,array2;

    @BeforeAll
    static void setUp() {
        array1 = List.of("abc", "def", "ghi");
        array2 = List.of("acb", "def", "ihg");
    }

    @Test
    void testLevenshteinDistance() {
        var result = levenshteinDistanceCounter.levenshteinDistanceCount(array1, array2);
        assertEquals(1.0, result);
    }

    @Test
    void testLevenshteinDistance_WithOneEmptyList() {
        var result = levenshteinDistanceCounter.levenshteinDistanceCount(array1, List.of());
        assertEquals(0.0, result);
    }

    @Test
    void testLevenshteinDistance_WithAnotherList() {
        var result = levenshteinDistanceCounter.levenshteinDistanceCount(array1, List.of("acb", "fed", "ihg"));
        assertNotEquals(0.0, result);
    }

    @Test
    void testLevenshteinDistance_WithDifferentList() {
        var result = levenshteinDistanceCounter.levenshteinDistanceCount(array1, List.of("zxc", "vbn", "rty"));
        assertNotEquals(0.0, result);
    }

}
