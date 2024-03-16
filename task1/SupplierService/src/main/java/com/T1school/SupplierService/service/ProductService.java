package com.T1school.SupplierService.service;

import com.T1school.SupplierService.entity.Category;
import com.T1school.SupplierService.entity.Product;
import com.T1school.SupplierService.repo.CategoryRepository;
import com.T1school.SupplierService.repo.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Page<Product> findAll(Integer minPrice, Integer maxPrice, String category,
                                 Integer pageNumber, Integer pageSize) {

        Pageable paging;
        if(pageNumber == null || pageSize == null)
            paging=Pageable.unpaged();
        else
            paging=PageRequest.of(pageNumber, pageSize);
        Specification<Product> spec = Specification.where(null);

        if (minPrice != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
        }

        if (maxPrice != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
        }

        if (category != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(
                            criteriaBuilder.lower(root.get("category").get("name")),
                            category.toLowerCase())
            );
        }

        return productRepository.findAll(spec, paging);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Transactional
    public Product save(Product product) {
        Category category = product.getCategory();
        if (category != null && categoryRepository.findById(category.getId()).isEmpty())
            return null;
        return productRepository.save(product);
    }

    @Transactional
    public void update(Product product, Long id) {
        product.setId(id);
        productRepository.save(product);
    }

    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}