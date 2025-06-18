package com.example.mapping.dto;

import com.example.mapping.domain.enums.Status;
import com.example.mapping.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequestDto {
    private String projectName;
    private String projectDepartment;
    private boolean status;
    private Status projectStatus;
    private List<User> users = new ArrayList<>();

}
