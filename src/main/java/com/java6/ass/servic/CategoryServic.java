package com.java6.ass.servic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.java6.ass.dao.CategoryDAO;
import com.java6.ass.dto.CategoryDTO;
import com.java6.ass.entity.Category;

@Service
public class CategoryServic {

	@Autowired
	CategoryDAO cateDAO;
	
	public List<Category> getAllCategory(){
		return cateDAO.findAll();
	}
	
	public Page<Category> getAllCategoryByPage(Optional<Integer> page,Integer limit){
		return cateDAO.findAll(PageRequest.of(page.orElse(0), limit));
	}
	
	public Category updateCategory(CategoryDTO dto) {
		return cateDAO.save(dtoToCate(dto));
	}
	
	public void deleteCategory(String id) {
		cateDAO.delete(cateDAO.findById(id).get());
	}
	
	public Category createCate(CategoryDTO dto) {
		return cateDAO.save(dtoToNewCate(dto));
	}
	
	public Category dtoToCate(CategoryDTO dto) {
		Category cate = cateDAO.findById(dto.getId()).get();
		cate.setName(dto.getName());
		return cateDAO.save(cate);
	}
	
	public Category dtoToNewCate(CategoryDTO dto) {
		return Category.builder()
				.name(dto.getName())
				.build();
	}
	
	public CategoryDTO cateToDto(Category cate) {
		return CategoryDTO.builder().id(cate.getId()).name(cate.getName()).build();
	}
}
