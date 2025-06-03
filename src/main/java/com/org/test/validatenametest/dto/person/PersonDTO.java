package com.org.test.validatenametest.dto.person;

import java.io.Serializable;

public record PersonDTO(String firstName, String lastName, String middleName) implements Serializable {
}
