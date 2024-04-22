package com.potenday.anotherme.repository;

import com.potenday.anotherme.model.Entity.MBTI;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MBTIRepository extends JpaRepository<MBTI, Long> {
    MBTI findByType(String type);
}
