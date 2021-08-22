package com.java6.ass.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {

	Long id;
	Integer productid;
	//name of product entity in card frontend
	String name;
	Integer quantity;
	Double price;
}
