package com.cg.controllers;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.model.Product;
import com.cg.model.ProductDTO;
import com.cg.services.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/product")
@Api(value = "/product", description = "Operations pertaining to products in Home ")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ModelMapper modelMapper;

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 400, message = "invalid input provided"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")

	})

	@ApiOperation(value = "Add new product", response = ProductDTO.class)
	@PostMapping(produces = "application/json")
	public ResponseEntity<ProductDTO> saveProduct(@Valid @RequestBody ProductDTO productDTO) {
		Product savedItem = productService.saveProduct(convertToEntity(productDTO));
		return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(savedItem));
	}

	@ApiOperation(value = "View a list of available products", response = Iterator.class)
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<ProductDTO>> getProductList(
			@Valid @RequestParam(value = "serialNumber", required = false) String serialNumber,
			@Valid @RequestParam(value = "brand", required = false) String brand,
			@Valid @RequestParam(value = "model", required = false) String model,
			@Valid @RequestParam(value = "status", required = false) String status,
			@Valid @RequestParam(value = "purchaseDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date purshaseDate) {

		List<Product> productList = productService.getProductList(serialNumber, brand, model, status, purshaseDate);

		List<ProductDTO> productDTO = productList.stream().map(this::convertToDto).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(productDTO);

	}

	@ApiOperation(value = "Get product by ID", response = ProductDTO.class)
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer id) {
		Product product = productService.getProductbyId(id);
		return ResponseEntity.status(HttpStatus.OK).body(convertToDto(product));
	}

	@ApiOperation(value = "Update a product", response = String.class)
	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<String> updateProduct(@PathVariable Integer id,
			@Valid @RequestParam(value = "serialNumber", required = false) String serialNumber,
			@Valid @RequestParam(value = "brand", required = false) String brand,
			@Valid @RequestParam(value = "model", required = false) String model,
			@Valid @RequestParam(value = "status", required = false) String status,
			@Valid @RequestParam(value = "purchaseDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date purchaseDate) {
		productService.updateProduct(id, serialNumber, brand, model, status, purchaseDate);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product updated Successfully!!");
	}

	@ApiOperation(value = "Delete a product", response = String.class)
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
		productService.deleteProduct(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Product deleted Successfully!!");
	}

	private ProductDTO convertToDto(Product product) {

		return (product == null) ? null : modelMapper.map(product, ProductDTO.class);
	}

	private Product convertToEntity(ProductDTO productDTO) {
		return (productDTO == null) ? null : modelMapper.map(productDTO, Product.class);
	}

}