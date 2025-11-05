package com.example.vti_2506_usermanagement.controller;

import com.example.vti_2506_usermanagement.dto.UserDTO;
import com.example.vti_2506_usermanagement.dto.UserFilter;
import com.example.vti_2506_usermanagement.entity.User;
import com.example.vti_2506_usermanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public Page<User> getAllUsers(Pageable pageable) {
        return userService.getAllUsers(pageable);
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
    public Page<User> searchUser(UserFilter userFilter, Pageable pageable) {
        return userService.searchUser(userFilter, pageable);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/searchByAge")
    public List<User> findbyAgeMoreThan(@RequestParam("age") String age) {
        return userService.findbyAgeMoreThan(age);
    }

    @GetMapping("/searchByAge2")
    public List<User> findByAgeMoreThan2(@RequestParam("age") String age) {
        return userService.finbyAgeMoreThan2(age);
    }

    @GetMapping("/searchByAge3")
    public List<User> findByAgeMoreThan3(@RequestParam("age") String age) {
        return userService.finbyAgeMoreThan3(age);
    }
}
