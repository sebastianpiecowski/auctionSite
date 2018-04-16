package com.zai.projekt.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
	@JsonInclude(Include.NON_NULL)
	private int categoryId;
	@JsonInclude(Include.NON_NULL)
	private String category;
}
