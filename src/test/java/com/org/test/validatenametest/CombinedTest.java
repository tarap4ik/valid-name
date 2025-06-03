package com.org.test.validatenametest;

import com.org.test.validatenametest.utils.Combined;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CombinedTest {

    private final Combined combined = new Combined();

    @Test
    void testCombinedArray() {
        String[] input = {"a", "b", "c"};
        var result = combined.combinedArray(input);
        assertEquals(List.of("ab", "ac", "bc"), result);
    }

    @Test
    void testCombinedArray_withTwoElements() {
        String[] input = {"a", "b"};
        var result = combined.combinedArray(input);
        assertEquals(List.of("ab"), result);
    }

    @Test
    void testCombinedArray_withOneElement() {
        String[] input = {"a"};
        var result = combined.combinedArray(input);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCombinedArray_withEmptyArray() {
        String[] input = {};
        var result = combined.combinedArray(input);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCombinedArray_withDuplicateElements() {
        String[] input = {"a", "a", "b"};
        var result = combined.combinedArray(input);
        assertTrue(result.contains("aa"));
    }

}
