package com.zai.projekt.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zai.projekt.Entity.AnnouncementEntity;
import com.zai.projekt.IService.IAnnouncementService;
import com.zai.projekt.Repository.AnnouncementRepository;
@Service
public class AnnouncementService implements IAnnouncementService{
	@Autowired
	private AnnouncementRepository announcementRepository;
	@Override
	public List<AnnouncementEntity> getAllAnnouncements() {
		List<AnnouncementEntity> list= new ArrayList<>();
		announcementRepository.findAll().forEach(e->list.add(e));
		return list;
	}

	@Override
	public AnnouncementEntity getAnnouncementById(int announcementId) {
		AnnouncementEntity obj=announcementRepository.findById(announcementId).get();
		return obj;
	}

	@Override
	public synchronized boolean addAnnouncement(AnnouncementEntity announcement) {
		announcementRepository.save(announcement);
		return true;
	}

	@Override
	public void updateAnnouncement(AnnouncementEntity announcement) {
		//announcementRepository.
		
	}

	@Override
	public void deleteAnnouncement(int announcementId) {
		announcementRepository.delete(getAnnouncementById(announcementId));
	}

	@Override
	public List<AnnouncementEntity> getAnnouncementByTitle(String announcementTitle) {
		List<AnnouncementEntity> list =new ArrayList<>();
		announcementRepository.findByTitleContaining(announcementTitle).forEach(e->list.add(e));
		return list;
	}

	@Override
	public List<AnnouncementEntity> getAnnouncementByCategory(String category) {
		List<AnnouncementEntity> list =new ArrayList<>();
		announcementRepository.findByCategoryNameContaining(category).forEach(e->list.add(e));
		return list;
	}

}
