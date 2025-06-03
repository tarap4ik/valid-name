package com.org.test.validatenametest;

import com.org.test.validatenametest.entity.SettingsEntity;
import com.org.test.validatenametest.entity.requests.CreditBureauEntity;
import com.org.test.validatenametest.entity.requests.PersonEntity;
import com.org.test.validatenametest.entity.requests.RequestEntity;
import com.org.test.validatenametest.entity.requests.VerifiedPersonEntity;
import com.org.test.validatenametest.exception.NotFoundSettingException;
import com.org.test.validatenametest.repository.SettingsRepository;
import com.org.test.validatenametest.repository.requests.RequestRepository;
import com.org.test.validatenametest.service.FactorService;
import com.org.test.validatenametest.utils.Combined;
import com.org.test.validatenametest.utils.ExcelParser;
import com.org.test.validatenametest.utils.LevenshteinDistanceCounter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FactorServiceTest {

    private static SettingsEntity settingsEntity;
    private static RequestEntity requestEntity;

    private final Combined combined = new Combined();
    private final ExcelParser excelParser = mock(ExcelParser.class);

    private final LevenshteinDistanceCounter levenshteinDistanceCounter = new LevenshteinDistanceCounter(excelParser);
    private final RequestRepository requestRepository = mock(RequestRepository.class);
    private final SettingsRepository settingsRepository = mock(SettingsRepository.class);

    private final FactorService factorService = new FactorService(requestRepository, settingsRepository, levenshteinDistanceCounter, combined);

    @BeforeAll
    static void init(){
        settingsEntity = new SettingsEntity();
        settingsEntity.setName("distance_ratio_threshold");
        settingsEntity.setValue("0.9");

        requestEntity = new RequestEntity();
        requestEntity.setRequestId(UUID.randomUUID());
        PersonEntity personEntity = new PersonEntity();
        personEntity.setFirstName("John");
        personEntity.setLastName("Doe");
        personEntity.setMiddleName("Smith");
        requestEntity.setPerson(personEntity);

        CreditBureauEntity creditBureauEntity = new CreditBureauEntity();
        VerifiedPersonEntity verifiedPersonEntity = new VerifiedPersonEntity();
        verifiedPersonEntity.setFirstName("John");
        verifiedPersonEntity.setSurname("Doe");
        verifiedPersonEntity.setOtherName("Smith");

        creditBureauEntity.setVerifiedPerson(verifiedPersonEntity);
        requestEntity.setCreditBureau(creditBureauEntity);
    }

    @Test
    void testFactorService() {
        when(settingsRepository.findByName(anyString())).thenReturn(Optional.of(settingsEntity));
        when(requestRepository.findAll()).thenReturn(List.of(requestEntity));
        var result = factorService.validateFactor();
        assertFalse(result.isEmpty());
    }

    @Test
    void testFactorService_WithNoSettingsEntity() {
        when(settingsRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(requestRepository.findAll()).thenReturn(List.of(requestEntity));

        Exception exception = assertThrows(NotFoundSettingException.class, factorService::validateFactor);

        String expectedMessage = "Настройка distance_ratio_threshold не была найдена";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testFactorService_WithNoRequestEntity() {
        when(settingsRepository.findByName(anyString())).thenReturn(Optional.of(settingsEntity));
        when(requestRepository.findAll()).thenReturn(List.of());

        var result = factorService.validateFactor();
        assertTrue(result.isEmpty());
    }

    @Test
    void testFactorServiceByRequestId() {
        when(settingsRepository.findByName(anyString())).thenReturn(Optional.of(settingsEntity));
        when(requestRepository.findAll()).thenReturn(List.of());

        var result = factorService.validateFactor(requestEntity.getRequestId());
        assertFalse(result);
    }

    @Test
    void testFactorServiceByRequestId_WithAnotherVerify() {
        initAnotherVerify();
        when(settingsRepository.findByName(anyString())).thenReturn(Optional.of(settingsEntity));
        when(requestRepository.findById(any())).thenReturn(Optional.of(requestEntity));

        var result = factorService.validateFactor(requestEntity.getRequestId());
        assertTrue(result);
    }

    private void initAnotherVerify(){
        var verify = requestEntity.getCreditBureau().getVerifiedPerson();
        verify.setFirstName("Mike");
        verify.setSurname("Jake");
        verify.setOtherName("Bill Gard");
        requestEntity.getCreditBureau().setVerifiedPerson(verify);
    }

}
