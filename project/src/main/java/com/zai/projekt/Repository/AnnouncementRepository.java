package com.zai.projekt.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zai.projekt.Entity.AnnouncementEntity;

public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Integer> {
	List<AnnouncementEntity> findByTitleContaining(String title);
	List<AnnouncementEntity> findByCategoryNameContaining(String category);
}
