package com.cg.controllers;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.model.Product;
import com.cg.services.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/product")
@Api(value = "/product",  description = "Operations pertaining to products in Home ")
public class ProductController {

	@Autowired
	private ProductService productService;

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 400, message = "invalid input provided"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")

	})

	@ApiOperation(value = "Add new product", response = Product.class)
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product) {
		Product savedItem = productService.saveProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
	}

	@ApiOperation(value = "View a list of available products", response = Iterator.class)
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Product>> getProductList(
			@Valid @RequestParam(value = "serialNumber", required = false) String serialNumber,
			@Valid @RequestParam(value = "brand", required = false) String brand,
			@Valid @RequestParam(value = "model", required = false) String model,
			@Valid @RequestParam(value = "status", required = false) String status,
			@Valid @RequestParam(value = "purchaseDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date purshaseDate) {

		List<Product> productList = productService.getProductList(serialNumber, brand, model, status, purshaseDate);

		return ResponseEntity.status(HttpStatus.OK).body(productList);

	}

	@ApiOperation(value = "Get product by ID", response = Product.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Product> getProduct(@PathVariable Integer id) {
		Product product = productService.getProductbyId(id);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}

	@ApiOperation(value = "Update a product", response = Product.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<String> updateProduct(@PathVariable Integer id,
			@Valid @RequestParam(value = "serialNumber", required = false) String serialNumber,
			@Valid @RequestParam(value = "brand", required = false) String brand,
			@Valid @RequestParam(value = "model", required = false) String model,
			@Valid @RequestParam(value = "status", required = false) String status,
			@Valid @RequestParam(value = "purchaseDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date purchaseDate) {
		productService.updateProduct(id, serialNumber, brand, model, status, purchaseDate);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product updated Successfully!!");
	}

	@ApiOperation(value = "Delete a product")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		productService.deleteProduct(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Product deleted Successfully!!");
	}

}