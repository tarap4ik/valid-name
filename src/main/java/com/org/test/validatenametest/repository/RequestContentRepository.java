package com.org.test.validatenametest.repository;

import com.org.test.validatenametest.entity.RequestContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestContentRepository extends JpaRepository<RequestContentEntity, Long> {

}
