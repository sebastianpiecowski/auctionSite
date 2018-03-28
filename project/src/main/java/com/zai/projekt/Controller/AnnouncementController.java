package com.zai.projekt.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.zai.projekt.Entity.AnnouncementEntity;
import com.zai.projekt.IService.IAnnouncementService;

import DTO.AnnouncementDTO;

@RestController
// @RequestMapping("user")
@CrossOrigin(origins = { "http://localhost:4200" })
public class AnnouncementController {
	@Autowired
	private IAnnouncementService announcementService;

	// fetch announcement by id
	@GetMapping(value = "announcement/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AnnouncementDTO> getAnnouncementById(@PathVariable("id") Integer id) {
		AnnouncementDTO ae = new AnnouncementDTO();
		BeanUtils.copyProperties(announcementService.getAnnouncementById(id), ae);
		return new ResponseEntity<AnnouncementDTO>(ae, HttpStatus.OK);
	}

	// fetch all announcements
	@GetMapping(value = "announcements", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<AnnouncementDTO>> getAllAnnouncements() {
		List<AnnouncementDTO> responseAnnouncementList = new ArrayList<>();
		List<AnnouncementEntity> announcementList = announcementService.getAllAnnouncements();
		for (int i = 0; i < announcementList.size(); i++) {
			AnnouncementDTO ae = new AnnouncementDTO();
			BeanUtils.copyProperties(announcementList.get(i), ae);
			responseAnnouncementList.add(ae);
		}
		return new ResponseEntity<List<AnnouncementDTO>>(responseAnnouncementList, HttpStatus.OK);
	}

	@GetMapping(value = "announcement/title={title}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<AnnouncementDTO>> getAnnouncementByTitle(@PathVariable("title") String title) {
		List<AnnouncementDTO> responseAnnouncementList = new ArrayList<>();
		List<AnnouncementEntity> announcementList = announcementService.getAnnouncementByTitle(title);
		for (int i = 0; i < announcementList.size(); i++) {
			AnnouncementDTO ae = new AnnouncementDTO();
			BeanUtils.copyProperties(announcementList.get(i), ae);
			responseAnnouncementList.add(ae);
		}
		return new ResponseEntity<List<AnnouncementDTO>>(responseAnnouncementList, HttpStatus.OK);
	}

	@GetMapping(value = "announcement/category={category}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<AnnouncementDTO>> getAnnouncementByCategory(@PathVariable("category") String category) {
		List<AnnouncementDTO> responseAnnouncementList = new ArrayList<>();
		List<AnnouncementEntity> announcementList = announcementService.getAnnouncementByCategory(category);
		for (int i = 0; i < announcementList.size(); i++) {
			AnnouncementDTO ae = new AnnouncementDTO();
			BeanUtils.copyProperties(announcementList.get(i), ae);
			responseAnnouncementList.add(ae);
		}
		return new ResponseEntity<List<AnnouncementDTO>>(responseAnnouncementList, HttpStatus.OK);

	}

	// create new announcement
	@PostMapping(value = "announcement", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> addAnnouncement(@RequestBody AnnouncementDTO announcementInfo,
			UriComponentsBuilder builder) {
		AnnouncementEntity announcement = new AnnouncementEntity();
		BeanUtils.copyProperties(announcementInfo, announcement);
		boolean flag = announcementService.addAnnouncement(announcement);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				builder.path("/announcement/{id}").buildAndExpand(announcement.getAnnouncementId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "announcement/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> deleteAnnouncement(@PathVariable("id") Integer id) {
		announcementService.deleteAnnouncement(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
