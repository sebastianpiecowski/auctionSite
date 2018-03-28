package com.zai.projekt.IService;

import java.util.List;

import com.zai.projekt.Entity.AnnouncementEntity;

public interface IAnnouncementService {

		List<AnnouncementEntity> getAllAnnouncements();
		List<AnnouncementEntity> getAnnouncementByTitle(String announcementTitle);
		List<AnnouncementEntity> getAnnouncementByCategory(String category);
		AnnouncementEntity getAnnouncementById(int announcementId);
		boolean addAnnouncement(AnnouncementEntity announcement);
		void updateAnnouncement(AnnouncementEntity announcement);
		void deleteAnnouncement(int announcementId);
}
