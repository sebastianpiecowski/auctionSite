package com.zai.projekt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zai.projekt.Entity.StatusEntity;

public interface StatusRepository extends JpaRepository<StatusEntity, Integer> {
}
