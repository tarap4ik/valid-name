package com.org.test.validatenametest.dto.person;

import java.io.Serializable;

public record VerifiedPersonDTO(String firstName, String surname, String otherName) implements Serializable {
}
