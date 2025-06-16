package com.example.mapping.service;

import com.example.mapping.entity.Product;
import com.example.mapping.entity.Project;
import com.example.mapping.entity.User;
import com.example.mapping.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService {
    @Autowired
    private final UserRepository userRepo;

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
}