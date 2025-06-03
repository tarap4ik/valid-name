package com.org.test.validatenametest.service;

import com.org.test.validatenametest.entity.requests.RequestEntity;
import com.org.test.validatenametest.exception.NotFoundSettingException;
import com.org.test.validatenametest.repository.SettingsRepository;
import com.org.test.validatenametest.repository.requests.RequestRepository;
import com.org.test.validatenametest.utils.Combined;
import com.org.test.validatenametest.utils.LevenshteinDistanceCounter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class FactorService {

    private final LevenshteinDistanceCounter levenshteinDistanceCounter;
    private final Combined combined;
    private final RequestRepository requestRepository;
    private final SettingsRepository settingsRepository;

    public FactorService(RequestRepository requestRepository, SettingsRepository settingsRepository, LevenshteinDistanceCounter levenshteinDistanceCounter, Combined combined) {
        this.requestRepository = requestRepository;
        this.settingsRepository = settingsRepository;
        this.levenshteinDistanceCounter = levenshteinDistanceCounter;
        this.combined = combined;
    }

    public Map<UUID, Boolean> validateFactor() {
        HashMap<UUID, Boolean> map = new HashMap<>();
        double distanceRatioThreshold = getDistanceRatio();
        var requests = requestRepository.findAll();

        if (requests.isEmpty()) {
            log.error("Данные по реквестам отсутствуют");
            return map;
        }

        requests.forEach(req -> {
            var result = getStopFactorByRequestId(req, distanceRatioThreshold);
            log.info("Результат для request {} - {}", req.getRequestId(), result);
            map.put(req.getRequestId(), result);
        });
        return map;
    }

    public boolean validateFactor(UUID requestId) {
        double distanceRatioThreshold = getDistanceRatio();
        var requests = requestRepository.findById(requestId);

        if (requests.isEmpty()) {
            log.error("Данные по request {} отсутствуют", requestId);
            return false;
        }
        var result = getStopFactorByRequestId(requests.get(), distanceRatioThreshold);
        log.info("Результат для request {} - {}", requestId, result);
        return result;
    }

    private double getDistanceRatio() {
        var settings = settingsRepository.findByName("distance_ratio_threshold");
        if (settings.isEmpty()) {
            throw new NotFoundSettingException("distance_ratio_threshold");
        }
        return Double.parseDouble(settings.get().getValue());
    }

    private boolean getStopFactorByRequestId(RequestEntity requestEntity, double distanceRatioThreshold) {
        levenshteinDistanceCounter.setRequestId(requestEntity.getRequestId());
        var person = requestEntity.getPerson();
        var pairsPerson = combined.setPairs(person.getFirstName(), person.getMiddleName(), person.getLastName());
        var listCombinedPerson = combined.combinedArray(pairsPerson);

        var verifyPerson = requestEntity.getCreditBureau().getVerifiedPerson();
        var pairsVerifyPerson = combined.setPairs(verifyPerson.getFirstName(), verifyPerson.getOtherName(), verifyPerson.getSurname());
        var listCombinedVerifyPerson = combined.combinedArray(pairsVerifyPerson);

        return levenshteinDistanceCounter.levenshteinDistanceCount(listCombinedPerson, listCombinedVerifyPerson) < distanceRatioThreshold;

    }

}
