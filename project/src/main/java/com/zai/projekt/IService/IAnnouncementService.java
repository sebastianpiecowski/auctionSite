package com.zai.projekt.IService;

import java.util.List;

import com.zai.projekt.DTO.AnnouncementDTO;
import com.zai.projekt.Entity.AnnouncementEntity;
import com.zai.projekt.Entity.UserEntity;

public interface IAnnouncementService {

		List<AnnouncementDTO> getAllAnnouncements();
		List<AnnouncementDTO> getAnnouncementByTitle(String announcementTitle);
		List<AnnouncementDTO> getAnnouncementByCategoryName(String category);
		List<AnnouncementDTO> getAnnouncementByCity(String city);
		List<AnnouncementDTO> getAnnouncementByUserId(int id);
		List<AnnouncementDTO> getAnnouncementByCategoryId(int id);
		AnnouncementDTO getAnnouncementById(int announcementId);
		boolean addAnnouncement(AnnouncementEntity announcement);
		int updateAnnouncement(AnnouncementEntity announcement);
		int deleteAnnouncement(int announcementId);
		
}
