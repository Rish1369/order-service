package com.rish.microservices.order_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class OrderServiceApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	@Test
	void shouldPlaceOrder() throws Exception{
		String requestBody = """
					{
				     "name" : "Lava agni 3",
				     "price": 18000,
				     "quantity":1
				 }
				""";
		mockMvc.perform(post("/api/order")
				.contentType("application/json")
				.content(requestBody))
				.andExpect(status().isCreated())
				.andExpect(content().string("Order Placed Successfully"));
	}

}
