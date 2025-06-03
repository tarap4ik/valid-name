package com.org.test.validatenametest.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record SettingsDTO(@NotBlank String name, @NotBlank String value) implements Serializable {
}
