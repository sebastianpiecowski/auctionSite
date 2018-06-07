package com.zai.projekt.IService;

import java.util.List;

import com.zai.projekt.DTO.AnnouncementDTO;
import com.zai.projekt.Entity.AnnouncementEntity;
import com.zai.projekt.Entity.CategoryEntity;
import com.zai.projekt.Entity.UserEntity;
import com.zai.projekt.Request.Announcement;

public interface IAnnouncementService {
		AnnouncementDTO getAnnouncementById(int announcementId);
		List<AnnouncementDTO> getAllAnnoucements();
		List<AnnouncementDTO> getAnnouncementsByUserAuth();
		List<AnnouncementDTO> getAnnouncementBySearch(String search);
		List<AnnouncementDTO> getAnnouncementByCategoryId(int id);
		List<AnnouncementDTO> getAnnouncementByCategoryAndSearch(int id, String search);
		List<AnnouncementDTO> getNewAnnouncement();
		boolean addAnnouncement(Announcement announcement);
		void confirmAnnouncement(int id);
		void closeAnnouncement(int id);
		void blockAnnouncement(int id);
		void updateAnnouncement(int id, Announcement announcement);
		void deleteAnnouncement(int id);
		
		
}
