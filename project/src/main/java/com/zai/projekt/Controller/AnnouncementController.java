package com.zai.projekt.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.net.HttpHeaders;
import com.zai.projekt.DTO.AnnouncementDTO;
import com.zai.projekt.Entity.AnnouncementEntity;
import com.zai.projekt.Entity.UserEntity;
import com.zai.projekt.IService.IAnnouncementService;
import com.zai.projekt.IService.IUserService;
import com.zai.projekt.Repository.CategoryRepository;
import com.zai.projekt.Repository.UserRepository;
import com.zai.projekt.Request.Announcement;
import com.zai.projekt.Service.UserService;

@RestController
@RequestMapping(value = "announcement")
public class AnnouncementController {
	@Autowired
	private IAnnouncementService announcementService;
	
	// fetch announcement by id
	@GetMapping(value = "id={id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AnnouncementDTO> getAnnouncementById(@PathVariable("id") Integer id) {
		return new ResponseEntity<AnnouncementDTO>(announcementService.getAnnouncementById(id), HttpStatus.OK);
	}
	@GetMapping(value = "category_id={id}&search={search}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AnnouncementDTO> getAnnouncementByCategoryIdAndSearch(@PathVariable("id") Integer id, @PathVariable("search") String search) {
		return new ResponseEntity<AnnouncementDTO>(announcementService.getAnnouncementById(id), HttpStatus.OK);
	}
	@GetMapping(value = "search={search}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<AnnouncementDTO>> getAnnouncementBySearch(@PathVariable("search") String search) {
		return new ResponseEntity<List<AnnouncementDTO>>(announcementService.getAnnouncementBySearch(search), HttpStatus.OK);
	}

	@GetMapping(value = "category_id={id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<AnnouncementDTO>> getAnnouncementByCategoryId(@PathVariable("id") int category) {
		return new ResponseEntity<List<AnnouncementDTO>>(announcementService.getAnnouncementByCategoryId(category), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping(value = "user/my", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<AnnouncementDTO>> getAnnouncementsByUserAuth(@PathVariable("id") int id){
		return new ResponseEntity<List<AnnouncementDTO>>(announcementService.getAnnouncementsByUserAuth(), HttpStatus.OK);
	}
	//confirm
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping(value = "confirm", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> confirmAnnouncement(@RequestBody Integer id) {
		announcementService.confirmAnnouncement(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	//close
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PostMapping(value = "close", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> closeAnnouncement(@RequestBody Integer id) {
		announcementService.closeAnnouncement(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	//confirm
		@PreAuthorize("hasAnyRole('ADMIN')")
		@PostMapping(value = "block", produces = { MediaType.APPLICATION_JSON_VALUE })
		public ResponseEntity<Void> blockAnnouncement(@RequestBody Integer id) {
			announcementService.blockAnnouncement(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	// create new announcement
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PostMapping(value = "add", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> addAnnouncement(@RequestBody Announcement announcementInfo,
			UriComponentsBuilder builder) {
		
		boolean flag = announcementService.addAnnouncement(announcementInfo);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@DeleteMapping(value = "id={id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> deleteAnnouncement(@PathVariable("id") Integer id) {
		announcementService.deleteAnnouncement(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
