package com.zai.projekt.Controller;

import java.math.BigDecimal;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class AnnouncementInfo {
	@JsonInclude(Include.NON_NULL)
	private int announcementId;
	@JsonInclude (Include.NON_NULL)
	private String title;
	@JsonInclude (Include.NON_NULL)
	private String description;
	@JsonInclude (Include.NON_NULL) 
	private BigDecimal price;
	@JsonInclude (Include.NON_NULL)
	private int userId;
	@JsonInclude (Include.NON_NULL)
	private int statusId;
	@JsonInclude (Include.NON_NULL)
	private Date startDate;
	@JsonInclude (Include.NON_NULL)
	private Date endDate;
	@JsonInclude (Include.NON_NULL)
	private int categoryId;
	public int getAnnouncementId() {
		return announcementId;
	}
	public void setAnnouncementId(int announcementId) {
		this.announcementId = announcementId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	
}
