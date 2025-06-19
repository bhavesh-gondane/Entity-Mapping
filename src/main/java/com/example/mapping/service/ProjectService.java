package com.example.mapping.service;

import com.example.mapping.entity.Project;
import com.example.mapping.entity.User;
import com.example.mapping.repository.ProjectRepository;
import com.example.mapping.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private  final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Transactional
    public Project saveProject(Project project){

        List<User> attachedUsers = new ArrayList<>();

        for (User user : project.getUsers()) {
            User existingUser = userRepository.findById(user.getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            attachedUsers.add(existingUser);
        }
        project.setUsers(attachedUsers);

        for (User user : project.getUsers()) {
            user.getProjects().add(project);
        }
        return projectRepository.save(project);
    }

    @Transactional
    public Project updateProject(Long id,Project projectDetails){
        Optional<Project> project=projectRepository.findById(id);

        if(project.isPresent()){
            Project newProject=project.get();
            newProject.setProjectName(projectDetails.getProjectName());
            newProject.setProjectDepartment(projectDetails.getProjectDepartment());
            return projectRepository.save(newProject);
        }
        else
            throw new EntityNotFoundException("No project found");
    }


    @Transactional
    public Page<Project> getAllProjectPage(int pageNo,int pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return projectRepository.findAll(pageable);
    }
}