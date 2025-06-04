package com.org.test.validatenametest.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Component
public class Combined {

    public String[] setPairs(String first, String second, String third) {
        return Stream.of(first, second, third)
                .filter(Objects::nonNull)
                .flatMap(s -> Arrays.stream(s.trim().toLowerCase().split(" ")))
                .toArray(String[]::new);
    }


    public List<String> combinedArray(String[] array) {
        List<String> pairs = new ArrayList<>();
        int i = 0;
        while (i < array.length) {
            var j = i + 1;
            while (j < array.length) {
                pairs.add(array[i] + array[j]);
                j++;
            }
            i++;
        }
        return pairs;
    }

}
