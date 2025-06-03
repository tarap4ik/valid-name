package com.org.test.validatenametest.service;

import com.org.test.validatenametest.dto.RequestContentDTO;
import com.org.test.validatenametest.entity.RequestContentEntity;
import com.org.test.validatenametest.exception.NotFoundRequestContentException;
import com.org.test.validatenametest.exception.NotFoundSettingException;
import com.org.test.validatenametest.repository.RequestContentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RequestContentService {

    private final RequestContentRepository requestContentRepository;

    public RequestContentService(RequestContentRepository requestContentRepository) {
        this.requestContentRepository = requestContentRepository;
    }

    public List<RequestContentDTO> getAllContent() {
        var content = requestContentRepository.findAll();
        if (content.isEmpty()) {
            throw new NotFoundRequestContentException();
        }
        return content.stream().map(
                c -> new RequestContentDTO(c.getRequestId(), c.getContent())
        ).toList();
    }

    public String getContentById(Long id) {
        var content = requestContentRepository.findById(id).orElseThrow(NotFoundSettingException::new);
        return content.getContent();
    }

    public long createContent(String content) {
        RequestContentEntity requestContentEntity = new RequestContentEntity();
        requestContentEntity.setContent(content);
        var result = requestContentRepository.save(requestContentEntity);
        return result.getRequestId();
    }

    public void updateContentById(long id, String content) {
        var result = requestContentRepository.findById(id).orElseThrow(NotFoundSettingException::new);
        result.setContent(content);
    }

    public void deleteContentById(Long id) {
        requestContentRepository.deleteById(id);
    }

}
