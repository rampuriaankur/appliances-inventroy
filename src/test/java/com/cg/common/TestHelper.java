package com.cg.common;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.cg.model.Product;
import com.cg.model.ProductDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestHelper {

	public static JSONObject productJsonObject() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("brand", "Apple");
		obj.put("model", "AIR");
		obj.put("price", "1200");
		obj.put("purchaseDate", "2020-05-04T04:44:49.983Z");
		obj.put("serialNumber", "111919zkaj1212");
		obj.put("status", "Old");
		return obj;
	}

	public static Product getProdectData() {
		Product expectedProduct = new Product();
		expectedProduct.setBrand("Godrej");
		expectedProduct.setModel("Bed");
		expectedProduct.setPrice(new BigDecimal(3000));
		expectedProduct.setSerialNumber("XUSAABC123");
		expectedProduct.setStatus("new");
		expectedProduct.setId(1);
		return expectedProduct;
	}
	
	public static ProductDTO getProdectDTOData() {
		ProductDTO expectedProduct = new ProductDTO();
		expectedProduct.setBrand("Godrej");
		expectedProduct.setModel("Bed");
		expectedProduct.setPrice(new BigDecimal(3000));
		expectedProduct.setSerialNumber("XUSAABC123");
		expectedProduct.setStatus("new");
		expectedProduct.setId(1);
		return expectedProduct;
	}

	public static List<Product> getProductList() {
		Product productOne = new Product();
		productOne.setBrand("Godrej");
		productOne.setModel("Bed");
		productOne.setPrice(new BigDecimal(3000));
		productOne.setSerialNumber("XUSAABC123");
		productOne.setStatus("new");
		productOne.setId(1);

		Product productTwo = new Product();
		productTwo.setBrand("Godrej");
		productTwo.setModel("Bed");
		productTwo.setPrice(new BigDecimal(3000));
		productTwo.setSerialNumber("XUSAABC124");
		productTwo.setStatus("old");
		productTwo.setId(1);

		List<Product> productList = new ArrayList();
		productList.add(productOne);
		productList.add(productTwo);
		return productList;

	}

	public static String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	public static <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
}
