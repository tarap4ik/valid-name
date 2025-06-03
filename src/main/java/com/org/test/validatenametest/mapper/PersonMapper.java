package com.org.test.validatenametest.mapper;

import com.org.test.validatenametest.dto.person.PersonDTO;
import com.org.test.validatenametest.entity.requests.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonDTO toPersonDTO(PersonEntity personEntity);

}
