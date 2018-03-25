package com.zai.projekt.Repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.zai.projekt.Entity.Announcement;

public interface AnnouncementRepository extends CrudRepository<Announcement, Integer> {
	List<Announcement> findByTitle(String title);
}
