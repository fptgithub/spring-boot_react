package com.java6.ass.dto;

import com.java6.ass.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
	Integer id;
	String name;
	String image;
	Double price;
	String categoryid;
	Boolean available;
}
