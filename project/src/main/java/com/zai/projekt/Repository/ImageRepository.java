package com.zai.projekt.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zai.projekt.Entity.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, Integer>{
	
	public ImageEntity findById(int id);
    
    public List<ImageEntity> findByAnnouncementIdId(int id);
}
