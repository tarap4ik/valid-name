package com.org.test.validatenametest.dto.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class PersonRequestDTO implements Serializable {

    UUID personId;

    PersonDTO person;

    VerifiedPersonDTO verifyPerson;

}
