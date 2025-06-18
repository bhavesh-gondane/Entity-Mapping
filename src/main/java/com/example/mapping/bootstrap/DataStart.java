package com.example.mapping.bootstrap;

import com.example.mapping.domain.enums.Status;
import com.example.mapping.dto.ProductRequestDto;
import com.example.mapping.dto.ProjectRequestDto;
import com.example.mapping.dto.UserRequestDto;
import com.example.mapping.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class DataStart {

    private final UserService userService;

    @PostConstruct
    public void dataSaver(){
        ProjectRequestDto project1=new ProjectRequestDto();
        project1.setProjectName("FleetManagement");
        project1.setProjectDepartment("ITM");
        project1.setProjectStatus(Status.Completed);
        project1.setStatus(true);

        ProjectRequestDto project2=new ProjectRequestDto();
        project2.setProjectName("NLP");
        project2.setProjectDepartment("DLM");
        project2.setStatus(false);
        project2.setProjectStatus(Status.Pending);

        ProductRequestDto product1 = new ProductRequestDto();
        product1.setProduct_name("SmartWatch");
        product1.setBrand("Boat");
        product1.setMfgDate(LocalDateTime.of(2024, 10, 20, 10, 30));


        UserRequestDto userDto = new UserRequestDto();
        userDto.setName("Anurag");
        userDto.setAge(25);
        userDto.setEmail("anurag@example.com");
        userDto.setPhoneNo("9876543211");
        userDto.setProjects(List.of(project1,project2));
        userDto.setProducts(List.of(product1));

        userService.saveUserWithProjectsAndProducts(userDto);
    }
}