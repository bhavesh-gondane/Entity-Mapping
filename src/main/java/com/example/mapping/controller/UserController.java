package com.example.mapping.controller;

import com.example.mapping.entity.Product;
import com.example.mapping.entity.User;
import com.example.mapping.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
//    @Autowired
    private final UserService userService;

//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

//    @PostMapping
//    public User createUser(@RequestBody User user) {
//        return userService.saveUser(user);
//    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/testSave")
    public void testSave(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PostMapping("/project")
    public User createUserWithProjects(@RequestBody User user){
        return userService.saveUserWithProjects(user);
    }


    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return "User deleted successfully";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Integer id, @RequestBody User user){
        userService.updateUser(id,user);
        return "User updated successfully";
    }

    @PatchMapping("/{id}")
    public String updateAge(@PathVariable Integer id, @RequestParam Integer age){
        userService.updateUserAge(id,age);
        return "User age updated";
    }



    @GetMapping("/projectId/{id}")
    public List<User> getUserByProjectId(@PathVariable Long id){
        return userService.getUserByProjectId(id);
    }

    @GetMapping("/projectName/{name}")
    public List<User> getUserByProjectName(@PathVariable String name){
        return userService.getUserByProjectName(name);
    }
}