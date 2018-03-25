package com.zai.projekt.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zai.projekt.Entity.Announcement;
import com.zai.projekt.Repository.AnnouncementRepository;
@Service
public class AnnouncementService implements IAnnouncementService{
	@Autowired
	private AnnouncementRepository announcementRepository;
	@Override
	public List<Announcement> getAllAnnouncements() {
		List<Announcement> list= new ArrayList<>();
		announcementRepository.findAll().forEach(e->list.add(e));
		return list;
	}

	@Override
	public Announcement getAnnouncementById(int announcementId) {
		Announcement obj=announcementRepository.findById(announcementId).get();
		return obj;
	}

	@Override
	public synchronized boolean addAnnouncement(Announcement announcement) {
		announcementRepository.save(announcement);
		return true;
	}

	@Override
	public void updateAnnouncement(Announcement announcement) {
		//announcementRepository.
		
	}

	@Override
	public void deleteAnnouncement(int announcementId) {
		announcementRepository.delete(getAnnouncementById(announcementId));
	}

}
