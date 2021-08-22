package com.java6.ass.servic;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.java6.ass.dao.CategoryDAO;
import com.java6.ass.dao.ProductDAO;
import com.java6.ass.dto.ProductDTO;
import com.java6.ass.entity.Product;

@Service
public class ProductServic {

	@Autowired
	ProductDAO productDAO;
	@Autowired
	CategoryDAO cateDAO;
	
	public Product dtoToProduct(ProductDTO productDTO) {
		Product product = productDAO.findById(productDTO.getId()).get();
		product.setCategory(cateDAO.findById(productDTO.getCategoryid()).get());
		product.setPrice(productDTO.getPrice());
		product.setImage(productDTO.getImage());
		product.setName(productDTO.getName());
		product.setAvailable(productDTO.getAvailable());
		return product;
	}
	
	public Product dtoToNewPro(ProductDTO dto) {
		return Product.builder()
				.category(cateDAO.findById(dto.getCategoryid()).get())
				.price(dto.getPrice())
				.image(dto.getImage())
				.name(dto.getName())
				.available(dto.getAvailable())
				.createDate(new Date())
				.build();
	}
	
	public ProductDTO proToDto(Product product) {
		return ProductDTO.
				builder().
				categoryid(product.getCategory().getId())
				.id(product.getId())
				.image(product.getImage())
				.name(product.getName())
				.price(product.getPrice())
				.available(product.getAvailable())
						.build();
	}
	
	public Page<Product> getAllProductByCategory(String id,Optional<Integer> pageNumber,Integer limit){
		Pageable page = PageRequest.of(pageNumber.orElse(0), limit);
		return productDAO.findAllByCategory(id, page);
	}
	
	public Product getById(Integer id) {
		return productDAO.getById(id);
	}
	
	public Page<Product> getAllProduct(Optional<Integer> pageNumber,Integer limit){
		return productDAO.findAll(PageRequest.of(pageNumber.orElse(0), limit));
	}
	
	public Page<Product> searchWithName(String name,Optional<Integer> pageNumber){
		return productDAO.findByNameContaining(name, PageRequest.of(pageNumber.orElse(0),6));
	}
	
	public Page<Product> searchhWithPrice(Double price1,Double price2,Optional<Integer> pageNumber){
		return productDAO.findByPrice(price1, price2, PageRequest.of(pageNumber.orElse(0), 6));
	}
	
	public void deleteProduct(Integer id) {
		productDAO.delete(productDAO.findById(id).get());
	}
	
	public Product updateProduct(ProductDTO dto) {
		return productDAO.save(dtoToProduct(dto));
	}
	
	public Product createProduct(ProductDTO dto) {
		return productDAO.save(dtoToNewPro(dto));
	}
}
