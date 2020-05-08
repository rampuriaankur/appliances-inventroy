package com.cg.services;

import static com.cg.common.Constants.BE001;
import static com.cg.common.Constants.BE002;
import static com.cg.common.Constants.VADLIDATION_ERROR;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cg.common.ProductException;
import com.cg.model.Product;
import com.cg.repositories.ProductRepo;

@Service
public class ProductService {

	@Autowired
	ProductRepo productRepo;

	public Product saveProduct(Product product) {
		if (productRepo.getProductBySerialNumberWithBrandWithModel(product.getSerialNumber(), product.getBrand(),
				product.getModel()) == null) {
			return productRepo.save(product);
		} else {
			throw new ProductException(VADLIDATION_ERROR, BE001);
		}
	}

	public List<Product> getProductList(String serialNumber, String brand, String model, String status,
			Date purchaseDate) {
		return productRepo.getListOfAllMatchingProduct(serialNumber, brand, model, status, purchaseDate);
	}

	public Product updateProduct(Integer id, @Valid String serialNumber, @Valid String brand, @Valid String model,
			@Valid String status, @Valid Date purchaseDate) {
		Product product = productRepo.findById(id).orElseThrow(() -> new ProductException(VADLIDATION_ERROR, BE002));

		if (StringUtils.hasText(serialNumber))
			product.setSerialNumber(serialNumber.trim());
		if (StringUtils.hasText(brand))
			product.setBrand(brand.trim());
		if (StringUtils.hasText(model))
			product.setModel(model.trim());
		if (StringUtils.hasText(status))
			product.setStatus(status.trim());
		if (purchaseDate != null)
			product.setPurchaseDate(purchaseDate);

		return productRepo.save(product);

	}

	public Product getProductbyId(Integer id) {
		return productRepo.findById(id).orElseThrow(() -> new ProductException(VADLIDATION_ERROR, BE002));
	}

	public void deleteProduct(Integer id) {
		Product product = productRepo.findById(id).orElseThrow(() -> new ProductException(VADLIDATION_ERROR, BE002));
		productRepo.delete(product);
	}

}
