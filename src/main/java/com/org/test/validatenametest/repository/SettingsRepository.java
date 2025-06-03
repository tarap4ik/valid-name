package com.org.test.validatenametest.repository;

import com.org.test.validatenametest.entity.SettingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SettingsRepository extends JpaRepository<SettingsEntity, Long> {
    Optional<SettingsEntity> findByName(String name);
}
