package com.example.mapping.dto;

import com.example.mapping.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    private String product_name;
    private String brand;
    private LocalDateTime mfgDate;
    private UserRequestDto user;
}
