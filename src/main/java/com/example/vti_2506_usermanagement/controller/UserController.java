package com.example.vti_2506_usermanagement.controller;

import com.example.vti_2506_usermanagement.common.BaseResponse;
import com.example.vti_2506_usermanagement.dto.UserDTO;
import com.example.vti_2506_usermanagement.dto.UserFilter;
import com.example.vti_2506_usermanagement.entity.User;
import com.example.vti_2506_usermanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<BaseResponse<Page<User> >> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(new BaseResponse<>(userService.getAllUsers(pageable), "Get all users successfully"));
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse<User>> createUser(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(new BaseResponse<>(userService.createUser(userDTO), "Create user successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<User>> updateUser(@PathVariable Long id ,@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(new BaseResponse<>(userService.updateUser(id, userDTO), "Update user successfully"));
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponse<Page<User>>> searchUser(UserFilter userFilter, Pageable pageable) {
        return ResponseEntity.ok(new BaseResponse<>(userService.searchUser(userFilter, pageable), "Get users successfully"));
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
