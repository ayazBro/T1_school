package com.T1school.SupplierService;

import com.T1school.SupplierService.controller.CategoryController;
import com.T1school.SupplierService.controller.ProductController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import io.restassured.RestAssured;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SupplierServiceApplicationTests {
	@BeforeEach
	void setUpUrl(@LocalServerPort Integer port){
		RestAssured.baseURI = "http://localhost:"+port;
	}

	static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres");
	@BeforeAll
	static void beforeAll(){
		postgreSQLContainer.start();
	}

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry){
		registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
		registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
		registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
	}

	@Autowired
	protected ProductController productController;

	@Autowired
	protected CategoryController categoryController;

	@Test
	void contextLoads() {
		assertThat(productController).isNotNull();
		assertThat(categoryController).isNotNull();
	}

}
