package com.java6.ass.utils;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.java6.ass.dto.ProductCardDTO;

@Service
public class CardUtils {

	@Autowired
	CookieUtils cookie;
	
	public void addToCard(ProductCardDTO p) {
		System.out.println(cookie.getsessionval("myCard"));
		try {
			ObjectMapper mapper = new ObjectMapper();
			if(cookie.getsessionval("myCard")==null) {
				System.out.println(mapper.writeValueAsString(Arrays.asList(p)));
				cookie.session("myCard", mapper.writeValueAsString(Arrays.asList(p)));
			}else {
				CollectionType javaType = mapper.getTypeFactory()
				      .constructCollectionType(List.class, ProductCardDTO.class);
				List<ProductCardDTO> list = mapper.readValue(cookie.getsessionval("myCard"), javaType);
				if(list.stream().anyMatch(prod -> prod.getProductid() == p.getProductid())){
				}else {
					list.add(p);
				}
				cookie.session("myCard", mapper.writeValueAsString(list));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void addToList() {
		
	}
	
	
	
	public void removeFromCard() {
		
	}
}
