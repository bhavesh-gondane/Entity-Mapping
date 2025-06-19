package com.example.mapping.service;

import com.example.mapping.dto.ProductRequestDto;
import com.example.mapping.dto.ProjectRequestDto;
import com.example.mapping.dto.UserRequestDto;
import com.example.mapping.entity.Product;
import com.example.mapping.entity.Project;
import com.example.mapping.entity.User;
import com.example.mapping.exception.DuplicatePhoneException;
import com.example.mapping.exception.DuplicateProjectNameException;
import com.example.mapping.repository.ProjectRepository;
import com.example.mapping.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService {
    @Autowired
    private final UserRepository userRepo;
    private  final  ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

//    public UserService(UserRepository userRepository) {
//        this.userRepo = userRepository;
//    }

    @Transactional
    public User saveUser(User user) {

        for (Product product : user.getProducts()) {
            product.setUser(user);
        }
        User savedUser = userRepo.save(user);

//        if (true) {
//            throw new RuntimeException("Testing transactional annotation");
//        }
        return savedUser;
    }

    @Transactional
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Transactional
    public Optional<User> getUserById(Integer id) {
        return userRepo.findById(id);
    }

    @Transactional
    public void deleteUser(Integer id) {
        userRepo.deleteById(id);
    }

    @Transactional
    public User updateUser(Integer id, User userDetails) {
        Optional<User> user = userRepo.findById(id);

        if(user.isPresent()){
            User newUser=user.get();
            newUser.setName(userDetails.getName());
            newUser.setAge(userDetails.getAge());
            return userRepo.save(newUser);
        }
//        return new EntityNotFoundException("User not found");
        else
            throw new EntityNotFoundException("No user found");
    }

    @Transactional
    public User updateUserAge(Integer id,Integer age){
        Optional<User> user = userRepo.findById(id);

        if(user.isPresent()){
            User updatedUser=user.get();
            updatedUser.setAge(age);
            return  userRepo.save(updatedUser);
        }
        else
            throw new EntityNotFoundException("No user found");
    }

    @Transactional
    public User saveUserWithProjects(User user){
        for (Project project : user.getProjects()) {
            project.getUsers().add(user);
        }
        return userRepo.save(user);
    }

    @Transactional
    public List<User> getUserByProjectId(Long id){
        return userRepo.getUserByProjectId(id);
    }

    @Transactional
    public List<User> getUserByProjectName(String name){
//        List<User> users = userRepo.findByProjects_ProjectName(name);
//        for(User user : users){
//            List<Project> projects= user.getProjects();
//            for(Project project : projects){
//                if(project.getProjectName().equals(name))
//                    return  project;
//            }
//        }
//        users.stream().flatMap(user -> user.getProjects().stream()).filter(project -> project.getProjectName().equals(name)).toList();

        return userRepo.findByProjects_ProjectName(name);
    }

    @Transactional
    public User saveUserWithProjectsAndProducts(UserRequestDto user){
        Optional<User> user1 = userRepo.findByPhoneNo(user.getPhoneNo());
        if(user1.isPresent()){
            throw new DuplicatePhoneException();
        }

        for (ProjectRequestDto projectDto : user.getProjects()) {
            Optional<Project> existingProject = projectRepository.findByProjectDepartment(projectDto.getProjectDepartment());
            if (existingProject.isPresent()) {
                throw new DuplicateProjectNameException();
            }
        }

        User user2=modelMapper.map(user,User.class);
        for(Product product : user2.getProducts()){
            product.setUser(user2);
        }
        for(Project project : user2.getProjects()){
            project.getUsers().add(user2);
        }
        return userRepo.save(user2);
    }


    @Transactional
    public Page<User> getAllUsersPage(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return userRepo.findAll(pageable);
    }
}