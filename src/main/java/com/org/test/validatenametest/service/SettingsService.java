package com.org.test.validatenametest.service;

import com.org.test.validatenametest.dto.SettingsDTO;
import com.org.test.validatenametest.exception.NotFoundSettingException;
import com.org.test.validatenametest.mapper.SettingsMapper;
import com.org.test.validatenametest.repository.SettingsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SettingsService {

    private final SettingsRepository settingsRepository;

    public SettingsService(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public List<SettingsDTO> getAllSettings() {
        var settings = settingsRepository.findAll();
        if (settings.isEmpty()) {
            throw new NotFoundSettingException();
        }
        return SettingsMapper.INSTANCE.toSettingsDTOList(settings);
    }

    public SettingsDTO getSettingsById(String name) {
        var settings = settingsRepository.findByName(name).orElseThrow(NotFoundSettingException::new);
        return SettingsMapper.INSTANCE.toSettingsDTO(settings);
    }

    public long createSettings(SettingsDTO settingsDTO) {
        var settings = settingsRepository.findByName(settingsDTO.name());
        if (settings.isPresent()) {
            log.info("Settings already exists");
        }
        var result = settingsRepository.save(SettingsMapper.INSTANCE.toSettingsEntity(settingsDTO));
        return result.getSettingId();
    }

    public void updateSettings(String name, SettingsDTO settingsDTO) {
        var settingsEntity = settingsRepository.findByName(name).orElseThrow(NotFoundSettingException::new);
        settingsEntity.setName(settingsDTO.name());
        settingsEntity.setValue(settingsDTO.value());
        settingsRepository.save(settingsEntity);
    }

    public void deleteSettings(String name) {
        var settingsEntity = settingsRepository.findByName(name).orElseThrow(NotFoundSettingException::new);
        settingsRepository.deleteById(settingsEntity.getSettingId());
    }
}
