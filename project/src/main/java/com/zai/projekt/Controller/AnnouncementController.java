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

import com.zai.projekt.Controller.AnnouncementInfo;
import com.zai.projekt.Entity.Announcement;
import com.zai.projekt.Service.IAnnouncementService;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = {"http://localhost:4200"})
public class AnnouncementController {
	@Autowired
	private IAnnouncementService announcementService;

	// fetch announcement by id
	@GetMapping(value = "announcement/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AnnouncementInfo> getAnnouncementById(@PathVariable("id") Integer id) {
		AnnouncementInfo ae = new AnnouncementInfo();
		BeanUtils.copyProperties(announcementService.getAnnouncementById(id), ae);
		return new ResponseEntity<AnnouncementInfo>(ae, HttpStatus.OK);
	}

	// fetch all announcements
	@GetMapping(value = "announcements", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<AnnouncementInfo>> getAllAnnouncements() {
		List<AnnouncementInfo> responseAnnouncementList = new ArrayList<>();
		List<Announcement> announcementList = announcementService.getAllAnnouncements();
		for (int i = 0; i < announcementList.size(); i++) {
			AnnouncementInfo ae = new AnnouncementInfo();
			BeanUtils.copyProperties(announcementList.get(i), ae);
			responseAnnouncementList.add(ae);
		}
		return new ResponseEntity<List<AnnouncementInfo>>(responseAnnouncementList, HttpStatus.OK);
	}

	// create new announcement
	@PostMapping(value = "announcement", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> addAnnouncement(@RequestBody AnnouncementInfo announcementInfo,
			UriComponentsBuilder builder) {
		Announcement announcement = new Announcement();
		BeanUtils.copyProperties(announcementInfo, announcement);
		boolean flag = announcementService.addAnnouncement(announcement);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers=new HttpHeaders();
		headers.setLocation(builder.path("/announcement/{id}").buildAndExpand(announcement.getAnnouncementId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@DeleteMapping(value="announcement/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> deleteAnnouncement(@PathVariable("id") Integer id) {
		announcementService.deleteAnnouncement(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
