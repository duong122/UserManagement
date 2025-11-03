package com.example.vti_2506_usermanagement.service;

import com.example.vti_2506_usermanagement.dto.UserDTO;
import com.example.vti_2506_usermanagement.dto.UserFilter;
import com.example.vti_2506_usermanagement.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User createUser(UserDTO userDTO);
    User updateUser(Long id, UserDTO userDTO);
    List<User> searchUser(UserFilter userFilter);
    void deleteUser(Long id);
}
