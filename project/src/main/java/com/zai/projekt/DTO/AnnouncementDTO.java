package com.zai.projekt.DTO;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.zai.projekt.Entity.AnnouncementEntity;
import com.zai.projekt.Entity.ImageEntity;

import lombok.Data;

@Data
public class AnnouncementDTO {
	@JsonInclude(Include.NON_NULL)
	private int id;
	@JsonInclude(Include.NON_NULL)
	private String title;
	@JsonInclude(Include.NON_NULL)
	private String description;
	@JsonInclude(Include.NON_NULL)
	private BigDecimal price;
	@JsonInclude(Include.NON_NULL)
	private UserDTO user;
	@JsonInclude(Include.NON_NULL)
	private Date startDate;
	@JsonInclude(Include.NON_NULL)
	private Date endDate;
	@JsonInclude(Include.NON_NULL)
	private List<String> images = new ArrayList<>();
	@JsonInclude(Include.NON_NULL)
	private String status;
	@JsonInclude(Include.NON_NULL)
	private int categoryId;
	public AnnouncementDTO(AnnouncementEntity announcement, List<ImageEntity> images) {
		id=announcement.getId();
		title=announcement.getTitle();
		description=announcement.getDescription();
		price=announcement.getPrice();
		try {
			startDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(announcement.getStartDate().toString());
			endDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(announcement.getEndDate().toString());
		} catch (ParseException e) {
			startDate=announcement.getStartDate();
			endDate=announcement.getEndDate();
			e.printStackTrace();
		}
		status=announcement.getStatus().toString();
		categoryId=announcement.getCategory().getId();
		images.forEach(e -> this.images.add(e.getImage()));
		user=new UserDTO(announcement.getUser());
	}
	
}
