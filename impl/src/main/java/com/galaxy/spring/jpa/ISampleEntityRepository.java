package com.galaxy.spring.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galaxy.spring.entity.SampleEntity;

/**
 * JPA Repository for entity.
 */
public interface ISampleEntityRepository extends JpaRepository<SampleEntity, Long> {

}
