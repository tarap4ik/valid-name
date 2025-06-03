package com.org.test.validatenametest.repository.requests;

import com.org.test.validatenametest.entity.requests.AccountInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountInfoRepository extends JpaRepository<AccountInfoEntity, Long> {
}
