package com.zai.projekt.Service;

import java.util.List;

import com.zai.projekt.Entity.Announcement;

public interface IAnnouncementService {

		List<Announcement> getAllAnnouncements();
		Announcement getAnnouncementById(int announcementId);
		boolean addAnnouncement(Announcement announcement);
		void updateAnnouncement(Announcement announcement);
		void deleteAnnouncement(int announcementId);
}
