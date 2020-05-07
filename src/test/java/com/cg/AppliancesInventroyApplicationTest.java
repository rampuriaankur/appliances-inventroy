package com.cg;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.common.TestHelper;
import com.cg.controllers.ProductController;
import com.cg.model.Product;
import com.cg.repositories.ProductRepo;
import com.cg.services.ProductService;

@WebMvcTest(ProductController.class)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class AppliancesInventroyApplicationTest {

	@MockBean
	ProductService mockService;

	@MockBean
	ProductRepo mockRepo;

	@Autowired
	MockMvc mockMvc;

	final String uri = "/product";

	@Test
	public void verifyaddVaildProduct() throws Exception {
		String uri = "/product";
		JSONObject obj = TestHelper.productJsonObject();
		Product expectedProduct = TestHelper.getProdectData();
		String inputJson = obj.toString();
		when(mockService.saveProduct(any())).thenReturn(expectedProduct);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}


	@Test
	public void getAllProduct() throws Exception {
		String uri = "/product";
		Product expectedProduct = TestHelper.getProdectData();
		when(mockService.saveProduct(any())).thenReturn(expectedProduct);

		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get(uri))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	
	@Test
	public void getProductById() throws Exception {
		String uri = "/product/1";
		Product expectedProduct = TestHelper.getProdectData();
		when(mockService.saveProduct(any())).thenReturn(expectedProduct);

		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get(uri))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}

	@Test
	public void updateProduct() throws Exception {
		String uri = "/product/1";
		doNothing().when(mockService).deleteProduct(any());
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.put(uri))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(204, status);

	}


	@Test
	public void deleteProduct() throws Exception {
		String uri = "/product/1";
		doNothing().when(mockService).deleteProduct(any());
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.delete(uri))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(202, status);

	}

	

}
