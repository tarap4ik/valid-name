package com.org.test.validatenametest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.test.validatenametest.repository.RequestContentRepository;
import com.org.test.validatenametest.repository.requests.CreditBureauRepository;
import com.org.test.validatenametest.repository.requests.RequestRepository;
import com.org.test.validatenametest.utils.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class DataService {

    private final RequestContentRepository requestContentRepository;
    private final RequestRepository requestRepository;
    private final CreditBureauRepository creditBureauRepository;
    private final Converter converter;


    public DataService(RequestContentRepository requestContentRepository, RequestRepository requestRepository, CreditBureauRepository creditBureauRepository, Converter converter) {
        this.requestContentRepository = requestContentRepository;
        this.requestRepository = requestRepository;
        this.creditBureauRepository = creditBureauRepository;
        this.converter = converter;
    }

    public void setDataFromJSONContent() {
        var listContent = requestContentRepository.findAll();
        if (listContent.isEmpty()) {
            log.info("Отсутствуют json");
            return;
        }
        listContent.forEach(content -> {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(content.getContent());
                var request = requestRepository.findById(UUID.fromString(jsonNode.get("loanRequestID").asText()));
                if (request.isPresent()) {
                    log.info("Данный request {} уже есть в БД", request.get().getRequestId());
                } else {
                    var credit = converter.convert(jsonNode);
                    creditBureauRepository.save(credit);
                }
            } catch (JsonProcessingException e) {
                log.error("Ошибка при переносе JSON контента в БД");
            }
        });
    }

}
