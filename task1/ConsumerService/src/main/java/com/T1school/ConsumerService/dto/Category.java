package com.T1school.ConsumerService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Category {
    @NotNull(message = "Id can not be null")
    private Long id;
    @NotBlank(message = "Name can not be empty")
    private String name;
}
