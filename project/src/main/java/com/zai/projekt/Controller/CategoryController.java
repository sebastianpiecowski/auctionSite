package com.zai.projekt.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.zai.projekt.Entity.CategoryEntity;
import com.zai.projekt.IService.ICategoryService;
import DTO.CategoryDTO;

@RestController
// @RequestMapping("user")
@CrossOrigin(origins = { "http://localhost:4200" })
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;

	// fetch category by id
	@GetMapping(value = "category/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id") Integer id) {
		CategoryDTO ct = new CategoryDTO();
		BeanUtils.copyProperties(categoryService.getCategoryById(id), ct);
		return new ResponseEntity<CategoryDTO>(ct, HttpStatus.OK);
	}

	// fetch all categories
	@GetMapping(value = "categories", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<CategoryDTO>> getAllCategories() {
		List<CategoryDTO> responseCategoryList = new ArrayList<>();
		List<CategoryEntity> categoryList = categoryService.getAllCategories();
		for (int i = 0; i < categoryList.size(); i++) {
			CategoryDTO ae = new CategoryDTO();
			BeanUtils.copyProperties(categoryList.get(i), ae);
			responseCategoryList.add(ae);
		}
		return new ResponseEntity<List<CategoryDTO>>(responseCategoryList, HttpStatus.OK);
	}

}