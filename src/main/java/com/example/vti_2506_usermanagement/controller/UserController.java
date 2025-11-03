package com.example.vti_2506_usermanagement.controller;

import com.example.vti_2506_usermanagement.dto.UserDTO;
import com.example.vti_2506_usermanagement.dto.UserFilter;
import com.example.vti_2506_usermanagement.entity.User;
import com.example.vti_2506_usermanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("")
    public User createUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id ,@Valid @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @GetMapping("/search")
    public List<User> searchUser(UserFilter userFilter) {
        return userService.searchUser(userFilter);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
