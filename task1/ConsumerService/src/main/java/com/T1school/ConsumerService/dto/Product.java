package com.T1school.ConsumerService.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Product {
    private Long id;

    @NotNull(message = "Name can not be null")
    @Size(max = 50, message = "Name can not be more than 50 characters")
    private String name;

    private String description;

    @NotNull (message = "Price can not be null")
    @Positive(message = "Price > 0")
    private int price;

    @Valid
    private Category category;
}