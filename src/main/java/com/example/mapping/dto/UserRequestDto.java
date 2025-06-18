package com.example.mapping.dto;

import com.example.mapping.entity.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto{
    private String name;
    @Min(value = 18,message = "Age must be 18")
    private Integer age;
    @Email(message = "Invalid email format")
    private String email;
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number")
    private String phoneNo;
    private List<ProjectRequestDto> projects;
    private List<ProductRequestDto> products;
}
