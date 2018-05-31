package com.zai.projekt.Request;

import java.math.BigDecimal;
import java.sql.Date;

import com.zai.projekt.Entity.CategoryEntity;
import com.zai.projekt.Entity.UserEntity;

import lombok.Data;

@Data
public class Announcement {
	private String title;
	private String description;
	private BigDecimal price;
	private Date startDate;
	private Date endDate;
	private int userId;
	private int categoryId;
}
