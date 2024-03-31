package com.T1school.SupplierService;

import com.T1school.SupplierService.entity.Product;
import com.T1school.SupplierService.repo.ProductRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.web.server.LocalServerPort;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@AutoConfigureMockMvc
class ProductTest extends SupplierServiceApplicationTests {
    @Autowired
    private ProductRepository productRepository;
    @BeforeEach
    void setUpUrl(@LocalServerPort Integer port) {
        RestAssured.baseURI = "http://localhost:" + port;
        productRepository.deleteAll();
    }

    @Test
    void createNewProduct() throws Exception {
        Product product = new Product("test product", "test product", 10, null);

        Product created = given()
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .post("/api/v1/products")
                .then()
                .statusCode(201)
                .body("name", equalTo(product.getName()))
                .body("description", equalTo(product.getDescription()))
                .body("category", nullValue())
                .body("id", notNullValue())
                .extract().as(Product.class);

        assertEquals(created.getPrice(), product.getPrice());
    }

    @Test
    void getProducts() throws Exception {
        productRepository.saveAll(List.of(
                new Product("test1", "test 1", 13, null),
                new Product("test2", "test 2", 12, null),
                new Product("test3", "test 3", 13, null),
                new Product("test4", "test 4", 14, null)
        ));

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/products")
                .then()
                .statusCode(200)
                .body("content", hasSize(4));
    }

    @Test
    void getProductById() throws Exception {
        Product product = new Product("test product", "test product", 10, null);
        product = productRepository.save(product);

        final Product searchedProduct = given()
                .when()
                .get("/api/v1/products/{id}", product.getId())
                .then()
                .statusCode(200)
                .body("name", equalTo(product.getName()))
                .body("description", equalTo(product.getDescription()))
                .extract().as(Product.class);

        Assertions.assertEquals(product.getPrice(), searchedProduct.getPrice());
        Assertions.assertEquals(product.getId(), searchedProduct.getId());
    }

    @Test
    void getProductByIdNotFound() throws Exception {
        given()
                .when()
                .get("/api/v1/products/{id}", 9999)
                .then()
                .statusCode(200)
                .body(emptyOrNullString());
    }

    @Test
    void updateProduct() throws Exception {
        Product product = new Product("test product", "test product", 10, null);
        product = productRepository.save(product);

        product.setName("new name");
        product.setPrice(15);

        given()
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .put("/api/v1/products/{id}", product.getId())
                .then()
                .statusCode(202);
        Long id=product.getId();
        Optional<Product> newProduct=productRepository.findById(id);

        assertEquals(product.getPrice(), newProduct.get().getPrice());
    }

    @Test
    void deleteProduct() throws Exception {
        Product product = new Product("test product", "test product", 10, null);
        product = productRepository.save(product);

        given()
                .when()
                .delete("/api/v1/products/{id}", product.getId())
                .then()
                .statusCode(204);

        assertFalse(productRepository.findById(product.getId()).isPresent());
    }

}