package com.example.mapping.service;

import com.example.mapping.entity.User;
import com.example.mapping.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepo.findById(id);
    }

    public void deleteUser(Integer id) {
        userRepo.deleteById(id);
    }

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


}
