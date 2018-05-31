package com.zai.projekt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zai.projekt.Entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
	CategoryEntity findById(int id);
}
