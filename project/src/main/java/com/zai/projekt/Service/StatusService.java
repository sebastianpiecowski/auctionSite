package com.zai.projekt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zai.projekt.Entity.CategoryEntity;
import com.zai.projekt.Entity.StatusEntity;
import com.zai.projekt.IService.IStatusService;
import com.zai.projekt.Repository.CategoryRepository;
import com.zai.projekt.Repository.StatusRepository;
@Service
@Transactional
public class StatusService implements IStatusService{
	@Autowired
	private StatusRepository statusRepository;
	@Override
	public StatusEntity getStatusById(int id) {
		return statusRepository.findById(id).get();
	}
}
