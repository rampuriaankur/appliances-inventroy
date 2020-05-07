package com.cg.services;

import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.cg.common.ProductException;
import com.cg.common.TestHelper;
import com.cg.model.Product;
import com.cg.repositories.ProductRepo;

public class ProductServiceTest {

	@InjectMocks
	ProductService mockService;

	@Mock
	ProductRepo mockRepo;

	@Before
	public void setup() {
		initMocks(this);
	}

	@Test(expected = ProductException.class)
	public void ShouldThrowProductExceptionWhenDuplicateProductAdded() throws Exception {

		JSONObject obj = TestHelper.productJsonObject();
		Product expectedProduct = TestHelper.getProdectData();
		String inputJson = obj.toString();
		when(mockRepo.getProductBySerialNumberWithBrandWithModel(any(), any(), any())).thenReturn(expectedProduct);
		mockService.saveProduct(expectedProduct);

	}

	@Test
	public void verifyNewProductCreated() throws Exception {

		JSONObject obj = TestHelper.productJsonObject();

		Product inputProduct = TestHelper.mapFromJson(obj.toString(), Product.class);
		Product expectedProduct = inputProduct;
		expectedProduct.setId(1);

		when(mockRepo.getProductBySerialNumberWithBrandWithModel(any(), any(), any())).thenReturn(null);
		when(mockRepo.save(any())).thenReturn(inputProduct);
		mockService.saveProduct(expectedProduct);
		assertSame(expectedProduct.getModel(), inputProduct.getModel());
		assertSame(expectedProduct.getSerialNumber(), inputProduct.getSerialNumber());
		assertSame(1, expectedProduct.getId());

	}

	@Test
	public void verifyGetProductList() {
		when(mockRepo.getListOfAllMatchingProduct(any(), any(), any(), any(), any()))
				.thenReturn(TestHelper.getProductList());
		List<Product> productList = mockService.getProductList("serialNumber", null, null, null, null);
		assertSame(productList.size(), 2);
	}

	@Test
	public void testUpdateProduct() {
		Product mockProduct = TestHelper.getProdectData();
		Optional<Product> optProduct = Optional.of(mockProduct);
		when(mockRepo.findById(1)).thenReturn(optProduct);
		Product expectedProduct = TestHelper.getProdectData();
		String serialNumber = "abcxyz";
		String brand = "godrej";
		String model = "lock";
		String status = "old";
		Date purchaseDate = new Date();
		expectedProduct.setSerialNumber(serialNumber);
		expectedProduct.setBrand(brand);
		expectedProduct.setModel(model);
		expectedProduct.setStatus(status);
		expectedProduct.setPurchaseDate(purchaseDate);
		mockService.updateProduct(mockProduct.getId(), serialNumber, brand, model, status, purchaseDate);
		when(mockRepo.save(mockProduct)).thenReturn(expectedProduct);
		assertSame(expectedProduct.getBrand(), brand);
		assertSame(expectedProduct.getSerialNumber(), serialNumber);
		assertSame(expectedProduct.getStatus(), status);
		assertSame(expectedProduct.getPurchaseDate(), purchaseDate);
	}

	@Test(expected = ProductException.class)
	public void ShouldThrowProductExceptionWhenUpdateProductIdNotFound() {

		Product mockProduct = TestHelper.getProdectData();
		Optional<Product> optProduct = Optional.ofNullable(null);
		when(mockRepo.findById(any(Integer.class))).thenReturn(Optional.ofNullable(null));

		mockService.updateProduct(1, null, null, null, null, null);

	}

	@Test(expected = ProductException.class)
	public void ShouldThrowProductExceptionWhenGetProductIdNotFound() {
		when(mockRepo.findById(any(Integer.class))).thenReturn(Optional.ofNullable(null));
		mockService.getProductbyId(1);

	}
	@Test(expected = ProductException.class)
	public void ShouldThrowProductExceptionWhenDeleteProductIdNotFound() {
		Product mockProduct = TestHelper.getProdectData();
		Optional<Product> optProduct = Optional.of(mockProduct);
		when(mockRepo.findById(1)).thenReturn(optProduct);
		doNothing().when(mockRepo).delete(mockProduct);
		mockService.deleteProduct(1);

		when(mockRepo.findById(any(Integer.class))).thenReturn(Optional.ofNullable(null));
		mockService.deleteProduct(1);

	}

	
	

	
	@Test
	public void testGetProductById() {
		Product mockProduct = TestHelper.getProdectData();
		Optional<Product> optProduct = Optional.of(mockProduct);
		when(mockRepo.findById(1)).thenReturn(optProduct);
		Product expectedProduct = TestHelper.getProdectData();
		String serialNumber = "abcxyz";
		String brand = "godrej";
		String model = "lock";
		String status = "old";
		Date purchaseDate = new Date();
		expectedProduct.setSerialNumber(serialNumber);
		expectedProduct.setBrand(brand);
		expectedProduct.setModel(model);
		expectedProduct.setStatus(status);
		expectedProduct.setPurchaseDate(purchaseDate);
		mockService.getProductbyId(mockProduct.getId());
		when(mockRepo.save(mockProduct)).thenReturn(expectedProduct);
		assertSame(expectedProduct.getBrand(), brand);
		assertSame(expectedProduct.getSerialNumber(), serialNumber);
		assertSame(expectedProduct.getStatus(), status);
		assertSame(expectedProduct.getPurchaseDate(), purchaseDate);
	}


}
