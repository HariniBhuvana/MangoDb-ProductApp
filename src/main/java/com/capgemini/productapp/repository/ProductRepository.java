package com.capgemini.productapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.capgemini.productapp.entities.Product;

public interface ProductRepository extends MongoRepository<Product, Integer> {
	
	@Query("{'productName': ?0}")
	public List<Product> findProductByName(String nameContains);

	@Query("{productCategory : ?0}")
	public List<Product> findProductByCategory(String categoryContains);

	@Query("{'productCategory': ?0 , 'productPrice':{$gt : ?2 , $lt: ?1}}")
	public List<Product> findProductByCategoryAndPrice(String categoryContains, double maxprice, double minprice);

	@Query("{'productPrice':{$gt:0}}")
	public List<Product> findProductByPrice(double productPrice);
}
