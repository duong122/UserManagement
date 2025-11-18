package com.example.vti_2506_usermanagement.controller;

import com.example.vti_2506_usermanagement.common.BaseResponse;
import com.example.vti_2506_usermanagement.dto.UpdateUserDTO;
import com.example.vti_2506_usermanagement.dto.UserDTO;
import com.example.vti_2506_usermanagement.dto.UserFilter;
import com.example.vti_2506_usermanagement.entity.User;
import com.example.vti_2506_usermanagement.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<BaseResponse<Page<User> >> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(new BaseResponse<>(userService.getAllUsers(pageable), "Get all users successfully", null));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<BaseResponse<User>> createUser(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(new BaseResponse<>(userService.createUser(userDTO), "Create user successfully", null));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<User>> updateUser(
            @PathVariable @Min(value = 1, message = "User id không thể nhỏ hơn 1")  Long id ,
            @Valid @RequestBody UpdateUserDTO userUpdateDTO
    ) {
        return ResponseEntity.ok(new BaseResponse<>(userService.updateUser(id, userUpdateDTO), "Update user successfully", null));
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponse<Page<User>>> searchUser(UserFilter userFilter, Pageable pageable) {
        return ResponseEntity.ok(new BaseResponse<>(userService.searchUser(userFilter, pageable), "Get users successfully", null));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public void deleteUser(
            @PathVariable @Min(value = 1, message = "User id không thể nhỏ hơn 1") Long id
    ) {
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
