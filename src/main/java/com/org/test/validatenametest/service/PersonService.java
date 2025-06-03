package com.org.test.validatenametest.service;

import com.org.test.validatenametest.dto.person.PersonRequestDTO;
import com.org.test.validatenametest.mapper.PersonMapper;
import com.org.test.validatenametest.mapper.VerifiedPersonMapper;
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
                    var person = PersonMapper.INSTANCE.toPersonDTO(requestEntity.getPerson());
                    var verifyPerson = VerifiedPersonMapper.INSTANCE.toVerifyPersonDTO(requestEntity.getCreditBureau().getVerifiedPerson());
                    return new PersonRequestDTO(requestEntity.getRequestId(), person, verifyPerson);
                }
        ).toList();
    }
}
