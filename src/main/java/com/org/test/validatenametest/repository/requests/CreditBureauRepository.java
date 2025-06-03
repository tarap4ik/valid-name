package com.org.test.validatenametest.repository.requests;

import com.org.test.validatenametest.entity.requests.CreditBureauEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditBureauRepository extends JpaRepository<CreditBureauEntity, Long> {
}
