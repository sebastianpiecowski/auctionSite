package com.zai.projekt.Service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.zai.projekt.DTO.AnnouncementDTO;
import com.zai.projekt.DTO.UserDTO;
import com.zai.projekt.Entity.AnnouncementEntity;
import com.zai.projekt.Entity.UserEntity;
import com.zai.projekt.IService.IAnnouncementService;
import com.zai.projekt.Repository.AnnouncementRepository;
import com.zai.projekt.Repository.CategoryRepository;
import com.zai.projekt.Repository.StatusRepository;
import com.zai.projekt.Repository.UserRepository;
@Service
public class AnnouncementService implements IAnnouncementService{
	@Autowired
	private AnnouncementRepository announcementRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StatusRepository statusRepository;

	private ModelMapper modelMapper=new ModelMapper();
	@Override
	public List<AnnouncementDTO> getAllAnnouncements() {
		List<AnnouncementDTO> list= new ArrayList<>();
		announcementRepository.findAll().forEach(e->list.add(modelMapper.map(e, AnnouncementDTO.class)));
		return list;
	}

	@Override
	public AnnouncementDTO getAnnouncementById(int announcementId) {
		AnnouncementEntity obj=announcementRepository.findById(announcementId).get();
		AnnouncementDTO ae=modelMapper.map(obj, AnnouncementDTO.class);
		return ae;
	}

	@Override
	public synchronized boolean addAnnouncement(AnnouncementEntity announcement) {
		if(announcement.getCategory()==null) {
			announcement.setCategory(categoryRepository.getOne(9));
			announcement.setUser(userRepository.getOne(2));
			announcement.setStatus(statusRepository.getOne(4));
		}
		announcementRepository.save(announcement);
		return true;
	}

	@Override
	public int updateAnnouncement(AnnouncementEntity announcement) {
		
		return 0;
	}

	@Override
	public int deleteAnnouncement(int announcementId) {
		//announcementRepository.delete(getAnnouncementById(announcementId));
		return 0;
	}

	@Override
	public List<AnnouncementDTO> getAnnouncementByTitle(String announcementTitle) {
		List<AnnouncementDTO> list =new ArrayList<>();
		announcementRepository.findByTitleContaining(announcementTitle).forEach(e->list.add(modelMapper.map(e, AnnouncementDTO.class)));
		return list;
	}
	

	@Override
	public List<AnnouncementDTO> getAnnouncementByCategoryName(String category) {
		List<AnnouncementDTO> list =new ArrayList<>();
		announcementRepository.findByCategoryNameContaining(category).forEach(e->list.add(modelMapper.map(e, AnnouncementDTO.class)));
		return list;
	}
	@Override
	public List<AnnouncementDTO> getAnnouncementByCity(String city) {
		List<AnnouncementDTO> list=new ArrayList<>();
		announcementRepository.findByUserCityContaining(city).forEach(e->list.add(modelMapper.map(e, AnnouncementDTO.class)));
		return list;
	}

	@Override
	public List<AnnouncementDTO> getAnnouncementByUserId(int id) {
		
		List<AnnouncementDTO> list=new ArrayList<>();
		announcementRepository.findByUserUserId(id).forEach(e->list.add(modelMapper.map(e, AnnouncementDTO.class)));
		return list;
	}

}
