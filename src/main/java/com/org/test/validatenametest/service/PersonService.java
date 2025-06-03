package com.org.test.validatenametest.service;

import com.org.test.validatenametest.dto.person.PersonDTO;
import com.org.test.validatenametest.dto.person.PersonRequestDTO;
import com.org.test.validatenametest.dto.person.VerifyPersonDTO;
import com.org.test.validatenametest.repository.requests.RequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PersonService {

    private final RequestRepository requestRepository;

    public PersonService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public List<PersonRequestDTO> getAllPersonRequests() {
        var requests = requestRepository.findAll();
        return requests.stream().map(
                requestEntity -> {
                    var person = new PersonDTO(requestEntity.getPerson().getFirstName(),
                            requestEntity.getPerson().getLastName(),
                            requestEntity.getPerson().getMiddleName());
                    var verifyPerson = new VerifyPersonDTO(requestEntity.getCreditBureau().getVerifiedPerson().getFirstName(),
                            requestEntity.getCreditBureau().getVerifiedPerson().getSurname(),
                            requestEntity.getCreditBureau().getVerifiedPerson().getOtherName());
                    return new PersonRequestDTO(requestEntity.getRequestId(), person, verifyPerson);
                }
        ).toList();
    }
}
