package com.T1school.ConsumerService.service;


import com.T1school.ConsumerService.dto.Product;
import com.T1school.ConsumerService.util.SerializablePage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private String url="http://supplier:8080/api/v1/products" ;

    private final RestTemplate restTemplate;

    @Autowired
    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Page<Product> findAll(Optional<Integer> minPrice, Optional<Integer> maxPrice,
                                 Optional<String> category, Optional<Integer> pageNumber, Optional<Integer> pageSize) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParamIfPresent("min_price", minPrice)
                .queryParamIfPresent("max_price", maxPrice)
                .queryParamIfPresent("category", category)
                .queryParamIfPresent("page", pageNumber)
                .queryParamIfPresent("size", pageSize);

        ResponseEntity<SerializablePage<Product>> response= restTemplate
                    .exchange(builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        return response.getBody();
    }

    public Product findById(Long id) {
        ResponseEntity<Product> response = restTemplate
                .getForEntity(url + "/" + id, Product.class);
        return response.getBody();
    }

    public List<Product> findByName(String name) {
        Page<Product> products=findAll(Optional.<Integer>empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty());
        List<Product> list=new ArrayList<>();
        for(Product product:products) {
            if(product.getName().equals(name))
                list.add(product);
        }
        return list;
    }

    public Product save(Product product) {
        ResponseEntity<Product> response=restTemplate.postForEntity(url, product, Product.class);
        return response.getBody();
    }

    public void update(Product product, Long id) {
        product.setId(id);
        restTemplate.put(url + "/" + id, product);
    }

    public void deleteById(Long id) {
        restTemplate.delete(url + "/" + id);
    }
}
