package com.org.test.validatenametest.repository.requests;

import com.org.test.validatenametest.entity.requests.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RequestRepository extends JpaRepository<RequestEntity, UUID> {
}
