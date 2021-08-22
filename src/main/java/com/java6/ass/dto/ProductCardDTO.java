package com.java6.ass.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCardDTO {

	Integer productid;
	Double price;
	Integer quantity;
	
}
