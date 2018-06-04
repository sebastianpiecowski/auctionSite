package com.zai.projekt.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zai.projekt.Entity.AnnouncementEntity;
import com.zai.projekt.Model.AnnouncementStatus;

public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Integer> {
	List<AnnouncementEntity> findByStatus(AnnouncementStatus status);
	List<AnnouncementEntity> findByIdAndStatus(String title, AnnouncementStatus status);
	List<AnnouncementEntity> findByTitleContainingIgnoreCaseAndStatus(String title, AnnouncementStatus status);
	List<AnnouncementEntity> findByCategoryIdAndStatus(int id, AnnouncementStatus status);
	List<AnnouncementEntity> findByUserEmail(String email);
	List<AnnouncementEntity> findByTitleContainingIgnoreCaseAndCategoryIdAndStatus(String title, int id, AnnouncementStatus status);
}
