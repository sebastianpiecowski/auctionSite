package com.zai.projekt.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.zai.projekt.DTO.AnnouncementDTO;
import com.zai.projekt.IService.IAnnouncementService;

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

	// fetch all announcements
	@GetMapping(value = "all", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<AnnouncementDTO>> getAllAnnouncements() {
		return new ResponseEntity<List<AnnouncementDTO>>(announcementService.getAllAnnouncements(), HttpStatus.OK);
	}

	@GetMapping(value = "title={title}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<AnnouncementDTO>> getAnnouncementByTitle(@PathVariable("title") String title) {
		return new ResponseEntity<List<AnnouncementDTO>>(announcementService.getAnnouncementByTitle(title), HttpStatus.OK);
	}

	
	@GetMapping(value = "category={category}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<AnnouncementDTO>> getAnnouncementByCategory(@PathVariable("category") String category) {
		return new ResponseEntity<List<AnnouncementDTO>>(announcementService.getAnnouncementByCategoryName(category), HttpStatus.OK);
	}
	@GetMapping(value = "category_id={id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<AnnouncementDTO>> getAnnouncementByCategoryId(@PathVariable("id") int category) {
		return new ResponseEntity<List<AnnouncementDTO>>(announcementService.getAnnouncementByCategoryId(category), HttpStatus.OK);
	}
	@GetMapping(value = "city={city}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<AnnouncementDTO>> getAnnouncementByCity(@PathVariable("city") String city){
		return new ResponseEntity<List<AnnouncementDTO>>(announcementService.getAnnouncementByCity(city), HttpStatus.OK);
	}
	@GetMapping(value = "user={id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<AnnouncementDTO>> getAnnouncementByUser(@PathVariable("id") int id){
		return new ResponseEntity<List<AnnouncementDTO>>(announcementService.getAnnouncementByUserId(id), HttpStatus.OK);
	}
	// create new announcement
	@PostMapping(value = "add", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> addAnnouncement(@RequestBody AnnouncementDTO announcementInfo,
			UriComponentsBuilder builder) {
				return null;
		/*AnnouncementEntity announcement = new AnnouncementEntity();
		BeanUtils.copyProperties(announcementInfo, announcement);
		boolean flag = announcementService.addAnnouncement(announcementInfo);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				builder.path("/announcement/{id}").buildAndExpand(announcementInfo.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);*/
	}

	@DeleteMapping(value = "id={id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> deleteAnnouncement(@PathVariable("id") Integer id) {
		announcementService.deleteAnnouncement(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
