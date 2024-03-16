package com.T1school.SupplierService;

import com.T1school.SupplierService.entity.Category;
import com.T1school.SupplierService.entity.Product;
import com.T1school.SupplierService.repo.CategoryRepository;
import com.T1school.SupplierService.repo.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class SupplierServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupplierServiceApplication.class, args);
	}

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

	@PostConstruct
	private void addTestData() {

		Category cars = new Category("cars");
		Category phones = new Category("phones");
		Category foods = new Category("foods");
		categoryRepository.saveAll(List.of(
				cars,
				phones,
				foods
		));
		productRepository.saveAll(List.of(
				new Product("BMW", "The best Germany Cars", 800,cars),
				new Product("Mercedes", "The most beautiful Germany cars", 100, cars),
				new Product("Porsche", "Fast and Luxury car", 500, cars),
				new Product("Lada", "Russian self-made car", 80, cars),
				new Product("Xiaomi", "Top for this price", 10, phones),
				new Product("Poco", "Daughter Xiaomi company", 9, phones),
				new Product("Apple", "Company with great history", 15, phones),
				new Product("Samsung", "Koreans phones", 12, phones),
				new Product("Fish", "Sea animal", 2, foods),
				new Product("Meat", "Tasty and satisfying", 2, foods),
				new Product("Chocolate", "With sugar(", 1, foods),
				new Product("Milk", "2L", 1,foods)
		));
	}
}
