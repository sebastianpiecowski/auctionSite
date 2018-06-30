package com.zai.projekt.Request;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.zai.projekt.Entity.CategoryEntity;
import com.zai.projekt.Entity.UserEntity;

import lombok.Data;

@Data
public class Announcement {
	private String title;
	private String description;
	private BigDecimal price;
	private int dayUpTime;
	private int categoryId;
	private List<String> images;
	
}
