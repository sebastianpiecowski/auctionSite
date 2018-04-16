package com.zai.projekt.IService;

import java.util.List;

import com.zai.projekt.Entity.CategoryEntity;

public interface ICategoryService {
	List<CategoryEntity> getAllCategories();
	CategoryEntity getCategoryById(int id);
}
