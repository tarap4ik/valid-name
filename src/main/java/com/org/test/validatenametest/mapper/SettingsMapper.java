package com.org.test.validatenametest.mapper;

import com.org.test.validatenametest.dto.SettingsDTO;
import com.org.test.validatenametest.entity.SettingsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SettingsMapper {

    SettingsMapper INSTANCE = Mappers.getMapper(SettingsMapper.class);

    SettingsDTO toSettingsDTO(SettingsEntity settings);

    SettingsEntity toSettingsEntity (SettingsDTO settingsDTO);

    List<SettingsDTO> toSettingsDTOList(List<SettingsEntity> settings);

}
