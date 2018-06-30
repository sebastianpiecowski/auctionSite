package com.zai.projekt.Entity;

import java.io.Serializable;
import java.math.BigDecimal;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.zai.projekt.Model.AnnouncementStatus;
import com.zai.projekt.Model.UserRole;

import lombok.Data;


@Data
@Entity
@Table(name = "announcement")
public class AnnouncementEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "price")
	private BigDecimal price;
	@Column(name = "start_date")
	private Date startDate;
	@Column(name = "end_date")
	private Date endDate;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
	@Column(name="status")
    @Enumerated(EnumType.STRING)
	private AnnouncementStatus status;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private CategoryEntity category;
}
