package com.zai.projekt.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;



@Data
@Entity
@Table(name = "image")
public class ImageEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Lob
	@Column(name = "image")
	private String image;
	@ManyToOne
	@JoinColumn(name = "announcement_id")
	private AnnouncementEntity announcementId;

}