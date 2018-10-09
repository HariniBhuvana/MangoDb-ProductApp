package com.capgemini.productapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.productapp.entities.Product;
import com.capgemini.productapp.exeception.ProductNotFoundException;
import com.capgemini.productapp.repository.ProductRepository;
import com.capgemini.productapp.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product findProductById(int productId) throws ProductNotFoundException {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if (optionalProduct.isPresent())
			return optionalProduct.get();
		throw new ProductNotFoundException("Product does not exists");
	}

	@Override
	public void deleteProduct(Product product) {
		productRepository.delete(product);

	}

	@Override
	public List<Product> findProductByName(String productName) throws ProductNotFoundException {
		
		List<Product> listProduct = productRepository.findProductByName(productName);

			return listProduct;
		
		//throw new ProductNotFoundException("no such product found");
	}

	@Override
	public List<Product> findProductByCategory(String productCategory) throws ProductNotFoundException {
		List<Product> listProduct = productRepository.findProductByCategory(productCategory);

		return listProduct;
	}

	@Override
	public List<Product> findProductByCategoryAndPrice(String productCategory, double maxprice, double minprice) {
		List<Product> listProduct = productRepository.findProductByCategoryAndPrice(productCategory, maxprice,
				minprice);

		return listProduct;
	}
	@Override
	public List<Product> findProductByPrice(double productPrice) throws ProductNotFoundException {
		List<Product> listProduct = productRepository.findProductByPrice(productPrice);

		return listProduct;
	}

}
