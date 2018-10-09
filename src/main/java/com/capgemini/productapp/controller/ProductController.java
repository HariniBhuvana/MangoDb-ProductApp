package com.capgemini.productapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.productapp.entities.Product;
import com.capgemini.productapp.exeception.ProductNotFoundException;
import com.capgemini.productapp.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/product")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		ResponseEntity<Product> responseEntity = new ResponseEntity<Product>(productService.addProduct(product),
				HttpStatus.OK);

		return responseEntity;

	}

	@PutMapping("/product")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		try {
			Product productFromDb = productService.findProductById(product.getProductId());
			if (productFromDb != null)
				return new ResponseEntity<Product>(productService.updateProduct(product), HttpStatus.OK);
		} catch (ProductNotFoundException exception) {
			// logged the exception
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> findProductById(@PathVariable int productId) {
		try {
			Product productFromDb = productService.findProductById(productId);
			return new ResponseEntity<Product>(productFromDb, HttpStatus.OK);
		} catch (ProductNotFoundException exception) {
			// logged the exception
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/product/{productId}")
	public ResponseEntity<Product> deleteProduct(@PathVariable int productId) {
		try {
			Product productFromDb = productService.findProductById(productId);
			if (productFromDb != null) {
				productService.deleteProduct(productFromDb);
				return new ResponseEntity<Product>(HttpStatus.OK);
			}
		} catch (ProductNotFoundException exception) {
			// logged the exception
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/product/name")
	public ResponseEntity<List<Product>> findProductByName(@RequestParam String productName)
			throws ProductNotFoundException {
		System.out.println("controller");
		return new ResponseEntity<List<Product>>(productService.findProductByName(productName), HttpStatus.OK);

	}

	@GetMapping("/product/category")
	public ResponseEntity<List<Product>> findProductByCategory(@RequestParam String productCategory)
			throws ProductNotFoundException {
		return new ResponseEntity<List<Product>>(productService.findProductByCategory(productCategory), HttpStatus.OK);

	}

	@GetMapping("/product/category/price")
	public ResponseEntity<List<Product>> findProductByCategoryAndPrice(@RequestParam String productCategory, double maxprice,
			double minprice) throws ProductNotFoundException {
		return new ResponseEntity<List<Product>>(
				productService.findProductByCategoryAndPrice(productCategory,maxprice, minprice), HttpStatus.OK);

	}
	@GetMapping("/product/price")
	public ResponseEntity<List<Product>> findProductByPrice(@RequestParam double productPrice)
			throws ProductNotFoundException {
		return new ResponseEntity<List<Product>>(productService.findProductByPrice(productPrice), HttpStatus.OK);

	}

}
