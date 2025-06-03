package com.org.test.validatenametest.mapper;

import com.org.test.validatenametest.dto.person.VerifiedPersonDTO;
import com.org.test.validatenametest.entity.requests.VerifiedPersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VerifiedPersonMapper {

    VerifiedPersonMapper INSTANCE = Mappers.getMapper(VerifiedPersonMapper.class);

    VerifiedPersonDTO toVerifyPersonDTO(VerifiedPersonEntity verifiedPersonEntity);

}
