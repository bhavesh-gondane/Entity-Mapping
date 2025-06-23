package com.example.mapping.controller;

import com.example.mapping.dto.UserRequestDto;
import com.example.mapping.entity.User;
import com.example.mapping.exception.DuplicatePhoneException;
import com.example.mapping.exception.DuplicateProjectNameException;
import com.example.mapping.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Validated
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
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRequestDto user) {
//        RequestValidator validator=new RequestValidator();
//        List<String> res=validator.validateUserCred(user);
//        if (res.isEmpty()) {
//            return
//        }
//            return ResponseEntity.ok(userService.saveUserWithProjectsAndProducts(user));

//        return ResponseEntity.badRequest().body(res);

        try {
            return ResponseEntity.ok(userService.saveUserWithProjectsAndProducts(user));
        } catch (DuplicatePhoneException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (DuplicateProjectNameException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/testSave")
    public void testSave(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PostMapping("/project")
    public User createUserWithProjects(@RequestBody User user) {
        return userService.saveUserWithProjects(user);
    }


    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Integer id, @RequestBody User user) {
        userService.updateUser(id, user);
        return "User updated successfully";
    }

    @PatchMapping("/{id}")
    public String updateAge(@PathVariable Integer id, @RequestParam Integer age) {
        userService.updateUserAge(id, age);
        return "User age updated";
    }


    @GetMapping("/projectId/{id}")
    public List<User> getUserByProjectId(@PathVariable Long id) {
        return userService.getUserByProjectId(id);
    }

    @GetMapping("/projectName/{name}")
    public List<User> getUserByProjectName(@PathVariable String name) {
        return userService.getUserByProjectName(name);
    }


    @GetMapping("/page")
    public ResponseEntity<?> getAllUsersPage(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        Page<User> users = userService.getAllUsersPage(pageNo, pageSize);
        return ResponseEntity.ok(users);
    }
}