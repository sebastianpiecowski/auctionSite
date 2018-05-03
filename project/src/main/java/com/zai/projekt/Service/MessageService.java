package com.zai.projekt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zai.projekt.IService.IMessageService;
import com.zai.projekt.Repository.MessageRepository;
@Service
public class MessageService implements IMessageService{
@Autowired
private MessageRepository messageRepository;


}
