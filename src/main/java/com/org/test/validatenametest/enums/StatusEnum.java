package com.org.test.validatenametest.enums;

import java.util.Objects;

public enum StatusEnum {
    OPEN("Open"),
    CLOSED("Closed"),
    UNDEFINED_STATUS("Undefined Status");

    private final String statusLevel;

    StatusEnum(String statusLevel) {
        this.statusLevel = statusLevel;
    }

    public static StatusEnum getStatusEnumByValue(final String StatusLevel) {
        for (final var level : StatusEnum.values()) {
            if (Objects.equals(StatusLevel, level.statusLevel))
                return level;
        }
        return UNDEFINED_STATUS;
    }

}
