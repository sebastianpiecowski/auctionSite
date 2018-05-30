package com.zai.projekt.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
/*
@Getter
@Setter
@Entity
@Table(name = "message")
public class MessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "announcement_id")
	private AnnouncementEntity announcement;
	@Column(name = "owner_id")
	private UserEntity owner;
	@Column(name = "client_id")
	private UserEntity client;
	@Column(name = "message_id")
	private int messageId;
	@Column(name = "message")
	private String message;
}*/