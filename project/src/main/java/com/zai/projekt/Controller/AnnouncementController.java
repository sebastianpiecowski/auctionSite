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
	
	@GetMapping("/all")
	public ResponseEntity<List<AnnouncementDTO>> getAllAnnouncement(){
		return new ResponseEntity<List<AnnouncementDTO>>(announcementService.getAllAnnoucements(), HttpStatus.OK);
	}
	// fetch announcement by id
	@GetMapping("/id={id}")
	public ResponseEntity<AnnouncementDTO> getAnnouncementById(@PathVariable("id") Integer id) {
		return new ResponseEntity<AnnouncementDTO>(announcementService.getAnnouncementById(id), HttpStatus.OK);
	}
	@GetMapping("/category_id={id}/search={title}")
	public ResponseEntity<List<AnnouncementDTO>> getAnnouncementByCategoryIdAndSearch(@PathVariable("id") int id, @PathVariable("title") String search) {
		System.out.println("ID:"+id+ " search: "+search);
		return new ResponseEntity<List<AnnouncementDTO>>(announcementService.getAnnouncementByCategoryAndSearch(id, search), HttpStatus.OK);
	}
	@GetMapping("/search={search}")
	public ResponseEntity<List<AnnouncementDTO>> getAnnouncementBySearch(@PathVariable("search") String search) {
		return new ResponseEntity<List<AnnouncementDTO>>(announcementService.getAnnouncementBySearch(search), HttpStatus.OK);
	}

	@GetMapping("/category_id={id}")
	public ResponseEntity<List<AnnouncementDTO>> getAnnouncementByCategoryId(@PathVariable("id") int category) {
		return new ResponseEntity<List<AnnouncementDTO>>(announcementService.getAnnouncementByCategoryId(category), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("/user/my")
	public ResponseEntity<List<AnnouncementDTO>> getAnnouncementsByUserAuth(){
		return new ResponseEntity<List<AnnouncementDTO>>(announcementService.getAnnouncementsByUserAuth(), HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/new")
	public ResponseEntity<List<AnnouncementDTO>> getAnnouncementsWithNewStatus() {
		return new ResponseEntity<List<AnnouncementDTO>>(announcementService.getNewAnnouncement(), HttpStatus.OK);
	}
	//confirm
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/confirm")
	public ResponseEntity<Void> confirmAnnouncement(@RequestBody Integer id) {
		announcementService.confirmAnnouncement(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	//close
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@PostMapping("/close")
	public ResponseEntity<Void> closeAnnouncement(@RequestBody Integer id) {
		announcementService.closeAnnouncement(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	//block
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/block")
	public ResponseEntity<Void> blockAnnouncement(@RequestBody Integer id) {
		announcementService.blockAnnouncement(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	// create new announcement
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@PostMapping("/add")
	public ResponseEntity<Void> addAnnouncement(@RequestBody Announcement announcementInfo) {
		
		boolean flag = announcementService.addAnnouncement(announcementInfo);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@PostMapping("/update/id={id}")
	public ResponseEntity<Void> updateAnnouncement(@PathVariable("id") Integer id, @RequestBody Announcement announcementInfo) {
		try {
		announcementService.updateAnnouncement(id, announcementInfo);
		
			return new ResponseEntity<Void>(HttpStatus.OK);
		}catch(Exception e)
		
		{
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@DeleteMapping("/id={id}")
	public ResponseEntity<Void> deleteAnnouncement(@PathVariable("id") Integer id) {
		announcementService.deleteAnnouncement(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
