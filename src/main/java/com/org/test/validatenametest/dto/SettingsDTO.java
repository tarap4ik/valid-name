package com.org.test.validatenametest.dto;

import java.io.Serializable;

public record SettingsDTO(String name, String value) implements Serializable {
}
