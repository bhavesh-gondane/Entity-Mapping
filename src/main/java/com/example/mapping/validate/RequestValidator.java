package com.example.mapping.validate;

import com.example.mapping.dto.ProjectRequestDto;
import com.example.mapping.dto.UserRequestDto;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class RequestValidator {
    private static final String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String phoneRegex="^[6-9]\\d{9}$";

    public List<String> validateUserCred(UserRequestDto user) {
        List<String> errorMsgs=new ArrayList<>();
        if(!user.getEmail().matches(emailRegex)) /*&& user.getAge()>=age && user.getPhoneNo().matches(phoneRegex)*/
            errorMsgs.add("Invalid email format");
        if(user.getAge()<18)
            errorMsgs.add("Age must be above 18");
        if(!user.getPhoneNo().matches(phoneRegex))
            errorMsgs.add("Invalid phone number");

        errorMsgs = validateProjects(user.getProjects(), errorMsgs);
        return errorMsgs;
    }

    private List<String> validateProjects(List<ProjectRequestDto> projects, List<String> errorMsgs) {
        projects.forEach(project -> {
            if(StringUtils.isEmpty(project.getProjectName())){
                errorMsgs.add("Project Name is empty");
            }
        });
        return errorMsgs;
    }
}
