package com.example.vti_2506_usermanagement.service;

import com.example.vti_2506_usermanagement.dto.UpdateUserDTO;
import com.example.vti_2506_usermanagement.dto.UserDTO;
import com.example.vti_2506_usermanagement.dto.UserFilter;
import com.example.vti_2506_usermanagement.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    Page<User> getAllUsers(Pageable pageable);
    User createUser(UserDTO userDTO);
    User updateUser(Long id, UpdateUserDTO userDTO);
    Page<User> searchUser(UserFilter userFilter, Pageable pageable);
    void deleteUser(Long id);
    List<User> findbyAgeMoreThan(String age);
    List<User> finbyAgeMoreThan2(String age);
    List<User> finbyAgeMoreThan3(String age);

}
