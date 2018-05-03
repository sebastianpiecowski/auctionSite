package com.zai.projekt.DTO;

import java.math.BigDecimal;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.zai.projekt.Entity.CategoryEntity;
import com.zai.projekt.Entity.StatusEntity;
import com.zai.projekt.Entity.UserEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnnouncementDTO {
	@JsonInclude(Include.NON_NULL)
	private int announcementId;
	@JsonInclude(Include.NON_NULL)
	private String title;
	@JsonInclude(Include.NON_NULL)
	private String description;
	@JsonInclude(Include.NON_NULL)
	private BigDecimal price;
	@JsonInclude(Include.NON_NULL)
	private UserDTO user;
	@JsonInclude(Include.NON_NULL)
	private StatusEntity status;
	@JsonInclude(Include.NON_NULL)
	private Date startDate;
	@JsonInclude(Include.NON_NULL)
	private Date endDate;
	@JsonInclude(Include.NON_NULL)
	private CategoryDTO category;
}
