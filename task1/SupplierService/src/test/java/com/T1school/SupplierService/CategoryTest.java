package com.T1school.SupplierService;

import com.T1school.SupplierService.entity.Category;
import com.T1school.SupplierService.repo.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
class CategoryTest extends SupplierServiceApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    private final ObjectWriter writer;

    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        writer = mapper.writer().withDefaultPrettyPrinter();
    }

    @Test
    void createNewCategory() throws Exception {
        Category Category = new Category("test category2");
        String requestJson = writer.writeValueAsString(Category);

        mockMvc.perform(post("/api/v1/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(Category.getName()));
    }

    @Test
    void getCategories() throws Exception {
        mockMvc.perform(get("/api/v1/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void getCategoryById() throws Exception {
        Category category = new Category("test category");
        category = categoryRepository.save(category);

        mockMvc.perform(get("/api/v1/categories/{id}", category.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(category.getName()));
    }

    @Test
    void getCategoryByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/categories/{id}", 10))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void updateCategory() throws Exception {
        Category category = new Category("test category");
        category = categoryRepository.save(category);
        category.setName("new name");
        mockMvc.perform(put("/api/v1/categories/{id}", category.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writer.writeValueAsString(category)))
                .andExpect(status().isAccepted());

        assertEquals(category.getName(),categoryRepository.findById(category.getId()).get().getName());
    }

    @Test
    void deleteCategory() throws Exception {
        Category category = new Category("test category");
        category = categoryRepository.save(category);
        mockMvc.perform(delete("/api/v1/categories/{id}", category.getId()))
                .andExpect(status().isOk());
        assertFalse(categoryRepository.findById(category.getId()).isPresent());
    }
}