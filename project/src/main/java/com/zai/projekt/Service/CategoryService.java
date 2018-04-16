package com.zai.projekt.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zai.projekt.Entity.CategoryEntity;
import com.zai.projekt.IService.ICategoryService;
import com.zai.projekt.Repository.CategoryRepository;
@Service
public class CategoryService implements ICategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<CategoryEntity> getAllCategories() {
		List<CategoryEntity> list = new ArrayList<>();
		categoryRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public CategoryEntity getCategoryById(int id) {
		CategoryEntity obj = categoryRepository.findById(id).get();
		return obj;
	}
	
	
}
