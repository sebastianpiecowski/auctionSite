package com.zai.projekt.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.zai.projekt.DTO.AnnouncementDTO;
import com.zai.projekt.Entity.AnnouncementEntity;
import com.zai.projekt.Entity.ImageEntity;
import com.zai.projekt.IService.IAnnouncementService;
import com.zai.projekt.Model.AnnouncementStatus;
import com.zai.projekt.Repository.AnnouncementRepository;
import com.zai.projekt.Repository.CategoryRepository;
import com.zai.projekt.Repository.ImageRepository;
import com.zai.projekt.Repository.UserRepository;
import com.zai.projekt.Request.Announcement;
@Service
@Transactional
public class AnnouncementService implements IAnnouncementService{
	@Autowired
	private AnnouncementRepository announcementRepository;
	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public AnnouncementDTO getAnnouncementById(int announcementId) {
		AnnouncementEntity announcement=announcementRepository.findById(announcementId).get();
		List<ImageEntity> images=imageRepository.findByAnnouncementIdId(announcementId);
		AnnouncementDTO ae=new AnnouncementDTO(announcement, images);
		return ae;
	}

	@Override
	public boolean addAnnouncement(Announcement announcement) {
		AnnouncementEntity newAnnouncement=new AnnouncementEntity();
		newAnnouncement.setTitle(announcement.getTitle());
		newAnnouncement.setDescription(announcement.getDescription());
		newAnnouncement.setPrice(announcement.getPrice());
		newAnnouncement.setStatus(AnnouncementStatus.NEW);
		newAnnouncement.setCategory(categoryRepository.findById(announcement.getCategoryId()));
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		newAnnouncement.setUser(userRepository.findByEmail(authentication.getName()));
		Date startDate=new Date();
		Date endDate=new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(endDate); 
		c.add(Calendar.DATE, announcement.getDayUpTime());
		endDate = c.getTime();
		
		newAnnouncement.setStartDate(startDate);
		newAnnouncement.setEndDate(endDate);
		final AnnouncementEntity addedAnnouncement=announcementRepository.save(newAnnouncement);
		if(addedAnnouncement != null) {			
			announcement.getImages().forEach(e-> {
				ImageEntity image=new ImageEntity();
				image.setImage(e);
				image.setAnnouncementId(addedAnnouncement);
				imageRepository.save(image);
			});		
			return true;	
		}
		else
		{
			return false;
		}
		
	}

	@Override
	public void updateAnnouncement(int id, Announcement updatedAnnouncement) {
		AnnouncementEntity announcement=announcementRepository.findById(id).get();
		announcement.setTitle(updatedAnnouncement.getTitle());
		announcement.setDescription(updatedAnnouncement.getDescription());
		announcement.setPrice(updatedAnnouncement.getPrice());
		announcement.setCategory(categoryRepository.findById(updatedAnnouncement.getCategoryId()));
		announcement.setStatus(AnnouncementStatus.NEW);
		announcementRepository.save(announcement);
	}

	@Override
	public void deleteAnnouncement(int announcementId) {
		announcementRepository.deleteById(announcementId);
	}

	@Override
	public List<AnnouncementDTO> getAnnouncementBySearch(String announcementTitle) {
		List<AnnouncementDTO> list =new ArrayList<>();
		List<AnnouncementEntity> announcements=announcementRepository.findByTitleContainingIgnoreCaseAndStatus(announcementTitle, AnnouncementStatus.ACTIVE);
		announcements.forEach(e -> {
			List<ImageEntity> images = imageRepository.findByAnnouncementIdId(e.getId());
			list.add(new AnnouncementDTO(e, images));
		});
		return list;
	}
	
	@Override
	public List<AnnouncementDTO> getAnnouncementByCategoryId(int id) {
		List<AnnouncementDTO> list =new ArrayList<>();
		List<AnnouncementEntity> announcements=announcementRepository.findByCategoryIdAndStatus(id, AnnouncementStatus.ACTIVE);
		announcements.forEach(e -> {
			List<ImageEntity> images = imageRepository.findByAnnouncementIdId(e.getId());
			list.add(new AnnouncementDTO(e, images));
		});
		return list;
	}
	@Override
	public List<AnnouncementDTO> getAnnouncementByCategoryAndSearch(int id, String search) {
		List<AnnouncementDTO> list=new ArrayList<>();
		List<AnnouncementEntity> announcements=announcementRepository.findByTitleContainingIgnoreCaseAndCategoryIdAndStatus(search, id, AnnouncementStatus.ACTIVE);
		announcements.forEach(e -> {
			List<ImageEntity> images = imageRepository.findByAnnouncementIdId(e.getId());
			list.add(new AnnouncementDTO(e, images));
		});
		return list;
	}
	@Override
	public List<AnnouncementDTO> getAnnouncementsByUserAuth() {		
		List<AnnouncementDTO> list=new ArrayList<>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<AnnouncementEntity> announcements=announcementRepository.findByUserEmail(authentication.getName());
		announcements.forEach(e -> {
			List<ImageEntity> images = imageRepository.findByAnnouncementIdId(e.getId());
			list.add(new AnnouncementDTO(e, images));
		});
		return list;

	}

	@Override
	public List<AnnouncementDTO> getNewAnnouncement() {
		List<AnnouncementDTO> list=new ArrayList<>();
		List<AnnouncementEntity> announcements=announcementRepository.findByStatus(AnnouncementStatus.NEW);
		announcements.forEach(e -> {
			List<ImageEntity> images = imageRepository.findByAnnouncementIdId(e.getId());
			list.add(new AnnouncementDTO(e, images));
		});
		return list;
		
	}

	@Override
	public void confirmAnnouncement(int id) {
		 	AnnouncementEntity announcement = announcementRepository.findById(id).get();
	        announcement.setStatus(AnnouncementStatus.ACTIVE);
	        announcementRepository.save(announcement);	
	}

	@Override
	public void closeAnnouncement(int id) {
		AnnouncementEntity announcement = announcementRepository.findById(id).get();
        announcement.setStatus(AnnouncementStatus.CLOSED);
        announcementRepository.save(announcement);	
	}

	@Override
	public void blockAnnouncement(int id) {
		AnnouncementEntity announcement = announcementRepository.findById(id).get();
        announcement.setStatus(AnnouncementStatus.BLOCKED);
        announcementRepository.save(announcement);	
	}

	@Override
	public List<AnnouncementDTO> getAllAnnoucements() {
		List<AnnouncementDTO> list=new ArrayList<>();
		List<AnnouncementEntity> announcements=announcementRepository.findByStatus(AnnouncementStatus.ACTIVE);
		announcements.forEach(e -> {
			List<ImageEntity> images = imageRepository.findByAnnouncementIdId(e.getId());
			list.add(new AnnouncementDTO(e, images));
		});
		return list;
	}

}
